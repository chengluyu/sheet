package ast;

import compiler.ExpressionCompiler;
import lexer.Tag;
import utils.CompileError;

public class CompareOperation extends Expression {

	public CompareOperation(Tag op, Expression left, Expression right) {
		op_ = op;
		left_ = left;
		right_ = right;
	}
	
	private Tag op_;
	private Expression left_;
	private Expression right_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("compare operation");
		printer.property("type", op_.literal());
		printer.child("left", left_);
		printer.child("right", right_);
		printer.endBlock();
	}

	@Override
	public void compile(ExpressionCompiler compiler) throws CompileError {
		left_.compile(compiler);
		right_.compile(compiler);
		compiler.emitByTag(op_);
	}

}
