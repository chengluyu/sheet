package ast;

import compiler.ByteCodeCompiler;
import utils.CompileError;

public class Index extends Expression {
	
	public Index(Expression left, Expression right) {
		value_ = left;
		refinement_ = right;
	}
	
	private Expression value_;
	private Expression refinement_;
	
	
	public Expression value() {
		return value_;
	}
	
	public Expression refinement() {
		return refinement_;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("index operation");
		printer.child("indexed object", value_);
		printer.child("index value", refinement_);
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		value_.compile(compiler);
		refinement_.compile(compiler);
		compiler.loadElement();
	}
	
}
