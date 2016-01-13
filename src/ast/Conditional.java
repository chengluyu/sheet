package ast;

import compiler.ExpressionCompiler;
import compiler.Instruction;
import utils.CompileError;

public class Conditional extends Expression {

	public Conditional(Expression cond, Expression then, Expression otherwise) {
		cond_ = cond;
		then_ = then;
		else_ = otherwise;
	}
	
	private Expression cond_;
	private Expression then_;
	private Expression else_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("conditional operation");
		printer.child("condition", cond_);
		printer.child("then", then_);
		printer.child("else", else_);
		printer.endBlock();
	}

	@Override
	public void compile(ExpressionCompiler compiler) throws CompileError {
		cond_.compile(compiler);
		Instruction br = compiler.branchFalse();
		then_.compile(compiler);
		br.setOperand(compiler.nextPosition());
		else_.compile(compiler);
	}

}
