package token;

public class NumberValue extends Value {
	
	public static enum Type {
		none,
		float32,
		float64
	}

	public NumberValue(String literal) {
		super(Tag.NUMBER, literal);
	}

}
