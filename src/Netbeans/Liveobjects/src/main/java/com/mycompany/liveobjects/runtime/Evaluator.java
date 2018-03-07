package com.mycompany.liveobjects.runtime;

public class Evaluator {
    private ConnectionProvider connectionProvider;
    private InstructionSet instructionSet;
    
    private LazyObjectLoader objectLoader;
    private ObjectMapper objectMapper;
    private Dispatcher dispatcher;
    private World world;
    private SymbolTable symbolTable;

    public Evaluator(ConnectionProvider connectionProvider, InstructionSet instructionSet) {
        this.connectionProvider = connectionProvider;
        this.instructionSet = instructionSet;
        
        JDBCObjectStore objectStore = new JDBCObjectStore(connectionProvider, instructionSet);
        objectLoader = new LazyObjectLoader(objectStore);
        objectMapper = new DefaultObjectMapper();
        
        DispatchGroup mainGroup = new JavaDispatchGroup()
            .withFallback(new PrimitiveDispatchGroup(objectLoader, objectStore));
        
        dispatcher = new DefaultDispatcher(objectLoader, mainGroup);
        world = new ObjectLoaderWorld(objectLoader);
        
        symbolTable = new DefaultSymbolTable();
    }
    
    public Environment evaluate(int localCount, Instruction[] instructions) {
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

        return environment;
    }
}
