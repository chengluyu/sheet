package ast;

import compiler.Instruction;
import compiler.StatementCompiler;
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
	public void compile(StatementCompiler compiler) throws CompileError {
		int start = compiler.nextPosition();
		super.setStartPosition(start);
		body_.compile(compiler);
		cond_.compile(compiler.getExpressionCompiler());
		Instruction ins = compiler.branchTrue();
		int end = compiler.nextPosition();
		ins.setOperand(start);
		super.fillBreak(end);
	}

}
