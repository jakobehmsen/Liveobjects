package com.mycompany.liveobjects;

public class Closure implements PrimitiveLObject, Behavior {
    private Frame frame;
    private Block block;

    public Closure(Frame frame, Block block) {
        this.frame = frame;
        this.block = block;
    }
    
    public void evaluateAs(LObject receiver, LObject[] arguments, Environment environment) {
        block.evaluateAsClosure(receiver, arguments, environment, frame);
    }
    
    public void evaluate(LObject[] arguments, Environment environment) {
        block.evaluateAsClosure(frame.getDistant(environment, 0, 0), arguments, environment, frame);
    }

    @Override
    public void invoke(LObject receiver, LObject[] arguments, Environment environment) {
        evaluateAs(receiver, arguments, environment);
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSlot(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSlot(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LObject cloneObject(Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nowUnusedFrom(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LObject getProto(Environment environment) {
        return environment.getWorld().getClosurePrototype();
    }
}
