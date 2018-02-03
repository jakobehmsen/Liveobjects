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
}
