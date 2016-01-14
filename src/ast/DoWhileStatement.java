package ast;

import compiler.Blank;
import compiler.ByteCodeCompiler;
import utils.CompileError;

public class DoWhileStatement extends IterationStatement {

	public DoWhileStatement() {
		
	}
	
	private Expression cond_;
	private Statement body_;
	
	public void setup(Expression cond, Statement body) {
		cond_ = cond;
		body_ = body;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("do-while loop");
		printer.child("condition", cond_);
		printer.child("body", body_);
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		int start = compiler.position();
		
		// body
		body_.compile(compiler);
		
		// condition
		cond_.compile(compiler);
		
		// if condition is true, jump to start
		Blank jumpToStart = compiler.branchTrue();
		
		int end = compiler.position();
		jumpToStart.fill(start);
		super.fillBreak(end);
		super.fillContinue(start);
	}

}
