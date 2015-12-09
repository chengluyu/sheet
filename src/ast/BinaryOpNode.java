package ast;

import type.Type;

public class BinaryOpNode extends Expression {

	public BinaryOpNode(BinaryOperator op, Expression lhs, Expression rhs) {
		op_ = op;
		lhs_ = lhs;
		rhs_ = rhs;
	}
	
	private final BinaryOperator op_;
	private final Expression lhs_, rhs_;

	@Override
	public Type getType() {
		return null;
	}

}
