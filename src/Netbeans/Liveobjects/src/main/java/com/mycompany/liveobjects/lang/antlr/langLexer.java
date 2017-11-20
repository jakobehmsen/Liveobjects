// Generated from /home/jakob/github/ideas/classpoolmod/src/NetBeans/liveobjects/src/main/java/com/mycompany/liveobjects/lang/antlr/lang.g4 by ANTLR 4.1
package com.mycompany.liveobjects.lang.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class langLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KW_SELF=1, SELF_ASSIGN=2, FRAME_ASSIGN=3, PIPE=4, OPEN_PAR=5, CLOSE_PAR=6, 
		OPEN_SQ=7, CLOSE_SQ=8, COLON=9, DOT=10, BIN_OP=11, ID=12, NUMBER=13, STRING=14, 
		WS=15, SINGLE_LINE_COMMENT=16, MULTI_LINE_COMMENT=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'self'", "':='", "'='", "'|'", "'('", "')'", "'['", "']'", "':'", "'.'", 
		"BIN_OP", "ID", "NUMBER", "STRING", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"KW_SELF", "SELF_ASSIGN", "FRAME_ASSIGN", "PIPE", "OPEN_PAR", "CLOSE_PAR", 
		"OPEN_SQ", "CLOSE_SQ", "COLON", "DOT", "BIN_OP", "DIGIT", "LETTER", "ID", 
		"NUMBER", "INT", "EXP", "STRING", "ESC", "UNICODE", "HEX", "WS", "SINGLE_LINE_COMMENT", 
		"MULTI_LINE_COMMENT"
	};


	public langLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lang.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 21: WS_action((RuleContext)_localctx, actionIndex); break;

		case 22: SINGLE_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 23: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void MULTI_LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void SINGLE_LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = 2;  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\23\u00be\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\5\16Q\n\16\3\17"+
		"\3\17\5\17U\n\17\3\17\3\17\3\17\7\17Z\n\17\f\17\16\17]\13\17\3\20\5\20"+
		"`\n\20\3\20\3\20\3\20\6\20e\n\20\r\20\16\20f\3\20\5\20j\n\20\3\20\5\20"+
		"m\n\20\3\20\3\20\3\20\3\20\5\20s\n\20\3\20\5\20v\n\20\3\21\3\21\3\21\7"+
		"\21{\n\21\f\21\16\21~\13\21\5\21\u0080\n\21\3\22\3\22\5\22\u0084\n\22"+
		"\3\22\3\22\3\23\3\23\3\23\7\23\u008b\n\23\f\23\16\23\u008e\13\23\3\23"+
		"\3\23\3\24\3\24\3\24\5\24\u0095\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\27\6\27\u00a0\n\27\r\27\16\27\u00a1\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\7\30\u00aa\n\30\f\30\16\30\u00ad\13\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\7\31\u00b5\n\31\f\31\16\31\u00b8\13\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\u00b6\32\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25"+
		"\f\1\27\r\1\31\2\1\33\2\1\35\16\1\37\17\1!\2\1#\2\1%\20\1\'\2\1)\2\1+"+
		"\2\1-\21\2/\22\3\61\23\4\3\2\r\b\2\'\',-//\61\61>>@@\3\2\62;\4\2C\\c|"+
		"\3\2\63;\4\2GGgg\4\2--//\4\2$$^^\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5"+
		"\2\13\f\17\17\"\"\4\2\f\f\17\17\u00ca\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2%\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\58\3\2\2\2\7;\3\2"+
		"\2\2\t=\3\2\2\2\13?\3\2\2\2\rA\3\2\2\2\17C\3\2\2\2\21E\3\2\2\2\23G\3\2"+
		"\2\2\25I\3\2\2\2\27K\3\2\2\2\31M\3\2\2\2\33P\3\2\2\2\35T\3\2\2\2\37u\3"+
		"\2\2\2!\177\3\2\2\2#\u0081\3\2\2\2%\u0087\3\2\2\2\'\u0091\3\2\2\2)\u0096"+
		"\3\2\2\2+\u009c\3\2\2\2-\u009f\3\2\2\2/\u00a5\3\2\2\2\61\u00b0\3\2\2\2"+
		"\63\64\7u\2\2\64\65\7g\2\2\65\66\7n\2\2\66\67\7h\2\2\67\4\3\2\2\289\7"+
		"<\2\29:\7?\2\2:\6\3\2\2\2;<\7?\2\2<\b\3\2\2\2=>\7~\2\2>\n\3\2\2\2?@\7"+
		"*\2\2@\f\3\2\2\2AB\7+\2\2B\16\3\2\2\2CD\7]\2\2D\20\3\2\2\2EF\7_\2\2F\22"+
		"\3\2\2\2GH\7<\2\2H\24\3\2\2\2IJ\7\60\2\2J\26\3\2\2\2KL\t\2\2\2L\30\3\2"+
		"\2\2MN\t\3\2\2N\32\3\2\2\2OQ\t\4\2\2PO\3\2\2\2Q\34\3\2\2\2RU\5\33\16\2"+
		"SU\7a\2\2TR\3\2\2\2TS\3\2\2\2U[\3\2\2\2VZ\5\33\16\2WZ\7a\2\2XZ\5\31\r"+
		"\2YV\3\2\2\2YW\3\2\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\36\3"+
		"\2\2\2][\3\2\2\2^`\7/\2\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\5!\21\2bd\7"+
		"\60\2\2ce\t\3\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hj"+
		"\5#\22\2ih\3\2\2\2ij\3\2\2\2jv\3\2\2\2km\7/\2\2lk\3\2\2\2lm\3\2\2\2mn"+
		"\3\2\2\2no\5!\21\2op\5#\22\2pv\3\2\2\2qs\7/\2\2rq\3\2\2\2rs\3\2\2\2st"+
		"\3\2\2\2tv\5!\21\2u_\3\2\2\2ul\3\2\2\2ur\3\2\2\2v \3\2\2\2w\u0080\7\62"+
		"\2\2x|\t\5\2\2y{\t\3\2\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\u0080"+
		"\3\2\2\2~|\3\2\2\2\177w\3\2\2\2\177x\3\2\2\2\u0080\"\3\2\2\2\u0081\u0083"+
		"\t\6\2\2\u0082\u0084\t\7\2\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\u0086\5!\21\2\u0086$\3\2\2\2\u0087\u008c\7$\2\2\u0088"+
		"\u008b\5\'\24\2\u0089\u008b\n\b\2\2\u008a\u0088\3\2\2\2\u008a\u0089\3"+
		"\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090\7$\2\2\u0090&\3\2\2\2\u0091"+
		"\u0094\7^\2\2\u0092\u0095\t\t\2\2\u0093\u0095\5)\25\2\u0094\u0092\3\2"+
		"\2\2\u0094\u0093\3\2\2\2\u0095(\3\2\2\2\u0096\u0097\7w\2\2\u0097\u0098"+
		"\5+\26\2\u0098\u0099\5+\26\2\u0099\u009a\5+\26\2\u009a\u009b\5+\26\2\u009b"+
		"*\3\2\2\2\u009c\u009d\t\n\2\2\u009d,\3\2\2\2\u009e\u00a0\t\13\2\2\u009f"+
		"\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2"+
		"\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\27\2\2\u00a4.\3\2\2\2\u00a5\u00a6"+
		"\7\61\2\2\u00a6\u00a7\7\61\2\2\u00a7\u00ab\3\2\2\2\u00a8\u00aa\n\f\2\2"+
		"\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac"+
		"\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\b\30\3\2"+
		"\u00af\60\3\2\2\2\u00b0\u00b1\7\61\2\2\u00b1\u00b2\7,\2\2\u00b2\u00b6"+
		"\3\2\2\2\u00b3\u00b5\13\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2"+
		"\u00b6\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6"+
		"\3\2\2\2\u00b9\u00ba\7,\2\2\u00ba\u00bb\7\61\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00bd\b\31\4\2\u00bd\62\3\2\2\2\26\2PTY[_filru|\177\u0083\u008a\u008c"+
		"\u0094\u00a1\u00ab\u00b6";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}