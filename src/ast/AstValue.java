package ast;

public class AstValue {
	
	public AstValue(String str) {
		type_ = AstValueType.STRING;
		nativeObj_ = new String(str);
	}
	
	public AstValue(char ch) {
		type_ = AstValueType.CHARACTER;
		nativeObj_ = new Character(ch);
	}
	
	public AstValue(byte i8) {
		type_ = AstValueType.INTEGER;
		nativeObj_ = new Byte(i8);
	}
	
	public AstValue(short i16) {
		type_ = AstValueType.INTEGER;
		nativeObj_ = new Short(i16);
	}

	public AstValue(int i32) {
		type_ = AstValueType.INTEGER;
		nativeObj_ = new Integer(i32);
	}
	
	public AstValue(long i64) {
		type_ = AstValueType.INTEGER;
		nativeObj_ = new Long(i64);
	}
	
	public AstValue(float f32) {
		type_ = AstValueType.NUMBER;
		nativeObj_ = new Float(f32);
	}
	
	public AstValue(double f64) {
		type_ = AstValueType.NUMBER;
		nativeObj_ = new Double(f64);
	}
	
	public AstValue(boolean bool) {
		type_ = AstValueType.BOOLEAN;
		nativeObj_ = new Boolean(bool);
	}
	
	public AstValue() {
		type_ = AstValueType.NULL;
		nativeObj_ = null;
	}
	
	public boolean isString() {
		return type_ == AstValueType.STRING;
	}
	
	public boolean isInteger() {
		return type_ == AstValueType.INTEGER;
	}
	
	public boolean isNumber() {
		return type_ == AstValueType.NUMBER;
	}
	
	public boolean isCharacter() {
		return type_ == AstValueType.CHARACTER;
	}
	
	public boolean isBoolean() {
		return type_ == AstValueType.BOOLEAN;
	}
	
	public boolean isNull() {
		return type_ == AstValueType.NULL;
	}
	
	public Object getNativeObject() {
		return nativeObj_;
	}
	
	private AstValueType type_;
	private Object nativeObj_;

}
