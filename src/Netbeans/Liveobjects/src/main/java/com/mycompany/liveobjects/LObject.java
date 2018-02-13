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

    LObject getSlot(Environment environment, String selector);

    default LObject setSlot(Environment environment, LObject[] arguments) {
        throw new UnsupportedOperationException();
    }
    
    boolean hasSlot(Environment environment, String selector);

    default LObject setParentSlot(Environment environment, LObject[] arguments) {
        throw new UnsupportedOperationException();
    }

    // TODO: Consider renaming to isAncestor or delegatesTo
    boolean isParent(Environment environment, AssociativeArrayLObject obj);
    
    String[] getSlotSelectors(Environment environment);
    
    default String toString(Environment environment) {
        return toString();
    }

    boolean isParentSlot(Environment environment, String selector);

    default Object toNative(Environment environment) {
        throw new UnsupportedOperationException();
    }
}
