package com.mycompany.liveobjects.runtime;

public interface InstructionDescriptorResolver {
    int getOpcode(Instruction instruction);
    InstructionDescriptor getDescriptor(int opcode);
}
