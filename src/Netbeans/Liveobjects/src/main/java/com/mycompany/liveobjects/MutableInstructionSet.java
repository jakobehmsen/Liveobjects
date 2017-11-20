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
import java.util.Hashtable;

/**
 *
 * @author jakob
 */
public class MutableInstructionSet implements InstructionSet {
    private Hashtable<Integer, InstructionDescriptor> opcodeToDescriptorMap = new Hashtable<>();
    private Hashtable<InstructionDescriptor, Integer> descriptorToOpcodeMap = new Hashtable<>();
    
    public void registerInstruction(int opcode, InstructionDescriptor descriptor) {
        opcodeToDescriptorMap.put(opcode, descriptor);
        descriptorToOpcodeMap.put(descriptor, opcode);
    }

    @Override
    public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
        InstructionDescriptor descriptor = instruction.getDescriptor();
        int opcode = descriptorToOpcodeMap.get(descriptor);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(opcode);
        descriptor.writeInstruction(instruction, outputStream);
    }

    @Override
    public Instruction readInstruction(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int opcode = dataInputStream.readInt();
        InstructionDescriptor descriptor = opcodeToDescriptorMap.get(opcode);
        return descriptor.readInstruction(inputStream);
    }
}
