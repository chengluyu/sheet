package ast;

import type.PrimitiveType;
import type.Type;

public class UInt8ValueNode extends ValueNode {

	public UInt8ValueNode(byte value) {
		data_ = new Byte(value);
	}
	
	private final Byte data_;

	@Override
	public Type getType() {
		return PrimitiveType.UBYTE;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
