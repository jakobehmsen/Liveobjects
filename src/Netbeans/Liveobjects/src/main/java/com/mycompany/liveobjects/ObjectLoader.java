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
    LObject load(int id);
    ArrayLObject newArray(LObject[] items);
    Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext);
    Frame newFrame(LObject sender, Instruction[] instructions);
    Closure newClosure(Frame frame, Block behavior);
}
