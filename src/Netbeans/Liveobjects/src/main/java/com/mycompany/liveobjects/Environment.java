/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects;

/**
 *
 * @author jakob
 */
public interface Environment {
    Frame currentFrame();
    void pushFrame(Instruction[] instructions);
    void pushFrame(Instruction[] instructions, Frame sender);
    void popFrame();

    void finish();
    
    int getSymbolCode(String str);

    void currentFrame(Frame frame);

    void send(LObject receiver, int selector, LObject[] arguments);

    void addSymbol(int symbolCode, String string);

    World getWorld();

    public String getSymbolString(int symbolCode);
}
