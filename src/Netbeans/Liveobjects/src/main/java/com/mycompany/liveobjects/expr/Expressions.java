package com.mycompany.liveobjects.expr;

import com.mycompany.liveobjects.runtime.Instruction;
import com.mycompany.liveobjects.runtime.Instructions;
import com.mycompany.liveobjects.runtime.PrimitiveSelectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Expressions {
    public static Expression integer(final int i) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.loadInteger(i));
                        }
                    }
                };
            }
        };
    }

    public static Expression string(final String str) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.loadString(str));
                        }
                    }
                };
            }
        };
    }
    
    public static Expression self() {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.loadLocal(0));
                        }
                    }
                };
            }
        };
    }
    
    public static Expression thisContext() {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.loadContext());
                        }
                    }
                };
            }
        };
    }
    
    public static Expression setSlot(final Expression target, final String symbol, final Expression value) {
        if(symbol.startsWith("*")) {
            return messageSend(target, PrimitiveSelectors.SET_PARENT_SLOT, Arrays.asList(Expressions.string(symbol), value));
        } else {
            return messageSend(target, PrimitiveSelectors.SET_SLOT, Arrays.asList(Expressions.string(symbol), value));
        }
    }
    
    public static Expression resolveSlot(final Expression target, final String symbol) {
        return messageSend(target, PrimitiveSelectors.RESOLVE_SLOT, Arrays.asList(Expressions.string(symbol)));
    }

    public static Expression sequence(List<Expression> items) {
        if(items.isEmpty()) {
            return nil();
        }
        
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                List<Emitter> emitters = 
                    Stream.concat(
                        items.stream().limit(items.size() - 1).map(x -> x.compile(false)).collect(Collectors.toList()).stream(),
                        Arrays.asList(items.get(items.size() - 1).compile(asExpression)).stream()
                    ).collect(Collectors.toList());
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        emitters.forEach(e -> e.emit(instructions));
                    }
                };
            }
        };
    }

    public static Expression getLocal(int contextDistance, final int ordinal) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            if(contextDistance == 0) {
                                instructions.add(Instructions.loadLocal(ordinal));
                            } else {
                                instructions.add(Instructions.loadContextLocal(contextDistance, ordinal));
                            }
                        }
                    }
                };
            }
        };
    }

    public static Expression messageSend(Expression receiver, String selector, List<Expression> arguments) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter receiverEmitter = receiver.compile(true);
                List<Emitter> argumentEmitters = arguments.stream().map(x -> x.compile(true)).collect(Collectors.toList());
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        receiverEmitter.emit(instructions);
                        argumentEmitters.forEach(ae -> ae.emit(instructions));
                        instructions.add(Instructions.send(selector, arguments.size()));
                        
                        if(!asExpression) {
                            instructions.add(Instructions.pop());
                        }
                    }
                };
            }
        };
    }

    public static Expression setLocal(int contextDistance, int ordinal, Expression valueExpression) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter valueEmitter = valueExpression.compile(true);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        valueEmitter.emit(instructions);
                        if(asExpression) {
                            instructions.add(Instructions.dup());
                        }
                            
                        if(contextDistance == 0) {
                            instructions.add(Instructions.storeLocal(ordinal));
                        } else {
                            instructions.add(Instructions.storeDistant(contextDistance, ordinal));
                        }
                    }
                };
            }
        };
    }
    
    public static Expression block(int arity, int localCount, Expression body) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter bodyEmitter = body.compile(true);
                
                ArrayList<Instruction> bodyInstructions = new ArrayList<>();
                
                bodyEmitter.emit(bodyInstructions);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.loadBlock(arity, localCount, bodyInstructions));
                        }
                    }
                };
            }
        };
    }

    public static Expression ret(Expression valueExpression) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter valueEmitter = valueExpression.compile(true);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        valueEmitter.emit(instructions);
                        instructions.add(Instructions.ret());
                    }
                };
            }
        };
    }

    public static Expression closure(Expression blockExpression) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter blockEmitter = blockExpression.compile(true);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        blockEmitter.emit(instructions);
                        instructions.add(Instructions.closure());
                        
                        if(!asExpression) {
                            instructions.add(Instructions.pop());
                        }
                    }
                };
            }
        };
    }
    
    public static Expression bool(boolean value) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.loadBool(value));
                        }
                    }
                };
            }
        };
    }
    
    public static Expression root() {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.root());
                        }
                    }
                };
            }
        };
    }
    
    public static Expression top() {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.top());
                        }
                    }
                };
            }
        };
    }

    public static Expression cascade(Expression targetExpression, List<Expression> expressions) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter targetEmitter = targetExpression.compile(true);
                List<Emitter> emitters = expressions.stream()
                    .map(e -> e.compile(false))
                    .collect(Collectors.toList());
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        targetEmitter.emit(instructions);
                        emitters.forEach(e -> e.emit(instructions));
                        
                        if(!asExpression) {
                            instructions.add(Instructions.pop());
                        }
                    }
                };
            }
        };
    }

    public static Expression whileTrue(Expression conditionExpression, Expression bodyExpression) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter conditionEmitter = conditionExpression.compile(true);
                Emitter bodyEmitter = bodyExpression.compile(false);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        int startPosition = instructions.size();
                        conditionEmitter.emit(instructions);
                        // Take note of index of break instruction
                        int breakInstructionIndex = instructions.size();
                        // Allocate space for break instruction
                        instructions.add(null); 
                        bodyEmitter.emit(instructions);
                        instructions.add(Instructions.jump(startPosition));
                        int endPosition = instructions.size();
                        // Put break instruction at noted index
                        instructions.set(breakInstructionIndex, Instructions.jumpIfFalse(endPosition));
                        
                        if(asExpression) {
                            instructions.add(Instructions.loadBool(true));
                        }
                    }
                };
            }
        };
    }
    
    public static Expression nil() {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        if(asExpression) {
                            instructions.add(Instructions.loadNil());
                        }
                    }
                };
            }
        };
    }

    public static Expression haltHere(Expression responseExpression) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter responseEmitter = responseExpression.compile(true);
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        responseEmitter.emit(instructions);
                        instructions.add(Instructions.halt());
                        // Before resume, a value is pushed onto the stack
                        if(!asExpression) {
                            instructions.add(Instructions.pop());
                        }
                    }
                };
            }
        };
    }

    public static Expression javaNew(String className, String[] parameterTypeNames, List<Expression> argumentExpressions) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                List<Emitter> argumentEmitters = argumentExpressions.stream()
                        .map(e -> e.compile(true))
                        .collect(Collectors.toList());
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        argumentEmitters.forEach(ae -> ae.emit(instructions));
                        instructions.add(Instructions.javaNew(className, parameterTypeNames));
                        
                        if(!asExpression) {
                            instructions.add(Instructions.pop());
                        }
                    }
                };
            }
        };
    }

    public static Expression javaInvokeInstance(
            String methodName, Expression targetExpression, String className, 
            String[] parameterTypeNames, List<Expression> argumentExpressions) {
        return new Expression() {
            @Override
            public Emitter compile(boolean asExpression) {
                Emitter targetEmitter = targetExpression.compile(true);
                List<Emitter> argumentEmitters = argumentExpressions.stream()
                        .map(e -> e.compile(true))
                        .collect(Collectors.toList());
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        targetEmitter.emit(instructions);
                        argumentEmitters.forEach(ae -> ae.emit(instructions));
                        instructions.add(Instructions.javaInvokeInstance(methodName, className, parameterTypeNames));
                        
                        if(!asExpression) {
                            instructions.add(Instructions.pop());
                        }
                    }
                };
            }
        };
    }
}
