package com.mycompany.liveobjects;

import java.util.function.Function;

public interface Frame extends LObject {

    public void load(LObject value);

    public LObject pop();

    public void loadInteger(int i);

    public void loadString(String str);

    public void loadThis();

    public void load(int index);

    public void incIP();

    public void popInto(LObject[] arguments, int count);

    public LObject peek();

    public void dup2();

    public void replaceInstruction(Instruction instruction);

    public LObject sender();

    public void dup();

    public void store(int ordinal);

    public void allocate(Environment environment, int localCount);
    
    public void handlePrimitiveError(Environment environment, Throwable error);
    
    public void resumeWith(Environment environment, LObject result);

    public void loadContext();

    public LObject getDistant(Environment environment, int contextDistance, int ordinal);

    public void setDistant(Environment environment, int contextDistance, int ordinal, LObject value);

    public void setIP(int location);

    void executeNext(Environment environment);

    public void onHalt(Environment environment);
}
