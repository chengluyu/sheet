package ast;

import ast.symbol.Symbol;
import compiler.ExpressionCompiler;
import lexer.Tag;
import utils.CompileError;

public class Assignment extends Expression {

	public Assignment(Tag op, Expression left, Expression right) {
		op_ = op;
		left_ = left;
		right_ = right;
	}
	
	private Tag op_;
	private Expression left_;
	private Expression right_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("assignment");
		printer.property("type", op_.literal());
		printer.child("left", left_);
		printer.child("right", right_);
		printer.endBlock();
	}

	@Override
	public void compile(ExpressionCompiler compiler) throws CompileError {
		right_.compile(compiler);
		if (left_ instanceof Index) {
			Index idx = (Index) left_;
			idx.value().compile(compiler);
			idx.refinement().compile(compiler);
			compiler.storeElement();
		} else if (left_ instanceof SymbolReference) {
			SymbolReference sr = (SymbolReference) left_;
			Symbol symb = sr.symbol();
			if (symb.isConstant())
				throw new CompileError("cannot assign a constant");
			if (symb.isArgument()) {
				compiler.storeArgument(symb.id());
			} else if (symb.isLocal()) {
				compiler.storeLocal(symb.id());
			} else if (symb.isGlobal()) {
				compiler.storeGlobal(symb.id());
			}
		} else {
			throw new CompileError("unreachable");
		}
	}
	
}
