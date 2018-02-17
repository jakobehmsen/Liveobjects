package com.mycompany.liveobjects;

public class JavaInstanceLObject<T extends Object> implements TransientLObject, ScalarLObject {
    private T object;
    
    public JavaInstanceLObject(T object) {
        this.object = object;
    }

    @Override
    public Object toNative(Environment environment) {
        return object;
    }

    @Override
    public Class<?> toNativeType(Environment environment) {
        return object.getClass();
    }

    @Override
    public LObject getProto(Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public T getValue() {
        return object;
    }
}
