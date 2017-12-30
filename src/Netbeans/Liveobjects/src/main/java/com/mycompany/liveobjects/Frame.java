package com.mycompany.liveobjects;

public interface Frame {

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

    public void allocate(int localCount);
    
    public void handlePrimitiveError(Environment environment, LObject error);
    
    public void resumeWith(Environment environment, LObject result);

    public void loadContext();

    public LObject getDistant(int contextDistance, int ordinal);

    public void setDistant(int contextDistance, int ordinal, LObject value);
}
