package com.mycompany.liveobjects.runtime;

public interface Behavior {
    void invoke(LObject receiver, LObject[] arguments, Environment environment);
}
