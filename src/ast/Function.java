package ast;

import java.util.ArrayList;

import compiler.ByteCodeCompiler;
import compiler.Compiler;
import parser.scope.*;
import parser.symbol.*;
import runtime.FieldInfo;
import runtime.FunctionInfo;
import utils.CompileError;

public class Function extends AstNode {

	public Function(
			FunctionSymbol symbol,
			FunctionScope scope,
			StatementBlock stmts) {
		symbol_ = symbol;
		arguments_ = scope.argumentSymbols();
		locals_ = scope.localSymbols();
		stmts_ = stmts;
	}
	
	private FunctionSymbol symbol_;
	private ArrayList<ArgumentSymbol> arguments_;
	private ArrayList<Symbol> locals_;
	private StatementBlock stmts_;
	
	public String name() {
		return symbol_.name();
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock();
		
		// inspect function name
		printer.property("name", symbol_.name());
		
		// inspect arguments
		printer.subBlock("arguments");
		arguments_.forEach(symb -> {
			printer.property(Integer.toString(symb.id()), symb.name());
		});
		printer.endBlock();
		
		// inspect locals
		printer.subBlock("locals");
		locals_.forEach(symb -> {
			printer.property(Integer.toString(symb.id()), symb.name());
		});
		printer.endBlock();
		
		// inspect statement
		printer.child("statements", stmts_);
		
		printer.endBlock();
	}

	public FunctionInfo compile(Compiler compiler) throws CompileError {
		FieldInfo[] arguments = compileArguments();
		FieldInfo[] locals = compileLocals();
		ByteCodeCompiler byteCodeCompiler = compiler.getByteCodeCompiler();
		stmts_.compile(byteCodeCompiler);
		byteCodeCompiler.addEpilogueRet();
		return new FunctionInfo(
				symbol_.id(),
				symbol_.name(),
				arguments,
				locals,
				byteCodeCompiler.getByteCode()
				);
	}

	private FieldInfo[] compileLocals() {
		FieldInfo[] locals = new FieldInfo[locals_.size()];
		for (int i = 0; i < locals_.size(); i++) {
			Symbol symbol = locals_.get(i);
			locals[i] = new FieldInfo(symbol.id(), symbol.name());
		}
		return locals;
	}

	private FieldInfo[] compileArguments() {
		FieldInfo[] arguments = new FieldInfo[arguments_.size()];
		for (int i = 0; i < arguments_.size(); i++) {
			Symbol symbol = arguments_.get(i);
			arguments[i] = new FieldInfo(symbol.id(), symbol.name());
		}
		return arguments;
	}
	
	

}
