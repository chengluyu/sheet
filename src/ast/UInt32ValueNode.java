package ast;

import type.PrimitiveType;
import type.Type;

public class UInt32ValueNode extends ValueNode {

	public UInt32ValueNode(int value) {
		data_ = new Integer(value);
	}
	
	private final Integer data_;

	@Override
	public Type getType() {
		return PrimitiveType.UINT;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
