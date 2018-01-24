/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OpcodeInstructionSet implements InstructionSet {
    private InstructionDescriptorResolver instructionDescriptorResolver;

    public OpcodeInstructionSet(InstructionDescriptorResolver instructionDescriptorResolver) {
        this.instructionDescriptorResolver = instructionDescriptorResolver;
    }

    @Override
    public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
        int opcode = instructionDescriptorResolver.getOpcode(instruction);
        InstructionDescriptor descriptor = instructionDescriptorResolver.getDescriptor(opcode);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(opcode);
        descriptor.writeInstruction(this, instruction, outputStream);
    }

    @Override
    public Instruction readInstruction(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int opcode = dataInputStream.readInt();
        InstructionDescriptor descriptor = instructionDescriptorResolver.getDescriptor(opcode);
        return descriptor.readInstruction(this, inputStream);
    }
}
