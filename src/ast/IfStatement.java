package ast;

import compiler.ExpressionCompiler;
import compiler.Instruction;
import compiler.StatementCompiler;
import utils.CompileError;

public class IfStatement extends Statement {

	public IfStatement(Expression cond, Statement then, Statement otherwise) {
		cond_ = cond;
		then_ = then;
		else_ = otherwise;
	}
	
	private Expression cond_;
	private Statement then_;
	private Statement else_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("if statement");
		printer.child("condition", cond_);
		printer.child("then", then_);
		if (else_ != null)
			printer.child("else", else_);
		printer.endBlock();
	}

	@Override
	public void compile(StatementCompiler compiler) throws CompileError {
		ExpressionCompiler ec = compiler.getExpressionCompiler();
		cond_.compile(ec);
		Instruction ins = compiler.branchFalse();
		then_.compile(compiler);
		ins.setOperand(compiler.nextPosition());
		else_.compile(compiler);
	}

}
