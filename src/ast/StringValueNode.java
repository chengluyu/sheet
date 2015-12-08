package ast;

import type.Type;
import type.PrimitiveType;

public class StringValueNode extends ValueNode {

	public StringValueNode(String data) {
		textData_ = data;
	}
	
	private String textData_;

	@Override
	public Type getType() {
		return PrimitiveType.STRING;
	}

	@Override
	public Object getNativeValue() {
		return textData_;
	}

}
