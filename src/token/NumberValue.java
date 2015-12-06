package token;

public class NumberValue extends Value {
	
	public static enum Type {
		float32,
		float64
	}

	public NumberValue(String literal, Type type) {
		super(Tag.NUMBER, literal);
		type_ = type;
	}
	
	private Type type_;
	private String textLiteral_;

	@Override
	public Object getNativeObject() {
		if (type_ == Type.float32)
			return Float.parseFloat(textLiteral_);
		else if (type_ == Type.float64)
			return Double.parseDouble(textLiteral_);
		else
			return null;
	}
	
	@Override
	public String toString() {
		return String.format("number [value=%s]", textLiteral_);
	}

}
