package com.mycompany.liveobjects;

public class IntegerLObject implements LObject {
    private int value;
    
    public IntegerLObject(int value) {
        this.value = value;
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        Block behavior = (Block)resolve(selector, environment);
        behavior.evaluate(this, arguments, environment);
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.deleteSlotIntegerValue();
    }

    @Override
    public void addSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.addIntegerSlot(value);
    }

    @Override
    public void updateSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.updateIntegerSlot(value);
    }

    @Override
    public LObject cloneObject(Environment environment) {
        return this;
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        
    }

    @Override
    public void nowUnusedFrom(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        return environment.getWorld().getIntegerPrototype().resolve(selector, environment);
    }

    @Override
    public boolean isParent(Environment environment, AssociativeArrayLObject obj) {
        return environment.getWorld().getIntegerPrototype().isParent(environment, obj);
    }
}
