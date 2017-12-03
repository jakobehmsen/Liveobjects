package com.mycompany.liveobjects;

public interface Dispatcher {
    void send(LObject receiver, LObject[] arguments, Environment environment, int selector);
    void registerSymbols(Environment environment);
    void sendResumeWithInRet(LObject receiver, LObject result, Environment environment);
    void handlePrimitiveError(Environment environment, LObject error);
}
