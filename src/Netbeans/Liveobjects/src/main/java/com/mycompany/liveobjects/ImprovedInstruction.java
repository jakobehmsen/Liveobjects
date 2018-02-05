package com.mycompany.liveobjects;

public interface ImprovedInstruction extends Instruction {
    Instruction revert(Environment environment);
}
