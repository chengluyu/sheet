package token;

public class Keyword extends Token {

	public Keyword(Tag tag, String literal) {
		super(tag, literal);
	}
	
	@Override
	public String toString() {
		return "keyword \"" + super.getLiteral() + '"'; 
	}
	
	// public static final Keyword ## = new Keyword(##, "##"); 

}
