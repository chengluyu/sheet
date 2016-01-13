package ast;

import compiler.ExpressionCompiler;
import utils.CompileError;

public abstract class Expression extends AstNode {
	
	public abstract void compile(ExpressionCompiler compiler)
			throws CompileError;
	
}
