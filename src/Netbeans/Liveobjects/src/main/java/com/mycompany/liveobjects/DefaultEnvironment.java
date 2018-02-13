package com.mycompany.liveobjects;

import java.util.Hashtable;

public class DefaultEnvironment implements Environment {
    private ObjectLoader objectLoader;
    private ObjectMapper objectMapper;
    private World world;
    private boolean finished;
    private Frame currentFrame;
    private Dispatcher dispatcher;
    private InstructionSet instructionSet;
    
    public DefaultEnvironment(ObjectLoader objectLoader, ObjectMapper objectMapper, World world, Dispatcher dispatcher, InstructionSet instructionSet, Instruction[] instructions) {
        this.objectLoader = objectLoader;
        this.objectMapper = objectMapper;
        this.world = world;
        this.dispatcher = dispatcher;
        this.instructionSet = instructionSet;
        pushFrame(instructions);
        
        initSymbolTable();
    }

    @Override
    public Frame currentFrame() {
        return currentFrame;
    }

    @Override
    public final void pushFrame(Instruction[] instructions) {
        //currentFrame = new DefaultFrame(currentFrame, instructions);
        currentFrame = objectLoader.newFrame(currentFrame, instructions);
    }

    @Override
    public void pushFrame(Instruction[] instructions, LObject sender) {
        //currentFrame = new DefaultFrame(sender, instructions);
        currentFrame = objectLoader.newFrame(sender, instructions);
    }

    @Override
    public void pushFrameClosure(Instruction[] instructions, Frame lexicalContext) {
        //currentFrame = new DefaultFrame(currentFrame, instructions, lexicalContext);
        currentFrame = objectLoader.newFrame(currentFrame, instructions, lexicalContext);
    }

    @Override
    public void popFrame() {
        currentFrame = (DefaultFrame) currentFrame.sender();
    }
    
    public boolean finished() {
        return finished;
    }
    
    public void executeNext() {
        currentFrame.executeNext(this);
    }

    @Override
    public void halt() {
        finished = true;
    }
    
    private Hashtable<String, Integer> stringToSymbolCodeMap;
    private Hashtable<Integer, String> symbolCodeToStringMap;

    @Override
    public int getSymbolCode(String str) {
        Integer symbolCode = stringToSymbolCodeMap.get(str);
        
        if(symbolCode == null) {
            symbolCode = stringToSymbolCodeMap.size();
            stringToSymbolCodeMap.put(str, symbolCode);
            symbolCodeToStringMap.put(symbolCode, str);
        }
        
        return symbolCode;
    }

    private void initSymbolTable() {
        stringToSymbolCodeMap = new Hashtable<>();
        symbolCodeToStringMap = new Hashtable<>();
        
        this.dispatcher.registerSymbols(this);
        
        /*
        // Use reflection to locate all primitive selectors
        stringToSymbolCodeMap.put(PrimitiveSelectors.GET_SLOT_SELECTOR, PrimitiveSelectors.GET_SLOT);
        stringToSymbolCodeMap.put(PrimitiveSelectors.SET_SLOT_SELECTOR, PrimitiveSelectors.SET_SLOT);
        */
    }

    @Override
    public void currentFrame(Frame frame) {
        this.currentFrame = (DefaultFrame)frame;
    }

    @Override
    public void send(LObject receiver, int selector, LObject[] arguments) {
        dispatcher.send(receiver, arguments, this, selector);
    }

    @Override
    public void addSymbol(int symbolCode, String string) {
        stringToSymbolCodeMap.put(string, symbolCode);
        symbolCodeToStringMap.put(symbolCode, string);
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public String getSymbolString(int symbolCode) {
        return symbolCodeToStringMap.get(symbolCode);
    }

    @Override
    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    @Override
    public InstructionSet getInstructionSet() {
        return instructionSet;
    }

    @Override
    public ObjectLoader getObjectLoader() {
        return objectLoader;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
