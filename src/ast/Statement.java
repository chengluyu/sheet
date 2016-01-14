package ast;

import compiler.ByteCodeCompiler;
import utils.CompileError;

public abstract class Statement extends AstNode {
	public abstract void compile(ByteCodeCompiler compiler) throws CompileError;
}
