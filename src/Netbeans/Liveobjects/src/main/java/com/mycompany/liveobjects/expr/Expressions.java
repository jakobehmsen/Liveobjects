/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.expr;

import com.mycompany.liveobjects.Instruction;
import com.mycompany.liveobjects.Instructions;
import com.mycompany.liveobjects.PrimitiveSelectors;
import com.mycompany.liveobjects.lang.Compiler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author jakob
 */
public class Expressions {
    public static Expression integer(final int i) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        instructions.add(Instructions.loadInteger(i));
                    }
                };
            }
        };
    }

    public static Expression string(final String str) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        instructions.add(Instructions.loadString(str));
                    }
                };
            }
        };
    }
    
    public static Expression self() {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        instructions.add(Instructions.loadLocal(0));
                    }
                };
            }
        };
    }
    
    public static Expression thisContext() {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        instructions.add(Instructions.loadContext());
                    }
                };
            }
        };
    }
    
    public static Expression setSlot(final Expression target, final String symbol, final Expression value) {
        return messageSend(target, PrimitiveSelectors.SET_SLOT_SELECTOR, Arrays.asList(Expressions.string(symbol), value));
    }
    
    public static Expression getSlot(final Expression target, final String symbol) {
        return messageSend(target, PrimitiveSelectors.GET_SLOT_SELECTOR, Arrays.asList(Expressions.string(symbol)));
    }

    public static Expression sequence(List<Expression> items) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                List<Emitter> emitters = 
                    Stream.concat(
                        items.stream().limit(items.size() - 1).map(x -> x.compile(ctx, false)),
                        Arrays.asList(items.get(items.size() - 1).compile(ctx, asExpression)).stream()
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

    public static Expression program(Expression code) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                Emitter codeEmitter = code.compile(ctx, true);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        codeEmitter.emit(instructions);
                        instructions.add(Instructions.finish());
                    }
                };
            }
        };
    }

    public static Expression getLocal(final int ordinal) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        instructions.add(Instructions.loadLocal(ordinal));
                    }
                };
            }
        };
    }

    public static Expression messageSend(Expression receiver, String selector, List<Expression> arguments) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                Emitter receiverEmitter = receiver.compile(ctx, true);
                List<Emitter> argumentEmitters = arguments.stream().map(x -> x.compile(ctx, true)).collect(Collectors.toList());
                
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

    public static Expression setLocal(int ordinal, Expression valueExpression) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                Emitter valueEmitter = valueExpression.compile(ctx, true);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        valueEmitter.emit(instructions);
                        if(asExpression) {
                            instructions.add(Instructions.dup());
                        }
                        instructions.add(Instructions.storeLocal(ordinal));
                    }
                };
            }
        };
    }
    
    public static Expression block(int arity, int localCount, Expression body) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                Emitter bodyEmitter = body.compile(ctx, true);
                
                ArrayList<Instruction> bodyInstructions = new ArrayList<>();
                
                bodyEmitter.emit(bodyInstructions);
                
                return new Emitter() {
                    @Override
                    public void emit(List<Instruction> instructions) {
                        instructions.add(Instructions.loadBlock(arity, localCount, bodyInstructions));
                    }
                };
            }
        };
    }

    public static Expression ret(Expression valueExpression) {
        return new Expression() {
            @Override
            public Emitter compile(ExpressionCompileContext ctx, boolean asExpression) {
                Emitter valueEmitter = valueExpression.compile(ctx, true);
                
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
}
