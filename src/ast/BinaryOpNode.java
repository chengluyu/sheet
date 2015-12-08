package ast;

import type.Type;

public class BinaryOpNode extends Expression {

	public BinaryOpNode(Expression lhs, Expression rhs) {
		lhs_ = lhs;
		rhs_ = rhs;
	}
	
	private Expression lhs_, rhs_;

	@Override
	public Type getType() {
		return null;
	}

}
