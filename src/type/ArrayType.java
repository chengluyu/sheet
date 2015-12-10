package type;

public class ArrayType extends Type {

	public ArrayType(Type elemType) {
		elemType_ = elemType;
	}
	
	private Type elemType_;

	@Override
	public String getName() {
		return elemType_.getName() + "[]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof ArrayType) {
			ArrayType ty = (ArrayType) o;
			return ty.elemType_.equals(elemType_);
		} else {
			return false;
		}
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		return false;
	}

}
