package token;

public class Illegal extends Token {

	public Illegal(Tag tag, String description) {
		super(Tag.ILLEGAL, "illegal token");
		description_ = description;
	}
	
	public String getDescription() {
		return description_;
	}
	
	private final String description_;
	
}
