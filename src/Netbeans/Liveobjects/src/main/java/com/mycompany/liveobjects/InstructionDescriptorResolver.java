package com.mycompany.liveobjects;

public interface InstructionDescriptorResolver {
    int getOpcode(Instruction instruction);
    InstructionDescriptor getDescriptor(int opcode);
}
