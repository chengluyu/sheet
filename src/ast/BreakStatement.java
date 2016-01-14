package ast;

import compiler.ByteCodeCompiler;
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
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		target_.addBreak(compiler.branch());
	}

}
