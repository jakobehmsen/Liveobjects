/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author jakob
 */
public interface InstructionSet {
    void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException;
    Instruction readInstruction(InputStream inputStream) throws IOException;
}
