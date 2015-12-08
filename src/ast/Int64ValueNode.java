package ast;

import type.PrimitiveType;
import type.Type;

public class Int64ValueNode extends ValueNode {

	public Int64ValueNode(long value) {
		data_ = new Long(value);
	}
	
	private final Long data_;

	@Override
	public Type getType() {
		return PrimitiveType.LONG;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
