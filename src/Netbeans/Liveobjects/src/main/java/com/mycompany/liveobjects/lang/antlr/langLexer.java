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
		PROTOCOL=1, KW_SELF=2, KW_THIS_CONTEXT=3, SELF_ASSIGN=4, FRAME_ASSIGN=5, 
		PIPE=6, OPEN_PAR=7, CLOSE_PAR=8, OPEN_SQ=9, CLOSE_SQ=10, COLON=11, DOT=12, 
		BIN_OP=13, ID=14, NUMBER=15, STRING=16, WS=17, SINGLE_LINE_COMMENT=18, 
		MULTI_LINE_COMMENT=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'§'", "'self'", "'thisContext'", "':='", "'='", "'|'", "'('", "')'", 
		"'['", "']'", "':'", "'.'", "BIN_OP", "ID", "NUMBER", "STRING", "WS", 
		"SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"PROTOCOL", "KW_SELF", "KW_THIS_CONTEXT", "SELF_ASSIGN", "FRAME_ASSIGN", 
		"PIPE", "OPEN_PAR", "CLOSE_PAR", "OPEN_SQ", "CLOSE_SQ", "COLON", "DOT", 
		"BIN_OP", "DIGIT", "LETTER", "ID", "NUMBER", "INT", "EXP", "STRING", "ESC", 
		"UNICODE", "HEX", "WS", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
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
		case 23: WS_action((RuleContext)_localctx, actionIndex); break;

		case 24: SINGLE_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 25: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\25\u00d0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\5\20"+
		"c\n\20\3\21\3\21\5\21g\n\21\3\21\3\21\3\21\7\21l\n\21\f\21\16\21o\13\21"+
		"\3\22\5\22r\n\22\3\22\3\22\3\22\6\22w\n\22\r\22\16\22x\3\22\5\22|\n\22"+
		"\3\22\5\22\177\n\22\3\22\3\22\3\22\3\22\5\22\u0085\n\22\3\22\5\22\u0088"+
		"\n\22\3\23\3\23\3\23\7\23\u008d\n\23\f\23\16\23\u0090\13\23\5\23\u0092"+
		"\n\23\3\24\3\24\5\24\u0096\n\24\3\24\3\24\3\25\3\25\3\25\7\25\u009d\n"+
		"\25\f\25\16\25\u00a0\13\25\3\25\3\25\3\26\3\26\3\26\5\26\u00a7\n\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\6\31\u00b2\n\31\r\31\16\31"+
		"\u00b3\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u00bc\n\32\f\32\16\32\u00bf"+
		"\13\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u00c7\n\33\f\33\16\33\u00ca"+
		"\13\33\3\33\3\33\3\33\3\33\3\33\3\u00c8\34\3\3\1\5\4\1\7\5\1\t\6\1\13"+
		"\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\2\1"+
		"\37\2\1!\20\1#\21\1%\2\1\'\2\1)\22\1+\2\1-\2\1/\2\1\61\23\2\63\24\3\65"+
		"\25\4\3\2\r\b\2\'\',-//\61\61>>@@\3\2\62;\4\2C\\c|\3\2\63;\4\2GGgg\4\2"+
		"--//\4\2$$^^\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2\13\f\17\17\"\"\4"+
		"\2\f\f\17\17\u00dc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2)\3\2"+
		"\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\59\3\2\2\2\7"+
		">\3\2\2\2\tJ\3\2\2\2\13M\3\2\2\2\rO\3\2\2\2\17Q\3\2\2\2\21S\3\2\2\2\23"+
		"U\3\2\2\2\25W\3\2\2\2\27Y\3\2\2\2\31[\3\2\2\2\33]\3\2\2\2\35_\3\2\2\2"+
		"\37b\3\2\2\2!f\3\2\2\2#\u0087\3\2\2\2%\u0091\3\2\2\2\'\u0093\3\2\2\2)"+
		"\u0099\3\2\2\2+\u00a3\3\2\2\2-\u00a8\3\2\2\2/\u00ae\3\2\2\2\61\u00b1\3"+
		"\2\2\2\63\u00b7\3\2\2\2\65\u00c2\3\2\2\2\678\7\u00a9\2\28\4\3\2\2\29:"+
		"\7u\2\2:;\7g\2\2;<\7n\2\2<=\7h\2\2=\6\3\2\2\2>?\7v\2\2?@\7j\2\2@A\7k\2"+
		"\2AB\7u\2\2BC\7E\2\2CD\7q\2\2DE\7p\2\2EF\7v\2\2FG\7g\2\2GH\7z\2\2HI\7"+
		"v\2\2I\b\3\2\2\2JK\7<\2\2KL\7?\2\2L\n\3\2\2\2MN\7?\2\2N\f\3\2\2\2OP\7"+
		"~\2\2P\16\3\2\2\2QR\7*\2\2R\20\3\2\2\2ST\7+\2\2T\22\3\2\2\2UV\7]\2\2V"+
		"\24\3\2\2\2WX\7_\2\2X\26\3\2\2\2YZ\7<\2\2Z\30\3\2\2\2[\\\7\60\2\2\\\32"+
		"\3\2\2\2]^\t\2\2\2^\34\3\2\2\2_`\t\3\2\2`\36\3\2\2\2ac\t\4\2\2ba\3\2\2"+
		"\2c \3\2\2\2dg\5\37\20\2eg\7a\2\2fd\3\2\2\2fe\3\2\2\2gm\3\2\2\2hl\5\37"+
		"\20\2il\7a\2\2jl\5\35\17\2kh\3\2\2\2ki\3\2\2\2kj\3\2\2\2lo\3\2\2\2mk\3"+
		"\2\2\2mn\3\2\2\2n\"\3\2\2\2om\3\2\2\2pr\7/\2\2qp\3\2\2\2qr\3\2\2\2rs\3"+
		"\2\2\2st\5%\23\2tv\7\60\2\2uw\t\3\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy"+
		"\3\2\2\2y{\3\2\2\2z|\5\'\24\2{z\3\2\2\2{|\3\2\2\2|\u0088\3\2\2\2}\177"+
		"\7/\2\2~}\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\5%\23\2\u0081"+
		"\u0082\5\'\24\2\u0082\u0088\3\2\2\2\u0083\u0085\7/\2\2\u0084\u0083\3\2"+
		"\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\5%\23\2\u0087"+
		"q\3\2\2\2\u0087~\3\2\2\2\u0087\u0084\3\2\2\2\u0088$\3\2\2\2\u0089\u0092"+
		"\7\62\2\2\u008a\u008e\t\5\2\2\u008b\u008d\t\3\2\2\u008c\u008b\3\2\2\2"+
		"\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0092"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0089\3\2\2\2\u0091\u008a\3\2\2\2\u0092"+
		"&\3\2\2\2\u0093\u0095\t\6\2\2\u0094\u0096\t\7\2\2\u0095\u0094\3\2\2\2"+
		"\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\5%\23\2\u0098(\3"+
		"\2\2\2\u0099\u009e\7$\2\2\u009a\u009d\5+\26\2\u009b\u009d\n\b\2\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u00a2\7$\2\2\u00a2*\3\2\2\2\u00a3\u00a6\7^\2\2\u00a4\u00a7\t\t\2\2\u00a5"+
		"\u00a7\5-\27\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7,\3\2\2\2"+
		"\u00a8\u00a9\7w\2\2\u00a9\u00aa\5/\30\2\u00aa\u00ab\5/\30\2\u00ab\u00ac"+
		"\5/\30\2\u00ac\u00ad\5/\30\2\u00ad.\3\2\2\2\u00ae\u00af\t\n\2\2\u00af"+
		"\60\3\2\2\2\u00b0\u00b2\t\13\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2"+
		"\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6"+
		"\b\31\2\2\u00b6\62\3\2\2\2\u00b7\u00b8\7\61\2\2\u00b8\u00b9\7\61\2\2\u00b9"+
		"\u00bd\3\2\2\2\u00ba\u00bc\n\f\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bf\3\2"+
		"\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00c0\u00c1\b\32\3\2\u00c1\64\3\2\2\2\u00c2\u00c3\7\61"+
		"\2\2\u00c3\u00c4\7,\2\2\u00c4\u00c8\3\2\2\2\u00c5\u00c7\13\2\2\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c8\u00c6\3\2"+
		"\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cc\7,\2\2\u00cc"+
		"\u00cd\7\61\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\b\33\4\2\u00cf\66\3\2"+
		"\2\2\26\2bfkmqx{~\u0084\u0087\u008e\u0091\u0095\u009c\u009e\u00a6\u00b3"+
		"\u00bd\u00c8";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}