package com.mycompany.liveobjects;

import com.mycompany.liveobjects.runtime.ConnectionProvider;
import com.mycompany.liveobjects.runtime.DefaultDispatcher;
import com.mycompany.liveobjects.runtime.DefaultEnvironment;
import com.mycompany.liveobjects.runtime.DefaultObjectMapper;
import com.mycompany.liveobjects.runtime.DefaultSymbolTable;
import com.mycompany.liveobjects.runtime.Dispatcher;
import com.mycompany.liveobjects.runtime.Environment;
import com.mycompany.liveobjects.runtime.Instruction;
import com.mycompany.liveobjects.runtime.InstructionSet;
import com.mycompany.liveobjects.runtime.Instructions;
import com.mycompany.liveobjects.runtime.JDBCObjectStore;
import com.mycompany.liveobjects.runtime.LazyObjectLoader;
import com.mycompany.liveobjects.runtime.ObjectLoaderWorld;
import com.mycompany.liveobjects.runtime.ObjectMapper;
import com.mycompany.liveobjects.runtime.SymbolTable;
import com.mycompany.liveobjects.runtime.World;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ScriptEvaluator {
    private ConnectionProvider connectionProvider;
    private InstructionSet instructionSet;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    
    private LazyObjectLoader objectLoader;
    private ObjectMapper objectMapper;
    private Dispatcher dispatcher;
    private World world;
    private SymbolTable symbolTable;

    public ScriptEvaluator(ConnectionProvider connectionProvider, InstructionSet instructionSet) {
        this.connectionProvider = connectionProvider;
        this.instructionSet = instructionSet;
        
        objectLoader = new LazyObjectLoader(ol -> new JDBCObjectStore(connectionProvider, instructionSet, ol));
        objectMapper = new DefaultObjectMapper();
        dispatcher = new DefaultDispatcher(objectLoader);
        world = new ObjectLoaderWorld(objectLoader);
        
        symbolTable = new DefaultSymbolTable();
    }
    
    public Future<Environment> evaluate(int localCount, Instruction[] instructions) {
        return executor.submit(() -> {
            // TODO: Trye to keep cached objects across evaluations to test sharing properly
            /*LazyObjectLoader objectLoader = new LazyObjectLoader(ol -> new JDBCObjectStore(connection, instructionSet, ol));
            ObjectMapper objectMapper = new DefaultObjectMapper();
            Dispatcher dispatcher = new DefaultDispatcher(objectLoader);
            World world = new ObjectLoaderWorld(objectLoader);*/
            
            // TODO: Consider:
            // - This is an external frame
            // - Should instructions be empty?
            DefaultEnvironment environment = new DefaultEnvironment(objectLoader, objectMapper, world, dispatcher, instructionSet, Instructions.ROOT_INSTRUCTIONS, symbolTable);
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
            
            //connection.commit();

            return environment;
        });
    }

    public void close() {
        executor.shutdown();
    }
}
