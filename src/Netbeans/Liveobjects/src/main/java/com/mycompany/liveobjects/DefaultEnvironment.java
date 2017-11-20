package com.mycompany.liveobjects;

import java.util.Hashtable;

public class DefaultEnvironment implements Environment {
    private World world;
    private boolean finished;
    private DefaultFrame currentFrame;
    private Dispatcher dispatcher;
    
    public DefaultEnvironment(World world, Dispatcher dispatcher, Instruction[] instructions) {
        this.world = world;
        this.dispatcher = dispatcher;
        pushFrame(instructions);
        
        initSymbolTable();
    }

    @Override
    public Frame currentFrame() {
        return currentFrame;
    }

    @Override
    public final void pushFrame(Instruction[] instructions) {
        currentFrame = new DefaultFrame(currentFrame, instructions);
        //frames.push(new DefaultFrame(instructions, arguments));
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
    public void finish() {
        finished = true;
    }
    
    private Hashtable<String, Integer> stringToSymbolCodeMap;

    @Override
    public int getSymbolCode(String str) {
        return stringToSymbolCodeMap.computeIfAbsent(str, k -> stringToSymbolCodeMap.size());
    }

    private void initSymbolTable() {
        stringToSymbolCodeMap = new Hashtable<>();
        
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
    }

    @Override
    public World getWorld() {
        return world;
    }
}
