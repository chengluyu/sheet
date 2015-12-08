package token;

public abstract class Value extends Token {

	public Value(Tag tag, String literal) {
		super(tag, literal);
	}
	
	public abstract Object getNativeObject();
	
	@Override
	public boolean isValue() {
		return true;
	}

}
