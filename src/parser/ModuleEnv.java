package parser;

import java.util.ArrayList;
import java.util.Collection;

import ast.StatementBlock;
import ast.symbol.ConstantSymbol;
import ast.symbol.FunctionSymbol;
import ast.symbol.Symbol;
import ast.symbol.SymbolTable;
import ast.symbol.VariableSymbol;

public class ModuleEnv extends Environment {

	public ModuleEnv() {
		localSymbols_ = new ArrayList<Symbol>();
		funcSymbols_ = new ArrayList<FunctionSymbol>();
		local_ = new SymbolTable();
		functions_ = new SymbolTable();
	}
	
	private ArrayList<Symbol> localSymbols_;
	private ArrayList<FunctionSymbol> funcSymbols_;
	private SymbolTable local_;
	private SymbolTable functions_;
	
	public SymbolTable symbolTable() {
		return local_;
	}
	
	public Collection<Symbol> locals() {
		return localSymbols_;
	}
	
	public Collection<FunctionSymbol> functions() {
		return funcSymbols_;
	}

	@Override
	public ConstantSymbol defineConstant(String name) {
		ConstantSymbol cs = new ConstantSymbol(name, localSymbols_.size());
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
	public VariableSymbol defineVariable(String name) {
		VariableSymbol vs = new VariableSymbol(name, localSymbols_.size());
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
