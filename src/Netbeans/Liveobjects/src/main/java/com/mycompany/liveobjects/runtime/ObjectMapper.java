package com.mycompany.liveobjects.runtime;

public interface ObjectMapper {
    Object mapToNative(Environment environment, LObject argumentLObject);
    LObject mapToLObject(Environment environment, Object responseNative);
    Class<?> mapToNativeType(Environment environment, LObject argumentLObject);
}
