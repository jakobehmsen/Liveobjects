grammar lang;

expressions             : expression*;
expression              : assignment | expression1;
assignment              : ID op=(SELF_ASSIGN | FRAME_ASSIGN) expression;
behaviorSelector        : kwSelector | unarySelector | binarySelector;
kwSelector              : (kwSelectorKW kwSelectorParam)+;
kwSelectorKW            : ID COLON;
kwSelectorParam         : ID;
unarySelector           : ID;
binarySelector          : BIN_OP ID;
parentSelector          : ASTERISK ID;
behaviorBody            : expression;
expression1             : expression2 keyAndArg*;
keyAndArg               : ID COLON expression2;
expression2             : expression3 binaryMessage*;
binaryMessage           : BIN_OP expression3;
expression3             : expression4 unaryMessage*;
unaryMessage            : DOT ID;
expression4             : number | string | identifier | block | 
                          embeddedExpression | self | thisContext |
                          bool | objectLiteral | nil;
number                  : NUMBER;
string                  : STRING;
identifier              : ID;
block                   : OPEN_SQ blockParams? expressions CLOSE_SQ;
blockParams             : ID+ PIPE;
embeddedExpression      : OPEN_PAR expression CLOSE_PAR;
self                    : KW_SELF;
thisContext             : KW_THIS_CONTEXT;
bool                    : boolTrue | boolFalse;
boolTrue                : KW_TRUE;
boolFalse               : KW_FALSE;
objectLiteral           : OPEN_BRA objectSlot* CLOSE_BRA;
objectSlot              : objectSlotUnquoted | objectSlotQuoted;
objectSlotUnquoted      : ASTERISK? id=ID FRAME_ASSIGN expression1;
objectSlotQuoted        : behaviorSelector OPEN_BRA expressions CLOSE_BRA;
nil                     : KW_NIL;

unaryMessageSend        : receiver=expression3 selector=unaryMessage;

KW_SELF: 'self';
KW_TRUE: 'true';
KW_FALSE: 'false';
KW_THIS_CONTEXT: 'thisContext';
KW_NIL: 'nil';
SELF_ASSIGN: '=';
FRAME_ASSIGN: ':=';
PIPE: '|';
OPEN_PAR: '(';
CLOSE_PAR: ')'; 
OPEN_SQ: '[';
CLOSE_SQ: ']';
OPEN_BRA: '{';
CLOSE_BRA: '}'; 
COLON: ':';
DOT: '.';
ASTERISK: '*';
BIN_OP: '+' | '-' | ASTERISK | '/' | '>' | '<' | '%';
fragment DIGIT: [0-9];
fragment LETTER: [A-Z]|[a-z];
ID: (LETTER | '_') (LETTER | '_' | DIGIT)*;
NUMBER
    :   '-'? INT '.' [0-9]+ EXP? // 1.35, 1.35E-9, 0.3, -4.5
    |   '-'? INT EXP             // 1e10 -3e4
    |   '-'? INT                 // -3, 45
    ;
fragment INT :   '0' | [1-9] [0-9]* ; // no leading zeros
fragment EXP :   [Ee] [+\-]? INT ; // \- since - means "range" inside [...]
STRING :  '"' (ESC | ~["\\])* '"';
fragment ESC:   '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE: 'u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];
WS                  : [ \t\n\r]+ -> channel(2) ;
SINGLE_LINE_COMMENT : '//' ~('\r' | '\n')* -> skip;
MULTI_LINE_COMMENT  : '/*' .*? '*/' -> skip;