package ast;

import lexer.Tag;

public class UnaryOperation extends Expression {
	
	public UnaryOperation(Tag op, Expression operand) {
		op_ = op;
		operand_ = operand;
	}
	
	private Tag op_;
	private Expression operand_;
	
}
