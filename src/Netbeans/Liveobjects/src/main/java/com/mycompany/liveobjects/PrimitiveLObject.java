package com.mycompany.liveobjects;

public abstract class PrimitiveLObject implements LObject {
    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        Block behavior = (Block)resolve(selector, environment);
        behavior.evaluate(this, arguments, environment);
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        return getProto(environment).resolve(selector, environment);
    }

    @Override
    public boolean isParent(Environment environment, AssociativeArrayLObject obj) {
        return getProto(environment).isParent(environment, obj);
    }
    
    protected abstract LObject getProto(Environment environment);
}
