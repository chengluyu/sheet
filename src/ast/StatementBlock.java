package ast;

import java.util.ArrayList;

import compiler.StatementCompiler;
import utils.CompileError;

public class StatementBlock extends Statement {

	public StatementBlock(ArrayList<Statement> stmts) {
		stmts_ = stmts;
	}
	
	private ArrayList<Statement> stmts_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("statement block");
		printer.property("statement count", String.valueOf(stmts_.size()));
		for (int i = 0; i < stmts_.size(); i++)
			printer.child('[' + String.valueOf(i) + ']', stmts_.get(i));
		printer.endBlock();
	}

	@Override
	public void compile(StatementCompiler compiler) throws CompileError {
		for (int i = 0; i < stmts_.size(); i++)
			stmts_.get(i).compile(compiler);
	}

}
