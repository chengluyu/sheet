package ast;

import type.PrimitiveType;
import type.Type;

public class Int32ValueNode extends ValueNode {

	public Int32ValueNode(int value) {
		data_ = new Integer(value);
	}
	
	private final Integer data_;

	@Override
	public Type getType() {
		return PrimitiveType.INT;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
