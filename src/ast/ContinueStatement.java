package ast;

import compiler.ByteCodeCompiler;
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
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		target_.addContinue(compiler.branch());
	}
	
}
