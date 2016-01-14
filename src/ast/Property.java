package ast;

import compiler.ByteCodeCompiler;
import utils.CompileError;

public class Property extends Expression {

	public Property(Expression expr, String prop) {
		expr_ = expr;
		prop_ = prop;
	}
	
	private Expression expr_;
	private String prop_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("get property");
		printer.child("object", expr_);
		printer.property("property name", prop_);
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		throw new CompileError("unimplemented compile routine: Property");
	}

}
