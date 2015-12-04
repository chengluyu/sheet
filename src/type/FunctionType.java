package type;

public class FunctionType extends Type {
	
	public FunctionType(Type retType) {
		returnType_ = retType;
		argumentTypes_ = new TupleType();
	}

	public FunctionType(Type retType, TupleType argTypes) {
		returnType_ = retType;
		argumentTypes_ = argTypes;
	}
	
	private Type returnType_;
	private TupleType argumentTypes_;
	
	@Override
	public boolean isFunction() {
		return true;
	}

	@Override
	public String getTypeName() {
		return String.format("func (%s) -> %s", argumentTypes_.getTypeName(), 
				returnType_.getTypeName());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FunctionType)) return false;
		
		FunctionType rhs = (FunctionType) o;
		return returnType_ == rhs.returnType_
				&& argumentTypes_ == rhs.argumentTypes_;
	}

}
