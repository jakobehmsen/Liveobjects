package com.mycompany.liveobjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Instructions {
    @Operation(opcode = 1)
    public static class JumpIfFalse implements Instruction {
        @Operand(ordinal = 0)
        private int location;

        public JumpIfFalse(int location) {
            this.location = location;
        }

        @Override
        public void execute(Environment environment) {
            LObject obj = environment.currentFrame().pop();
            if(obj == environment.getWorld().getFalse()) {
                environment.currentFrame().setIP(location);
            } else {
                environment.currentFrame().incIP();
            }
        }
    }
    
    public static Instruction jumpIfFalse(int location) {
        return new JumpIfFalse(location);
    }
    
    @Operation(opcode = 2)
    public static class Jump implements Instruction {
        @Operand(ordinal = 0)
        private int location;

        public Jump(int location) {
            this.location = location;
        }

        @Override
        public void execute(Environment environment) {
            environment.currentFrame().setIP(location);
        }
    }

    public static Instruction jump(int location) {
        return new Jump(location);
    }
    
    @Operation(opcode = 3)
    public static class Top implements Instruction {
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().dup();
            environment.currentFrame().incIP();
        }
    }

    public static Instruction top() {
        return new Top();
    }
    
    @Operation(opcode = 4)
    public static class Root implements Instruction {
        @Override
        public void execute(Environment environment) {
            LObject root = environment.getWorld().getRoot();
            environment.currentFrame().load(root);
            environment.currentFrame().incIP();
        }
    }
    
    public static Instruction root() {
        return new Root();
    }
    
    @Operation(opcode = 5)
    public static class LoadBool implements Instruction {
        @Operand(ordinal = 0)
        private boolean value;

        public LoadBool(boolean value) {
            this.value = value;
        }

        @Override
        public void execute(Environment environment) {
            LObject bool = wrap(value, environment);
            environment.currentFrame().load(bool);
            environment.currentFrame().incIP();
        }
        
        public static LObject wrap(boolean value, Environment environment) {
            return value ? environment.getWorld().getTrue() : environment.getWorld().getFalse();
        }
    }
    
    public static Instruction loadBool(boolean value) {
        return new LoadBool(value);
    }
    
    @Operation(opcode = 6)
    public static class Closure implements Instruction {
        @Override
        public void execute(Environment environment) {
            Block block = (Block) environment.currentFrame().pop();
            Frame frame = environment.currentFrame();
            com.mycompany.liveobjects.Closure closure = environment.getObjectLoader().newClosure(frame, block);
            //com.mycompany.liveobjects.Closure closure = new com.mycompany.liveobjects.Closure(frame, block);
            environment.currentFrame().load(closure);
            environment.currentFrame().incIP();
        }
    }

    public static Instruction closure() {
        return new Closure();
    }
    
    @Operation(opcode = 7)
    public static class StoreContextLocal implements Instruction {
        @Operand(ordinal = 0)
        private int contextDistance;
        @Operand(ordinal = 1)
        private int ordinal;

        public StoreContextLocal(int contextDistance, int ordinal) {
            this.contextDistance = contextDistance;
            this.ordinal = ordinal;
        }
        
        @Override
        public void execute(Environment environment) {
            LObject distantValue = environment.currentFrame().pop();
            environment.currentFrame().setDistant(environment, contextDistance, ordinal, distantValue);
            environment.currentFrame().incIP();
        }
    }

    public static Instruction storeDistant(int contextDistance, int ordinal) {
        return new StoreContextLocal(contextDistance, ordinal);
    }
    
    @Operation(opcode = 8)
    public static class LoadContextLocal implements Instruction {
        @Operand(ordinal = 0)
        private int contextDistance;
        @Operand(ordinal = 1)
        private int ordinal;

        public LoadContextLocal(int contextDistance, int ordinal) {
            this.contextDistance = contextDistance;
            this.ordinal = ordinal;
        }
        
        @Override
        public void execute(Environment environment) {
            LObject distantValue = environment.currentFrame().getDistant(environment, contextDistance, ordinal);
            environment.currentFrame().load(distantValue);
            environment.currentFrame().incIP();
        }
    }

    public static Instruction loadContextLocal(int contextDistance, int ordinal) {
        return new LoadContextLocal(contextDistance, ordinal);
    }
    
    @Operation(opcode = 9)
    public static class LoadContext implements Instruction {
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().loadContext();
            environment.currentFrame().incIP();
        }
    }

    public static Instruction loadContext() {
        return new LoadContext();
    }
    
    @Operation(opcode = 10)
    public static class Dup implements Instruction {
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().dup();
            environment.currentFrame().incIP();
        }
    }

    public static Instruction dup() {
        return new Dup();
    }
    
    @Operation(opcode = 11)
    public static class StoreLocal implements Instruction {
        @Operand(ordinal = 0)
        private int ordinal;

        public StoreLocal(int ordinal) {
            this.ordinal = ordinal;
        }

        @Override
        public void execute(Environment environment) {
            environment.currentFrame().store(ordinal);
            environment.currentFrame().incIP();
        }
    }

    public static Instruction storeLocal(int ordinal) {
        return new StoreLocal(ordinal);
    }
    
    @Operation(opcode = 12)
    public static class LoadLocal implements Instruction {
        @Operand(ordinal = 0)
        private int i;

        public LoadLocal(int i) {
            this.i = i;
        }
        
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().load(i);
            environment.currentFrame().incIP();
        }
    }
    
    public static Instruction loadLocal(final int i) {
        return new LoadLocal(i);
    }
    
    @Operation(opcode = 13)
    public static class LoadString implements Instruction {
        @Operand(ordinal = 0)
        private String str;

        public LoadString(String str) {
            this.str = str;
        }

        @Override
        public void execute(Environment environment) {
            environment.currentFrame().loadString(str);
            environment.currentFrame().incIP();
        }
    }

    public static Instruction loadString(final String str) {
        return new LoadString(str);
    }
    
    @Operation(opcode = 14)
    public static class LoadInteger implements Instruction {
        @Operand(ordinal = 0)
        private int i;

        public LoadInteger(int i) {
            this.i = i;
        }

        @Override
        public void execute(Environment environment) {
            environment.currentFrame().loadInteger(i);
            environment.currentFrame().incIP();
        }
    }

    public static Instruction loadInteger(final int i) {
        return new LoadInteger(i);
    }
    
    public static class SendI implements ImprovedInstruction {
        private int symbolCode;
        private int arity;

        public SendI(int symbolCode, int arity) {
            this.symbolCode = symbolCode;
            this.arity = arity;
        }

        @Override
        public Instruction revert(Environment environment) {
            String selector = environment.getSymbolString(symbolCode);
            return new Send(selector, arity);
        }

        @Override
        public void execute(Environment environment) {
            LObject[] arguments = new LObject[arity];
            environment.currentFrame().popInto(arguments, arity);
            LObject receiver = environment.currentFrame().pop();
            send(environment, receiver, symbolCode, arguments);
        }
        
        public void send(Environment environment, LObject receiver, int symbolCode, LObject[] arguments) {
            environment.send(receiver, symbolCode, arguments);
        }
    }

    public static Instruction send(final int symbolCode, final int arity) {
        return new SendI(symbolCode, arity);
    }
    
    @Operation(opcode = 15)
    public static class Send implements Instruction {
        @Operand(ordinal = 0)
        private String selector;
        @Operand(ordinal = 1)
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
    }

    public static Instruction send(String selector, int arity) {
        return new Send(selector, arity);
    }
    
    @Operation(opcode = 16)
    public static class Halt implements Instruction {
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().incIP();
            environment.halt();
        }
    }
    
    public static Instruction halt() {
        return new Halt();
    }
    
    public static final Instruction[] ROOT_INSTRUCTIONS = new Instruction[] {
        Instructions.loadInteger(0), // Dummy instruction; is always ignored due to ip incr
        Instructions.halt()
    };
    
    @Operation(opcode = 17)
    public static class Dup2 implements Instruction {
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().dup2();
            environment.currentFrame().incIP();
        }
    }
    
    public static Instruction dup2() {
        return new Dup2();
    }
    
    @Operation(opcode = 18)
    public static class Pop implements Instruction {
        @Override
        public void execute(Environment environment) {
            environment.currentFrame().pop();
            environment.currentFrame().incIP();
        }
    }
    
    public static Instruction pop() {
        return new Pop();
    }
    
    @Operation(opcode = 19)
    public static class LoadBlock implements Instruction {
        @Operand(ordinal = 0)
        private int arity;
        @Operand(ordinal = 1)
        private int varCount;
        @Operand(ordinal = 2)
        private Instruction[] bodyInstructions;

        public LoadBlock(int arity, int varCount, Instruction[] bodyInstructions) {
            this.arity = arity;
            this.varCount = varCount;
            this.bodyInstructions = bodyInstructions;
        }
        
        @Override
        public void execute(Environment environment) {
            Block block = new Block(arity, varCount, Arrays.asList(bodyInstructions));
            
            environment.currentFrame().load(block);
            environment.currentFrame().incIP();
        }
    }
    
    public static Instruction loadBlock(int arity, int varCount, List<Instruction> bodyInstructions) {
        return new LoadBlock(arity, varCount, bodyInstructions.stream().toArray(s -> new Instruction[s]));
    }
    
    @Operation(opcode = 20)
    public static class RetInstruction implements Instruction {
        @Override
        public void execute(Environment environment) {
            LObject sender = environment.currentFrame().sender();
            LObject result = environment.currentFrame().pop();
            environment.currentFrame().incIP();
            environment.currentFrame().onHalt(environment);
            
            environment.getDispatcher().sendResumeWithInRet(sender, result, environment);
        }
    }

    public static Instruction ret() {
        return new RetInstruction();
    }
    
    @Operation(opcode = 21)
    public static class LoadNil implements Instruction {
        @Override
        public void execute(Environment environment) {
            LObject nil = environment.getWorld().getNil();
            environment.currentFrame().load(nil);
            environment.currentFrame().incIP();
        }
    }

    public static Instruction loadNil() {
        return new LoadNil();
    }
    
    public static class Java {
        
        public static Class<?> parseType(final String typeName) {
            switch (typeName) {
                case "boolean":
                    return boolean.class;
                case "byte":
                    return byte.class;
                case "short":
                    return short.class;
                case "int":
                    return int.class;
                case "long":
                    return long.class;
                case "float":
                    return float.class;
                case "double":
                    return double.class;
                case "char":
                    return char.class;
                case "void":
                    return void.class;
                default:
                    String fqn = typeName.contains(".") ? typeName : "java.lang.".concat(typeName);
                    try {
                        return Class.forName(fqn);
                    } catch (ClassNotFoundException ex) {
                        throw new IllegalArgumentException("Class not found: " + fqn);
                    }
            }
        }
    }
    
    @Operation(opcode = 22)
    public static class JavaNew implements Instruction {
        @Operand(ordinal = 0)
        private String className;
        @Operand(ordinal = 1)
        private String[] parameterTypeNames;

        public JavaNew(String className, String[] parameterTypeNames) {
            this.className = className;
            this.parameterTypeNames = parameterTypeNames;
        }
        
        @Override
        public void execute(Environment environment) {
            try {
                Class<?> c = Class.forName(className);
                Class<?>[] parameterTypes = new Class<?>[parameterTypeNames.length];
                for(int i = 0; i < parameterTypeNames.length; i++) {
                    String n = parameterTypeNames[i];
                    parameterTypes[i] = Java.parseType(n);
                }
                
                Constructor constructor = c.getConstructor(parameterTypes);
                Instruction javaNew = Instructions.javaNew(constructor);
                environment.currentFrame().replaceInstruction(javaNew);
            } catch (ClassNotFoundException | NoSuchMethodException ex) {
                throw new JavaInteropException(ex);
            }
        }
    }

    public static Instruction javaNew(String className, String[] parameterTypeNames) {
        return new JavaNew(className, parameterTypeNames);
    }

    private static Instruction javaNew(Constructor constructor) throws NoSuchMethodException {
        return new ImprovedInstruction() {
            @Override
            public Instruction revert(Environment environment) {
                return new JavaNew(
                        constructor.getDeclaringClass().getName(), 
                        Arrays.asList(constructor.getParameterTypes()).stream().map(c -> c.getName()).toArray(s -> new String[s]));
            }

            @Override
            public void execute(Environment environment) {
                try {
                    // Convert LObjects to compatible Objects
                    Object[] arguments = new Object[constructor.getParameterTypes().length];
                    for(int i = arguments.length - 1; i >= 0; i--) {
                        LObject argumentLObject = environment.currentFrame().pop();
                        Object argumentNative = environment.getObjectMapper().mapToNative(environment, argumentLObject);
                        arguments[i] = argumentNative;
                    }
                    
                    // Convert Object to compatible LObject
                    Object responseNative = constructor.newInstance(arguments);
                    LObject responseLObject = environment.getObjectMapper().mapToLObject(environment, responseNative);
                    
                    environment.currentFrame().load(responseLObject);
                    environment.currentFrame().incIP();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new JavaInteropException(ex);
                }
            }
        };
    }
    
    @Operation(opcode = 23)
    public static class JavaInvokeInstance implements Instruction {
        @Operand(ordinal = 0)
        private String methodName;
        @Operand(ordinal = 1)
        private String className;
        @Operand(ordinal = 2)
        private String[] parameterTypeNames;

        public JavaInvokeInstance(String methodName, String className, String[] parameterTypeNames) {
            this.methodName = methodName;
            this.className = className;
            this.parameterTypeNames = parameterTypeNames;
        }

        @Override
        public void execute(Environment environment) {
            try {
                Class<?> c = Class.forName(className);
                Class<?>[] parameterTypes = new Class<?>[parameterTypeNames.length];
                for(int i = 0; i < parameterTypeNames.length; i++) {
                    String n = parameterTypeNames[i];
                    parameterTypes[i] = Java.parseType(n);
                }
                
                Method method = c.getDeclaredMethod(methodName, parameterTypes);
                Instruction javaInvokeInstance = Instructions.javaInvokeInstance(method);
                environment.currentFrame().replaceInstruction(javaInvokeInstance);
            } catch (ClassNotFoundException | NoSuchMethodException ex) {
                throw new JavaInteropException(ex);
            }
        }
    }

    public static Instruction javaInvokeInstance(String methodName, String className, String[] parameterTypeNames) {
        return new JavaInvokeInstance(methodName, className, parameterTypeNames);
    }

    private static Instruction javaInvokeInstance(Method method) {
        return new ImprovedInstruction() {
            @Override
            public Instruction revert(Environment environment) {
                return new JavaNew(
                        method.getDeclaringClass().getName(), 
                        Arrays.asList(method.getParameterTypes()).stream().map(c -> c.getName()).toArray(s -> new String[s]));
            }

            @Override
            public void execute(Environment environment) {
                try {
                    // Convert LObjects to compatible Objects
                    Object[] arguments = new Object[method.getParameterTypes().length];
                    for(int i = arguments.length - 1; i >= 0; i--) {
                        LObject argumentLObject = environment.currentFrame().pop();
                        Object argumentNative = environment.getObjectMapper().mapToNative(environment, argumentLObject);
                        arguments[i] = argumentNative;
                    }
                    LObject targetLObject = environment.currentFrame().pop();
                    Object target = environment.getObjectMapper().mapToNative(environment, targetLObject);
                    
                    // Convert Object to compatible LObject
                    Object responseNative = method.invoke(target, arguments);
                    LObject responseLObject = environment.getObjectMapper().mapToLObject(environment, responseNative);
                    
                    environment.currentFrame().load(responseLObject);
                    environment.currentFrame().incIP();
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new JavaInteropException(ex);
                }
            }
        };
    }
}
