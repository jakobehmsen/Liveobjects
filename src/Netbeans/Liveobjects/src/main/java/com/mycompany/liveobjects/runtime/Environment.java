/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.runtime;

/**
 *
 * @author jakob
 */
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

    public Dispatcher getDispatcher();

    public void pushFrameClosure(Instruction[] instructions, Frame lexicalContext);
    
    InstructionSet getInstructionSet();

    ObjectLoader getObjectLoader();
    
    ObjectMapper getObjectMapper();
}
