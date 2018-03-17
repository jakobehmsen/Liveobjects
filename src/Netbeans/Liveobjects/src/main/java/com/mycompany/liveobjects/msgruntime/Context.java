package com.mycompany.liveobjects.msgruntime;

public interface Context {
    void push(LObject object);
    LObject pop();
    LObject get(int index);
    void set(int index, LObject object);
    void incIP();
    void setIP();
    void evaluateNext(Environment environment);
}
