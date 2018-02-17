package com.mycompany.liveobjects;

public interface TransientLObject extends LObject {
    @Override
    public default void nowUsedFrom(int id, Environment environment) {
        throw new UnsupportedOperationException("Object is transient.");
    }

    @Override
    public default void nowUnusedFrom(int id) {
        throw new UnsupportedOperationException("Object is transient.");
    }

    @Override
    public default void updateSlot(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Object is transient.");
    }

    @Override
    public default void addSlot(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Object is transient.");
    }

    @Override
    public default void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Object is transient.");
    }
}
