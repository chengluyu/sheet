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
	
	private static final HashMap<String, Keyword> keywords_ = new HashMap<String, Keyword>();
	
	private static void reserve(String word, Tag tag) {
		keywords_.put(word, new Keyword(tag, word));
	}
	
	public static boolean isKeyword(String iden) {
		return keywords_.containsKey(iden);
	}
	
	public static Keyword getKeywordToken(String iden) {
		return keywords_.get(iden);
	}
	
	static {
		reserve("as",Tag.AS);
		reserve("class",Tag.CLASS);
		reserve("enum",Tag.ENUM);
		reserve("export",Tag.EXPORT);
		reserve("from",Tag.FROM);
		reserve("import",Tag.IMPORT);
		reserve("module",Tag.MODULE);
		reserve("of",Tag.OF);
		reserve("private",Tag.PRIVATE);
		reserve("protected",Tag.PROTECTED);
		reserve("public",Tag.PUBLIC);
		reserve("type",Tag.TYPE);
	}

}
