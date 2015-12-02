package token;

public class Punctuator extends Token {

	private Punctuator(Tag tag, String literal) {
		super(tag, literal);
	}
	
	@Override
	public String toString() {
		return String.format("punctuator [literal = '%s']", super.getLiteral());
	}
	
	public static final Punctuator LPAREN = new Punctuator(Tag.LPAREN, "(");
	public static final Punctuator RPAREN = new Punctuator(Tag.RPAREN, ")");
	public static final Punctuator LBRACK = new Punctuator(Tag.LBRACK, "[");
	public static final Punctuator RBRACK = new Punctuator(Tag.RBRACK, "]");
	public static final Punctuator LBRACE = new Punctuator(Tag.LBRACE, "{");
	public static final Punctuator RBRACE = new Punctuator(Tag.RBRACE, "}");
	public static final Punctuator COLON = new Punctuator(Tag.COLON, ":");
	public static final Punctuator SEMICOLON = new Punctuator(Tag.SEMICOLON, ";");
	public static final Punctuator PERIOD = new Punctuator(Tag.PERIOD, ".");
	public static final Punctuator ELLIPSIS = new Punctuator(Tag.ELLIPSIS, "...");
	public static final Punctuator CONDITIONAL = new Punctuator(Tag.CONDITIONAL, "?");
	public static final Punctuator INC = new Punctuator(Tag.INC, "++");
	public static final Punctuator DEC = new Punctuator(Tag.DEC, "--");
	public static final Punctuator ARROW = new Punctuator(Tag.ARROW, "=>");

}
