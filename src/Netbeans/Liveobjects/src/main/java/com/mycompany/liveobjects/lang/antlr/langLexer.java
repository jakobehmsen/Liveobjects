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
		KW_SELF=1, KW_TRUE=2, KW_FALSE=3, KW_THIS_CONTEXT=4, KW_NIL=5, SELF_ASSIGN=6, 
		FRAME_ASSIGN=7, PIPE=8, OPEN_PAR=9, CLOSE_PAR=10, OPEN_SQ=11, CLOSE_SQ=12, 
		OPEN_BRA=13, CLOSE_BRA=14, COLON=15, DOT=16, ASTERISK=17, BIN_OP=18, ID=19, 
		NUMBER=20, STRING=21, WS=22, SINGLE_LINE_COMMENT=23, MULTI_LINE_COMMENT=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'self'", "'true'", "'false'", "'thisContext'", "'nil'", "':='", "'='", 
		"'|'", "'('", "')'", "'['", "']'", "'{'", "'}'", "':'", "'.'", "'*'", 
		"BIN_OP", "ID", "NUMBER", "STRING", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"KW_SELF", "KW_TRUE", "KW_FALSE", "KW_THIS_CONTEXT", "KW_NIL", "SELF_ASSIGN", 
		"FRAME_ASSIGN", "PIPE", "OPEN_PAR", "CLOSE_PAR", "OPEN_SQ", "CLOSE_SQ", 
		"OPEN_BRA", "CLOSE_BRA", "COLON", "DOT", "ASTERISK", "BIN_OP", "DIGIT", 
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
		case 28: WS_action((RuleContext)_localctx, actionIndex); break;

		case 29: SINGLE_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 30: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\32\u00f0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\5\23~\n\23\3\24\3\24"+
		"\3\25\5\25\u0083\n\25\3\26\3\26\5\26\u0087\n\26\3\26\3\26\3\26\7\26\u008c"+
		"\n\26\f\26\16\26\u008f\13\26\3\27\5\27\u0092\n\27\3\27\3\27\3\27\6\27"+
		"\u0097\n\27\r\27\16\27\u0098\3\27\5\27\u009c\n\27\3\27\5\27\u009f\n\27"+
		"\3\27\3\27\3\27\3\27\5\27\u00a5\n\27\3\27\5\27\u00a8\n\27\3\30\3\30\3"+
		"\30\7\30\u00ad\n\30\f\30\16\30\u00b0\13\30\5\30\u00b2\n\30\3\31\3\31\5"+
		"\31\u00b6\n\31\3\31\3\31\3\32\3\32\3\32\7\32\u00bd\n\32\f\32\16\32\u00c0"+
		"\13\32\3\32\3\32\3\33\3\33\3\33\5\33\u00c7\n\33\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\36\6\36\u00d2\n\36\r\36\16\36\u00d3\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\7\37\u00dc\n\37\f\37\16\37\u00df\13\37\3\37\3\37\3 \3"+
		" \3 \3 \7 \u00e7\n \f \16 \u00ea\13 \3 \3 \3 \3 \3 \3\u00e8!\3\3\1\5\4"+
		"\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16"+
		"\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\2\1)\2\1+\25\1-\26\1/\2"+
		"\1\61\2\1\63\27\1\65\2\1\67\2\19\2\1;\30\2=\31\3?\32\4\3\2\r\4\2--//\6"+
		"\2\'\'\61\61>>@@\3\2\62;\4\2C\\c|\3\2\63;\4\2GGgg\4\2$$^^\n\2$$\61\61"+
		"^^ddhhppttvv\5\2\62;CHch\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u00fe\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2\63\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?"+
		"\3\2\2\2\3A\3\2\2\2\5F\3\2\2\2\7K\3\2\2\2\tQ\3\2\2\2\13]\3\2\2\2\ra\3"+
		"\2\2\2\17d\3\2\2\2\21f\3\2\2\2\23h\3\2\2\2\25j\3\2\2\2\27l\3\2\2\2\31"+
		"n\3\2\2\2\33p\3\2\2\2\35r\3\2\2\2\37t\3\2\2\2!v\3\2\2\2#x\3\2\2\2%}\3"+
		"\2\2\2\'\177\3\2\2\2)\u0082\3\2\2\2+\u0086\3\2\2\2-\u00a7\3\2\2\2/\u00b1"+
		"\3\2\2\2\61\u00b3\3\2\2\2\63\u00b9\3\2\2\2\65\u00c3\3\2\2\2\67\u00c8\3"+
		"\2\2\29\u00ce\3\2\2\2;\u00d1\3\2\2\2=\u00d7\3\2\2\2?\u00e2\3\2\2\2AB\7"+
		"u\2\2BC\7g\2\2CD\7n\2\2DE\7h\2\2E\4\3\2\2\2FG\7v\2\2GH\7t\2\2HI\7w\2\2"+
		"IJ\7g\2\2J\6\3\2\2\2KL\7h\2\2LM\7c\2\2MN\7n\2\2NO\7u\2\2OP\7g\2\2P\b\3"+
		"\2\2\2QR\7v\2\2RS\7j\2\2ST\7k\2\2TU\7u\2\2UV\7E\2\2VW\7q\2\2WX\7p\2\2"+
		"XY\7v\2\2YZ\7g\2\2Z[\7z\2\2[\\\7v\2\2\\\n\3\2\2\2]^\7p\2\2^_\7k\2\2_`"+
		"\7n\2\2`\f\3\2\2\2ab\7<\2\2bc\7?\2\2c\16\3\2\2\2de\7?\2\2e\20\3\2\2\2"+
		"fg\7~\2\2g\22\3\2\2\2hi\7*\2\2i\24\3\2\2\2jk\7+\2\2k\26\3\2\2\2lm\7]\2"+
		"\2m\30\3\2\2\2no\7_\2\2o\32\3\2\2\2pq\7}\2\2q\34\3\2\2\2rs\7\177\2\2s"+
		"\36\3\2\2\2tu\7<\2\2u \3\2\2\2vw\7\60\2\2w\"\3\2\2\2xy\7,\2\2y$\3\2\2"+
		"\2z~\t\2\2\2{~\5#\22\2|~\t\3\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2~&\3\2\2"+
		"\2\177\u0080\t\4\2\2\u0080(\3\2\2\2\u0081\u0083\t\5\2\2\u0082\u0081\3"+
		"\2\2\2\u0083*\3\2\2\2\u0084\u0087\5)\25\2\u0085\u0087\7a\2\2\u0086\u0084"+
		"\3\2\2\2\u0086\u0085\3\2\2\2\u0087\u008d\3\2\2\2\u0088\u008c\5)\25\2\u0089"+
		"\u008c\7a\2\2\u008a\u008c\5\'\24\2\u008b\u0088\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e,\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0092\7/\2\2\u0091"+
		"\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\5/"+
		"\30\2\u0094\u0096\7\60\2\2\u0095\u0097\t\4\2\2\u0096\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2"+
		"\2\2\u009a\u009c\5\61\31\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u00a8\3\2\2\2\u009d\u009f\7/\2\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\5/\30\2\u00a1\u00a2\5\61\31\2\u00a2"+
		"\u00a8\3\2\2\2\u00a3\u00a5\7/\2\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\5/\30\2\u00a7\u0091\3\2\2\2\u00a7"+
		"\u009e\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a8.\3\2\2\2\u00a9\u00b2\7\62\2\2"+
		"\u00aa\u00ae\t\6\2\2\u00ab\u00ad\t\4\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0"+
		"\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b1\u00a9\3\2\2\2\u00b1\u00aa\3\2\2\2\u00b2\60\3\2\2"+
		"\2\u00b3\u00b5\t\7\2\2\u00b4\u00b6\t\2\2\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6"+
		"\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\5/\30\2\u00b8\62\3\2\2\2\u00b9"+
		"\u00be\7$\2\2\u00ba\u00bd\5\65\33\2\u00bb\u00bd\n\b\2\2\u00bc\u00ba\3"+
		"\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7$"+
		"\2\2\u00c2\64\3\2\2\2\u00c3\u00c6\7^\2\2\u00c4\u00c7\t\t\2\2\u00c5\u00c7"+
		"\5\67\34\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7\66\3\2\2\2\u00c8"+
		"\u00c9\7w\2\2\u00c9\u00ca\59\35\2\u00ca\u00cb\59\35\2\u00cb\u00cc\59\35"+
		"\2\u00cc\u00cd\59\35\2\u00cd8\3\2\2\2\u00ce\u00cf\t\n\2\2\u00cf:\3\2\2"+
		"\2\u00d0\u00d2\t\13\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\b\36"+
		"\2\2\u00d6<\3\2\2\2\u00d7\u00d8\7\61\2\2\u00d8\u00d9\7\61\2\2\u00d9\u00dd"+
		"\3\2\2\2\u00da\u00dc\n\f\2\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00e0\u00e1\b\37\3\2\u00e1>\3\2\2\2\u00e2\u00e3\7\61\2\2\u00e3\u00e4"+
		"\7,\2\2\u00e4\u00e8\3\2\2\2\u00e5\u00e7\13\2\2\2\u00e6\u00e5\3\2\2\2\u00e7"+
		"\u00ea\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00eb\3\2"+
		"\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ec\7,\2\2\u00ec\u00ed\7\61\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\u00ef\b \4\2\u00ef@\3\2\2\2\27\2}\u0082\u0086\u008b"+
		"\u008d\u0091\u0098\u009b\u009e\u00a4\u00a7\u00ae\u00b1\u00b5\u00bc\u00be"+
		"\u00c6\u00d3\u00dd\u00e8";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}