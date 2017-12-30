/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.lang;

import com.mycompany.liveobjects.expr.Expression;
import com.mycompany.liveobjects.expr.Expressions;
import com.mycompany.liveobjects.lang.antlr.langBaseVisitor;
import com.mycompany.liveobjects.lang.antlr.langLexer;
import com.mycompany.liveobjects.lang.antlr.langParser;
import com.mycompany.liveobjects.lang.antlr.langParser.ExpressionsContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author jakob
 */
public class Parser {
    public Compiler parse(String src) {
        langLexer lexer = new langLexer(new ANTLRInputStream(src));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        langParser parser = new langParser(tokens);
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
            public Compiler visitSimpleAssignment(langParser.SimpleAssignmentContext ctx) {
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
                        
                        /*compileCtx.declareLocal(id);
                        int ordinal = compileCtx.getLocalOrdinal(id);
                        return Expressions.setLocal(0, ordinal, valueExpression);*/
                    } else {
                        /*int distanceToLocal = compileCtx.distanceToLocal(id);
                        if(distanceToLocal != -1) {
                            //int ordinal = compileCtx.getLocalOrdinal(id);
                            int ordinal = compileCtx.atContext(distanceToLocal).getLocalOrdinal(id);
                            return Expressions.setLocal(distanceToLocal, ordinal, valueExpression);
                        } else {
                            return Expressions.setSlot(Expressions.self(), id, valueExpression);
                        }*/
                        return Expressions.setSlot(Expressions.self(), id, valueExpression);
                    }
                };
            }

            @Override
            public Compiler visitBehaviorAssignment(langParser.BehaviorAssignmentContext ctx) {
                MessageProtocol messageProtocol = ctx.selector.accept(new langBaseVisitor<MessageProtocol>() {
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
                
                Compiler bodyCompiler = parse(ctx.behaviorBody());
                
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
                Compiler expr2Compiler = parse(ctx.expression2());
                
                if(ctx.keyAndArg().size() > 0) {
                    String selector = ctx.keyAndArg().stream().map(x -> x.ID().getText() + ":").collect(Collectors.joining());
                    List<Compiler> argumentCompilers = ctx.keyAndArg().stream().map(x -> parse(x.expression2())).collect(Collectors.toList());
                    
                    return compileCtx -> {
                        Expression receiverExpression = expr2Compiler.compile(compileCtx);
                        List<Expression> argumentExpressions = argumentCompilers.stream().map(x -> x.compile(compileCtx)).collect(Collectors.toList());
                        return Expressions.messageSend(receiverExpression, selector, argumentExpressions);
                    };
                }
                
                return expr2Compiler;
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
                List<String> locals = params;//Stream.concat(Arrays.asList("self").stream(), params.stream()).collect(Collectors.toList());
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
        });
    }
}
