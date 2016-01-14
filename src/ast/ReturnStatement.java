package ast;

import compiler.ExpressionCompiler;
import compiler.StatementCompiler;
import utils.CompileError;

public class ReturnStatement extends Statement {

	public ReturnStatement(Expression retValue) {
		retValue_ = retValue;
	}
	
	private Expression retValue_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		if (retValue_ == null) {
			printer.text("return statement");
		} else {
			printer.beginBlock("return statement");
			printer.child("return value", retValue_);
			printer.endBlock();
		}
	}

	@Override
	public void compile(StatementCompiler compiler) throws CompileError {
		ExpressionCompiler ec = compiler.getExpressionCompiler();
		if (retValue_ == null) {
			compiler.ret();
		} else {
			retValue_.compile(ec);
			compiler.ret();
		}
	}

}
