package ast;

import java.util.ArrayList;

import parser.scope.*;
import parser.symbol.*;

public class Module extends AstNode {

	public Module(
			GlobalScope scope,
			StatementBlock globalInits,
			ArrayList<Function> functions) {
		scope_ = scope;
		globalInits_ = globalInits;
		globals_ = scope.globalSymbols();
		functions_ = functions;
	}
	
	private GlobalScope scope_;
	private StatementBlock globalInits_;
	private ArrayList<Symbol> globals_;
	private ArrayList<Function> functions_;
	
	public GlobalScope scope() {
		return scope_;
	}
	
	public StatementBlock initialzations() {
		return globalInits_;
	}
	
	public ArrayList<Symbol> globals() {
		return globals_;
	}
	
	public ArrayList<Function> functions() {
		return functions_;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("module");
		
		printer.subBlock("globals");
		globals_.forEach(symb -> {
			printer.property(Integer.toString(symb.id()), symb.name());
		});
		printer.endBlock();
		
		printer.child("initializations", globalInits_);
		
		printer.subBlock("functions");
		functions_.forEach(func -> {
			printer.child("function", func);
		});
		printer.endBlock();
		
		printer.endBlock();
	}

}
