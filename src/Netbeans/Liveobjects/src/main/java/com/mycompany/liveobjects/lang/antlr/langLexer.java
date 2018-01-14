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
		KW_SELF=1, KW_TRUE=2, KW_FALSE=3, KW_THIS_CONTEXT=4, SELF_ASSIGN=5, FRAME_ASSIGN=6, 
		PIPE=7, OPEN_PAR=8, CLOSE_PAR=9, OPEN_SQ=10, CLOSE_SQ=11, OPEN_BRA=12, 
		CLOSE_BRA=13, COLON=14, DOT=15, BIN_OP=16, ID=17, NUMBER=18, STRING=19, 
		WS=20, SINGLE_LINE_COMMENT=21, MULTI_LINE_COMMENT=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'self'", "'true'", "'false'", "'thisContext'", "':='", "'='", "'|'", 
		"'('", "')'", "'['", "']'", "'{'", "'}'", "':'", "'.'", "BIN_OP", "ID", 
		"NUMBER", "STRING", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"KW_SELF", "KW_TRUE", "KW_FALSE", "KW_THIS_CONTEXT", "SELF_ASSIGN", "FRAME_ASSIGN", 
		"PIPE", "OPEN_PAR", "CLOSE_PAR", "OPEN_SQ", "CLOSE_SQ", "OPEN_BRA", "CLOSE_BRA", 
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
		case 26: WS_action((RuleContext)_localctx, actionIndex); break;

		case 27: SINGLE_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 28: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\30\u00e3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3"+
		"\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\5\23v\n\23\3\24\3\24\5\24z\n\24\3\24\3\24\3\24\7\24\177"+
		"\n\24\f\24\16\24\u0082\13\24\3\25\5\25\u0085\n\25\3\25\3\25\3\25\6\25"+
		"\u008a\n\25\r\25\16\25\u008b\3\25\5\25\u008f\n\25\3\25\5\25\u0092\n\25"+
		"\3\25\3\25\3\25\3\25\5\25\u0098\n\25\3\25\5\25\u009b\n\25\3\26\3\26\3"+
		"\26\7\26\u00a0\n\26\f\26\16\26\u00a3\13\26\5\26\u00a5\n\26\3\27\3\27\5"+
		"\27\u00a9\n\27\3\27\3\27\3\30\3\30\3\30\7\30\u00b0\n\30\f\30\16\30\u00b3"+
		"\13\30\3\30\3\30\3\31\3\31\3\31\5\31\u00ba\n\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\33\3\33\3\34\6\34\u00c5\n\34\r\34\16\34\u00c6\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\7\35\u00cf\n\35\f\35\16\35\u00d2\13\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\7\36\u00da\n\36\f\36\16\36\u00dd\13\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\u00db\37\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n"+
		"\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\2\1%\2"+
		"\1\'\23\1)\24\1+\2\1-\2\1/\25\1\61\2\1\63\2\1\65\2\1\67\26\29\27\3;\30"+
		"\4\3\2\r\b\2\'\',-//\61\61>>@@\3\2\62;\4\2C\\c|\3\2\63;\4\2GGgg\4\2--"+
		"//\4\2$$^^\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2\13\f\17\17\"\"\4\2"+
		"\f\f\17\17\u00ef\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2/\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2"+
		";\3\2\2\2\3=\3\2\2\2\5B\3\2\2\2\7G\3\2\2\2\tM\3\2\2\2\13Y\3\2\2\2\r\\"+
		"\3\2\2\2\17^\3\2\2\2\21`\3\2\2\2\23b\3\2\2\2\25d\3\2\2\2\27f\3\2\2\2\31"+
		"h\3\2\2\2\33j\3\2\2\2\35l\3\2\2\2\37n\3\2\2\2!p\3\2\2\2#r\3\2\2\2%u\3"+
		"\2\2\2\'y\3\2\2\2)\u009a\3\2\2\2+\u00a4\3\2\2\2-\u00a6\3\2\2\2/\u00ac"+
		"\3\2\2\2\61\u00b6\3\2\2\2\63\u00bb\3\2\2\2\65\u00c1\3\2\2\2\67\u00c4\3"+
		"\2\2\29\u00ca\3\2\2\2;\u00d5\3\2\2\2=>\7u\2\2>?\7g\2\2?@\7n\2\2@A\7h\2"+
		"\2A\4\3\2\2\2BC\7v\2\2CD\7t\2\2DE\7w\2\2EF\7g\2\2F\6\3\2\2\2GH\7h\2\2"+
		"HI\7c\2\2IJ\7n\2\2JK\7u\2\2KL\7g\2\2L\b\3\2\2\2MN\7v\2\2NO\7j\2\2OP\7"+
		"k\2\2PQ\7u\2\2QR\7E\2\2RS\7q\2\2ST\7p\2\2TU\7v\2\2UV\7g\2\2VW\7z\2\2W"+
		"X\7v\2\2X\n\3\2\2\2YZ\7<\2\2Z[\7?\2\2[\f\3\2\2\2\\]\7?\2\2]\16\3\2\2\2"+
		"^_\7~\2\2_\20\3\2\2\2`a\7*\2\2a\22\3\2\2\2bc\7+\2\2c\24\3\2\2\2de\7]\2"+
		"\2e\26\3\2\2\2fg\7_\2\2g\30\3\2\2\2hi\7}\2\2i\32\3\2\2\2jk\7\177\2\2k"+
		"\34\3\2\2\2lm\7<\2\2m\36\3\2\2\2no\7\60\2\2o \3\2\2\2pq\t\2\2\2q\"\3\2"+
		"\2\2rs\t\3\2\2s$\3\2\2\2tv\t\4\2\2ut\3\2\2\2v&\3\2\2\2wz\5%\23\2xz\7a"+
		"\2\2yw\3\2\2\2yx\3\2\2\2z\u0080\3\2\2\2{\177\5%\23\2|\177\7a\2\2}\177"+
		"\5#\22\2~{\3\2\2\2~|\3\2\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081(\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0085"+
		"\7/\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"\u0087\5+\26\2\u0087\u0089\7\60\2\2\u0088\u008a\t\3\2\2\u0089\u0088\3"+
		"\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008e\3\2\2\2\u008d\u008f\5-\27\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u009b\3\2\2\2\u0090\u0092\7/\2\2\u0091\u0090\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\5+\26\2\u0094\u0095\5-"+
		"\27\2\u0095\u009b\3\2\2\2\u0096\u0098\7/\2\2\u0097\u0096\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\5+\26\2\u009a\u0084\3\2"+
		"\2\2\u009a\u0091\3\2\2\2\u009a\u0097\3\2\2\2\u009b*\3\2\2\2\u009c\u00a5"+
		"\7\62\2\2\u009d\u00a1\t\5\2\2\u009e\u00a0\t\3\2\2\u009f\u009e\3\2\2\2"+
		"\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a5"+
		"\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u009c\3\2\2\2\u00a4\u009d\3\2\2\2\u00a5"+
		",\3\2\2\2\u00a6\u00a8\t\6\2\2\u00a7\u00a9\t\7\2\2\u00a8\u00a7\3\2\2\2"+
		"\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\5+\26\2\u00ab.\3"+
		"\2\2\2\u00ac\u00b1\7$\2\2\u00ad\u00b0\5\61\31\2\u00ae\u00b0\n\b\2\2\u00af"+
		"\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2"+
		"\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4"+
		"\u00b5\7$\2\2\u00b5\60\3\2\2\2\u00b6\u00b9\7^\2\2\u00b7\u00ba\t\t\2\2"+
		"\u00b8\u00ba\5\63\32\2\u00b9\u00b7\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\62"+
		"\3\2\2\2\u00bb\u00bc\7w\2\2\u00bc\u00bd\5\65\33\2\u00bd\u00be\5\65\33"+
		"\2\u00be\u00bf\5\65\33\2\u00bf\u00c0\5\65\33\2\u00c0\64\3\2\2\2\u00c1"+
		"\u00c2\t\n\2\2\u00c2\66\3\2\2\2\u00c3\u00c5\t\13\2\2\u00c4\u00c3\3\2\2"+
		"\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\u00c9\b\34\2\2\u00c98\3\2\2\2\u00ca\u00cb\7\61\2\2\u00cb"+
		"\u00cc\7\61\2\2\u00cc\u00d0\3\2\2\2\u00cd\u00cf\n\f\2\2\u00ce\u00cd\3"+
		"\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d3\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d4\b\35\3\2\u00d4:\3\2\2\2"+
		"\u00d5\u00d6\7\61\2\2\u00d6\u00d7\7,\2\2\u00d7\u00db\3\2\2\2\u00d8\u00da"+
		"\13\2\2\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00dc\3\2\2\2"+
		"\u00db\u00d9\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00df"+
		"\7,\2\2\u00df\u00e0\7\61\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\b\36\4\2"+
		"\u00e2<\3\2\2\2\26\2uy~\u0080\u0084\u008b\u008e\u0091\u0097\u009a\u00a1"+
		"\u00a4\u00a8\u00af\u00b1\u00b9\u00c6\u00d0\u00db";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}