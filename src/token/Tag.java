package token;

public enum Tag {
	// End of source indicator
	EOS,
	
	// Illegal token
	ILLEGAL,
	
	// Punctuators
	LPAREN,
	RPAREN,
	LBRACK,
	RBRACK,
	LBRACE,
	RBRACE,
	COLON,
	SEMICOLON,
	PERIOD,
	ELLIPSIS,
	CONDITIONAL,
	INC,
	DEC,
	ARROW,
	
	// Assignment operators
	ASSIGN,
	ASSIGN_BIT_OR,
	ASSIGN_BIT_XOR,
	ASSIGN_BIT_AND,
	ASSIGN_SHL,
	ASSIGN_SHR,
	ASSIGN_ADD,
	ASSIGN_SUB,
	ASSIGN_MUL,
	ASSIGN_DIV,
	ASSIGN_MOD,
	
	// Binary operators sorted by precedence
	COMMA,
	OR,
	AND,
	BIT_OR,
	BIT_XOR,
	BIT_AND,
	SHL,
	SHR,
	ADD,
	SUB,
	MUL,
	DIV,
	MOD,
	
	// Compare operators sorted by precedence
	EQ,
	NE,
	LT,
	GT,
	LTE,
	GTE,
	
	// Unary operators
	NOT,
	BIT_NOT,
	TYPEOF,
	
	// Identifier
	IDENTIFIER,
	
	// Reserved words
	AS,
	BREAK,
	CATCH,
	CLASS,
	CONTINUE,
	DEFAULT,
	DO,
	ELSE,
	ENUM,
	EXPORT,
	FINALLY,
	FOR,
	FROM,
	IF,
	IMPORT,
	MODULE,
	NEW,
	OF,
	PRIVATE,
	PROTECTED,
	PUBLIC,
	RETURN,
	THIS,
	TYPE,
	WHILE,
	
	// Literals
	NULL_LITERAL,
	TRUE_LITERAL,
	FALSE_LITERAL,
	
	// Scanner-internal use only
	WHITESPACE
}
