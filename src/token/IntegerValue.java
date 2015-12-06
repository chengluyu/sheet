package token;

public class IntegerValue extends Value {
	
	public static enum Type {
		none,
		int8,
		uint8,
		int16,
		uint16,
		int32,
		uint32,
		int64,
		uint64,
	}
	
	public IntegerValue(String literal, Type type) {
		super(Tag.INTEGER, literal);
	}
	
	private Type type_;
	private String textLiteral_;
	
	@Override
	public Object getNativeObject() {
		switch (type_) {
		case int16:
		case uint16:
			return Short.parseShort(textLiteral_);
		case int32:
		case uint32:
			return Integer.parseInt(textLiteral_);
		case int64:
		case uint64:
			return Long.parseLong(textLiteral_);
		case int8:
		case uint8:
			return Byte.parseByte(textLiteral_);
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		return String.format("integer [value=%s]", textLiteral_);
	}
	
}
