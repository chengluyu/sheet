package parser;

import java.util.ArrayList;

import ast.StatementBlock;
import ast.symbol.LocalSymbol;
import ast.symbol.FunctionSymbol;
import ast.symbol.Symbol;
import ast.symbol.SymbolTable;

public class ModuleEnv extends Environment {

	public ModuleEnv() {
		localSymbols_ = new ArrayList<Symbol>();
		funcSymbols_ = new ArrayList<FunctionSymbol>();
		local_ = new SymbolTable();
		functions_ = new SymbolTable();
		inits_ = null;
	}
	
	private ArrayList<Symbol> localSymbols_;
	private ArrayList<FunctionSymbol> funcSymbols_;
	private StatementBlock inits_;
	private SymbolTable local_;
	private SymbolTable functions_;
	
	public SymbolTable symbolTable() {
		return local_;
	}
	
	public ArrayList<Symbol> globals() {
		return localSymbols_;
	}
	
	public ArrayList<FunctionSymbol> functions() {
		return funcSymbols_;
	}

	public StatementBlock initializations() {
		return inits_;
	}
	
	public void setInitializations(StatementBlock sb) {
		inits_ = sb;
	}

	@Override
	public LocalSymbol defineConstant(String name) {
		LocalSymbol cs = new LocalSymbol(name, localSymbols_.size(), true);
		localSymbols_.add(cs);
		local_.insert(cs);
		return cs;
	}
	
	public void defineFunction(String name, FunctionEnv env,
			StatementBlock stmts) {
		FunctionSymbol fs = new FunctionSymbol(name, env, stmts,
				funcSymbols_.size());
		funcSymbols_.add(fs);
		functions_.insert(fs);
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
		Symbol symb = local_.findThis(name);
		if (symb == null)
			symb = functions_.findThis(name);
		return symb;
	}

}
