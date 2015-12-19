package ast;

import lexer.Tag;

public class Assignment extends Expression {

	public Assignment(Tag op, Expression left, Expression right) {
		op_ = op;
		left_ = left;
		right_ = right;
	}
	
	private Tag op_;
	private Expression left_;
	private Expression right_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("assignment");
		printer.begin();
		printer.property("type", op_.literal());
		printer.child("left", left_);
		printer.child("right", right_);
		printer.end();
	}

}
