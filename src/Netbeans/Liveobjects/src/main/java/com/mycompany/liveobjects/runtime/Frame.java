package com.mycompany.liveobjects.runtime;

public interface Frame extends LObject {
    void load(LObject value);
    LObject pop();
    void loadInteger(int i);
    void loadString(String str);
    void loadThis();
    void load(int index);
    void incIP();
    void popInto(LObject[] arguments, int count);
    LObject peek();
    void dup2();
    void replaceInstruction(Instruction instruction);
    LObject sender();
    void dup();
    void store(int ordinal);
    void allocate(Environment environment, int localCount);
    void handlePrimitiveError(Environment environment, Throwable error);
    void resumeWith(Environment environment, LObject result);
    void loadContext();
    LObject getDistant(Environment environment, int contextDistance, int ordinal);
    void setDistant(Environment environment, int contextDistance, int ordinal, LObject value);
    void setIP(int location);
    void executeNext(Environment environment);
    void onHalt(Environment environment);
}
