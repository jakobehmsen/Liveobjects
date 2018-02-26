package com.mycompany.liveobjects.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface InstructionSet {
    void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException;
    Instruction readInstruction(InputStream inputStream) throws IOException;
}
