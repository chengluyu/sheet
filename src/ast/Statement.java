package ast;

import compiler.StatementCompiler;
import utils.CompileError;

public abstract class Statement extends AstNode {
	
	public abstract void compile(StatementCompiler compiler)
			throws CompileError;
	
}
