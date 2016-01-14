package ast;

import compiler.Instruction;
import compiler.StatementCompiler;
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
	public void compile(StatementCompiler compiler) throws CompileError {
		int start = compiler.nextPosition();
		super.setStartPosition(start);
		cond_.compile(compiler.getExpressionCompiler());
		Instruction ins = compiler.branchFalse();
		body_.compile(compiler);
		compiler.branch(start);
		int end = compiler.nextPosition();
		ins.setOperand(end);
		super.fillBreak(end);
	}

}
