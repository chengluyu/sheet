package ast;

import lexer.Tag;

public class CompareOperation extends Expression {

	public CompareOperation(Tag op, Expression left, Expression right) {
		op_ = op;
		left_ = left;
		right_ = right;
	}
	
	private Tag op_;
	private Expression left_;
	private Expression right_;

}
