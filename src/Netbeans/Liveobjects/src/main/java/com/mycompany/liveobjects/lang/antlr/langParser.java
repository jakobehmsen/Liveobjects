// Generated from /home/jakob/github/Liveobjects/src/Netbeans/Liveobjects/src/main/java/com/mycompany/liveobjects/lang/antlr/lang.g4 by ANTLR 4.1
package com.mycompany.liveobjects.lang.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class langParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KW_SELF=1, KW_TRUE=2, KW_FALSE=3, KW_THIS_CONTEXT=4, KW_NIL=5, SELF_ASSIGN=6, 
		FRAME_ASSIGN=7, PIPE=8, OPEN_PAR=9, CLOSE_PAR=10, OPEN_SQ=11, CLOSE_SQ=12, 
		OPEN_BRA=13, CLOSE_BRA=14, COLON=15, DOT=16, ASTERISK=17, BIN_OP=18, ID=19, 
		NUMBER=20, STRING=21, WS=22, SINGLE_LINE_COMMENT=23, MULTI_LINE_COMMENT=24;
	public static final String[] tokenNames = {
		"<INVALID>", "'self'", "'true'", "'false'", "'thisContext'", "'nil'", 
		"'='", "':='", "'|'", "'('", "')'", "'['", "']'", "'{'", "'}'", "':'", 
		"'.'", "'*'", "BIN_OP", "ID", "NUMBER", "STRING", "WS", "SINGLE_LINE_COMMENT", 
		"MULTI_LINE_COMMENT"
	};
	public static final int
		RULE_expressions = 0, RULE_expression = 1, RULE_assignment = 2, RULE_behaviorSelector = 3, 
		RULE_kwSelector = 4, RULE_kwSelectorKW = 5, RULE_kwSelectorParam = 6, 
		RULE_unarySelector = 7, RULE_binarySelector = 8, RULE_parentSelector = 9, 
		RULE_behaviorBody = 10, RULE_expression1 = 11, RULE_keyAndArg = 12, RULE_expression2 = 13, 
		RULE_binaryMessage = 14, RULE_expression3 = 15, RULE_unaryMessage = 16, 
		RULE_expression4 = 17, RULE_number = 18, RULE_string = 19, RULE_identifier = 20, 
		RULE_block = 21, RULE_blockParams = 22, RULE_embeddedExpression = 23, 
		RULE_self = 24, RULE_thisContext = 25, RULE_bool = 26, RULE_boolTrue = 27, 
		RULE_boolFalse = 28, RULE_objectLiteral = 29, RULE_objectSlot = 30, RULE_objectSlotUnquoted = 31, 
		RULE_objectSlotQuoted = 32, RULE_nil = 33, RULE_unaryMessageSend = 34;
	public static final String[] ruleNames = {
		"expressions", "expression", "assignment", "behaviorSelector", "kwSelector", 
		"kwSelectorKW", "kwSelectorParam", "unarySelector", "binarySelector", 
		"parentSelector", "behaviorBody", "expression1", "keyAndArg", "expression2", 
		"binaryMessage", "expression3", "unaryMessage", "expression4", "number", 
		"string", "identifier", "block", "blockParams", "embeddedExpression", 
		"self", "thisContext", "bool", "boolTrue", "boolFalse", "objectLiteral", 
		"objectSlot", "objectSlotUnquoted", "objectSlotQuoted", "nil", "unaryMessageSend"
	};

	@Override
	public String getGrammarFileName() { return "lang.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public langParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionsContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionsContext expressions() throws RecognitionException {
		ExpressionsContext _localctx = new ExpressionsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_SELF) | (1L << KW_TRUE) | (1L << KW_FALSE) | (1L << KW_THIS_CONTEXT) | (1L << KW_NIL) | (1L << OPEN_PAR) | (1L << OPEN_SQ) | (1L << OPEN_BRA) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(70); expression();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Expression1Context expression1() {
			return getRuleContext(Expression1Context.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(78);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76); assignment();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77); expression1();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Token op;
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode FRAME_ASSIGN() { return getToken(langParser.FRAME_ASSIGN, 0); }
		public TerminalNode SELF_ASSIGN() { return getToken(langParser.SELF_ASSIGN, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(ID);
			setState(81);
			((AssignmentContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==SELF_ASSIGN || _la==FRAME_ASSIGN) ) {
				((AssignmentContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(82); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BehaviorSelectorContext extends ParserRuleContext {
		public KwSelectorContext kwSelector() {
			return getRuleContext(KwSelectorContext.class,0);
		}
		public UnarySelectorContext unarySelector() {
			return getRuleContext(UnarySelectorContext.class,0);
		}
		public BinarySelectorContext binarySelector() {
			return getRuleContext(BinarySelectorContext.class,0);
		}
		public BehaviorSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_behaviorSelector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBehaviorSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BehaviorSelectorContext behaviorSelector() throws RecognitionException {
		BehaviorSelectorContext _localctx = new BehaviorSelectorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_behaviorSelector);
		try {
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84); kwSelector();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85); unarySelector();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86); binarySelector();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KwSelectorContext extends ParserRuleContext {
		public List<KwSelectorKWContext> kwSelectorKW() {
			return getRuleContexts(KwSelectorKWContext.class);
		}
		public KwSelectorParamContext kwSelectorParam(int i) {
			return getRuleContext(KwSelectorParamContext.class,i);
		}
		public List<KwSelectorParamContext> kwSelectorParam() {
			return getRuleContexts(KwSelectorParamContext.class);
		}
		public KwSelectorKWContext kwSelectorKW(int i) {
			return getRuleContext(KwSelectorKWContext.class,i);
		}
		public KwSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kwSelector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitKwSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KwSelectorContext kwSelector() throws RecognitionException {
		KwSelectorContext _localctx = new KwSelectorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_kwSelector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(89); kwSelectorKW();
				setState(90); kwSelectorParam();
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KwSelectorKWContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode COLON() { return getToken(langParser.COLON, 0); }
		public KwSelectorKWContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kwSelectorKW; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitKwSelectorKW(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KwSelectorKWContext kwSelectorKW() throws RecognitionException {
		KwSelectorKWContext _localctx = new KwSelectorKWContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_kwSelectorKW);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(ID);
			setState(97); match(COLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KwSelectorParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public KwSelectorParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kwSelectorParam; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitKwSelectorParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KwSelectorParamContext kwSelectorParam() throws RecognitionException {
		KwSelectorParamContext _localctx = new KwSelectorParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_kwSelectorParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnarySelectorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public UnarySelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unarySelector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitUnarySelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnarySelectorContext unarySelector() throws RecognitionException {
		UnarySelectorContext _localctx = new UnarySelectorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_unarySelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinarySelectorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode BIN_OP() { return getToken(langParser.BIN_OP, 0); }
		public BinarySelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binarySelector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBinarySelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinarySelectorContext binarySelector() throws RecognitionException {
		BinarySelectorContext _localctx = new BinarySelectorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_binarySelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(BIN_OP);
			setState(104); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParentSelectorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode ASTERISK() { return getToken(langParser.ASTERISK, 0); }
		public ParentSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parentSelector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitParentSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParentSelectorContext parentSelector() throws RecognitionException {
		ParentSelectorContext _localctx = new ParentSelectorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parentSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); match(ASTERISK);
			setState(107); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BehaviorBodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BehaviorBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_behaviorBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBehaviorBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BehaviorBodyContext behaviorBody() throws RecognitionException {
		BehaviorBodyContext _localctx = new BehaviorBodyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_behaviorBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression1Context extends ParserRuleContext {
		public List<KeyAndArgContext> keyAndArg() {
			return getRuleContexts(KeyAndArgContext.class);
		}
		public KeyAndArgContext keyAndArg(int i) {
			return getRuleContext(KeyAndArgContext.class,i);
		}
		public Expression2Context expression2() {
			return getRuleContext(Expression2Context.class,0);
		}
		public Expression1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitExpression1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression1Context expression1() throws RecognitionException {
		Expression1Context _localctx = new Expression1Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression1);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111); expression2();
			setState(115);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(112); keyAndArg();
					}
					} 
				}
				setState(117);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyAndArgContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode COLON() { return getToken(langParser.COLON, 0); }
		public Expression2Context expression2() {
			return getRuleContext(Expression2Context.class,0);
		}
		public KeyAndArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyAndArg; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitKeyAndArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyAndArgContext keyAndArg() throws RecognitionException {
		KeyAndArgContext _localctx = new KeyAndArgContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keyAndArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); match(ID);
			setState(119); match(COLON);
			setState(120); expression2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression2Context extends ParserRuleContext {
		public BinaryMessageContext binaryMessage(int i) {
			return getRuleContext(BinaryMessageContext.class,i);
		}
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public List<BinaryMessageContext> binaryMessage() {
			return getRuleContexts(BinaryMessageContext.class);
		}
		public Expression2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitExpression2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression2Context expression2() throws RecognitionException {
		Expression2Context _localctx = new Expression2Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression2);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122); expression3();
			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(123); binaryMessage();
					}
					} 
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryMessageContext extends ParserRuleContext {
		public TerminalNode BIN_OP() { return getToken(langParser.BIN_OP, 0); }
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public BinaryMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryMessage; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBinaryMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryMessageContext binaryMessage() throws RecognitionException {
		BinaryMessageContext _localctx = new BinaryMessageContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_binaryMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); match(BIN_OP);
			setState(130); expression3();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression3Context extends ParserRuleContext {
		public Expression4Context expression4() {
			return getRuleContext(Expression4Context.class,0);
		}
		public UnaryMessageContext unaryMessage(int i) {
			return getRuleContext(UnaryMessageContext.class,i);
		}
		public List<UnaryMessageContext> unaryMessage() {
			return getRuleContexts(UnaryMessageContext.class);
		}
		public Expression3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitExpression3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression3Context expression3() throws RecognitionException {
		Expression3Context _localctx = new Expression3Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_expression3);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(132); expression4();
			setState(136);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(133); unaryMessage();
					}
					} 
				}
				setState(138);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryMessageContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(langParser.DOT, 0); }
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public UnaryMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryMessage; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitUnaryMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryMessageContext unaryMessage() throws RecognitionException {
		UnaryMessageContext _localctx = new UnaryMessageContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_unaryMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); match(DOT);
			setState(140); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression4Context extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public EmbeddedExpressionContext embeddedExpression() {
			return getRuleContext(EmbeddedExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public ThisContextContext thisContext() {
			return getRuleContext(ThisContextContext.class,0);
		}
		public SelfContext self() {
			return getRuleContext(SelfContext.class,0);
		}
		public NilContext nil() {
			return getRuleContext(NilContext.class,0);
		}
		public Expression4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression4; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitExpression4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression4Context expression4() throws RecognitionException {
		Expression4Context _localctx = new Expression4Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_expression4);
		try {
			setState(152);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(142); number();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(143); string();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(144); identifier();
				}
				break;
			case OPEN_SQ:
				enterOuterAlt(_localctx, 4);
				{
				setState(145); block();
				}
				break;
			case OPEN_PAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(146); embeddedExpression();
				}
				break;
			case KW_SELF:
				enterOuterAlt(_localctx, 6);
				{
				setState(147); self();
				}
				break;
			case KW_THIS_CONTEXT:
				enterOuterAlt(_localctx, 7);
				{
				setState(148); thisContext();
				}
				break;
			case KW_TRUE:
			case KW_FALSE:
				enterOuterAlt(_localctx, 8);
				{
				setState(149); bool();
				}
				break;
			case OPEN_BRA:
				enterOuterAlt(_localctx, 9);
				{
				setState(150); objectLiteral();
				}
				break;
			case KW_NIL:
				enterOuterAlt(_localctx, 10);
				{
				setState(151); nil();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(langParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(langParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public BlockParamsContext blockParams() {
			return getRuleContext(BlockParamsContext.class,0);
		}
		public TerminalNode OPEN_SQ() { return getToken(langParser.OPEN_SQ, 0); }
		public TerminalNode CLOSE_SQ() { return getToken(langParser.CLOSE_SQ, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(OPEN_SQ);
			setState(162);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(161); blockParams();
				}
				break;
			}
			setState(164); expressions();
			setState(165); match(CLOSE_SQ);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockParamsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(langParser.ID); }
		public TerminalNode PIPE() { return getToken(langParser.PIPE, 0); }
		public TerminalNode ID(int i) {
			return getToken(langParser.ID, i);
		}
		public BlockParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockParams; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBlockParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockParamsContext blockParams() throws RecognitionException {
		BlockParamsContext _localctx = new BlockParamsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_blockParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(167); match(ID);
				}
				}
				setState(170); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(172); match(PIPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmbeddedExpressionContext extends ParserRuleContext {
		public TerminalNode OPEN_PAR() { return getToken(langParser.OPEN_PAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PAR() { return getToken(langParser.CLOSE_PAR, 0); }
		public EmbeddedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_embeddedExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitEmbeddedExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmbeddedExpressionContext embeddedExpression() throws RecognitionException {
		EmbeddedExpressionContext _localctx = new EmbeddedExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_embeddedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); match(OPEN_PAR);
			setState(175); expression();
			setState(176); match(CLOSE_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelfContext extends ParserRuleContext {
		public TerminalNode KW_SELF() { return getToken(langParser.KW_SELF, 0); }
		public SelfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_self; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitSelf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelfContext self() throws RecognitionException {
		SelfContext _localctx = new SelfContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_self);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178); match(KW_SELF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThisContextContext extends ParserRuleContext {
		public TerminalNode KW_THIS_CONTEXT() { return getToken(langParser.KW_THIS_CONTEXT, 0); }
		public ThisContextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thisContext; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitThisContext(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThisContextContext thisContext() throws RecognitionException {
		ThisContextContext _localctx = new ThisContextContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_thisContext);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(KW_THIS_CONTEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolContext extends ParserRuleContext {
		public BoolTrueContext boolTrue() {
			return getRuleContext(BoolTrueContext.class,0);
		}
		public BoolFalseContext boolFalse() {
			return getRuleContext(BoolFalseContext.class,0);
		}
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_bool);
		try {
			setState(184);
			switch (_input.LA(1)) {
			case KW_TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(182); boolTrue();
				}
				break;
			case KW_FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(183); boolFalse();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolTrueContext extends ParserRuleContext {
		public TerminalNode KW_TRUE() { return getToken(langParser.KW_TRUE, 0); }
		public BoolTrueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolTrue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBoolTrue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolTrueContext boolTrue() throws RecognitionException {
		BoolTrueContext _localctx = new BoolTrueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_boolTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186); match(KW_TRUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolFalseContext extends ParserRuleContext {
		public TerminalNode KW_FALSE() { return getToken(langParser.KW_FALSE, 0); }
		public BoolFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolFalse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitBoolFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolFalseContext boolFalse() throws RecognitionException {
		BoolFalseContext _localctx = new BoolFalseContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_boolFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); match(KW_FALSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectLiteralContext extends ParserRuleContext {
		public List<ObjectSlotContext> objectSlot() {
			return getRuleContexts(ObjectSlotContext.class);
		}
		public ObjectSlotContext objectSlot(int i) {
			return getRuleContext(ObjectSlotContext.class,i);
		}
		public TerminalNode CLOSE_BRA() { return getToken(langParser.CLOSE_BRA, 0); }
		public TerminalNode OPEN_BRA() { return getToken(langParser.OPEN_BRA, 0); }
		public ObjectLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitObjectLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectLiteralContext objectLiteral() throws RecognitionException {
		ObjectLiteralContext _localctx = new ObjectLiteralContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_objectLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190); match(OPEN_BRA);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASTERISK) | (1L << BIN_OP) | (1L << ID))) != 0)) {
				{
				{
				setState(191); objectSlot();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197); match(CLOSE_BRA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectSlotContext extends ParserRuleContext {
		public ObjectSlotUnquotedContext objectSlotUnquoted() {
			return getRuleContext(ObjectSlotUnquotedContext.class,0);
		}
		public ObjectSlotQuotedContext objectSlotQuoted() {
			return getRuleContext(ObjectSlotQuotedContext.class,0);
		}
		public ObjectSlotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectSlot; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitObjectSlot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectSlotContext objectSlot() throws RecognitionException {
		ObjectSlotContext _localctx = new ObjectSlotContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_objectSlot);
		try {
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(199); objectSlotUnquoted();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(200); objectSlotQuoted();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectSlotUnquotedContext extends ParserRuleContext {
		public Token id;
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode FRAME_ASSIGN() { return getToken(langParser.FRAME_ASSIGN, 0); }
		public Expression1Context expression1() {
			return getRuleContext(Expression1Context.class,0);
		}
		public TerminalNode ASTERISK() { return getToken(langParser.ASTERISK, 0); }
		public ObjectSlotUnquotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectSlotUnquoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitObjectSlotUnquoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectSlotUnquotedContext objectSlotUnquoted() throws RecognitionException {
		ObjectSlotUnquotedContext _localctx = new ObjectSlotUnquotedContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_objectSlotUnquoted);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if (_la==ASTERISK) {
				{
				setState(203); match(ASTERISK);
				}
			}

			setState(206); ((ObjectSlotUnquotedContext)_localctx).id = match(ID);
			setState(207); match(FRAME_ASSIGN);
			setState(208); expression1();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectSlotQuotedContext extends ParserRuleContext {
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode CLOSE_BRA() { return getToken(langParser.CLOSE_BRA, 0); }
		public BehaviorSelectorContext behaviorSelector() {
			return getRuleContext(BehaviorSelectorContext.class,0);
		}
		public TerminalNode OPEN_BRA() { return getToken(langParser.OPEN_BRA, 0); }
		public ObjectSlotQuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectSlotQuoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitObjectSlotQuoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectSlotQuotedContext objectSlotQuoted() throws RecognitionException {
		ObjectSlotQuotedContext _localctx = new ObjectSlotQuotedContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_objectSlotQuoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); behaviorSelector();
			setState(211); match(OPEN_BRA);
			setState(212); expressions();
			setState(213); match(CLOSE_BRA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NilContext extends ParserRuleContext {
		public TerminalNode KW_NIL() { return getToken(langParser.KW_NIL, 0); }
		public NilContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nil; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitNil(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NilContext nil() throws RecognitionException {
		NilContext _localctx = new NilContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_nil);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); match(KW_NIL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryMessageSendContext extends ParserRuleContext {
		public Expression3Context receiver;
		public UnaryMessageContext selector;
		public UnaryMessageContext unaryMessage() {
			return getRuleContext(UnaryMessageContext.class,0);
		}
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public UnaryMessageSendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryMessageSend; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitUnaryMessageSend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryMessageSendContext unaryMessageSend() throws RecognitionException {
		UnaryMessageSendContext _localctx = new UnaryMessageSendContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_unaryMessageSend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217); ((UnaryMessageSendContext)_localctx).receiver = expression3();
			setState(218); ((UnaryMessageSendContext)_localctx).selector = unaryMessage();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\32\u00df\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\7\2J\n\2\f\2\16\2M\13\2\3\3\3\3\5\3Q\n\3\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5Z\n\5\3\6\3\6\3\6\6\6_\n\6\r\6\16\6`\3\7"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\7"+
		"\rt\n\r\f\r\16\rw\13\r\3\16\3\16\3\16\3\16\3\17\3\17\7\17\177\n\17\f\17"+
		"\16\17\u0082\13\17\3\20\3\20\3\20\3\21\3\21\7\21\u0089\n\21\f\21\16\21"+
		"\u008c\13\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\5\23\u009b\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\5\27"+
		"\u00a5\n\27\3\27\3\27\3\27\3\30\6\30\u00ab\n\30\r\30\16\30\u00ac\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\5\34\u00bb\n\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\7\37\u00c3\n\37\f\37\16\37\u00c6\13\37"+
		"\3\37\3\37\3 \3 \5 \u00cc\n \3!\5!\u00cf\n!\3!\3!\3!\3!\3\"\3\"\3\"\3"+
		"\"\3\"\3#\3#\3$\3$\3$\3$\2%\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:<>@BDF\2\3\3\2\b\t\u00d2\2K\3\2\2\2\4P\3\2\2\2\6R"+
		"\3\2\2\2\bY\3\2\2\2\n^\3\2\2\2\fb\3\2\2\2\16e\3\2\2\2\20g\3\2\2\2\22i"+
		"\3\2\2\2\24l\3\2\2\2\26o\3\2\2\2\30q\3\2\2\2\32x\3\2\2\2\34|\3\2\2\2\36"+
		"\u0083\3\2\2\2 \u0086\3\2\2\2\"\u008d\3\2\2\2$\u009a\3\2\2\2&\u009c\3"+
		"\2\2\2(\u009e\3\2\2\2*\u00a0\3\2\2\2,\u00a2\3\2\2\2.\u00aa\3\2\2\2\60"+
		"\u00b0\3\2\2\2\62\u00b4\3\2\2\2\64\u00b6\3\2\2\2\66\u00ba\3\2\2\28\u00bc"+
		"\3\2\2\2:\u00be\3\2\2\2<\u00c0\3\2\2\2>\u00cb\3\2\2\2@\u00ce\3\2\2\2B"+
		"\u00d4\3\2\2\2D\u00d9\3\2\2\2F\u00db\3\2\2\2HJ\5\4\3\2IH\3\2\2\2JM\3\2"+
		"\2\2KI\3\2\2\2KL\3\2\2\2L\3\3\2\2\2MK\3\2\2\2NQ\5\6\4\2OQ\5\30\r\2PN\3"+
		"\2\2\2PO\3\2\2\2Q\5\3\2\2\2RS\7\25\2\2ST\t\2\2\2TU\5\4\3\2U\7\3\2\2\2"+
		"VZ\5\n\6\2WZ\5\20\t\2XZ\5\22\n\2YV\3\2\2\2YW\3\2\2\2YX\3\2\2\2Z\t\3\2"+
		"\2\2[\\\5\f\7\2\\]\5\16\b\2]_\3\2\2\2^[\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a"+
		"\3\2\2\2a\13\3\2\2\2bc\7\25\2\2cd\7\21\2\2d\r\3\2\2\2ef\7\25\2\2f\17\3"+
		"\2\2\2gh\7\25\2\2h\21\3\2\2\2ij\7\24\2\2jk\7\25\2\2k\23\3\2\2\2lm\7\23"+
		"\2\2mn\7\25\2\2n\25\3\2\2\2op\5\4\3\2p\27\3\2\2\2qu\5\34\17\2rt\5\32\16"+
		"\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v\31\3\2\2\2wu\3\2\2\2xy\7\25"+
		"\2\2yz\7\21\2\2z{\5\34\17\2{\33\3\2\2\2|\u0080\5 \21\2}\177\5\36\20\2"+
		"~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\35\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7\24\2\2\u0084\u0085\5 \21"+
		"\2\u0085\37\3\2\2\2\u0086\u008a\5$\23\2\u0087\u0089\5\"\22\2\u0088\u0087"+
		"\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"!\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7\22\2\2\u008e\u008f\7\25\2"+
		"\2\u008f#\3\2\2\2\u0090\u009b\5&\24\2\u0091\u009b\5(\25\2\u0092\u009b"+
		"\5*\26\2\u0093\u009b\5,\27\2\u0094\u009b\5\60\31\2\u0095\u009b\5\62\32"+
		"\2\u0096\u009b\5\64\33\2\u0097\u009b\5\66\34\2\u0098\u009b\5<\37\2\u0099"+
		"\u009b\5D#\2\u009a\u0090\3\2\2\2\u009a\u0091\3\2\2\2\u009a\u0092\3\2\2"+
		"\2\u009a\u0093\3\2\2\2\u009a\u0094\3\2\2\2\u009a\u0095\3\2\2\2\u009a\u0096"+
		"\3\2\2\2\u009a\u0097\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u0099\3\2\2\2\u009b"+
		"%\3\2\2\2\u009c\u009d\7\26\2\2\u009d\'\3\2\2\2\u009e\u009f\7\27\2\2\u009f"+
		")\3\2\2\2\u00a0\u00a1\7\25\2\2\u00a1+\3\2\2\2\u00a2\u00a4\7\r\2\2\u00a3"+
		"\u00a5\5.\30\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00a7\5\2\2\2\u00a7\u00a8\7\16\2\2\u00a8-\3\2\2\2\u00a9\u00ab"+
		"\7\25\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2"+
		"\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\n\2\2\u00af/\3"+
		"\2\2\2\u00b0\u00b1\7\13\2\2\u00b1\u00b2\5\4\3\2\u00b2\u00b3\7\f\2\2\u00b3"+
		"\61\3\2\2\2\u00b4\u00b5\7\3\2\2\u00b5\63\3\2\2\2\u00b6\u00b7\7\6\2\2\u00b7"+
		"\65\3\2\2\2\u00b8\u00bb\58\35\2\u00b9\u00bb\5:\36\2\u00ba\u00b8\3\2\2"+
		"\2\u00ba\u00b9\3\2\2\2\u00bb\67\3\2\2\2\u00bc\u00bd\7\4\2\2\u00bd9\3\2"+
		"\2\2\u00be\u00bf\7\5\2\2\u00bf;\3\2\2\2\u00c0\u00c4\7\17\2\2\u00c1\u00c3"+
		"\5> \2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\7\20"+
		"\2\2\u00c8=\3\2\2\2\u00c9\u00cc\5@!\2\u00ca\u00cc\5B\"\2\u00cb\u00c9\3"+
		"\2\2\2\u00cb\u00ca\3\2\2\2\u00cc?\3\2\2\2\u00cd\u00cf\7\23\2\2\u00ce\u00cd"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\7\25\2\2"+
		"\u00d1\u00d2\7\t\2\2\u00d2\u00d3\5\30\r\2\u00d3A\3\2\2\2\u00d4\u00d5\5"+
		"\b\5\2\u00d5\u00d6\7\17\2\2\u00d6\u00d7\5\2\2\2\u00d7\u00d8\7\20\2\2\u00d8"+
		"C\3\2\2\2\u00d9\u00da\7\7\2\2\u00daE\3\2\2\2\u00db\u00dc\5 \21\2\u00dc"+
		"\u00dd\5\"\22\2\u00ddG\3\2\2\2\20KPY`u\u0080\u008a\u009a\u00a4\u00ac\u00ba"+
		"\u00c4\u00cb\u00ce";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}