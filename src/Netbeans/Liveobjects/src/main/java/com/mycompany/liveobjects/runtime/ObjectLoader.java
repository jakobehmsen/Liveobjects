package com.mycompany.liveobjects.runtime;

public interface ObjectLoader {
    LObject load(int id);
    ArrayLObject newArray(LObject[] items);
    Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext);
    Frame newFrame(LObject sender, Instruction[] instructions);
    Closure newClosure(Frame frame, Block behavior);
    LObject newNative(Object object);
}
