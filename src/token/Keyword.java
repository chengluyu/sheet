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
		reserve("as", Tag.AS);
		reserve("break", Tag.BREAK);
		reserve("catch", Tag.CATCH);
		reserve("class", Tag.CLASS);
		reserve("continue", Tag.CONTINUE);
		reserve("default", Tag.DEFAULT);
		reserve("do", Tag.DO);
		reserve("else", Tag.ELSE);
		reserve("enum", Tag.ENUM);
		reserve("export", Tag.EXPORT);
		reserve("finally", Tag.FINALLY);
		reserve("for", Tag.FOR);
		reserve("from", Tag.FROM);
		reserve("if", Tag.IF);
		reserve("import", Tag.IMPORT);
		reserve("module", Tag.MODULE);
		reserve("new", Tag.NEW);
		reserve("of", Tag.OF);
		reserve("private", Tag.PRIVATE);
		reserve("protected", Tag.PROTECTED);
		reserve("public", Tag.PUBLIC);
		reserve("return", Tag.RETURN);
		reserve("this", Tag.THIS);
		reserve("type", Tag.TYPE);
		reserve("typeof", UnaryOp.TYPEOF);
		reserve("while", Tag.WHILE);
		reserve("null", Tag.NULL_LITERAL);
		reserve("true", BoolValue.TRUE);
		reserve("false", BoolValue.FALSE);
	}

}
