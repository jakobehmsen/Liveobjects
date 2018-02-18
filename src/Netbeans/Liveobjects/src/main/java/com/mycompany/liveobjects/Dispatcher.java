package com.mycompany.liveobjects;

public interface Dispatcher {
    void send(LObject receiver, LObject[] arguments, Environment environment, int selector);
    void sendResumeWithInRet(LObject receiver, LObject result, Environment environment);
    void handlePrimitiveError(Environment environment, Throwable error);
}
