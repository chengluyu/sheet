package parser;

import java.util.HashMap;

import utils.ParsingException;

public class Scope {
	
	public Scope() {
		isTopScope_ = true;
		parent_ = null;
		symbolTable_ = new HashMap<String, Symbol>();
	}

	public Scope(Scope parent) {
		isTopScope_ = false;
		parent_ = parent;
		symbolTable_ = new HashMap<String, Symbol>();
	}
	
	public boolean isTopScope() {
		return isTopScope_;
	}
	
	public Scope getParent() {
		return parent_;
	}
	
	public void declareVariable(String varName) throws ParsingException {
		checkIfDeclared(varName, "duplicated definition of a variable");
		symbolTable_.put(varName, new Symbol());
	}
	
	public void declareConstant(String constantName) throws ParsingException {
		checkIfDeclared(constantName, "duplicated definition of a constant");
		symbolTable_.put(constantName, new Symbol());
	}
	
	public void declareEnumType(String enumName) throws ParsingException {
		checkIfDeclared(enumName, "duplicated definition of a enum type");
		symbolTable_.put(enumName, new Symbol());
	}
	
	public void declareClassType(String className) throws ParsingException {
		checkIfDeclared(className, "duplicated definition of a variable");
		symbolTable_.put(className, new Symbol());
	}
	
	public void declareTypeAlias(String alias) throws ParsingException {
		checkIfDeclared(alias, "duplicated definition of a variable");
		symbolTable_.put(alias, new Symbol());
	}
	
	public Symbol findSymbol(String symbName) {
		Scope current = this;
		while (current != null) {
			Symbol sym = current.findSymbolInThisScope(symbName);
			if (sym != null) return sym;
			current = current.parent_;
		}
		return null;
	}
	
	public Symbol findSymbolInThisScope(String symbName) {
		return symbolTable_.get(symbName);
	}
	
	public boolean isDeclared(String symbolName) {
		Scope current = this;
		while (current != null) {
			if (current.isDeclaredInThisScope(symbolName))
				return true;
			current = current.parent_;
		}
		return false;
	}
	
	public boolean isDeclaredInThisScope(String symbolName) {
		return symbolTable_.containsKey(symbolName);
	}
	
	private void checkIfDeclared(String symbolName, String message)
			throws ParsingException {
		if (isDeclaredInThisScope(symbolName)) {
			throw new ParsingException(message);
		}
	}
	
	private final HashMap<String, Symbol> symbolTable_;
	private final boolean isTopScope_;
	private final Scope parent_;

}
