package ast;

import compiler.Compiler;
import utils.CompileError;

public abstract class AstNode {
	
	public abstract void inspect(AstNodePrinter printer);

}