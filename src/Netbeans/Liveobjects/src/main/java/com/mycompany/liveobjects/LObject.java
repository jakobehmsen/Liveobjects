package com.mycompany.liveobjects;

public interface LObject {
    void send(int selector, LObject[] arguments, Environment environment);
    void deleteSlotValue(ObjectSlotTransaction slotTransaction);
    void addSlot(ObjectSlotTransaction slotTransaction);
    void updateSlot(ObjectSlotTransaction slotTransaction);
    LObject cloneObject(Environment environment);

    void nowUsedFrom(int id, Environment environment);
    void nowUnusedFrom(int id);
    
    LObject resolve(int selector, Environment environment);

    default LObject getSlot(Environment environment, LObject[] arguments) {
        throw new UnsupportedOperationException();
    }

    default LObject setSlot(Environment environment, LObject[] arguments) {
        throw new UnsupportedOperationException();
    }

    default LObject setParentSlot(Environment environment, LObject[] arguments) {
        throw new UnsupportedOperationException();
    }

    public boolean isParent(Environment environment, AssociativeArrayLObject obj);
    
    default String toString(Environment environment) {
        return toString();
    }
}
