package com.mycompany.liveobjects;

public class StringLObject implements LObject {
    private String value;

    public StringLObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        Block behavior = (Block)resolve(selector, environment);
        behavior.evaluate(this, arguments, environment);
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.deleteStringSlot(value);
    }

    @Override
    public void setSlotValue(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.addStringSlot(value);
    }

    @Override
    public void updateSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.updateStringSlot(value);
    }

    @Override
    public LObject cloneObject() {
        return this;
    }

    @Override
    public void nowUsedFrom(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nowUnusedFrom(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}