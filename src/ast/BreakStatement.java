package ast;

import compiler.StatementCompiler;
import utils.CompileError;

public class BreakStatement extends Statement {

	public BreakStatement(BreakableStatement target) {
		target_ = target;
	}
	
	private BreakableStatement target_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.text("break statement");
	}

	@Override
	public void compile(StatementCompiler compiler) throws CompileError {
		target_.addBreakFillBack(compiler.branch());
	}

}
