package ast;

import type.PrimitiveType;
import type.Type;

public class UInt64ValueNode extends ValueNode {

	public UInt64ValueNode(long value) {
		data_ = new Long(value);
	}
	
	private final Long data_;

	@Override
	public Type getType() {
		return PrimitiveType.ULONG;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
