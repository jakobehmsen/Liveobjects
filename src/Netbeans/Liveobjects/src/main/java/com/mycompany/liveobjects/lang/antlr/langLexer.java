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
		PROTOCOL=1, KW_SELF=2, KW_TRUE=3, KW_FALSE=4, KW_THIS_CONTEXT=5, SELF_ASSIGN=6, 
		FRAME_ASSIGN=7, PIPE=8, OPEN_PAR=9, CLOSE_PAR=10, OPEN_SQ=11, CLOSE_SQ=12, 
		OPEN_BRA=13, CLOSE_BRA=14, COLON=15, DOT=16, BIN_OP=17, ID=18, NUMBER=19, 
		STRING=20, WS=21, SINGLE_LINE_COMMENT=22, MULTI_LINE_COMMENT=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'§'", "'self'", "'true'", "'false'", "'thisContext'", "':='", "'='", 
		"'|'", "'('", "')'", "'['", "']'", "'{'", "'}'", "':'", "'.'", "BIN_OP", 
		"ID", "NUMBER", "STRING", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"PROTOCOL", "KW_SELF", "KW_TRUE", "KW_FALSE", "KW_THIS_CONTEXT", "SELF_ASSIGN", 
		"FRAME_ASSIGN", "PIPE", "OPEN_PAR", "CLOSE_PAR", "OPEN_SQ", "CLOSE_SQ", 
		"OPEN_BRA", "CLOSE_BRA", "COLON", "DOT", "BIN_OP", "DIGIT", "LETTER", 
		"ID", "NUMBER", "INT", "EXP", "STRING", "ESC", "UNICODE", "HEX", "WS", 
		"SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
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
		case 27: WS_action((RuleContext)_localctx, actionIndex); break;

		case 28: SINGLE_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 29: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\31\u00e7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\5\24z\n\24\3\25\3\25\5\25~\n\25\3\25"+
		"\3\25\3\25\7\25\u0083\n\25\f\25\16\25\u0086\13\25\3\26\5\26\u0089\n\26"+
		"\3\26\3\26\3\26\6\26\u008e\n\26\r\26\16\26\u008f\3\26\5\26\u0093\n\26"+
		"\3\26\5\26\u0096\n\26\3\26\3\26\3\26\3\26\5\26\u009c\n\26\3\26\5\26\u009f"+
		"\n\26\3\27\3\27\3\27\7\27\u00a4\n\27\f\27\16\27\u00a7\13\27\5\27\u00a9"+
		"\n\27\3\30\3\30\5\30\u00ad\n\30\3\30\3\30\3\31\3\31\3\31\7\31\u00b4\n"+
		"\31\f\31\16\31\u00b7\13\31\3\31\3\31\3\32\3\32\3\32\5\32\u00be\n\32\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\6\35\u00c9\n\35\r\35\16\35"+
		"\u00ca\3\35\3\35\3\36\3\36\3\36\3\36\7\36\u00d3\n\36\f\36\16\36\u00d6"+
		"\13\36\3\36\3\36\3\37\3\37\3\37\3\37\7\37\u00de\n\37\f\37\16\37\u00e1"+
		"\13\37\3\37\3\37\3\37\3\37\3\37\3\u00df \3\3\1\5\4\1\7\5\1\t\6\1\13\7"+
		"\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37"+
		"\21\1!\22\1#\23\1%\2\1\'\2\1)\24\1+\25\1-\2\1/\2\1\61\26\1\63\2\1\65\2"+
		"\1\67\2\19\27\2;\30\3=\31\4\3\2\r\b\2\'\',-//\61\61>>@@\3\2\62;\4\2C\\"+
		"c|\3\2\63;\4\2GGgg\4\2--//\4\2$$^^\n\2$$\61\61^^ddhhppttvv\5\2\62;CHc"+
		"h\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u00f3\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2\61"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2\5A\3\2\2\2\7F\3\2"+
		"\2\2\tK\3\2\2\2\13Q\3\2\2\2\r]\3\2\2\2\17`\3\2\2\2\21b\3\2\2\2\23d\3\2"+
		"\2\2\25f\3\2\2\2\27h\3\2\2\2\31j\3\2\2\2\33l\3\2\2\2\35n\3\2\2\2\37p\3"+
		"\2\2\2!r\3\2\2\2#t\3\2\2\2%v\3\2\2\2\'y\3\2\2\2)}\3\2\2\2+\u009e\3\2\2"+
		"\2-\u00a8\3\2\2\2/\u00aa\3\2\2\2\61\u00b0\3\2\2\2\63\u00ba\3\2\2\2\65"+
		"\u00bf\3\2\2\2\67\u00c5\3\2\2\29\u00c8\3\2\2\2;\u00ce\3\2\2\2=\u00d9\3"+
		"\2\2\2?@\7\u00a9\2\2@\4\3\2\2\2AB\7u\2\2BC\7g\2\2CD\7n\2\2DE\7h\2\2E\6"+
		"\3\2\2\2FG\7v\2\2GH\7t\2\2HI\7w\2\2IJ\7g\2\2J\b\3\2\2\2KL\7h\2\2LM\7c"+
		"\2\2MN\7n\2\2NO\7u\2\2OP\7g\2\2P\n\3\2\2\2QR\7v\2\2RS\7j\2\2ST\7k\2\2"+
		"TU\7u\2\2UV\7E\2\2VW\7q\2\2WX\7p\2\2XY\7v\2\2YZ\7g\2\2Z[\7z\2\2[\\\7v"+
		"\2\2\\\f\3\2\2\2]^\7<\2\2^_\7?\2\2_\16\3\2\2\2`a\7?\2\2a\20\3\2\2\2bc"+
		"\7~\2\2c\22\3\2\2\2de\7*\2\2e\24\3\2\2\2fg\7+\2\2g\26\3\2\2\2hi\7]\2\2"+
		"i\30\3\2\2\2jk\7_\2\2k\32\3\2\2\2lm\7}\2\2m\34\3\2\2\2no\7\177\2\2o\36"+
		"\3\2\2\2pq\7<\2\2q \3\2\2\2rs\7\60\2\2s\"\3\2\2\2tu\t\2\2\2u$\3\2\2\2"+
		"vw\t\3\2\2w&\3\2\2\2xz\t\4\2\2yx\3\2\2\2z(\3\2\2\2{~\5\'\24\2|~\7a\2\2"+
		"}{\3\2\2\2}|\3\2\2\2~\u0084\3\2\2\2\177\u0083\5\'\24\2\u0080\u0083\7a"+
		"\2\2\u0081\u0083\5%\23\2\u0082\177\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"*\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\7/\2\2\u0088\u0087\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\5-\27\2\u008b\u008d\7\60"+
		"\2\2\u008c\u008e\t\3\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u0093\5/"+
		"\30\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u009f\3\2\2\2\u0094"+
		"\u0096\7/\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\u0098\5-\27\2\u0098\u0099\5/\30\2\u0099\u009f\3\2\2\2\u009a"+
		"\u009c\7/\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009f\5-\27\2\u009e\u0088\3\2\2\2\u009e\u0095\3\2\2\2\u009e"+
		"\u009b\3\2\2\2\u009f,\3\2\2\2\u00a0\u00a9\7\62\2\2\u00a1\u00a5\t\5\2\2"+
		"\u00a2\u00a4\t\3\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3"+
		"\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8"+
		"\u00a0\3\2\2\2\u00a8\u00a1\3\2\2\2\u00a9.\3\2\2\2\u00aa\u00ac\t\6\2\2"+
		"\u00ab\u00ad\t\7\2\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00af\5-\27\2\u00af\60\3\2\2\2\u00b0\u00b5\7$\2\2\u00b1"+
		"\u00b4\5\63\32\2\u00b2\u00b4\n\b\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3"+
		"\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\7$\2\2\u00b9\62\3\2\2\2"+
		"\u00ba\u00bd\7^\2\2\u00bb\u00be\t\t\2\2\u00bc\u00be\5\65\33\2\u00bd\u00bb"+
		"\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be\64\3\2\2\2\u00bf\u00c0\7w\2\2\u00c0"+
		"\u00c1\5\67\34\2\u00c1\u00c2\5\67\34\2\u00c2\u00c3\5\67\34\2\u00c3\u00c4"+
		"\5\67\34\2\u00c4\66\3\2\2\2\u00c5\u00c6\t\n\2\2\u00c68\3\2\2\2\u00c7\u00c9"+
		"\t\13\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2"+
		"\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\b\35\2\2\u00cd:\3"+
		"\2\2\2\u00ce\u00cf\7\61\2\2\u00cf\u00d0\7\61\2\2\u00d0\u00d4\3\2\2\2\u00d1"+
		"\u00d3\n\f\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7"+
		"\u00d8\b\36\3\2\u00d8<\3\2\2\2\u00d9\u00da\7\61\2\2\u00da\u00db\7,\2\2"+
		"\u00db\u00df\3\2\2\2\u00dc\u00de\13\2\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1"+
		"\3\2\2\2\u00df\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e3\7,\2\2\u00e3\u00e4\7\61\2\2\u00e4\u00e5\3\2"+
		"\2\2\u00e5\u00e6\b\37\4\2\u00e6>\3\2\2\2\26\2y}\u0082\u0084\u0088\u008f"+
		"\u0092\u0095\u009b\u009e\u00a5\u00a8\u00ac\u00b3\u00b5\u00bd\u00ca\u00d4"+
		"\u00df";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}