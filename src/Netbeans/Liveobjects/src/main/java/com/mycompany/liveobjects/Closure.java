package com.mycompany.liveobjects;

import java.util.Map;

public class Closure extends IdentityLObject implements PrimitiveLObject, Behavior {
    private Frame frame;
    private Block block;

    public Closure(int id, ObjectStore objectStore, Frame frame, Block block) {
        super(id, objectStore);
        this.frame = frame;
        this.block = block;
    }

    public Closure(int id, ObjectStore objectStore) {
        super(id, objectStore);
    }
    
    public void evaluateAs(LObject receiver, LObject[] arguments, Environment environment) {
        ensureLoaded(environment);
        block.evaluateAsClosure(receiver, arguments, environment, frame);
    }
    
    public void evaluate(LObject[] arguments, Environment environment) {
        ensureLoaded(environment);
        block.evaluateAsClosure(frame.getDistant(environment, 0, 0), arguments, environment, frame);
    }

    @Override
    public void invoke(LObject receiver, LObject[] arguments, Environment environment) {
        ensureLoaded(environment);
        evaluateAs(receiver, arguments, environment);
    }

    @Override
    public LObject cloneObject(Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LObject getProto(Environment environment) {
        return environment.getWorld().getClosurePrototype();
    }

    public LObject getFrame() {
        return frame;
    }

    @Override
    protected String getSelector(Environment environment, int referenceType, int symbolCode) {
        return environment.getSymbolString(symbolCode);
    }

    @Override
    protected LObject getValue(Environment environment, int referenceType, int symbolCode, String selector) {
        switch (selector) {
            case "frame":
                return frame;
            case "block":
                return block;
        }
        
        return null;
    }

    @Override
    protected int getObjectType() {
        return ObjectStore.OBJECT_TYPE_CLOSURE;
    }

    @Override
    protected void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int writeContext) {
        slots.put(environment.getSymbolCode("frame"), frame);
        slots.put(environment.getSymbolCode("block"), block);
    }

    @Override
    protected void readSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        frame = (Frame) slots.get(environment.getSymbolCode("frame"));
        block = (Block) slots.get(environment.getSymbolCode("block"));
    }
    
    private void ensureLoaded(Environment environment) {
        if(frame == null) {
            readSlots(environment);
        }
    }
}
