package ast;

import compiler.ByteCodeCompiler;
import compiler.OpCode;
import lexer.Tag;
import utils.CompileError;

public class BinaryOperation extends Expression {

	public BinaryOperation(Tag op, Expression left, Expression right) {
		op_ = op;
		left_ = left;
		right_ = right;
	}
	
	private Tag op_;
	private Expression left_;
	private Expression right_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("binary operation");
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
		case ADD:
			compiler.emit(OpCode.ADD);
			break;
		case AND:
			compiler.emit(OpCode.AND);
			break;
		case BIT_AND:
			compiler.emit(OpCode.AND);
			break;
		case BIT_OR:
			compiler.emit(OpCode.OR);
			break;
		case BIT_XOR:
			compiler.emit(OpCode.XOR);
			break;
		case DIV:
			compiler.emit(OpCode.DIV);
			break;
		case MOD:
			compiler.emit(OpCode.MOD);
			break;
		case MUL:
			compiler.emit(OpCode.MUL);
			break;
		case OR:
			compiler.emit(OpCode.OR);
			break;
		case SAR:
			compiler.emit(OpCode.SAR);
			break;
		case SHL:
			compiler.emit(OpCode.SHL);
			break;
		case SHR:
			compiler.emit(OpCode.SHR);
			break;
		case SUB:
			compiler.emit(OpCode.SUB);
			break;
		default:
			throw new CompileError(
					"(internal error) unknown binary operation");
		}
	}

}
