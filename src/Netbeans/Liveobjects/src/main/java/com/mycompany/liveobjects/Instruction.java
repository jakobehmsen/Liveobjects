package com.mycompany.liveobjects;

public interface Instruction {
    void execute(Environment environment);
    InstructionDescriptor getDescriptor();
}
