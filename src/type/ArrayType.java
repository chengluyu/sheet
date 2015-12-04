package type;

public class ArrayType extends Type {

	public ArrayType(Type elemType) {
		elementType_ = elemType;
	}
	
	private Type elementType_;

	@Override
	public String getTypeName() {
		return elementType_.getTypeName() + "[]";
	}

	@Override
	public boolean isArray() {
		return true;
	}
	
	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Type)) return false;
		Type rhs = (Type) o;
		
		if (rhs.isArray()) {
			ArrayType arrayType = (ArrayType) rhs;
			return elementType_.equals(arrayType.elementType_);
		}
		return false;
	}

}
