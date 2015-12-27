package lexer;

public enum Tag {

	EOS("end of source", TokenType.EOS),
	
	ILLEGAL("illegal", TokenType.ILLEGAL),

	LPAREN("(", TokenType.OPERATOR, Tag.LBP_REFINEMENT),
	RPAREN(")", TokenType.OPERATOR),
	LBRACK("[", TokenType.OPERATOR, Tag.LBP_REFINEMENT),
	RBRACK("]", TokenType.OPERATOR),
	LBRACE("{", TokenType.OPERATOR),
	RBRACE("}", TokenType.OPERATOR),
	COLON(":", TokenType.OPERATOR),
	SEMICOLON(";", TokenType.OPERATOR),
	PERIOD(".", TokenType.OPERATOR, Tag.LBP_REFINEMENT),
	ELLIPSIS("...", TokenType.OPERATOR),
	CONDITIONAL("?", TokenType.OPERATOR, Tag.LBP_CONDITIONAL),
	INC("++", TokenType.OPERATOR, Tag.LBP_UNARY),
	DEC("--", TokenType.OPERATOR, Tag.LBP_UNARY),
	POSTFIX_INC("<postfix> ++", TokenType.AST_ONLY),
	POSTFIX_DEC("<postfix> --", TokenType.AST_ONLY),

	INIT_LET("<init-let>", TokenType.AST_ONLY),
	INIT_CONST("<init-const>", TokenType.AST_ONLY),
	ASSIGN("=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_BIT_OR("|=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_BIT_XOR("^=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_BIT_AND("&=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_SHL("<<=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_SHR(">>=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_SAR(">>>=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_ADD("+=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_SUB("-=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_MUL("*=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_DIV("/=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),
	ASSIGN_MOD("%=", TokenType.OPERATOR, Tag.LBP_ASSIGNMENT),

	COMMA(",", TokenType.OPERATOR, Tag.LBP_COMMA),
	OR("||", TokenType.OPERATOR, Tag.LBP_OR),
	AND("&&", TokenType.OPERATOR, Tag.LBP_AND),
	BIT_OR("|", TokenType.OPERATOR, Tag.LBP_BIT_OR),
	BIT_XOR("^", TokenType.OPERATOR, Tag.LBP_BIT_XOR),
	BIT_AND("&", TokenType.OPERATOR, Tag.LBP_BIT_AND),
	SHL("<<", TokenType.OPERATOR, Tag.LBP_BITWISE),
	SHR(">>", TokenType.OPERATOR, Tag.LBP_BITWISE),
	SAR(">>>", TokenType.OPERATOR, Tag.LBP_BITWISE),
	ADD("+", TokenType.OPERATOR, Tag.LBP_FACTOR),
	SUB("-", TokenType.OPERATOR, Tag.LBP_FACTOR),
	MUL("*", TokenType.OPERATOR, Tag.LBP_TERM),
	DIV("/", TokenType.OPERATOR, Tag.LBP_TERM),
	MOD("%", TokenType.OPERATOR, Tag.LBP_TERM),
	
	EQ("==", TokenType.OPERATOR, Tag.LBP_EQUALITY),
	NE("!=", TokenType.OPERATOR, Tag.LBP_EQUALITY),
	LT("<", TokenType.OPERATOR, Tag.LBP_INEQUALITY),
	GT(">", TokenType.OPERATOR, Tag.LBP_INEQUALITY),
	LTE("<=", TokenType.OPERATOR, Tag.LBP_INEQUALITY),
	GTE(">=", TokenType.OPERATOR, Tag.LBP_INEQUALITY),
	
	NOT("!", TokenType.OPERATOR, Tag.LBP_UNARY),
	BIT_NOT("~", TokenType.OPERATOR, Tag.LBP_UNARY),
	
	IDENTIFIER("identifier", TokenType.IDENTIFIER),
	
	AS("as", TokenType.KEYWORD),
	BREAK("break", TokenType.KEYWORD),
	CASE("case", TokenType.KEYWORD),
	CATCH("catch", TokenType.KEYWORD),
	CLASS("class", TokenType.KEYWORD),
	CONST("const", TokenType.KEYWORD),
	CONTINUE("continue", TokenType.KEYWORD),
	DEFAULT("default", TokenType.KEYWORD),
	DO("do", TokenType.KEYWORD),
	ELSE("else", TokenType.KEYWORD),
	ENUM("enum", TokenType.KEYWORD),
	EXPORT("export", TokenType.KEYWORD),
	FINALLY("finally", TokenType.KEYWORD),
	FOR("for", TokenType.KEYWORD),
	FOREACH("foreach", TokenType.KEYWORD),
	FROM("from", TokenType.KEYWORD),
	FUNCTION("function", TokenType.KEYWORD),
	IF("if", TokenType.KEYWORD),
	IMPORT("import", TokenType.KEYWORD),
	IN("in", TokenType.KEYWORD),
	LET("let", TokenType.KEYWORD),
	NEW("new", TokenType.KEYWORD),
	PRIVATE("private", TokenType.KEYWORD),
	PROTECTED("protected", TokenType.KEYWORD),
	PUBLIC("public", TokenType.KEYWORD),
	RETURN("return", TokenType.KEYWORD),
	SUPER("super", TokenType.KEYWORD),
	SWITCH("switch", TokenType.KEYWORD),
	THIS("this", TokenType.KEYWORD),
	WHILE("while", TokenType.KEYWORD),
	
	NULL_LITERAL("null", TokenType.KEYWORD),
	TRUE_LITERAL("true", TokenType.KEYWORD),
	FALSE_LITERAL("false", TokenType.KEYWORD),

	STRING_LITERAL("string literal", TokenType.LITERAL),
	CHAR_LITERAL("char literal", TokenType.LITERAL),
	INTEGER("integer literal", TokenType.LITERAL),
	NUMBER("number literal", TokenType.LITERAL);

	private Tag(String literal, TokenType type) {
		literal_ = literal;
		type_ = type;
		lbp_ = LBP_NONE;
	}

	private Tag(String literal, TokenType type, int lbp) {
		literal_ = literal;
		type_ = type;
		lbp_ = lbp;
	}

	private final String literal_;
	private final TokenType type_;
	private final int lbp_;

	// Left binding powers

	private static final int LBP_NONE = -1;
	private static final int LBP_COMMA = 0;
	private static final int LBP_ASSIGNMENT = 20;
	private static final int LBP_CONDITIONAL = 30;
	private static final int LBP_OR = 40;
	private static final int LBP_AND = 50;
	private static final int LBP_BIT_OR = 60;
	private static final int LBP_BIT_XOR = 70;
	private static final int LBP_BIT_AND = 80;
	private static final int LBP_EQUALITY = 90;
	private static final int LBP_INEQUALITY = 100;
	private static final int LBP_BITWISE = 110;
	private static final int LBP_FACTOR = 120;
	private static final int LBP_TERM = 130;
	private static final int LBP_UNARY = 140;
	private static final int LBP_REFINEMENT = 150;

	// Properties

	public final String literal() {
		return literal_;
	}

	public final int lbp() {
		return lbp_;
	}

	public final TokenType type() {
		return type_;
	}

}