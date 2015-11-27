package token;

public class Identifier extends Token {

	public Identifier(String literal) {
		super(Tag.IDENTIFIER, literal);
	}

}
