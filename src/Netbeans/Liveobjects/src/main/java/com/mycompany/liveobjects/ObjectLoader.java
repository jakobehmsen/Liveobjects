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
public interface ObjectLoader {
    public LObject load(int id);

    public ArrayLObject newArray(int length);
    public Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext);
    public Frame newFrame(LObject sender, Instruction[] instructions);
    Closure newClosure(Frame frame, Block behavior);
}
