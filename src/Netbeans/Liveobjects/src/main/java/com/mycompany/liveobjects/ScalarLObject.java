package com.mycompany.liveobjects;

public interface ScalarLObject extends PrimitiveLObject {
    @Override
    default LObject getNonParentSlot(Environment environment, String selector) {
        return null;
    }

    @Override
    default boolean hasNonParentSlot(Environment environment, String selector) {
        return false;
    }

    @Override
    default String[] getParentAndNonParentSlotSelectors(Environment environment, String parentSelector) {
        return new String[]{parentSelector};
    }

    @Override
    public default LObject cloneObject(Environment environment) {
        return this;
    }
}
