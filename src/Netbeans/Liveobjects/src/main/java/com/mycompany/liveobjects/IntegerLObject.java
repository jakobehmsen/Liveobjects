package com.mycompany.liveobjects;

public class IntegerLObject implements ScalarLObject {
    private int value;
    
    public IntegerLObject(int value) {
        this.value = value;
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
        
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public LObject getProto(Environment environment) {
        return environment.getWorld().getIntegerPrototype();
    }
}
