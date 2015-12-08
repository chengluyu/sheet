package ast;

import type.PrimitiveType;
import type.Type;

public class UInt16ValueNode extends ValueNode {

	public UInt16ValueNode(short value) {
		data_ = new Short(value);
	}
	
	private final Short data_;

	@Override
	public Type getType() {
		return PrimitiveType.USHORT;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
