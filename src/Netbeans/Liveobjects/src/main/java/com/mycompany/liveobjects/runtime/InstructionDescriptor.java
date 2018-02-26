package com.mycompany.liveobjects.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface InstructionDescriptor {
    void writeInstruction(InstructionSet instructionSet, Instruction instruction, OutputStream outputStream) throws IOException;
    Instruction readInstruction(InstructionSet instructionSet, InputStream inputStream) throws IOException;
}
