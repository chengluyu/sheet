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
	private long data_;
}
