package com.mycompany.liveobjects;

public interface PrimitiveLObject extends LObject {
    @Override
    default void send(int selector, LObject[] arguments, Environment environment) {
        Block behavior = (Block)resolve(selector, environment);
        behavior.evaluate(this, arguments, environment);
    }

    @Override
    default LObject resolve(int selector, Environment environment) {
        return getProto(environment).resolve(selector, environment);
    }

    @Override
    default boolean isParent(Environment environment, AssociativeArrayLObject obj) {
        return getProto(environment).isParent(environment, obj);
    }
    
    LObject getProto(Environment environment);

    @Override
    default LObject getSlot(Environment environment, String selector) {
        if(selector.equals(PrimitiveSelectors.PARENT)) {
            return getProto(environment);
        }
        
        return getNonParentSlot(environment, selector);
    }
    
    LObject getNonParentSlot(Environment environment, String selector);

    @Override
    default boolean isParentSlot(Environment environment, String selector) {
        return selector.equals(PrimitiveSelectors.PARENT);
    }

    @Override
    default boolean hasSlot(Environment environment, String selector) {
        return selector.equals(PrimitiveSelectors.PARENT) || hasNonParentSlot(environment, selector);
    }
    
    boolean hasNonParentSlot(Environment environment, String selector);

    @Override
    default String[] getSlotSelectors(Environment environment) {
        return getParentAndNonParentSlotSelectors(environment, PrimitiveSelectors.PARENT);
    }

    String[] getParentAndNonParentSlotSelectors(Environment environment, String parentSelector);
}
