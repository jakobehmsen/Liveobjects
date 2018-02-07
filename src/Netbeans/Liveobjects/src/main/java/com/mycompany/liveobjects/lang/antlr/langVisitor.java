// Generated from /home/jakob/github/Liveobjects/src/Netbeans/Liveobjects/src/main/java/com/mycompany/liveobjects/lang/antlr/lang.g4 by ANTLR 4.1
package com.mycompany.liveobjects.lang.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link langParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface langVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link langParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(@NotNull langParser.StringContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(@NotNull langParser.BoolContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#objectSlotUnquoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectSlotUnquoted(@NotNull langParser.ObjectSlotUnquotedContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#kwSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKwSelector(@NotNull langParser.KwSelectorContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#objectSlot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectSlot(@NotNull langParser.ObjectSlotContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#unaryMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMessage(@NotNull langParser.UnaryMessageContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#binarySelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinarySelector(@NotNull langParser.BinarySelectorContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#nil}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNil(@NotNull langParser.NilContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull langParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#boolTrue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolTrue(@NotNull langParser.BoolTrueContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#keyAndArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyAndArg(@NotNull langParser.KeyAndArgContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#embeddedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmbeddedExpression(@NotNull langParser.EmbeddedExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#behaviorSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBehaviorSelector(@NotNull langParser.BehaviorSelectorContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull langParser.BlockContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#parentSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentSelector(@NotNull langParser.ParentSelectorContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#behaviorBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBehaviorBody(@NotNull langParser.BehaviorBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#blockParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockParams(@NotNull langParser.BlockParamsContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(@NotNull langParser.IdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull langParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#objectLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectLiteral(@NotNull langParser.ObjectLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#unarySelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnarySelector(@NotNull langParser.UnarySelectorContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#boolFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolFalse(@NotNull langParser.BoolFalseContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull langParser.AssignmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#kwSelectorParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKwSelectorParam(@NotNull langParser.KwSelectorParamContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expression4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression4(@NotNull langParser.Expression4Context ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#unaryMessageSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMessageSend(@NotNull langParser.UnaryMessageSendContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(@NotNull langParser.Expression2Context ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(@NotNull langParser.ExpressionsContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expression3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression3(@NotNull langParser.Expression3Context ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#kwSelectorKW}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKwSelectorKW(@NotNull langParser.KwSelectorKWContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#thisContext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisContext(@NotNull langParser.ThisContextContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expression1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression1(@NotNull langParser.Expression1Context ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#binaryMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryMessage(@NotNull langParser.BinaryMessageContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#self}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelf(@NotNull langParser.SelfContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#objectSlotQuoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectSlotQuoted(@NotNull langParser.ObjectSlotQuotedContext ctx);
}