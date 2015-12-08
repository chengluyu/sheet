package ast;

import type.PrimitiveType;
import type.Type;

public class Int8ValueNode extends ValueNode {

	public Int8ValueNode(byte value) {
		data_ = new Byte(value);
	}
	
	private final Byte data_;

	@Override
	public Type getType() {
		return PrimitiveType.BYTE;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
