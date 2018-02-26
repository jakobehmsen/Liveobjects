package com.mycompany.liveobjects.runtime;

public interface ImprovedInstruction extends Instruction {
    Instruction revert(Environment environment);
}
