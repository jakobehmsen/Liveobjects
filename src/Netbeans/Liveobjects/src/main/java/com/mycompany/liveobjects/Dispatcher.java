package com.mycompany.liveobjects;

public interface Dispatcher {
    void send(LObject receiver, LObject[] arguments, Environment environment, int selector);
    void registerSymbols(Environment environment);
}
