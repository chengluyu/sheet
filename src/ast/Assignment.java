package ast;

import parser.symbol.Symbol;
import compiler.ByteCodeCompiler;
import compiler.OpCode;
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
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		if (op_ == Tag.ASSIGN) {
			compileRight(compiler);
			compiler.emit(OpCode.COPY);
			storeLeft(compiler);
		} else if (op_ == Tag.INIT_LET || op_ == Tag.INIT_CONST) {
			// left_ must be an instance of SymbolReference
			compileRight(compiler);
			SymbolReference symbRef = (SymbolReference) left_;
			if (!symbRef.resolved())
				symbRef.resolve(compiler);
			Symbol symbol = symbRef.symbol();
			if (symbol.isGlobal())
				compiler.storeGlobal(symbol.id());
			else
				compiler.storeLocal(symbol.id());
		} else {
			loadLeft(compiler);
			compileRight(compiler);
			switch (op_) {
			case ASSIGN_ADD:
				compiler.emit(OpCode.ADD);
				break;
			case ASSIGN_BIT_AND:
				compiler.emit(OpCode.AND);
				break;
			case ASSIGN_BIT_OR:
				compiler.emit(OpCode.OR);
				break;
			case ASSIGN_BIT_XOR:
				compiler.emit(OpCode.XOR);
				break;
			case ASSIGN_DIV:
				compiler.emit(OpCode.DIV);
				break;
			case ASSIGN_MOD:
				compiler.emit(OpCode.MOD);
				break;
			case ASSIGN_MUL:
				compiler.emit(OpCode.MUL);
				break;
			case ASSIGN_SAR:
				compiler.emit(OpCode.SAR);
				break;
			case ASSIGN_SHL:
				compiler.emit(OpCode.SHL);
				break;
			case ASSIGN_SHR:
				compiler.emit(OpCode.SHR);
				break;
			case ASSIGN_SUB:
				compiler.emit(OpCode.SUB);
				break;
			default:
				throw new CompileError(
						"(internal error) unknown assignment type");
			}
			compiler.emit(OpCode.COPY);
			storeLeft(compiler);
		}
	}
	
	private void loadLeft(ByteCodeCompiler compiler) throws CompileError {
		if (left_ instanceof SymbolReference) {
			SymbolReference ref = (SymbolReference) left_;
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
				compiler.loadGlobal(symbol.id());
			} else if (symbol.isArgument()) {
				compiler.loadArgument(symbol.id());
			} else {
				compiler.loadLocal(symbol.id());
			}
		} else if (left_ instanceof Index) {
			Index index = (Index) left_;
			index.value().compile(compiler);
			index.refinement().compile(compiler);
			compiler.loadElement();
		} else {
			throw new CompileError(
					"left-hand side of assignment must be a left value");
		}
	}
	
	private void storeLeft(ByteCodeCompiler compiler) throws CompileError {
		if (left_ instanceof SymbolReference) {
			SymbolReference ref = (SymbolReference) left_;
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
		} else if (left_ instanceof Index) {
			Index index = (Index) left_;
			index.value().compile(compiler);
			index.refinement().compile(compiler);
			compiler.storeElement();
		} else {
			throw new CompileError(
					"left-hand side of assignment must be a left value");
		}
	}
	
	private void compileRight(ByteCodeCompiler compiler) throws CompileError {
		right_.compile(compiler);
	}
	
}
