package parser;

import java.util.ArrayList;
import java.util.Collection;

import ast.symbol.ArgumentSymbol;
import ast.symbol.LocalSymbol;
import ast.symbol.Symbol;
import ast.symbol.SymbolTable;

public class FunctionEnv extends Environment {

	public FunctionEnv(ModuleEnv moduleEnv) {
		local_ = new SymbolTable();
		arguments_ = new SymbolTable();
		moduleEnv_ = moduleEnv;
		argSymbols_ = new ArrayList<Symbol>();
		localSymbols_ = new ArrayList<Symbol>();
	}
	
	private ModuleEnv moduleEnv_;
	private ArrayList<Symbol> argSymbols_;
	private ArrayList<Symbol> localSymbols_;
	private SymbolTable arguments_;
	private SymbolTable local_;
	
	public void enterLocalScope() {
		local_ = new SymbolTable(local_);
	}
	
	public void leaveLocalScope() {
		local_ = local_.parent();
	}
	
	public void defineArgument(String name) {
		ArgumentSymbol as = new ArgumentSymbol(name, argSymbols_.size());
		argSymbols_.add(as);
		arguments_.insert(as);
	}
	
	public ArrayList<Symbol> arguments() {
		return argSymbols_;
	}
	
	public ArrayList<Symbol> locals() {
		return localSymbols_;
	}
	
	@Override
	public LocalSymbol defineConstant(String name) {
		LocalSymbol cs = new LocalSymbol(name, localSymbols_.size(), true);
		localSymbols_.add(cs);
		local_.insert(cs);
		return cs;
	}
	
	@Override
	public LocalSymbol defineVariable(String name) {
		LocalSymbol vs = new LocalSymbol(name, localSymbols_.size(), false);
		localSymbols_.add(vs);
		local_.insert(vs);
		return vs;
	}

	@Override
	public Symbol lookup(String name) {
		Symbol symb = local_.find(name);
		if (symb == null)
			symb = arguments_.find(name);
		if (symb == null)
			symb = moduleEnv_.lookup(name);
		return symb;
	}

}
