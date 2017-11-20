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
    public void setSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.setSlotIntegerValue(value);
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
    public LObject cloneObject() {
        return this;
    }

    @Override
    public void nowUsedFrom(int id) {
        
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
}