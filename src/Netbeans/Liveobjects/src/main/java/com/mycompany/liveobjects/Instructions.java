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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakob
 */
public class Instructions {
    public static InstructionDescriptor loadLocalDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
            new DataOutputStream(outputStream).writeInt(((LoadLocal)instruction).i);
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) throws IOException {
            int i = new DataInputStream(inputStream).readInt();
            return new LoadLocal(i);
        }
    };
    
    public static class LoadContext implements Instruction {
        public static InstructionDescriptor descriptor = new InstructionDescriptor() {
            @Override
            public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
                
            }

            @Override
            public Instruction readInstruction(InputStream inputStream) throws IOException {
                return new LoadContext();
            }
        };
        
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().loadContext();
            environment.currentFrame().incIP();
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            return descriptor;
        }
    }

    public static Instruction loadContext() {
        return new LoadContext();
    }
    
    public static class Dup implements Instruction {
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().dup();
            environment.currentFrame().incIP();
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static Instruction dup() {
        return new Dup();
    }
    
    public static InstructionDescriptor storeLocalDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
            new DataOutputStream(outputStream).writeInt(((StoreLocal)instruction).ordinal);
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) throws IOException {
            int ordinal = new DataInputStream(inputStream).readInt();
            return new StoreLocal(ordinal);
        }
    };
    
    public static class StoreLocal implements Instruction {
        private int ordinal;

        public StoreLocal(int ordinal) {
            this.ordinal = ordinal;
        }

        @Override
        public void execute(Environment environment) {
            environment.currentFrame().store(ordinal);
            environment.currentFrame().incIP();
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            return storeLocalDescriptor;
        }
    }

    public static Instruction storeLocal(int ordinal) {
        return new StoreLocal(ordinal);
    }
    
    public static class LoadLocal implements Instruction {
        private int i;

        public LoadLocal(int i) {
            this.i = i;
        }
        
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().load(i);
            environment.currentFrame().incIP();
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            return loadLocalDescriptor;
        }
    }
    
    public static Instruction loadLocal(final int i) {
        return new LoadLocal(i);
        
        /*return new Instruction() {
            @Override
            public void execute(Environment environment) {
                environment.currentFrame().load(i);
                environment.currentFrame().incIP();
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return loadLocalDescriptor;
            }
        };*/
    }
    
    public static InstructionDescriptor loadStringDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(((LoadString)instruction).str);
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String str = dataInputStream.readUTF();
            return new LoadString(str);
        }
    };
    
    public static class LoadString implements Instruction {
        private String str;

        public LoadString(String str) {
            this.str = str;
        }

        @Override
        public void execute(Environment environment) {
            environment.currentFrame().loadString(str);
            environment.currentFrame().incIP();
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            return loadStringDescriptor;
        }
    }

    public static Instruction loadString(final String str) {
        return new LoadString(str);
        
        /*return new Instruction() {
            @Override
            public void execute(Environment environment) {
                environment.currentFrame().loadString(str);
                environment.currentFrame().incIP();
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return loadStringDescriptor;
            }
        };*/
    }
    
    public static InstructionDescriptor loadIntegerDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(((LoadInteger)instruction).i);
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int i = dataInputStream.readInt();
            return new LoadInteger(i);
        }
    };
    
    public static class LoadInteger implements Instruction {
        private int i;

        public LoadInteger(int i) {
            this.i = i;
        }

        @Override
        public void execute(Environment environment) {
                environment.currentFrame().loadInteger(i);
                environment.currentFrame().incIP();
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            return loadIntegerDescriptor;
        }
    }

    public static Instruction loadInteger(final int i) {
        return new LoadInteger(i);
        
        /*return new Instruction() {
            @Override
            public void execute(Environment environment) {
                environment.currentFrame().loadInteger(i);
                environment.currentFrame().incIP();
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return loadIntegerDescriptor;
            }
        };*/
    }
    
    public static InstructionDescriptor cloneObjectDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Instruction send(final int selector, final int arity) {
        return new Instruction() {
            @Override
            public void execute(Environment environment) {
                LObject[] arguments = new LObject[arity];
                environment.currentFrame().popInto(arguments, arity);
                LObject receiver = environment.currentFrame().pop();
                environment.send(receiver, selector, arguments);
                //receiver.send(selector, arguments, environment);
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    public static InstructionDescriptor sendDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(((Send)instruction).selector);
            dataOutputStream.writeInt(((Send)instruction).arity);
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            
            return new Send(dataInputStream.readUTF(), dataInputStream.readInt());
        }
    };
    
    public static class Send implements Instruction {
        private String selector;
        private int arity;

        public Send(String selector, int arity) {
            this.selector = selector;
            this.arity = arity;
        }
        
        @Override
        public void execute(Environment environment) {
            int symbolCode = environment.getSymbolCode(selector);
            environment.currentFrame().replaceInstruction(Instructions.send(symbolCode, arity));
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            return sendDescriptor;
        }
    }

    public static Instruction send(String selector, int arity) {
        return new Send(selector, arity);
        /*return new Instruction() {
            @Override
            public void execute(Environment environment) {
                int symbolCode = environment.getSymbolCode(selector);
                environment.currentFrame().replaceInstruction(Instructions.send(symbolCode, arity));
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return sendDescriptor;
            }
        };*/
    }
    
    public static InstructionDescriptor finishDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Instruction finish() {
        return new Instruction() {
            @Override
            public void execute(Environment environment) {
                environment.finish();
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return finishDescriptor;
            }
        };
    }
    
    public static InstructionDescriptor dup2Descriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Instruction dup2() {
        return new Instruction() {
            @Override
            public void execute(Environment environment) {
                environment.currentFrame().dup2();
                environment.currentFrame().incIP();
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return dup2Descriptor;
            }
        };
    }
    
    public static InstructionDescriptor popDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Instruction pop() {
        return new Instruction() {
            @Override
            public void execute(Environment environment) {
                environment.currentFrame().pop();
                environment.currentFrame().incIP();
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return popDescriptor;
            }
        };
    }
    
    public static InstructionDescriptor loadBlockDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Instruction loadBlock(int arity, int varCount, List<Instruction> bodyInstructions) {
        final Block block = new Block(arity, varCount, bodyInstructions);
        
        return new Instruction() {
            @Override
            public void execute(Environment environment) {
                environment.currentFrame().load(block);
                environment.currentFrame().incIP();
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return loadBlockDescriptor;
            }
        };
    }
    
    public static InstructionDescriptor retDescriptor = new InstructionDescriptor() {
        @Override
        public void writeInstruction(Instruction instruction, OutputStream outputStream) {
            
        }

        @Override
        public Instruction readInstruction(InputStream inputStream) {
            return new RetInstruction();
        }
    };
    
    public static class RetInstruction implements Instruction {
        @Override
        public void execute(Environment environment) {
            Frame sender = environment.currentFrame().sender();
            LObject result = environment.currentFrame().pop();
            sender.resumeWith(environment, result);
        }

        @Override
        public InstructionDescriptor getDescriptor() {
            return retDescriptor;
        }
    }

    public static Instruction ret() {
        return new RetInstruction();
        
        /*return new Instruction() {
            @Override
            public void execute(Environment environment) {
                Frame sender = environment.currentFrame().sender();
                sender.load(environment.currentFrame().pop());
                sender.incIP();
                environment.currentFrame(sender);
            }

            @Override
            public InstructionDescriptor getDescriptor() {
                return retDescriptor;
            }
        };*/
    }
}
