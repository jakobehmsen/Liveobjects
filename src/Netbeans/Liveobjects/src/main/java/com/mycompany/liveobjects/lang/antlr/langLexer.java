// Generated from /home/jakob/github/Liveobjects/src/Netbeans/Liveobjects/src/main/java/com/mycompany/liveobjects/lang/antlr/lang.g4 by ANTLR 4.1
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
		KW_SELF=1, KW_THIS_CONTEXT=2, SELF_ASSIGN=3, FRAME_ASSIGN=4, PIPE=5, OPEN_PAR=6, 
		CLOSE_PAR=7, OPEN_SQ=8, CLOSE_SQ=9, COLON=10, DOT=11, BIN_OP=12, ID=13, 
		NUMBER=14, STRING=15, WS=16, SINGLE_LINE_COMMENT=17, MULTI_LINE_COMMENT=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'self'", "'thisContext'", "':='", "'='", "'|'", "'('", "')'", "'['", 
		"']'", "':'", "'.'", "BIN_OP", "ID", "NUMBER", "STRING", "WS", "SINGLE_LINE_COMMENT", 
		"MULTI_LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"KW_SELF", "KW_THIS_CONTEXT", "SELF_ASSIGN", "FRAME_ASSIGN", "PIPE", "OPEN_PAR", 
		"CLOSE_PAR", "OPEN_SQ", "CLOSE_SQ", "COLON", "DOT", "BIN_OP", "DIGIT", 
		"LETTER", "ID", "NUMBER", "INT", "EXP", "STRING", "ESC", "UNICODE", "HEX", 
		"WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
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
		case 22: WS_action((RuleContext)_localctx, actionIndex); break;

		case 23: SINGLE_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 24: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\24\u00cc\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\5\17_\n\17\3\20\3\20\5\20"+
		"c\n\20\3\20\3\20\3\20\7\20h\n\20\f\20\16\20k\13\20\3\21\5\21n\n\21\3\21"+
		"\3\21\3\21\6\21s\n\21\r\21\16\21t\3\21\5\21x\n\21\3\21\5\21{\n\21\3\21"+
		"\3\21\3\21\3\21\5\21\u0081\n\21\3\21\5\21\u0084\n\21\3\22\3\22\3\22\7"+
		"\22\u0089\n\22\f\22\16\22\u008c\13\22\5\22\u008e\n\22\3\23\3\23\5\23\u0092"+
		"\n\23\3\23\3\23\3\24\3\24\3\24\7\24\u0099\n\24\f\24\16\24\u009c\13\24"+
		"\3\24\3\24\3\25\3\25\3\25\5\25\u00a3\n\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\30\6\30\u00ae\n\30\r\30\16\30\u00af\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\7\31\u00b8\n\31\f\31\16\31\u00bb\13\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\7\32\u00c3\n\32\f\32\16\32\u00c6\13\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\u00c4\33\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13"+
		"\1\25\f\1\27\r\1\31\16\1\33\2\1\35\2\1\37\17\1!\20\1#\2\1%\2\1\'\21\1"+
		")\2\1+\2\1-\2\1/\22\2\61\23\3\63\24\4\3\2\r\b\2\'\',-//\61\61>>@@\3\2"+
		"\62;\4\2C\\c|\3\2\63;\4\2GGgg\4\2--//\4\2$$^^\n\2$$\61\61^^ddhhppttvv"+
		"\5\2\62;CHch\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u00d8\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2\'\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65"+
		"\3\2\2\2\5:\3\2\2\2\7F\3\2\2\2\tI\3\2\2\2\13K\3\2\2\2\rM\3\2\2\2\17O\3"+
		"\2\2\2\21Q\3\2\2\2\23S\3\2\2\2\25U\3\2\2\2\27W\3\2\2\2\31Y\3\2\2\2\33"+
		"[\3\2\2\2\35^\3\2\2\2\37b\3\2\2\2!\u0083\3\2\2\2#\u008d\3\2\2\2%\u008f"+
		"\3\2\2\2\'\u0095\3\2\2\2)\u009f\3\2\2\2+\u00a4\3\2\2\2-\u00aa\3\2\2\2"+
		"/\u00ad\3\2\2\2\61\u00b3\3\2\2\2\63\u00be\3\2\2\2\65\66\7u\2\2\66\67\7"+
		"g\2\2\678\7n\2\289\7h\2\29\4\3\2\2\2:;\7v\2\2;<\7j\2\2<=\7k\2\2=>\7u\2"+
		"\2>?\7E\2\2?@\7q\2\2@A\7p\2\2AB\7v\2\2BC\7g\2\2CD\7z\2\2DE\7v\2\2E\6\3"+
		"\2\2\2FG\7<\2\2GH\7?\2\2H\b\3\2\2\2IJ\7?\2\2J\n\3\2\2\2KL\7~\2\2L\f\3"+
		"\2\2\2MN\7*\2\2N\16\3\2\2\2OP\7+\2\2P\20\3\2\2\2QR\7]\2\2R\22\3\2\2\2"+
		"ST\7_\2\2T\24\3\2\2\2UV\7<\2\2V\26\3\2\2\2WX\7\60\2\2X\30\3\2\2\2YZ\t"+
		"\2\2\2Z\32\3\2\2\2[\\\t\3\2\2\\\34\3\2\2\2]_\t\4\2\2^]\3\2\2\2_\36\3\2"+
		"\2\2`c\5\35\17\2ac\7a\2\2b`\3\2\2\2ba\3\2\2\2ci\3\2\2\2dh\5\35\17\2eh"+
		"\7a\2\2fh\5\33\16\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2"+
		"ij\3\2\2\2j \3\2\2\2ki\3\2\2\2ln\7/\2\2ml\3\2\2\2mn\3\2\2\2no\3\2\2\2"+
		"op\5#\22\2pr\7\60\2\2qs\t\3\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2"+
		"\2uw\3\2\2\2vx\5%\23\2wv\3\2\2\2wx\3\2\2\2x\u0084\3\2\2\2y{\7/\2\2zy\3"+
		"\2\2\2z{\3\2\2\2{|\3\2\2\2|}\5#\22\2}~\5%\23\2~\u0084\3\2\2\2\177\u0081"+
		"\7/\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0084\5#\22\2\u0083m\3\2\2\2\u0083z\3\2\2\2\u0083\u0080\3\2\2\2\u0084"+
		"\"\3\2\2\2\u0085\u008e\7\62\2\2\u0086\u008a\t\5\2\2\u0087\u0089\t\3\2"+
		"\2\u0088\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b"+
		"\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u0085\3\2\2\2\u008d"+
		"\u0086\3\2\2\2\u008e$\3\2\2\2\u008f\u0091\t\6\2\2\u0090\u0092\t\7\2\2"+
		"\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094"+
		"\5#\22\2\u0094&\3\2\2\2\u0095\u009a\7$\2\2\u0096\u0099\5)\25\2\u0097\u0099"+
		"\n\b\2\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009d\u009e\7$\2\2\u009e(\3\2\2\2\u009f\u00a2\7^\2\2\u00a0\u00a3"+
		"\t\t\2\2\u00a1\u00a3\5+\26\2\u00a2\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3"+
		"*\3\2\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\5-\27\2\u00a6\u00a7\5-\27\2\u00a7"+
		"\u00a8\5-\27\2\u00a8\u00a9\5-\27\2\u00a9,\3\2\2\2\u00aa\u00ab\t\n\2\2"+
		"\u00ab.\3\2\2\2\u00ac\u00ae\t\13\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3"+
		"\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b2\b\30\2\2\u00b2\60\3\2\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00b5\7\61"+
		"\2\2\u00b5\u00b9\3\2\2\2\u00b6\u00b8\n\f\2\2\u00b7\u00b6\3\2\2\2\u00b8"+
		"\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2"+
		"\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\b\31\3\2\u00bd\62\3\2\2\2\u00be\u00bf"+
		"\7\61\2\2\u00bf\u00c0\7,\2\2\u00c0\u00c4\3\2\2\2\u00c1\u00c3\13\2\2\2"+
		"\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c4\u00c2"+
		"\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\7,\2\2\u00c8"+
		"\u00c9\7\61\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\b\32\4\2\u00cb\64\3\2"+
		"\2\2\26\2^bgimtwz\u0080\u0083\u008a\u008d\u0091\u0098\u009a\u00a2\u00af"+
		"\u00b9\u00c4";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}