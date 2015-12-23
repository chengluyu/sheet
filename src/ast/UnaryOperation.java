package ast;

import lexer.Tag;

public class UnaryOperation extends Expression {
	
	public UnaryOperation(Tag op, Expression operand) {
		op_ = op;
		operand_ = operand;
	}
	
	private Tag op_;
	private Expression operand_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("unary operation");
		printer.property("operator", op_.literal());
		printer.child("operand", operand_);
		printer.endBlock();
	}
	
}
