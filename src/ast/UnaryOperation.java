package ast;

import compiler.ByteCodeCompiler;
import compiler.OpCode;
import lexer.Tag;
import parser.symbol.Symbol;
import utils.CompileError;

public class UnaryOperation extends Expression {
	
	public UnaryOperation(Tag op, Expression operand) {
		op_ = op;
		operand_ = operand;
	}
	
	private Tag op_;
	private Expression operand_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("unary operation");
		printer.property("operator", op_.literal());
		printer.child("operand", operand_);
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		operand_.compile(compiler);
		switch (op_) {
		case INC:
			prefix(compiler, OpCode.INC);
			break;
		case DEC:
			prefix(compiler, OpCode.DEC);
			break;
		case POSTFIX_INC:
			postfix(compiler, OpCode.INC);
			break;
		case POSTFIX_DEC:
			postfix(compiler, OpCode.DEC);
			break;
		case SUB:
			compiler.emit(OpCode.NEG);
			break;
		case NOT:
		case BIT_NOT:
			compiler.emit(OpCode.NOT);
			break;
		default:
			throw new CompileError(String.format(
					"(internal error) unknown unary operation \"%s\"",
					op_.name()));
		}
	}
	
	private void store(ByteCodeCompiler compiler) throws CompileError {
		if (operand_ instanceof SymbolReference) {
			SymbolReference ref = (SymbolReference) operand_;
			if (!ref.resolved())
				ref.resolve(compiler);
			Symbol symbol = ref.symbol();
			if (symbol.isFunction()) {
				throw new CompileError(String.format(
						"illegal assignment to the function \"%s\"",
						symbol.name()));
			} else if (symbol.isContant()) {
				throw new CompileError(String.format(
						"illegal assignment to the constant \"%s\"",
						symbol.name()));
			} else if (symbol.isGlobal()) {
				compiler.storeGlobal(symbol.id());
			} else if (symbol.isArgument()) {
				compiler.storeArgument(symbol.id());
			} else {
				compiler.storeLocal(symbol.id());
			}
		} else if (operand_ instanceof Index) {
			Index index = (Index) operand_;
			index.value().compile(compiler);
			index.refinement().compile(compiler);
			compiler.storeElement();
		} else {
			throw new CompileError(
					"the operand of increment/decrement must be a left value");
		}
	}

	private void prefix(ByteCodeCompiler compiler, OpCode opcode)
			throws CompileError {
		compiler.emit(opcode);
		compiler.emit(OpCode.COPY);
		store(compiler);
	}
	
	private void postfix(ByteCodeCompiler compiler, OpCode opcode)
			throws CompileError {
		compiler.emit(OpCode.COPY);
		compiler.emit(opcode);
		store(compiler);
	}
	
}
