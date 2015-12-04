package token;

import java.util.HashMap;

public class Keyword extends Token {

	private Keyword(Tag tag, String literal) {
		super(tag, literal);
	}
	
	@Override
	public String toString() {
		return "keyword \"" + super.getLiteral() + '"'; 
	}
	
	private static final HashMap<String, Token> keywords_ = new HashMap<String, Token>();
	
	private static void reserve(String word, Tag tag) {
		keywords_.put(word, new Keyword(tag, word));
	}
	
	private static void reserve(String word, Token token) {
		keywords_.put(word, token);
	}
	
	public static boolean isKeyword(String iden) {
		return keywords_.containsKey(iden);
	}
	
	public static Token getKeywordToken(String iden) {
		return keywords_.get(iden);
	}
	
	static {
		reserve("as", Keyword.AS);
		reserve("break", Keyword.BREAK);
		reserve("byte", Keyword.BYTE);
		reserve("catch", Keyword.CATCH);
		reserve("char", Keyword.CHAR);
		reserve("class", Keyword.CLASS);
		reserve("continue", Keyword.CONTINUE);
		reserve("default", Keyword.DEFAULT);
		reserve("do", Keyword.DO);
		reserve("double", Keyword.DOUBLE);
		reserve("else", Keyword.ELSE);
		reserve("enum", Keyword.ENUM);
		reserve("export", Keyword.EXPORT);
		reserve("finally", Keyword.FINALLY);
		reserve("for", Keyword.FOR);
		reserve("from", Keyword.FROM);
		reserve("float", Keyword.FLOAT);
		reserve("if", Keyword.IF);
		reserve("import", Keyword.IMPORT);
		reserve("int", Keyword.INT);
		reserve("long", Keyword.LONG);
		reserve("module", Keyword.MODULE);
		reserve("new", Keyword.NEW);
		reserve("of", Keyword.OF);
		reserve("private", Keyword.PRIVATE);
		reserve("protected", Keyword.PROTECTED);
		reserve("public", Keyword.PUBLIC);
		reserve("return", Keyword.RETURN);
		reserve("short", Keyword.SHORT);
		reserve("string", Keyword.STRING);
		reserve("this", Keyword.THIS);
		reserve("type", Keyword.TYPE);
		reserve("typeof", UnaryOp.TYPEOF);
		reserve("while", Keyword.WHILE);
		reserve("unsigned", Keyword.UNSIGNED);
		reserve("null", Tag.NULL_LITERAL);
		reserve("true", BoolValue.TRUE);
		reserve("false", BoolValue.FALSE);
	}
	
	public static final Keyword AS = new Keyword(Tag.AS, "as");
	public static final Keyword BREAK = new Keyword(Tag.BREAK, "break");
	public static final Keyword BYTE = new Keyword(Tag.BYTE, "byte");
	public static final Keyword CATCH = new Keyword(Tag.CATCH, "catch");
	public static final Keyword CHAR = new Keyword(Tag.CHAR, "char");
	public static final Keyword CLASS = new Keyword(Tag.CLASS, "class");
	public static final Keyword CONTINUE = new Keyword(Tag.CONTINUE, "continue");
	public static final Keyword DEFAULT = new Keyword(Tag.DEFAULT, "default");
	public static final Keyword DO = new Keyword(Tag.DO, "do");
	public static final Keyword DOUBLE = new Keyword(Tag.DOUBLE, "double");
	public static final Keyword ELSE = new Keyword(Tag.ELSE, "else");
	public static final Keyword ENUM = new Keyword(Tag.ENUM, "enum");
	public static final Keyword EXPORT = new Keyword(Tag.EXPORT, "export");
	public static final Keyword FINALLY = new Keyword(Tag.FINALLY, "finally");
	public static final Keyword FLOAT = new Keyword(Tag.FLOAT, "float");
	public static final Keyword FOR = new Keyword(Tag.FOR, "for");
	public static final Keyword FROM = new Keyword(Tag.FROM, "from");
	public static final Keyword IF = new Keyword(Tag.IF, "if");
	public static final Keyword IMPORT = new Keyword(Tag.IMPORT, "import");
	public static final Keyword INT = new Keyword(Tag.INT, "int");
	public static final Keyword LONG = new Keyword(Tag.LONG, "long");
	public static final Keyword MODULE = new Keyword(Tag.MODULE, "module");
	public static final Keyword NEW = new Keyword(Tag.NEW, "new");
	public static final Keyword OF = new Keyword(Tag.OF, "of");
	public static final Keyword PRIVATE = new Keyword(Tag.PRIVATE, "private");
	public static final Keyword PROTECTED = new Keyword(Tag.PROTECTED, "protected");
	public static final Keyword PUBLIC = new Keyword(Tag.PUBLIC, "public");
	public static final Keyword RETURN = new Keyword(Tag.RETURN, "return");
	public static final Keyword SHORT = new Keyword(Tag.SHORT, "short");
	public static final Keyword STRING = new Keyword(Tag.STRING, "string");
	public static final Keyword THIS = new Keyword(Tag.THIS, "this");
	public static final Keyword TYPE = new Keyword(Tag.TYPE, "type");
	public static final Keyword UNSIGNED = new Keyword(Tag.UNSIGNED, "unsigned");
	public static final Keyword WHILE = new Keyword(Tag.WHILE, "while");

}
