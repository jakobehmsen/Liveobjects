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
	 * Visit a parse tree produced by {@link langParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(@NotNull langParser.StringContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull langParser.AssignmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expression4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression4(@NotNull langParser.Expression4Context ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(@NotNull langParser.Expression2Context ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#unaryMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMessage(@NotNull langParser.UnaryMessageContext ctx);

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
	 * Visit a parse tree produced by {@link langParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull langParser.NumberContext ctx);

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
	 * Visit a parse tree produced by {@link langParser#self}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelf(@NotNull langParser.SelfContext ctx);

	/**
	 * Visit a parse tree produced by {@link langParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull langParser.BlockContext ctx);
}