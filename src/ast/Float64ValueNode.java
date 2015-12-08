package ast;

import type.PrimitiveType;
import type.Type;

public class Float64ValueNode extends ValueNode {

	public Float64ValueNode(double value) {
		data_ = new Double(value);
	}
	
	private final Double data_;

	@Override
	public Type getType() {
		return PrimitiveType.DOUBLE;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
