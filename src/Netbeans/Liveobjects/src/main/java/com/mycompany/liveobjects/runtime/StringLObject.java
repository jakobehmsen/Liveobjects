package com.mycompany.liveobjects.runtime;

public class StringLObject implements ScalarLObject {
    private String value;

    public StringLObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.deleteStringSlot(value);
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

    @Override
    public LObject getProto(Environment environment) {
        return environment.getWorld().getStringPrototype();
    }

    @Override
    public Object toNative(Environment environment) {
        return value;
    }

    @Override
    public Class<?> toNativeType(Environment environment) {
        return String.class;
    }
}
