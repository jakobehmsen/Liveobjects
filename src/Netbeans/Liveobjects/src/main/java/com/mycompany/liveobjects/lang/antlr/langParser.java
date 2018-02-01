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
		"':='", "'='", "'|'", "'('", "')'", "'['", "']'", "'{'", "'}'", "':'", 
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
		RULE_objectSlotQuoted = 32, RULE_nil = 33;
	public static final String[] ruleNames = {
		"expressions", "expression", "assignment", "behaviorSelector", "kwSelector", 
		"kwSelectorKW", "kwSelectorParam", "unarySelector", "binarySelector", 
		"parentSelector", "behaviorBody", "expression1", "keyAndArg", "expression2", 
		"binaryMessage", "expression3", "unaryMessage", "expression4", "number", 
		"string", "identifier", "block", "blockParams", "embeddedExpression", 
		"self", "thisContext", "bool", "boolTrue", "boolFalse", "objectLiteral", 
		"objectSlot", "objectSlotUnquoted", "objectSlotQuoted", "nil"
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
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_SELF) | (1L << KW_TRUE) | (1L << KW_FALSE) | (1L << KW_THIS_CONTEXT) | (1L << KW_NIL) | (1L << OPEN_PAR) | (1L << OPEN_SQ) | (1L << OPEN_BRA) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(68); expression();
				}
				}
				setState(73);
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
			setState(76);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74); assignment();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75); expression1();
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
			setState(78); match(ID);
			setState(79);
			((AssignmentContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==SELF_ASSIGN || _la==FRAME_ASSIGN) ) {
				((AssignmentContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(80); expression();
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
			setState(85);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82); kwSelector();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83); unarySelector();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(84); binarySelector();
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
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(87); kwSelectorKW();
				setState(88); kwSelectorParam();
				}
				}
				setState(92); 
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
			setState(94); match(ID);
			setState(95); match(COLON);
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
			setState(97); match(ID);
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
			setState(101); match(BIN_OP);
			setState(102); match(ID);
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
			setState(104); match(ASTERISK);
			setState(105); match(ID);
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
			setState(107); expression();
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
			setState(109); expression2();
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(110); keyAndArg();
					}
					} 
				}
				setState(115);
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
			setState(116); match(ID);
			setState(117); match(COLON);
			setState(118); expression2();
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
			setState(120); expression3();
			setState(124);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(121); binaryMessage();
					}
					} 
				}
				setState(126);
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
			setState(127); match(BIN_OP);
			setState(128); expression3();
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); expression4();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(131); unaryMessage();
				}
				}
				setState(136);
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
			setState(137); match(DOT);
			setState(138); match(ID);
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
			setState(150);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(140); number();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(141); string();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(142); identifier();
				}
				break;
			case OPEN_SQ:
				enterOuterAlt(_localctx, 4);
				{
				setState(143); block();
				}
				break;
			case OPEN_PAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(144); embeddedExpression();
				}
				break;
			case KW_SELF:
				enterOuterAlt(_localctx, 6);
				{
				setState(145); self();
				}
				break;
			case KW_THIS_CONTEXT:
				enterOuterAlt(_localctx, 7);
				{
				setState(146); thisContext();
				}
				break;
			case KW_TRUE:
			case KW_FALSE:
				enterOuterAlt(_localctx, 8);
				{
				setState(147); bool();
				}
				break;
			case OPEN_BRA:
				enterOuterAlt(_localctx, 9);
				{
				setState(148); objectLiteral();
				}
				break;
			case KW_NIL:
				enterOuterAlt(_localctx, 10);
				{
				setState(149); nil();
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
			setState(152); match(NUMBER);
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
			setState(154); match(STRING);
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
			setState(156); match(ID);
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
			setState(158); match(OPEN_SQ);
			setState(160);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(159); blockParams();
				}
				break;
			}
			setState(162); expressions();
			setState(163); match(CLOSE_SQ);
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
			setState(166); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(165); match(ID);
				}
				}
				setState(168); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(170); match(PIPE);
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
			setState(172); match(OPEN_PAR);
			setState(173); expression();
			setState(174); match(CLOSE_PAR);
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
			setState(176); match(KW_SELF);
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
			setState(178); match(KW_THIS_CONTEXT);
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
			setState(182);
			switch (_input.LA(1)) {
			case KW_TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(180); boolTrue();
				}
				break;
			case KW_FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(181); boolFalse();
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
			setState(184); match(KW_TRUE);
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
			setState(186); match(KW_FALSE);
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
			setState(188); match(OPEN_BRA);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASTERISK) | (1L << BIN_OP) | (1L << ID))) != 0)) {
				{
				{
				setState(189); objectSlot();
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(195); match(CLOSE_BRA);
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
			setState(199);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(197); objectSlotUnquoted();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198); objectSlotQuoted();
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
			setState(202);
			_la = _input.LA(1);
			if (_la==ASTERISK) {
				{
				setState(201); match(ASTERISK);
				}
			}

			setState(204); ((ObjectSlotUnquotedContext)_localctx).id = match(ID);
			setState(205); match(FRAME_ASSIGN);
			setState(206); expression1();
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
			setState(208); behaviorSelector();
			setState(209); match(OPEN_BRA);
			setState(210); expressions();
			setState(211); match(CLOSE_BRA);
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
			setState(213); match(KW_NIL);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\32\u00da\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\7\2H\n\2\f\2\16\2K\13\2\3\3\3\3\5\3O\n\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\5\5X\n\5\3\6\3\6\3\6\6\6]\n\6\r\6\16\6^\3\7\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\7\rr\n\r"+
		"\f\r\16\ru\13\r\3\16\3\16\3\16\3\16\3\17\3\17\7\17}\n\17\f\17\16\17\u0080"+
		"\13\17\3\20\3\20\3\20\3\21\3\21\7\21\u0087\n\21\f\21\16\21\u008a\13\21"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u0099\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\5\27\u00a3\n\27\3"+
		"\27\3\27\3\27\3\30\6\30\u00a9\n\30\r\30\16\30\u00aa\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\5\34\u00b9\n\34\3\35\3\35\3\36"+
		"\3\36\3\37\3\37\7\37\u00c1\n\37\f\37\16\37\u00c4\13\37\3\37\3\37\3 \3"+
		" \5 \u00ca\n \3!\5!\u00cd\n!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#"+
		"\2$\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B"+
		"D\2\3\3\2\b\t\u00ce\2I\3\2\2\2\4N\3\2\2\2\6P\3\2\2\2\bW\3\2\2\2\n\\\3"+
		"\2\2\2\f`\3\2\2\2\16c\3\2\2\2\20e\3\2\2\2\22g\3\2\2\2\24j\3\2\2\2\26m"+
		"\3\2\2\2\30o\3\2\2\2\32v\3\2\2\2\34z\3\2\2\2\36\u0081\3\2\2\2 \u0084\3"+
		"\2\2\2\"\u008b\3\2\2\2$\u0098\3\2\2\2&\u009a\3\2\2\2(\u009c\3\2\2\2*\u009e"+
		"\3\2\2\2,\u00a0\3\2\2\2.\u00a8\3\2\2\2\60\u00ae\3\2\2\2\62\u00b2\3\2\2"+
		"\2\64\u00b4\3\2\2\2\66\u00b8\3\2\2\28\u00ba\3\2\2\2:\u00bc\3\2\2\2<\u00be"+
		"\3\2\2\2>\u00c9\3\2\2\2@\u00cc\3\2\2\2B\u00d2\3\2\2\2D\u00d7\3\2\2\2F"+
		"H\5\4\3\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\3\3\2\2\2KI\3\2\2\2"+
		"LO\5\6\4\2MO\5\30\r\2NL\3\2\2\2NM\3\2\2\2O\5\3\2\2\2PQ\7\25\2\2QR\t\2"+
		"\2\2RS\5\4\3\2S\7\3\2\2\2TX\5\n\6\2UX\5\20\t\2VX\5\22\n\2WT\3\2\2\2WU"+
		"\3\2\2\2WV\3\2\2\2X\t\3\2\2\2YZ\5\f\7\2Z[\5\16\b\2[]\3\2\2\2\\Y\3\2\2"+
		"\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\13\3\2\2\2`a\7\25\2\2ab\7\21\2\2b\r"+
		"\3\2\2\2cd\7\25\2\2d\17\3\2\2\2ef\7\25\2\2f\21\3\2\2\2gh\7\24\2\2hi\7"+
		"\25\2\2i\23\3\2\2\2jk\7\23\2\2kl\7\25\2\2l\25\3\2\2\2mn\5\4\3\2n\27\3"+
		"\2\2\2os\5\34\17\2pr\5\32\16\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2"+
		"t\31\3\2\2\2us\3\2\2\2vw\7\25\2\2wx\7\21\2\2xy\5\34\17\2y\33\3\2\2\2z"+
		"~\5 \21\2{}\5\36\20\2|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2"+
		"\177\35\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\24\2\2\u0082\u0083\5 \21"+
		"\2\u0083\37\3\2\2\2\u0084\u0088\5$\23\2\u0085\u0087\5\"\22\2\u0086\u0085"+
		"\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"!\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7\22\2\2\u008c\u008d\7\25\2"+
		"\2\u008d#\3\2\2\2\u008e\u0099\5&\24\2\u008f\u0099\5(\25\2\u0090\u0099"+
		"\5*\26\2\u0091\u0099\5,\27\2\u0092\u0099\5\60\31\2\u0093\u0099\5\62\32"+
		"\2\u0094\u0099\5\64\33\2\u0095\u0099\5\66\34\2\u0096\u0099\5<\37\2\u0097"+
		"\u0099\5D#\2\u0098\u008e\3\2\2\2\u0098\u008f\3\2\2\2\u0098\u0090\3\2\2"+
		"\2\u0098\u0091\3\2\2\2\u0098\u0092\3\2\2\2\u0098\u0093\3\2\2\2\u0098\u0094"+
		"\3\2\2\2\u0098\u0095\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099"+
		"%\3\2\2\2\u009a\u009b\7\26\2\2\u009b\'\3\2\2\2\u009c\u009d\7\27\2\2\u009d"+
		")\3\2\2\2\u009e\u009f\7\25\2\2\u009f+\3\2\2\2\u00a0\u00a2\7\r\2\2\u00a1"+
		"\u00a3\5.\30\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4\u00a5\5\2\2\2\u00a5\u00a6\7\16\2\2\u00a6-\3\2\2\2\u00a7\u00a9"+
		"\7\25\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2"+
		"\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\7\n\2\2\u00ad/\3"+
		"\2\2\2\u00ae\u00af\7\13\2\2\u00af\u00b0\5\4\3\2\u00b0\u00b1\7\f\2\2\u00b1"+
		"\61\3\2\2\2\u00b2\u00b3\7\3\2\2\u00b3\63\3\2\2\2\u00b4\u00b5\7\6\2\2\u00b5"+
		"\65\3\2\2\2\u00b6\u00b9\58\35\2\u00b7\u00b9\5:\36\2\u00b8\u00b6\3\2\2"+
		"\2\u00b8\u00b7\3\2\2\2\u00b9\67\3\2\2\2\u00ba\u00bb\7\4\2\2\u00bb9\3\2"+
		"\2\2\u00bc\u00bd\7\5\2\2\u00bd;\3\2\2\2\u00be\u00c2\7\17\2\2\u00bf\u00c1"+
		"\5> \2\u00c0\u00bf\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7\20"+
		"\2\2\u00c6=\3\2\2\2\u00c7\u00ca\5@!\2\u00c8\u00ca\5B\"\2\u00c9\u00c7\3"+
		"\2\2\2\u00c9\u00c8\3\2\2\2\u00ca?\3\2\2\2\u00cb\u00cd\7\23\2\2\u00cc\u00cb"+
		"\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7\25\2\2"+
		"\u00cf\u00d0\7\t\2\2\u00d0\u00d1\5\30\r\2\u00d1A\3\2\2\2\u00d2\u00d3\5"+
		"\b\5\2\u00d3\u00d4\7\17\2\2\u00d4\u00d5\5\2\2\2\u00d5\u00d6\7\20\2\2\u00d6"+
		"C\3\2\2\2\u00d7\u00d8\7\7\2\2\u00d8E\3\2\2\2\20INW^s~\u0088\u0098\u00a2"+
		"\u00aa\u00b8\u00c2\u00c9\u00cc";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}