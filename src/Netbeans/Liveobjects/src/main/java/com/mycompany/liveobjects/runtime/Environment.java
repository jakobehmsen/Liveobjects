package com.mycompany.liveobjects.runtime;

public interface Environment {
    Frame currentFrame();
    void pushFrame(Instruction[] instructions);
    void pushFrame(Instruction[] instructions, LObject sender);
    void popFrame();
    void halt();
    int getSymbolCode(String str);
    String getSymbolString(int symbolCode);
    void currentFrame(Frame frame);
    void send(LObject receiver, int selector, LObject[] arguments);
    World getWorld();
    Dispatcher getDispatcher();
    void pushFrameClosure(Instruction[] instructions, Frame lexicalContext);
    InstructionSet getInstructionSet();
    ObjectLoader getObjectLoader();
    ObjectMapper getObjectMapper();
}
