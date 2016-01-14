package ast;

import compiler.ByteCodeCompiler;
import compiler.OpCode;
import lexer.Tag;
import utils.CompileError;

public class CompareOperation extends Expression {

	public CompareOperation(Tag op, Expression left, Expression right) {
		op_ = op;
		left_ = left;
		right_ = right;
	}
	
	private Tag op_;
	private Expression left_;
	private Expression right_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("compare operation");
		printer.property("type", op_.literal());
		printer.child("left", left_);
		printer.child("right", right_);
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		left_.compile(compiler);
		right_.compile(compiler);
		switch (op_) {
		case EQ:
			compiler.emit(OpCode.EQ);
			break;
		case GT:
			compiler.emit(OpCode.GT);
			break;
		case GTE:
			compiler.emit(OpCode.GTE);
			break;
		case LT:
			compiler.emit(OpCode.LT);
			break;
		case LTE:
			compiler.emit(OpCode.LTE);
			break;
		case NE:
			compiler.emit(OpCode.NE);
			break;
		default:
			throw new CompileError(
					"(internal error) unknown compare operation");
		}
	}

}
