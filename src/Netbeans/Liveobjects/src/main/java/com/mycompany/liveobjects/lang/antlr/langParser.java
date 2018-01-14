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
		KW_SELF=1, KW_TRUE=2, KW_FALSE=3, KW_THIS_CONTEXT=4, SELF_ASSIGN=5, FRAME_ASSIGN=6, 
		PIPE=7, OPEN_PAR=8, CLOSE_PAR=9, OPEN_SQ=10, CLOSE_SQ=11, OPEN_BRA=12, 
		CLOSE_BRA=13, COLON=14, DOT=15, BIN_OP=16, ID=17, NUMBER=18, STRING=19, 
		WS=20, SINGLE_LINE_COMMENT=21, MULTI_LINE_COMMENT=22;
	public static final String[] tokenNames = {
		"<INVALID>", "'self'", "'true'", "'false'", "'thisContext'", "':='", "'='", 
		"'|'", "'('", "')'", "'['", "']'", "'{'", "'}'", "':'", "'.'", "BIN_OP", 
		"ID", "NUMBER", "STRING", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
	};
	public static final int
		RULE_expressions = 0, RULE_expression = 1, RULE_assignment = 2, RULE_behaviorSelector = 3, 
		RULE_kwSelector = 4, RULE_kwSelectorKW = 5, RULE_kwSelectorParam = 6, 
		RULE_unarySelector = 7, RULE_binarySelector = 8, RULE_behaviorBody = 9, 
		RULE_expression1 = 10, RULE_keyAndArg = 11, RULE_expression2 = 12, RULE_binaryMessage = 13, 
		RULE_expression3 = 14, RULE_unaryMessage = 15, RULE_expression4 = 16, 
		RULE_number = 17, RULE_string = 18, RULE_identifier = 19, RULE_block = 20, 
		RULE_blockParams = 21, RULE_embeddedExpression = 22, RULE_self = 23, RULE_thisContext = 24, 
		RULE_bool = 25, RULE_boolTrue = 26, RULE_boolFalse = 27, RULE_objectLiteral = 28, 
		RULE_objectSlot = 29, RULE_objectSlotValue = 30, RULE_objectSlotUnquotedValue = 31, 
		RULE_objectSlotQuotedValue = 32;
	public static final String[] ruleNames = {
		"expressions", "expression", "assignment", "behaviorSelector", "kwSelector", 
		"kwSelectorKW", "kwSelectorParam", "unarySelector", "binarySelector", 
		"behaviorBody", "expression1", "keyAndArg", "expression2", "binaryMessage", 
		"expression3", "unaryMessage", "expression4", "number", "string", "identifier", 
		"block", "blockParams", "embeddedExpression", "self", "thisContext", "bool", 
		"boolTrue", "boolFalse", "objectLiteral", "objectSlot", "objectSlotValue", 
		"objectSlotUnquotedValue", "objectSlotQuotedValue"
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
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_SELF) | (1L << KW_TRUE) | (1L << KW_FALSE) | (1L << KW_THIS_CONTEXT) | (1L << OPEN_PAR) | (1L << OPEN_SQ) | (1L << OPEN_BRA) | (1L << ID) | (1L << NUMBER) | (1L << STRING))) != 0)) {
				{
				{
				setState(66); expression();
				}
				}
				setState(71);
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
			setState(74);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72); assignment();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73); expression1();
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
			setState(76); match(ID);
			setState(77);
			((AssignmentContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==SELF_ASSIGN || _la==FRAME_ASSIGN) ) {
				((AssignmentContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(78); expression();
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
			setState(83);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80); kwSelector();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81); unarySelector();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82); binarySelector();
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
			setState(88); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(85); kwSelectorKW();
				setState(86); kwSelectorParam();
				}
				}
				setState(90); 
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
			setState(92); match(ID);
			setState(93); match(COLON);
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
			setState(95); match(ID);
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
			setState(99); match(BIN_OP);
			setState(100); match(ID);
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
		enterRule(_localctx, 18, RULE_behaviorBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); expression();
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
		enterRule(_localctx, 20, RULE_expression1);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(104); expression2();
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(105); keyAndArg();
					}
					} 
				}
				setState(110);
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
		enterRule(_localctx, 22, RULE_keyAndArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(ID);
			setState(112); match(COLON);
			setState(113); expression2();
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
		enterRule(_localctx, 24, RULE_expression2);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(115); expression3();
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(116); binaryMessage();
					}
					} 
				}
				setState(121);
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
		enterRule(_localctx, 26, RULE_binaryMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); match(BIN_OP);
			setState(123); expression3();
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
		enterRule(_localctx, 28, RULE_expression3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); expression4();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(126); unaryMessage();
				}
				}
				setState(131);
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
		enterRule(_localctx, 30, RULE_unaryMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); match(DOT);
			setState(133); match(ID);
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
		enterRule(_localctx, 32, RULE_expression4);
		try {
			setState(144);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(135); number();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(136); string();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(137); identifier();
				}
				break;
			case OPEN_SQ:
				enterOuterAlt(_localctx, 4);
				{
				setState(138); block();
				}
				break;
			case OPEN_PAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(139); embeddedExpression();
				}
				break;
			case KW_SELF:
				enterOuterAlt(_localctx, 6);
				{
				setState(140); self();
				}
				break;
			case KW_THIS_CONTEXT:
				enterOuterAlt(_localctx, 7);
				{
				setState(141); thisContext();
				}
				break;
			case KW_TRUE:
			case KW_FALSE:
				enterOuterAlt(_localctx, 8);
				{
				setState(142); bool();
				}
				break;
			case OPEN_BRA:
				enterOuterAlt(_localctx, 9);
				{
				setState(143); objectLiteral();
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
		enterRule(_localctx, 34, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146); match(NUMBER);
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
		enterRule(_localctx, 36, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); match(STRING);
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
		enterRule(_localctx, 38, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150); match(ID);
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
		enterRule(_localctx, 40, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152); match(OPEN_SQ);
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(153); blockParams();
				}
				break;
			}
			setState(156); expressions();
			setState(157); match(CLOSE_SQ);
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
		enterRule(_localctx, 42, RULE_blockParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(159); match(ID);
				}
				}
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(164); match(PIPE);
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
		enterRule(_localctx, 44, RULE_embeddedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); match(OPEN_PAR);
			setState(167); expression();
			setState(168); match(CLOSE_PAR);
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
		enterRule(_localctx, 46, RULE_self);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170); match(KW_SELF);
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
		enterRule(_localctx, 48, RULE_thisContext);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172); match(KW_THIS_CONTEXT);
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
		enterRule(_localctx, 50, RULE_bool);
		try {
			setState(176);
			switch (_input.LA(1)) {
			case KW_TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(174); boolTrue();
				}
				break;
			case KW_FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(175); boolFalse();
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
		enterRule(_localctx, 52, RULE_boolTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178); match(KW_TRUE);
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
		enterRule(_localctx, 54, RULE_boolFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(KW_FALSE);
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
		enterRule(_localctx, 56, RULE_objectLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); match(OPEN_BRA);
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIN_OP || _la==ID) {
				{
				{
				setState(183); objectSlot();
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(189); match(CLOSE_BRA);
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
		public ObjectSlotValueContext objectSlotValue() {
			return getRuleContext(ObjectSlotValueContext.class,0);
		}
		public BehaviorSelectorContext behaviorSelector() {
			return getRuleContext(BehaviorSelectorContext.class,0);
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
		enterRule(_localctx, 58, RULE_objectSlot);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191); behaviorSelector();
			setState(192); objectSlotValue();
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

	public static class ObjectSlotValueContext extends ParserRuleContext {
		public ObjectSlotQuotedValueContext objectSlotQuotedValue() {
			return getRuleContext(ObjectSlotQuotedValueContext.class,0);
		}
		public ObjectSlotUnquotedValueContext objectSlotUnquotedValue() {
			return getRuleContext(ObjectSlotUnquotedValueContext.class,0);
		}
		public ObjectSlotValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectSlotValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitObjectSlotValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectSlotValueContext objectSlotValue() throws RecognitionException {
		ObjectSlotValueContext _localctx = new ObjectSlotValueContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_objectSlotValue);
		try {
			setState(196);
			switch (_input.LA(1)) {
			case FRAME_ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(194); objectSlotUnquotedValue();
				}
				break;
			case OPEN_BRA:
				enterOuterAlt(_localctx, 2);
				{
				setState(195); objectSlotQuotedValue();
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

	public static class ObjectSlotUnquotedValueContext extends ParserRuleContext {
		public TerminalNode FRAME_ASSIGN() { return getToken(langParser.FRAME_ASSIGN, 0); }
		public Expression1Context expression1() {
			return getRuleContext(Expression1Context.class,0);
		}
		public ObjectSlotUnquotedValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectSlotUnquotedValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitObjectSlotUnquotedValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectSlotUnquotedValueContext objectSlotUnquotedValue() throws RecognitionException {
		ObjectSlotUnquotedValueContext _localctx = new ObjectSlotUnquotedValueContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_objectSlotUnquotedValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198); match(FRAME_ASSIGN);
			setState(199); expression1();
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

	public static class ObjectSlotQuotedValueContext extends ParserRuleContext {
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode CLOSE_BRA() { return getToken(langParser.CLOSE_BRA, 0); }
		public TerminalNode OPEN_BRA() { return getToken(langParser.OPEN_BRA, 0); }
		public ObjectSlotQuotedValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectSlotQuotedValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof langVisitor ) return ((langVisitor<? extends T>)visitor).visitObjectSlotQuotedValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectSlotQuotedValueContext objectSlotQuotedValue() throws RecognitionException {
		ObjectSlotQuotedValueContext _localctx = new ObjectSlotQuotedValueContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_objectSlotQuotedValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201); match(OPEN_BRA);
			setState(202); expressions();
			setState(203); match(CLOSE_BRA);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\30\u00d0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\7\2F\n\2\f\2\16\2I\13\2\3\3\3\3\5\3M\n\3\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\5\5V\n\5\3\6\3\6\3\6\6\6[\n\6\r\6\16\6\\\3\7\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\7\fm\n\f\f\f\16\fp\13\f\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\7\16x\n\16\f\16\16\16{\13\16\3\17\3\17\3\17\3"+
		"\20\3\20\7\20\u0082\n\20\f\20\16\20\u0085\13\20\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0093\n\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\5\26\u009d\n\26\3\26\3\26\3\26\3\27\6\27\u00a3"+
		"\n\27\r\27\16\27\u00a4\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3"+
		"\32\3\33\3\33\5\33\u00b3\n\33\3\34\3\34\3\35\3\35\3\36\3\36\7\36\u00bb"+
		"\n\36\f\36\16\36\u00be\13\36\3\36\3\36\3\37\3\37\3\37\3 \3 \5 \u00c7\n"+
		" \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\2#\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@B\2\3\3\2\7\b\u00c3\2G\3\2\2\2\4L\3\2\2\2"+
		"\6N\3\2\2\2\bU\3\2\2\2\nZ\3\2\2\2\f^\3\2\2\2\16a\3\2\2\2\20c\3\2\2\2\22"+
		"e\3\2\2\2\24h\3\2\2\2\26j\3\2\2\2\30q\3\2\2\2\32u\3\2\2\2\34|\3\2\2\2"+
		"\36\177\3\2\2\2 \u0086\3\2\2\2\"\u0092\3\2\2\2$\u0094\3\2\2\2&\u0096\3"+
		"\2\2\2(\u0098\3\2\2\2*\u009a\3\2\2\2,\u00a2\3\2\2\2.\u00a8\3\2\2\2\60"+
		"\u00ac\3\2\2\2\62\u00ae\3\2\2\2\64\u00b2\3\2\2\2\66\u00b4\3\2\2\28\u00b6"+
		"\3\2\2\2:\u00b8\3\2\2\2<\u00c1\3\2\2\2>\u00c6\3\2\2\2@\u00c8\3\2\2\2B"+
		"\u00cb\3\2\2\2DF\5\4\3\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\3\3"+
		"\2\2\2IG\3\2\2\2JM\5\6\4\2KM\5\26\f\2LJ\3\2\2\2LK\3\2\2\2M\5\3\2\2\2N"+
		"O\7\23\2\2OP\t\2\2\2PQ\5\4\3\2Q\7\3\2\2\2RV\5\n\6\2SV\5\20\t\2TV\5\22"+
		"\n\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\t\3\2\2\2WX\5\f\7\2XY\5\16\b\2Y[\3"+
		"\2\2\2ZW\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\13\3\2\2\2^_\7\23\2"+
		"\2_`\7\20\2\2`\r\3\2\2\2ab\7\23\2\2b\17\3\2\2\2cd\7\23\2\2d\21\3\2\2\2"+
		"ef\7\22\2\2fg\7\23\2\2g\23\3\2\2\2hi\5\4\3\2i\25\3\2\2\2jn\5\32\16\2k"+
		"m\5\30\r\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\27\3\2\2\2pn\3\2\2"+
		"\2qr\7\23\2\2rs\7\20\2\2st\5\32\16\2t\31\3\2\2\2uy\5\36\20\2vx\5\34\17"+
		"\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\33\3\2\2\2{y\3\2\2\2|}\7\22"+
		"\2\2}~\5\36\20\2~\35\3\2\2\2\177\u0083\5\"\22\2\u0080\u0082\5 \21\2\u0081"+
		"\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2"+
		"\2\2\u0084\37\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7\21\2\2\u0087\u0088"+
		"\7\23\2\2\u0088!\3\2\2\2\u0089\u0093\5$\23\2\u008a\u0093\5&\24\2\u008b"+
		"\u0093\5(\25\2\u008c\u0093\5*\26\2\u008d\u0093\5.\30\2\u008e\u0093\5\60"+
		"\31\2\u008f\u0093\5\62\32\2\u0090\u0093\5\64\33\2\u0091\u0093\5:\36\2"+
		"\u0092\u0089\3\2\2\2\u0092\u008a\3\2\2\2\u0092\u008b\3\2\2\2\u0092\u008c"+
		"\3\2\2\2\u0092\u008d\3\2\2\2\u0092\u008e\3\2\2\2\u0092\u008f\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093#\3\2\2\2\u0094\u0095\7\24\2\2"+
		"\u0095%\3\2\2\2\u0096\u0097\7\25\2\2\u0097\'\3\2\2\2\u0098\u0099\7\23"+
		"\2\2\u0099)\3\2\2\2\u009a\u009c\7\f\2\2\u009b\u009d\5,\27\2\u009c\u009b"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\5\2\2\2\u009f"+
		"\u00a0\7\r\2\2\u00a0+\3\2\2\2\u00a1\u00a3\7\23\2\2\u00a2\u00a1\3\2\2\2"+
		"\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a7\7\t\2\2\u00a7-\3\2\2\2\u00a8\u00a9\7\n\2\2\u00a9"+
		"\u00aa\5\4\3\2\u00aa\u00ab\7\13\2\2\u00ab/\3\2\2\2\u00ac\u00ad\7\3\2\2"+
		"\u00ad\61\3\2\2\2\u00ae\u00af\7\6\2\2\u00af\63\3\2\2\2\u00b0\u00b3\5\66"+
		"\34\2\u00b1\u00b3\58\35\2\u00b2\u00b0\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3"+
		"\65\3\2\2\2\u00b4\u00b5\7\4\2\2\u00b5\67\3\2\2\2\u00b6\u00b7\7\5\2\2\u00b7"+
		"9\3\2\2\2\u00b8\u00bc\7\16\2\2\u00b9\u00bb\5<\37\2\u00ba\u00b9\3\2\2\2"+
		"\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf"+
		"\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7\17\2\2\u00c0;\3\2\2\2\u00c1"+
		"\u00c2\5\b\5\2\u00c2\u00c3\5> \2\u00c3=\3\2\2\2\u00c4\u00c7\5@!\2\u00c5"+
		"\u00c7\5B\"\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7?\3\2\2\2\u00c8"+
		"\u00c9\7\b\2\2\u00c9\u00ca\5\26\f\2\u00caA\3\2\2\2\u00cb\u00cc\7\16\2"+
		"\2\u00cc\u00cd\5\2\2\2\u00cd\u00ce\7\17\2\2\u00ceC\3\2\2\2\17GLU\\ny\u0083"+
		"\u0092\u009c\u00a4\u00b2\u00bc\u00c6";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}