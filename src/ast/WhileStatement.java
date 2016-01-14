package ast;

import compiler.Blank;
import compiler.ByteCodeCompiler;
import utils.CompileError;

public class WhileStatement extends IterationStatement {

	public WhileStatement() {
		cond_ = null;
		body_ = null;
	}
	
	private Expression cond_;
	private Statement body_;
	
	public void setup(Expression cond, Statement body) {
		cond_ = cond;
		body_ = body;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("while loop");
		printer.child("condition", cond_);
		printer.child("body", body_);
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		int start = compiler.position();
		
		// condition
		cond_.compile(compiler);
		
		// if condition is false, jump to end
		Blank jumpToEnd = compiler.branchFalse();
		
		// body
		body_.compile(compiler);
		
		// jump to start
		compiler.branch(start);
		
		int end = compiler.position();
		jumpToEnd.fill(end);
		super.fillBreak(end);
		super.fillContinue(start);
	}

}
