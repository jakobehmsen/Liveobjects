package com.mycompany.liveobjects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ReflectiveInstructionDescriptorResolver implements InstructionDescriptorResolver {
    private Map<Integer, Class<? extends Instruction>> instructionClasses;

    public ReflectiveInstructionDescriptorResolver(List<Class<? extends Instruction>> instructionClasses) {
        this.instructionClasses = instructionClasses.stream().collect(Collectors.toMap(c -> c.getAnnotation(Operation.class).opcode(), c -> c));
    }
    
    @Override
    public int getOpcode(Instruction instruction) {        
        Operation operation = instruction.getClass().getAnnotation(Operation.class);
        return operation.opcode();
    }

    @Override
    public InstructionDescriptor getDescriptor(int opcode) {
        try {
            Class<? extends Instruction> c = instructionClasses.get(opcode);
            List<Field> operands = Arrays.asList(c.getDeclaredFields()).stream()
                    .filter(f -> f.isAnnotationPresent(Operand.class))
                    .sorted((x, y) -> getOrdinal(x) - getOrdinal(y))
                    .collect(Collectors.toList());
            operands.forEach(f -> f.setAccessible(true));
            
            Class<?>[] parameterTypes = operands.stream().map(x -> x.getType()).toArray(s -> new Class<?>[s]);
            Constructor cons = c.getConstructor(parameterTypes);
            
            return new InstructionDescriptor() {
                @Override
                public void writeInstruction(InstructionSet instructionSet, Instruction instruction, OutputStream outputStream) throws IOException {
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    operands.forEach(f -> {
                        if(f.getType().equals(int.class)) {
                            try {
                                dataOutputStream.writeInt(f.getInt(instruction));
                            } catch (IllegalArgumentException | IllegalAccessException | IOException ex) {
                                Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if(f.getType().equals(String.class)) {
                            try {
                                dataOutputStream.writeUTF((String)f.get(instruction));
                            } catch (IllegalArgumentException | IllegalAccessException | IOException ex) {
                                Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if(f.getType().isArray() && f.getType().getComponentType().equals(Instruction.class)) {
                            Instruction[] instructions;
                            try {
                                instructions = (Instruction[]) f.get(instruction);
                                dataOutputStream.writeInt(instructions.length);
                                for (Instruction childInstruction : instructions) {
                                    instructionSet.writeInstruction(childInstruction, outputStream);
                                }
                            } catch (IllegalArgumentException | IllegalAccessException | IOException ex) {
                                Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            // How to handle unsupported types?
                            new String();
                        }
                    });
                }
                
                @Override
                public Instruction readInstruction(InstructionSet instructionSet, InputStream inputStream) throws IOException {
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    Object[] initargs = operands.stream().map(f -> {
                        if(f.getType().equals(int.class)) {
                            try {
                                return dataInputStream.readInt();
                            } catch (IllegalArgumentException | IOException ex) {
                                Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if(f.getType().equals(String.class)) {
                            try {
                                return dataInputStream.readUTF();
                            } catch (IllegalArgumentException | IOException ex) {
                                Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if(f.getType().isArray() && f.getType().getComponentType().equals(Instruction.class)) {
                            try {
                                int length = dataInputStream.readInt();
                                Instruction[] instructions = new Instruction[length];
                                for(int i = 0; i < length; i++) {
                                    instructions[i] = instructionSet.readInstruction(inputStream);
                                }
                                return instructions;
                            } catch (IOException ex) {
                                Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            // How to handle unsupported types?
                            new String();
                        }
                        
                        return null;
                    }).toArray(s -> new Object[s]);
                    try {
                        return (Instruction) cons.newInstance(initargs);
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    return null;
                }
            };
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(ReflectiveInstructionDescriptorResolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private int getOrdinal(Field f) {
        return f.getAnnotation(Operand.class).ordinal();
    }
}
