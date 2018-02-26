package com.mycompany.liveobjects.runtime;

import java.util.Hashtable;

public class DefaultEnvironment implements Environment {
    private ObjectLoader objectLoader;
    private ObjectMapper objectMapper;
    private World world;
    private boolean finished;
    private Frame currentFrame;
    private Dispatcher dispatcher;
    private InstructionSet instructionSet;
    private SymbolTable symbolTable;
    
    public DefaultEnvironment(ObjectLoader objectLoader, ObjectMapper objectMapper, World world, Dispatcher dispatcher, InstructionSet instructionSet, Instruction[] instructions, SymbolTable symbolTable) {
        this.objectLoader = objectLoader;
        this.objectMapper = objectMapper;
        this.world = world;
        this.dispatcher = dispatcher;
        this.instructionSet = instructionSet;
        this.symbolTable = symbolTable;
        pushFrame(instructions);
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

    @Override
    public int getSymbolCode(String str) {
        return symbolTable.getSymbolCode(str);
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
    public World getWorld() {
        return world;
    }

    @Override
    public String getSymbolString(int symbolCode) {
        return symbolTable.getSymbolString(symbolCode);
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
