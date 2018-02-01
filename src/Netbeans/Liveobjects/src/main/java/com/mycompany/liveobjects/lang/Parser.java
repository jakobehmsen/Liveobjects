package com.mycompany.liveobjects.lang;

import com.mycompany.liveobjects.expr.Expression;
import com.mycompany.liveobjects.expr.Expressions;
import com.mycompany.liveobjects.lang.antlr.langBaseVisitor;
import com.mycompany.liveobjects.lang.antlr.langLexer;
import com.mycompany.liveobjects.lang.antlr.langParser;
import com.mycompany.liveobjects.lang.antlr.langParser.ExpressionsContext;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;

public class Parser {
    private ErrorHandler errorHandler;
    
    public Parser() {
        setErrorHandler(new ErrorHandler() {
            @Override
            public void syntaxError(int line, int column, String message) {
                
            }
        });
    }

    public final Parser setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }
    
    public Compiler parse(String src) {
        langLexer lexer = new langLexer(new ANTLRInputStream(src));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        langParser parser = new langParser(tokens);
        
        parser.addErrorListener(new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int i, int i1, String string, RecognitionException re) {
                errorHandler.syntaxError(i, i1, string);
            }

            @Override
            public void reportAmbiguity(org.antlr.v4.runtime.Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
                
            }

            @Override
            public void reportAttemptingFullContext(org.antlr.v4.runtime.Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
                
            }

            @Override
            public void reportContextSensitivity(org.antlr.v4.runtime.Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
                
            }
        });
        
        ExpressionsContext expressionsCtx = parser.expressions();
        
        Compiler expressionCompiler = parse(expressionsCtx);
        
        return compileCtx -> Expressions.ret(expressionCompiler.compile(compileCtx));
    }

    private Compiler parse(ParseTree parseCtx) {
        return parseCtx.accept(new langBaseVisitor<Compiler>() {
            @Override
            public Compiler visitExpressions(ExpressionsContext ctx) {
                List<Compiler> expressionCompilers = ctx.expression().stream().map(x -> parse(x)).collect(Collectors.toList());
                
                return compileCtx -> Expressions.sequence(expressionCompilers.stream().map(x -> x.compile(compileCtx)).collect(Collectors.toList()));
            }
            
            @Override
            public Compiler visitAssignment(langParser.AssignmentContext ctx) {
                String id = ctx.ID().getText();
                String operator = ctx.op.getText();
                Compiler valueCompiler = parse(ctx.expression());
                
                return compileCtx -> {
                    Expression valueExpression = valueCompiler.compile(compileCtx);
                    if(operator.equals("=")) {
                        int distanceToLocal = compileCtx.distanceToLocal(id);
                        if(distanceToLocal != -1) {
                            int ordinal = compileCtx.atContext(distanceToLocal).getLocalOrdinal(id);
                            return Expressions.setLocal(distanceToLocal, ordinal, valueExpression);
                        } else {
                            compileCtx.declareLocal(id);
                            int ordinal = compileCtx.getLocalOrdinal(id);
                            return Expressions.setLocal(0, ordinal, valueExpression);
                        }
                    } else {
                        return Expressions.setSlot(Expressions.self(), id, valueExpression);
                    }
                };
            }
            
            private MessageProtocol parseMessageProtocol(langParser.BehaviorSelectorContext ctx) {
                return ctx.accept(new langBaseVisitor<MessageProtocol>() {
                    @Override
                    public MessageProtocol visitKwSelector(langParser.KwSelectorContext ctx) {
                        String selector = ctx.kwSelectorKW().stream().map(kw -> kw.getText()).collect(Collectors.joining());
                        List<String> parameters = ctx.kwSelectorParam().stream().map(p -> p.getText()).collect(Collectors.toList());
                        
                        return new MessageProtocol() {
                            @Override
                            public String getSelector() {
                                return selector;
                            }

                            @Override
                            public List<String> getParameters() {
                                return parameters;
                            }
                        };
                    }

                    @Override
                    public MessageProtocol visitUnarySelector(langParser.UnarySelectorContext ctx) {
                        String selector = ctx.ID().getText();
                        
                        return new MessageProtocol() {
                            @Override
                            public String getSelector() {
                                return selector;
                            }

                            @Override
                            public List<String> getParameters() {
                                return Collections.emptyList();
                            }
                        };
                    }

                    @Override
                    public MessageProtocol visitBinarySelector(langParser.BinarySelectorContext ctx) {
                        String selector = ctx.BIN_OP().getText();
                        List<String> parameters = Arrays.asList(ctx.ID().getText());
                        
                        return new MessageProtocol() {
                            @Override
                            public String getSelector() {
                                return selector;
                            }

                            @Override
                            public List<String> getParameters() {
                                return parameters;
                            }
                        };
                    }
                });
            }
            
            private Compiler parseBehavior(ParseTree behaviorBodyCtx, MessageProtocol messageProtocol) {
                Compiler bodyCompiler = parse(behaviorBodyCtx);
                
                return compileCtx -> {
                    List<String> locals = Stream.concat(Arrays.asList("self").stream(), messageProtocol.getParameters().stream()).collect(Collectors.toList());
                    CompileContext behaviorCompilerCtx = compileCtx.newForBlock(locals, false);
                    Expression bodyExpression = Expressions.ret(bodyCompiler.compile(behaviorCompilerCtx));
                    Expression blockExpression = Expressions.block(messageProtocol.getParameters().size(), behaviorCompilerCtx.localCount() - messageProtocol.getParameters().size(), bodyExpression);
                    return Expressions.setSlot(Expressions.self(), messageProtocol.getSelector(), blockExpression);
                };
            }

            @Override
            public Compiler visitExpression1(langParser.Expression1Context ctx) {
                if(ctx.keyAndArg().size() > 0) {
                    String selector = ctx.keyAndArg().stream().map(x -> x.ID().getText() + ":").collect(Collectors.joining());
                    
                    List<ParseTree> argumentCtxs = ctx.keyAndArg().stream().map(x -> x.expression2()).collect(Collectors.toList());
                    Compiler replacement = replaceMessageSend(ctx.expression2(), selector, argumentCtxs);
                    
                    if(replacement != null) {
                        return replacement;
                    }
                    
                    Compiler expr2Compiler = parse(ctx.expression2());
                    List<Compiler> argumentCompilers = ctx.keyAndArg().stream().map(x -> parse(x.expression2())).collect(Collectors.toList());
                    
                    return compileCtx -> {
                        Expression receiverExpression = expr2Compiler.compile(compileCtx);
                        List<Expression> argumentExpressions = argumentCompilers.stream().map(x -> x.compile(compileCtx)).collect(Collectors.toList());
                        return Expressions.messageSend(receiverExpression, selector, argumentExpressions);
                    };
                }
                
                return parse(ctx.expression2());
            }
            
            private Compiler replaceMessageSend(ParseTree targetCtx, String selector, List<ParseTree> argumentCtxs) {
                switch(selector) {
                    case "extend:":
                        return replaceExtend(targetCtx, selector, argumentCtxs);
                    case "whileTrue:":
                        return replaceWhileTrue(targetCtx, selector, argumentCtxs);
                }
                
                return null;
            }

            private Compiler replaceExtend(ParseTree targetCtx, String selector, List<ParseTree> argumentCtxs) {
                Compiler targetCompiler = parse(targetCtx);
                
                langParser.ObjectLiteralContext objectLiteralCtx = argumentCtxs.get(0).accept(new langBaseVisitor<langParser.ObjectLiteralContext>() {
                    @Override
                    public langParser.ObjectLiteralContext visitObjectLiteral(langParser.ObjectLiteralContext ctx) {
                        return ctx;
                    }
                });
                
                Map<String, Compiler> slotCompilers = parseObjectLiteralSlotCompilers(objectLiteralCtx);
                
                return extendCompiler(targetCompiler, slotCompilers);
            }

            private Compiler replaceWhileTrue(ParseTree targetCtx, String selector, List<ParseTree> argumentCtxs) {
                ParseTree conditionCtx = targetCtx.accept(new langBaseVisitor<langParser.BlockContext>() {
                    @Override
                    public langParser.BlockContext visitBlock(langParser.BlockContext ctx) {
                        return ctx;
                    }
                }).expressions();
                
                ParseTree bodyCtx = argumentCtxs.get(0).accept(new langBaseVisitor<langParser.BlockContext>() {
                    @Override
                    public langParser.BlockContext visitBlock(langParser.BlockContext ctx) {
                        return ctx;
                    }
                }).expressions();
                
                Compiler conditionCompiler = parse(conditionCtx);
                Compiler bodyCompiler = parse(bodyCtx);
                
                return compileCtx -> {
                    Expression conditionExpression = conditionCompiler.compile(compileCtx);
                    Expression bodyExpression = bodyCompiler.compile(compileCtx);
                    
                    return Expressions.whileTrue(conditionExpression, bodyExpression);
                };
            }

            @Override
            public Compiler visitExpression2(langParser.Expression2Context ctx) {
                Compiler expr3Compiler = parse(ctx.expression3());
                
                Compiler exprCompiler = expr3Compiler;
                    
                for(int i = 0; i < ctx.binaryMessage().size(); i++) {
                    final String selector = ctx.binaryMessage(i).BIN_OP().getText();
                    final Compiler operandCompiler = parse(ctx.binaryMessage(i).expression3());
                    final Compiler receiverCompiler = exprCompiler;
                    exprCompiler = compileCtx -> 
                        Expressions.messageSend(receiverCompiler.compile(compileCtx), selector, Arrays.asList(operandCompiler.compile(compileCtx)));
                }
                
                return exprCompiler;
            }

            @Override
            public Compiler visitExpression3(langParser.Expression3Context ctx) {
                Compiler expr4Compiler = parse(ctx.expression4());
                
                Compiler exprCompiler = expr4Compiler;
                    
                for(int i = 0; i < ctx.unaryMessage().size(); i++) {
                    final String selector = ctx.unaryMessage(i).ID().getText();
                    final Compiler receiverCompiler = exprCompiler;
                    exprCompiler = compileCtx -> 
                        Expressions.messageSend(receiverCompiler.compile(compileCtx), selector, Arrays.asList());
                }
                
                return exprCompiler;
            }

            @Override
            public Compiler visitIdentifier(langParser.IdentifierContext ctx) {
                String id = ctx.ID().getText();
                
                return compileCtx -> {
                    int distanceToLocal = compileCtx.distanceToLocal(id);
                    if(distanceToLocal != -1) {
                        int ordinal = compileCtx.atContext(distanceToLocal).getLocalOrdinal(id);
                        return Expressions.getLocal(distanceToLocal, ordinal);
                    } else {
                        return Expressions.getSlot(Expressions.self(), id);
                    }
                };
            }

            @Override
            public Compiler visitNumber(langParser.NumberContext ctx) {
                int i = Integer.parseInt(ctx.getText());
                
                return compileCtx -> Expressions.integer(i);
            }

            @Override
            public Compiler visitString(langParser.StringContext ctx) {
                String str = ctx.getText()
                    .substring(1, ctx.getText().length() - 1);
                
                return compileCtx -> Expressions.string(str);
            }

            @Override
            public Compiler visitBlock(langParser.BlockContext ctx) {
                Compiler bodyCompiler = parse(ctx.expressions());
                List<String> params = ctx.blockParams() != null ? ctx.blockParams().ID().stream().map(x -> x.getText()).collect(Collectors.toList()) : Arrays.asList();
                List<String> locals = Stream.concat(Arrays.asList("self").stream(), params.stream()).collect(Collectors.toList());
                return compileCtx -> {
                    CompileContext bodyCompileContext = compileCtx.newForBlock(locals, true);
                    Expression bodyExpression = bodyCompiler.compile(bodyCompileContext);
                    bodyExpression = Expressions.ret(bodyExpression);
                    Expression blockExpression = Expressions.block(params.size(), bodyCompileContext.localCount() - params.size(), bodyExpression);
                    return Expressions.closure(blockExpression);
                };
            }

            @Override
            public Compiler visitEmbeddedExpression(langParser.EmbeddedExpressionContext ctx) {
                return parse(ctx.expression());
            }

            @Override
            public Compiler visitSelf(langParser.SelfContext ctx) {
                return compileCtx -> {
                    String selfId = "self";
                    int distanceToLocal = compileCtx.distanceToLocal(selfId);
                    if(distanceToLocal != -1) {
                        int ordinal = compileCtx.atContext(distanceToLocal).getLocalOrdinal(selfId);
                        return Expressions.getLocal(distanceToLocal, ordinal);
                    } else {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override
            public Compiler visitThisContext(langParser.ThisContextContext ctx) {
                return compileCtx -> Expressions.thisContext();
            }

            @Override
            public Compiler visitBoolTrue(langParser.BoolTrueContext ctx) {
                return compileCtx -> Expressions.bool(true);
            }

            @Override
            public Compiler visitBoolFalse(langParser.BoolFalseContext ctx) {
                return compileCtx -> Expressions.bool(false);
            }

            @Override
            public Compiler visitObjectLiteral(langParser.ObjectLiteralContext ctx) {
                Map<String, Compiler> slotCompilers = parseObjectLiteralSlotCompilers(ctx);
                
                return extendCompiler(
                    compileContext -> Expressions.messageSend(Expressions.root(), "clone", Arrays.asList()),
                    slotCompilers
                );
            }
            
            private Compiler extendCompiler(Compiler targetCompiler, Map<String, Compiler> slotCompilers) {
                return compileCtx -> {
                    Expression target = targetCompiler.compile(compileCtx);
                    List<Expression> setSlotExpressions = slotCompilers.entrySet().stream()
                        .map(x -> Expressions.setSlot(Expressions.top(), x.getKey(), x.getValue().compile(compileCtx)))
                        .collect(Collectors.toList());
                    return Expressions.cascade(target, setSlotExpressions);
                };
            }            
            
            private Map<String, Compiler> parseObjectLiteralSlotCompilers(langParser.ObjectLiteralContext ctx) {
                Hashtable<String, Compiler> slotCompilers  = new Hashtable<>();
                        
                ctx.objectSlot().forEach(objectSlot -> {
                    MessageProtocol messageProtocol = objectSlot.accept(new langBaseVisitor<MessageProtocol>() {
                        @Override
                        public MessageProtocol visitObjectSlotUnquoted(langParser.ObjectSlotUnquotedContext objectSlotUnquotedCtx) {
                            boolean isParent = objectSlotUnquotedCtx.ASTERISK() != null;
                            String selector = (isParent ? "*" : "") + objectSlotUnquotedCtx.id.getText();
                            
                            return new MessageProtocol() {
                                @Override
                                public String getSelector() {
                                    return selector;
                                }

                                @Override
                                public List<String> getParameters() {
                                    return Arrays.asList();
                                }
                            };
                        }
                        
                        @Override
                        public MessageProtocol visitObjectSlotQuoted(langParser.ObjectSlotQuotedContext objectSlotQuotedCtx) {
                            return parseMessageProtocol(objectSlotQuotedCtx.behaviorSelector());
                        }
                    });
                    
                    Compiler slotValueCompiler = objectSlot.accept(new langBaseVisitor<Compiler>() {
                        @Override
                        public Compiler visitObjectSlotUnquoted(langParser.ObjectSlotUnquotedContext objectSlotUnquotedCtx) {
                            Compiler compiler = parse(objectSlotUnquotedCtx.expression1());
                            return compiler;
                        }
                        
                        @Override
                        public Compiler visitObjectSlotQuoted(langParser.ObjectSlotQuotedContext objectSlotQuotedCtx) {
                            return parseBehavior(objectSlotQuotedCtx.expressions(), messageProtocol);
                        }
                    });
                    
                    slotCompilers.put(messageProtocol.getSelector(), slotValueCompiler);
                });
                
                return slotCompilers;
            }

            @Override
            public Compiler visitNil(langParser.NilContext ctx) {
                return compileCtx -> Expressions.nil();
            }
        });
    }
}
