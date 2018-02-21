package com.mycompany.liveobjects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScriptEvaluator {
    private Connection connection;
    private InstructionSet instructionSet;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public ScriptEvaluator(Connection connection, InstructionSet instructionSet) {
        this.connection = connection;
        this.instructionSet = instructionSet;
    }
    
    public Future<Environment> evaluate(int localCount, Instruction[] instructions) {
        return executor.submit(() -> {
            LazyObjectLoader objectLoader = new LazyObjectLoader(ol -> new JDBCObjectStore(connection, instructionSet, ol));
            ObjectMapper objectMapper = new DefaultObjectMapper();
            Dispatcher dispatcher = new DefaultDispatcher(objectLoader);
            World world = new ObjectLoaderWorld(objectLoader);

            // TODO: Consider:
            // - This is an external frame
            // - Should instructions be empty?
            DefaultEnvironment environment = new DefaultEnvironment(objectLoader, objectMapper, world, dispatcher, instructionSet, Instructions.ROOT_INSTRUCTIONS);
            environment.pushFrame(instructions);
            environment.currentFrame().load(world.getRoot());
            environment.currentFrame().allocate(environment, localCount);

            while(!environment.finished()) {
                try {
                    while(!environment.finished()) {
                        environment.executeNext();
                    }
                } catch(Exception ex) {
                    environment.getDispatcher().handlePrimitiveError(environment, ex);
                }
            }

            try {
                connection.commit();
            } catch (SQLException ex) {
                Logger.getLogger(ScriptEvaluator.class.getName()).log(Level.SEVERE, null, ex);
            }

            return environment;
        });
    }

    public void close() {
        executor.shutdown();
    }
}
