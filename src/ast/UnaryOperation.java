package ast;

import compiler.ExpressionCompiler;
import lexer.Tag;
import utils.CompileError;

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

	@Override
	public void compile(ExpressionCompiler compiler) throws CompileError {
		operand_.compile(compiler);
		compiler.emitByTag(op_);
	}
	
}
