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
		COLON=13, DOT=14, BIN_OP=15, ID=16, NUMBER=17, STRING=18, WS=19, SINGLE_LINE_COMMENT=20, 
		MULTI_LINE_COMMENT=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'§'", "'self'", "'true'", "'false'", "'thisContext'", "':='", "'='", 
		"'|'", "'('", "')'", "'['", "']'", "':'", "'.'", "BIN_OP", "ID", "NUMBER", 
		"STRING", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"PROTOCOL", "KW_SELF", "KW_TRUE", "KW_FALSE", "KW_THIS_CONTEXT", "SELF_ASSIGN", 
		"FRAME_ASSIGN", "PIPE", "OPEN_PAR", "CLOSE_PAR", "OPEN_SQ", "CLOSE_SQ", 
		"COLON", "DOT", "BIN_OP", "DIGIT", "LETTER", "ID", "NUMBER", "INT", "EXP", 
		"STRING", "ESC", "UNICODE", "HEX", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
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
		case 25: WS_action((RuleContext)_localctx, actionIndex); break;

		case 26: SINGLE_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 27: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\27\u00df\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\5\22"+
		"r\n\22\3\23\3\23\5\23v\n\23\3\23\3\23\3\23\7\23{\n\23\f\23\16\23~\13\23"+
		"\3\24\5\24\u0081\n\24\3\24\3\24\3\24\6\24\u0086\n\24\r\24\16\24\u0087"+
		"\3\24\5\24\u008b\n\24\3\24\5\24\u008e\n\24\3\24\3\24\3\24\3\24\5\24\u0094"+
		"\n\24\3\24\5\24\u0097\n\24\3\25\3\25\3\25\7\25\u009c\n\25\f\25\16\25\u009f"+
		"\13\25\5\25\u00a1\n\25\3\26\3\26\5\26\u00a5\n\26\3\26\3\26\3\27\3\27\3"+
		"\27\7\27\u00ac\n\27\f\27\16\27\u00af\13\27\3\27\3\27\3\30\3\30\3\30\5"+
		"\30\u00b6\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33\6\33\u00c1"+
		"\n\33\r\33\16\33\u00c2\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00cb\n\34\f"+
		"\34\16\34\u00ce\13\34\3\34\3\34\3\35\3\35\3\35\3\35\7\35\u00d6\n\35\f"+
		"\35\16\35\u00d9\13\35\3\35\3\35\3\35\3\35\3\35\3\u00d7\36\3\3\1\5\4\1"+
		"\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1"+
		"\33\17\1\35\20\1\37\21\1!\2\1#\2\1%\22\1\'\23\1)\2\1+\2\1-\24\1/\2\1\61"+
		"\2\1\63\2\1\65\25\2\67\26\39\27\4\3\2\r\b\2\'\',-//\61\61>>@@\3\2\62;"+
		"\4\2C\\c|\3\2\63;\4\2GGgg\4\2--//\4\2$$^^\n\2$$\61\61^^ddhhppttvv\5\2"+
		"\62;CHch\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u00eb\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2-\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5=\3\2\2\2\7B\3\2\2\2\tG\3"+
		"\2\2\2\13M\3\2\2\2\rY\3\2\2\2\17\\\3\2\2\2\21^\3\2\2\2\23`\3\2\2\2\25"+
		"b\3\2\2\2\27d\3\2\2\2\31f\3\2\2\2\33h\3\2\2\2\35j\3\2\2\2\37l\3\2\2\2"+
		"!n\3\2\2\2#q\3\2\2\2%u\3\2\2\2\'\u0096\3\2\2\2)\u00a0\3\2\2\2+\u00a2\3"+
		"\2\2\2-\u00a8\3\2\2\2/\u00b2\3\2\2\2\61\u00b7\3\2\2\2\63\u00bd\3\2\2\2"+
		"\65\u00c0\3\2\2\2\67\u00c6\3\2\2\29\u00d1\3\2\2\2;<\7\u00a9\2\2<\4\3\2"+
		"\2\2=>\7u\2\2>?\7g\2\2?@\7n\2\2@A\7h\2\2A\6\3\2\2\2BC\7v\2\2CD\7t\2\2"+
		"DE\7w\2\2EF\7g\2\2F\b\3\2\2\2GH\7h\2\2HI\7c\2\2IJ\7n\2\2JK\7u\2\2KL\7"+
		"g\2\2L\n\3\2\2\2MN\7v\2\2NO\7j\2\2OP\7k\2\2PQ\7u\2\2QR\7E\2\2RS\7q\2\2"+
		"ST\7p\2\2TU\7v\2\2UV\7g\2\2VW\7z\2\2WX\7v\2\2X\f\3\2\2\2YZ\7<\2\2Z[\7"+
		"?\2\2[\16\3\2\2\2\\]\7?\2\2]\20\3\2\2\2^_\7~\2\2_\22\3\2\2\2`a\7*\2\2"+
		"a\24\3\2\2\2bc\7+\2\2c\26\3\2\2\2de\7]\2\2e\30\3\2\2\2fg\7_\2\2g\32\3"+
		"\2\2\2hi\7<\2\2i\34\3\2\2\2jk\7\60\2\2k\36\3\2\2\2lm\t\2\2\2m \3\2\2\2"+
		"no\t\3\2\2o\"\3\2\2\2pr\t\4\2\2qp\3\2\2\2r$\3\2\2\2sv\5#\22\2tv\7a\2\2"+
		"us\3\2\2\2ut\3\2\2\2v|\3\2\2\2w{\5#\22\2x{\7a\2\2y{\5!\21\2zw\3\2\2\2"+
		"zx\3\2\2\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}&\3\2\2\2~|\3\2\2\2"+
		"\177\u0081\7/\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3"+
		"\2\2\2\u0082\u0083\5)\25\2\u0083\u0085\7\60\2\2\u0084\u0086\t\3\2\2\u0085"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u008a\3\2\2\2\u0089\u008b\5+\26\2\u008a\u0089\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u0097\3\2\2\2\u008c\u008e\7/\2\2\u008d\u008c\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\5)\25\2\u0090"+
		"\u0091\5+\26\2\u0091\u0097\3\2\2\2\u0092\u0094\7/\2\2\u0093\u0092\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\5)\25\2\u0096"+
		"\u0080\3\2\2\2\u0096\u008d\3\2\2\2\u0096\u0093\3\2\2\2\u0097(\3\2\2\2"+
		"\u0098\u00a1\7\62\2\2\u0099\u009d\t\5\2\2\u009a\u009c\t\3\2\2\u009b\u009a"+
		"\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u0098\3\2\2\2\u00a0\u0099\3\2"+
		"\2\2\u00a1*\3\2\2\2\u00a2\u00a4\t\6\2\2\u00a3\u00a5\t\7\2\2\u00a4\u00a3"+
		"\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\5)\25\2\u00a7"+
		",\3\2\2\2\u00a8\u00ad\7$\2\2\u00a9\u00ac\5/\30\2\u00aa\u00ac\n\b\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0"+
		"\u00b1\7$\2\2\u00b1.\3\2\2\2\u00b2\u00b5\7^\2\2\u00b3\u00b6\t\t\2\2\u00b4"+
		"\u00b6\5\61\31\2\u00b5\u00b3\3\2\2\2\u00b5\u00b4\3\2\2\2\u00b6\60\3\2"+
		"\2\2\u00b7\u00b8\7w\2\2\u00b8\u00b9\5\63\32\2\u00b9\u00ba\5\63\32\2\u00ba"+
		"\u00bb\5\63\32\2\u00bb\u00bc\5\63\32\2\u00bc\62\3\2\2\2\u00bd\u00be\t"+
		"\n\2\2\u00be\64\3\2\2\2\u00bf\u00c1\t\13\2\2\u00c0\u00bf\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c5\b\33\2\2\u00c5\66\3\2\2\2\u00c6\u00c7\7\61\2\2\u00c7"+
		"\u00c8\7\61\2\2\u00c8\u00cc\3\2\2\2\u00c9\u00cb\n\f\2\2\u00ca\u00c9\3"+
		"\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\b\34\3\2\u00d08\3\2\2\2"+
		"\u00d1\u00d2\7\61\2\2\u00d2\u00d3\7,\2\2\u00d3\u00d7\3\2\2\2\u00d4\u00d6"+
		"\13\2\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d8\3\2\2\2"+
		"\u00d7\u00d5\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db"+
		"\7,\2\2\u00db\u00dc\7\61\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\b\35\4\2"+
		"\u00de:\3\2\2\2\26\2quz|\u0080\u0087\u008a\u008d\u0093\u0096\u009d\u00a0"+
		"\u00a4\u00ab\u00ad\u00b5\u00c2\u00cc\u00d7";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}