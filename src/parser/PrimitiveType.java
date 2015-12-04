package parser;

import token.Keyword;
import token.Tag;

public class PrimitiveType extends Type {

	private PrimitiveType(String typeName) {
		typeName_ = typeName;
	}	
	
	private String typeName_;
	
	public static final PrimitiveType BYTE = new PrimitiveType("byte");
	public static final PrimitiveType SHORT = new PrimitiveType("short");
	public static final PrimitiveType INT = new PrimitiveType("int");
	public static final PrimitiveType LONG = new PrimitiveType("long");
	public static final PrimitiveType UBYTE = new PrimitiveType("unsigned byte");
	public static final PrimitiveType USHORT = new PrimitiveType("unsigned short");
	public static final PrimitiveType UINT = new PrimitiveType("unsigned int");
	public static final PrimitiveType ULONG = new PrimitiveType("unsigned long");
	public static final PrimitiveType CHAR = new PrimitiveType("char");
	public static final PrimitiveType STRING = new PrimitiveType("string");
	public static final PrimitiveType FLOAT = new PrimitiveType("float");
	public static final PrimitiveType DOUBLE = new PrimitiveType("double");

	@Override
	public String getTypeName() {
		return typeName_;
	}

	@Override
	public boolean equals(Object rhs) {
		return rhs == this;
	}

	public static PrimitiveType fromKeywordToken(Keyword keyword, boolean isUnsigned) {
		return fromTokenTag(keyword.getTag(), isUnsigned);
	}
	
	public static PrimitiveType fromTokenTag(Tag tag, boolean isUnsigned) {
		switch (tag) {
		case BYTE:
			return isUnsigned ? PrimitiveType.UBYTE : PrimitiveType.BYTE;
		case SHORT:
			return isUnsigned ? PrimitiveType.USHORT : PrimitiveType.SHORT;
		case INT:
			return isUnsigned ? PrimitiveType.UINT : PrimitiveType.INT;
		case LONG:
			return isUnsigned ? PrimitiveType.ULONG : PrimitiveType.LONG;
		case CHAR:
			return PrimitiveType.CHAR;
		case STRING:
			return PrimitiveType.STRING;
		case FLOAT:
			return PrimitiveType.FLOAT;
		case DOUBLE:
			return PrimitiveType.DOUBLE;
		default:
			// TODO: add error here
			return null;
		}
	}
	
}
