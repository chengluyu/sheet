package type;

import token.Token;

public abstract class PrimitiveType extends Type {
	
	protected PrimitiveType(String name) {
		typeName_ = name;
	}
	
	private String typeName_;
	
	@Override
	public final String getName() {
		return typeName_;
	}
	
	public boolean isIntegral() {
		return false;
	}
	
	public boolean isRealDomain() {
		return false;
	}
	
	public boolean isBoolean() {
		return false;
	}
	
	public boolean isString() {
		return false;
	}
	
	public boolean isCharacter() {
		return false;
	}
	
	public static final IntegralType BYTE = new IntegralType("byte");
	public static final IntegralType SHORT = new IntegralType("short");
	public static final IntegralType INT = new IntegralType("int");
	public static final IntegralType LONG = new IntegralType("long");
	public static final IntegralType UBYTE = new IntegralType("unsigned byte");
	public static final IntegralType USHORT = new IntegralType("unsigned short");
	public static final IntegralType UINT = new IntegralType("unsigned int");
	public static final IntegralType ULONG = new IntegralType("unsigned long");
	
	public static final RealNumberType FLOAT = new RealNumberType("float");
	public static final RealNumberType DOUBLE = new RealNumberType("double");
	
	public static final BooleanType BOOL = new BooleanType("bool");
	
	public static final CharacterType CHAR = new CharacterType("char");
	public static final StringType STRING = new StringType("string");
	
	public static PrimitiveType fromToken(Token token, boolean unsigned) {
		switch (token.getTag()) {
		case BYTE:
			return unsigned ? UBYTE : BYTE;
		case SHORT:
			return unsigned ? USHORT : SHORT;
		case INT:
			return unsigned ? UINT : INT;
		case LONG:
			return unsigned ? ULONG : LONG;
		case FLOAT:
			return FLOAT;
		case DOUBLE:
			return DOUBLE;
		case BOOL:
			return BOOL;
		case CHAR:
			return CHAR;
		case STRING:
			return STRING;
		default:
			// TODO add error handler here
			assert false;
			return null;
		}
	}
	
}
