package ast;

import compiler.ByteCodeCompiler;
import compiler.OpCode;
import utils.CompileError;

public class ExpressionStatement extends Statement {

	public ExpressionStatement(Expression expr) {
		expr_ = expr;
	}
	
	private Expression expr_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("expression statement");
		printer.child("expr", expr_);
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		expr_.compile(compiler);
		compiler.emit(OpCode.POP);
	}

}
