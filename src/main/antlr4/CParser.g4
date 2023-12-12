grammar CParser;

translationUnit
    : externalDeclaration+ EOF
    ;

externalDeclaration:
    structDefinition
    | unionDefinition
    | functionDefinition
    | enumDefinition
    | declaration
    ;


structDefinition:
    Struct Identifier?  LeftBrace structDeclaration+ RightBrace SemiColon
    | Struct Identifier SemiColon
    ;

unionDefinition:
    Union Identifier? LeftBrace structDeclaration+ RightBrace SemiColon
    | Union Identifier SemiColon
    ;

enumDefinition:
    Enum Identifier? LeftBrace enumeratorList RightBrace SemiColon
    | Enum Identifier LeftBrace enumeratorList Comma RightBrace SemiColon
    | Enum Identifier SemiColon
    ;

functionDefinition
    : declarationSpecifiers declarator compoundStatement
    ;


declaration:
    declarationSpecifiers initDeclaratorList SemiColon
    ;


declarationSpecifiers:
    storageClassSpecifier declarationSpecifiers?
    | typeSpecifier declarationSpecifiers?
    | typeQualifier declarationSpecifiers?
    ;

storageClassSpecifier:
    Extern
    | Static
    | Register

    ;

typeSpecifier:
    Void
    | Char
    | Short
    | Int
    | Long
    | Float
    | Double
    | Signed
    | Unsigned
    | structSpecifier
    | unionSpecifier
    | enumSpecifier
    ;

structSpecifier:
    Struct Identifier
    ;

unionSpecifier:
    Union Identifier
    ;
    


enumSpecifier:
    Enum Identifier
    ;

enumeratorList
    : enumerator (Comma enumerator)*
    ;

enumerator
    : Identifier (Assign IntegerConstant)?
    ;


typeQualifier:
    Const
    | Volatile
    ;

structDeclaration:
    specifierQualifierList declarator SemiColon
    ;

specifierQualifierList:
    typeSpecifier specifierQualifierList?
    | typeQualifier specifierQualifierList?
    ;


declarator:
    pointer? directDeclarator
    ;

directDeclarator:
    Identifier // Variable
    | Identifier LeftBracket IntegerConstant RightBracket // Array
    | Identifier LeftBracket RightBracket // Array arg with no size
    | Identifier LeftParen parameterList? RightParen // Function
    ;

pointer:
    Star typeQualifierList? pointer?
    ;

typeQualifierList:
    typeQualifier+
    ;

parameterList:
    parameterDeclaration (Comma parameterDeclaration)*
    ;

parameterDeclaration:
    declarationSpecifiers declarator
    ;

initDeclaratorList:
    initDeclarator (Comma initDeclarator)*
    ;

initDeclarator:
    declarator
    | declarator Assign initializer
    ;

initializer:
    assignmentExpression
    | LeftBrace initializerList RightBrace
    | LeftBrace initializerList Comma RightBrace
    ;

initializerList:
    initializer (Comma initializer)*
    ;



statement:
    labeledStatement
    | expressionStatement
    | compoundStatement
    | selectionStatement
    | iterationStatement
    | jumpStatement
    ;

labeledStatement:
    Identifier Colon statement
    | Case constantExpression Colon statement
    | Default Colon statement
    ;

expressionStatement:
    expression? SemiColon
    ;

compoundStatement:
    LeftBrace blockItemList? RightBrace
    ;

blockItemList:
    blockItem+
    ;

blockItem:
    declaration
    | statement
    ;

selectionStatement:
    If LeftParen expression RightParen statement
    | If LeftParen expression RightParen statement Else statement
    | Switch LeftParen expression RightParen statement
    ;

iterationStatement:
    While LeftParen expression RightParen statement
    | Do statement While LeftParen expression RightParen SemiColon
    | For LeftParen expressionStatement expressionStatement expression? RightParen statement
    | For LeftParen declaration expressionStatement expression? RightParen statement
    ;

jumpStatement:
    Goto Identifier SemiColon
    | Continue SemiColon
    | Break SemiColon
    | Return expression? SemiColon
    ;

expression:
    assignmentExpression (Comma assignmentExpression)*
    ;

assignmentExpression:
    conditionalExpression
    | unaryExpression assignmentOperator assignmentExpression
    ;

assignmentOperator:
    Assign
    | StarAssign
    | DivAssign
    | ModAssign
    | PlusAssign
    | MinusAssign
    | LeftShiftAssign
    | RightShiftAssign
    | AndAssign
    | XorAssign
    | OrAssign
    ;

conditionalExpression:
    logicalOrExpression
    | logicalOrExpression Question expression Colon conditionalExpression
    ;

constantExpression:
    conditionalExpression
    ;

logicalOrExpression:
    logicalAndExpression
    | logicalOrExpression OrOr logicalAndExpression
    ;

logicalAndExpression:
    inclusiveOrExpression
    | logicalAndExpression AndAnd inclusiveOrExpression
    ;

inclusiveOrExpression:
    exclusiveOrExpression
    | inclusiveOrExpression Or exclusiveOrExpression
    ;

exclusiveOrExpression:
    andExpression
    | exclusiveOrExpression Caret andExpression
    ;

andExpression:
    equalityExpression
    | andExpression And equalityExpression
    ;

equalityExpression:
    relationalExpression
    | equalityExpression Equal relationalExpression
    | equalityExpression NotEqual relationalExpression
    ;

relationalExpression:
    shiftExpression
    | relationalExpression Less shiftExpression
    | relationalExpression Greater shiftExpression
    | relationalExpression LessEqual shiftExpression
    | relationalExpression GreaterEqual shiftExpression
    ;

shiftExpression:
    additiveExpression
    | shiftExpression LeftShift additiveExpression
    | shiftExpression RightShift additiveExpression
    ;

additiveExpression:
    multiplicativeExpression
    | additiveExpression Plus multiplicativeExpression
    | additiveExpression Minus multiplicativeExpression
    ;

multiplicativeExpression:
    castExpression
    | multiplicativeExpression Star castExpression
    | multiplicativeExpression Div castExpression
    | multiplicativeExpression Mod castExpression
    ;

castExpression:
    unaryExpression
    | LeftParen castType RightParen castExpression
    ;

castType:
    typeSpecifier
    | typeSpecifier pointer
    ;

unaryExpression:
    postfixExpression
    | PlusPlus unaryExpression
    | MinusMinus unaryExpression
    | unaryOperator castExpression
    | Sizeof unaryExpression
    | Sizeof LeftParen declarationSpecifiers RightParen
    ;

unaryOperator:
    And
    | Star
    | Plus
    | Minus
    | Tilde
    | Not
    ;

postfixExpression:
    primaryExpression
    | postfixExpression LeftBracket expression RightBracket
    | postfixExpression LeftParen argumentExpressionList? RightParen
    | postfixExpression Dot Identifier
    | postfixExpression Arrow Identifier
    | postfixExpression PlusPlus
    | postfixExpression MinusMinus
    ;

argumentExpressionList:
    assignmentExpression (Comma assignmentExpression)*
    ;

primaryExpression:
    Identifier
    | IntegerConstant
    | FloatingConstant
    | CharacterConstant
    | StringLiteral
    | LeftParen expression RightParen
    ;

SemiColon: ';';
Break
    : 'break'
    ;

Case
    : 'case'
    ;

Char
    : 'char'
    ;

Const
    : 'const'
    ;

Continue
    : 'continue'
    ;

Default
    : 'default'
    ;

Do
    : 'do'
    ;

Double
    : 'double'
    ;

Else
    : 'else'
    ;

Enum
    : 'enum'
    ;

Extern
    : 'extern'
    ;

Float
    : 'float'
    ;

For
    : 'for'
    ;

Goto
    : 'goto'
    ;

If
    : 'if'
    ;

Int
    : 'int'
    ;

Long
    : 'long'
    ;

Register
    : 'register'
    ;

Return
    : 'return'
    ;

Short
    : 'short'
    ;

Signed
    : 'signed'
    ;

Sizeof
    : 'sizeof'
    ;

Static
    : 'static'
    ;

Struct
    : 'struct'
    ;

Switch
    : 'switch'
    ;

Union
    : 'union'
    ;

Unsigned
    : 'unsigned'
    ;

Void
    : 'void'
    ;

Volatile
    : 'volatile'
    ;

While
    : 'while'
    ;

LeftParen
    : '('
    ;

RightParen
    : ')'
    ;

LeftBracket
    : '['
    ;

RightBracket
    : ']'
    ;

LeftBrace
    : '{'
    ;

RightBrace
    : '}'
    ;

Less
    : '<'
    ;

LessEqual
    : '<='
    ;

Greater
    : '>'
    ;

GreaterEqual
    : '>='
    ;

LeftShift
    : '<<'
    ;

RightShift
    : '>>'
    ;

Plus
    : '+'
    ;

PlusPlus
    : '++'
    ;

Minus
    : '-'
    ;

MinusMinus
    : '--'
    ;

Star
    : '*'
    ;

Div
    : '/'
    ;

Mod
    : '%'
    ;

And
    : '&'
    ;

Or
    : '|'
    ;

AndAnd
    : '&&'
    ;

OrOr
    : '||'
    ;

Caret
    : '^'
    ;

Not
    : '!'
    ;

Tilde
    : '~'
    ;

Question
    : '?'
    ;

Colon
    : ':'
    ;

Semi
    : SemiColon
    ;

Comma
    : ','
    ;

Assign
    : '='
    ;

// '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
StarAssign
    : '*='
    ;

DivAssign
    : '/='
    ;

ModAssign
    : '%='
    ;

PlusAssign
    : '+='
    ;

MinusAssign
    : '-='
    ;

LeftShiftAssign
    : '<<='
    ;

RightShiftAssign
    : '>>='
    ;

AndAssign
    : '&='
    ;

XorAssign
    : '^='
    ;

OrAssign
    : '|='
    ;

Equal
    : '=='
    ;

NotEqual
    : '!='
    ;

Arrow
    : '->'
    ;

Dot
    : '.'
    ;

Identifier
    : IdentifierNondigit (IdentifierNondigit | Digit)*
    ;

fragment IdentifierNondigit
    : Nondigit
    | UniversalCharacterName
    //|   // other implementation-defined characters...
    ;

fragment Nondigit
    : [a-zA-Z_]
    ;

fragment Digit
    : [0-9]
    ;

fragment UniversalCharacterName
    : '\\u' HexQuad
    | '\\U' HexQuad HexQuad
    ;

fragment HexQuad
    : HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit
    ;

IntegerConstant
    : DecimalConstant IntegerSuffix?
    | OctalConstant IntegerSuffix?
    | HexadecimalConstant IntegerSuffix?
    | BinaryConstant
    ;

fragment BinaryConstant
    : '0' [bB] [0-1]+
    ;

fragment DecimalConstant
    : NonzeroDigit Digit*
    ;

fragment OctalConstant
    : '0' OctalDigit*
    ;

fragment HexadecimalConstant
    : HexadecimalPrefix HexadecimalDigit+
    ;

fragment HexadecimalPrefix
    : '0' [xX]
    ;

fragment NonzeroDigit
    : [1-9]
    ;

fragment OctalDigit
    : [0-7]
    ;

fragment HexadecimalDigit
    : [0-9a-fA-F]
    ;

fragment IntegerSuffix
    : UnsignedSuffix LongSuffix?
    | UnsignedSuffix LongLongSuffix
    | LongSuffix UnsignedSuffix?
    | LongLongSuffix UnsignedSuffix?
    ;

fragment UnsignedSuffix
    : [uU]
    ;

fragment LongSuffix
    : [lL]
    ;

fragment LongLongSuffix
    : 'll'
    | 'LL'
    ;

FloatingConstant
    : DecimalFloatingConstant
    | HexadecimalFloatingConstant
    ;

fragment DecimalFloatingConstant
    : FractionalConstant ExponentPart? FloatingSuffix?
    | DigitSequence ExponentPart FloatingSuffix?
    ;

fragment HexadecimalFloatingConstant
    : HexadecimalPrefix (HexadecimalFractionalConstant | HexadecimalDigitSequence) BinaryExponentPart FloatingSuffix?
    ;

fragment FractionalConstant
    : DigitSequence? '.' DigitSequence
    | DigitSequence '.'
    ;

fragment ExponentPart
    : [eE] Sign? DigitSequence
    ;

fragment Sign
    : [+-]
    ;

DigitSequence
    : Digit+
    ;

fragment HexadecimalFractionalConstant
    : HexadecimalDigitSequence? '.' HexadecimalDigitSequence
    | HexadecimalDigitSequence '.'
    ;

fragment BinaryExponentPart
    : [pP] Sign? DigitSequence
    ;

fragment HexadecimalDigitSequence
    : HexadecimalDigit+
    ;

fragment FloatingSuffix
    : [flFL]
    ;

CharacterConstant
    : '\'' CCharSequence '\''
    | 'L\'' CCharSequence '\''
    | 'u\'' CCharSequence '\''
    | 'U\'' CCharSequence '\''
    ;

fragment CCharSequence
    : CChar+
    ;

fragment CChar
    : ~['\\\r\n]
    | EscapeSequence
    ;

fragment EscapeSequence
    : SimpleEscapeSequence
    | OctalEscapeSequence
    | HexadecimalEscapeSequence
    | UniversalCharacterName
    ;

fragment SimpleEscapeSequence
    : '\\' ['"?abfnrtv\\]
    ;

fragment OctalEscapeSequence
    : '\\' OctalDigit OctalDigit? OctalDigit?
    ;

fragment HexadecimalEscapeSequence
    : '\\x' HexadecimalDigit+
    ;

StringLiteral
    : EncodingPrefix? '"' SCharSequence? '"'
    ;

fragment EncodingPrefix
    : 'u8'
    | 'u'
    | 'U'
    | 'L'
    ;

fragment SCharSequence
    : SChar+
    ;

fragment SChar
    : ~["\\\r\n]
    | EscapeSequence
    | '\\\n'   // Added line
    | '\\\r\n' // Added line
    ;

MultiLineMacro
    : '#' (~[\n]*? '\\' '\r'? '\n')+ ~ [\n]+ -> channel (HIDDEN)
    ;

Directive
    : '#' ~ [\n]* -> channel (HIDDEN)
    ;

// ignore the following asm blocks:
/*
    asm
    {
        mfspr x, 286;
    }
 */

Whitespace
    : [ \t]+ -> channel(HIDDEN)
    ;

Newline
    : ('\r' '\n'? | '\n') -> channel(HIDDEN)
    ;

BlockComment
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

LineComment
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;