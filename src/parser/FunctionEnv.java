package parser;

import java.util.ArrayList;
import java.util.Collection;

import ast.symbol.ConstantSymbol;
import ast.symbol.Symbol;
import ast.symbol.SymbolTable;
import ast.symbol.VariableSymbol;

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
		VariableSymbol vs = new VariableSymbol(name, argSymbols_.size());
		argSymbols_.add(vs);
		arguments_.insert(vs);
	}
	
	public Collection<Symbol> arguments() {
		return argSymbols_;
	}
	
	public Collection<Symbol> locals() {
		return localSymbols_;
	}
	
	@Override
	public ConstantSymbol defineConstant(String name) {
		ConstantSymbol cs = new ConstantSymbol(name, localSymbols_.size());
		localSymbols_.add(cs);
		local_.insert(cs);
		return cs;
	}
	
	@Override
	public VariableSymbol defineVariable(String name) {
		VariableSymbol vs = new VariableSymbol(name, localSymbols_.size());
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
