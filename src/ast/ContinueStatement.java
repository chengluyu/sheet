package ast;

import compiler.StatementCompiler;
import utils.CompileError;

public class ContinueStatement extends Statement {

	public ContinueStatement(IterationStatement target) {
		target_ = target;
	}
	
	private IterationStatement target_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.text("continue statement");
	}

	@Override
	public void compile(StatementCompiler compiler) throws CompileError {
		compiler.branch(target_.startPosition());
	}
	
}
