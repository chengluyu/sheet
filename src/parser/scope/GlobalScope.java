package parser.scope;

import java.util.ArrayList;

import parser.Context;
import parser.symbol.ArgumentSymbol;
import parser.symbol.ConstantSymbol;
import parser.symbol.FunctionSymbol;
import parser.symbol.Symbol;
import parser.symbol.VariableSymbol;
import utils.SyntaxError;

public class GlobalScope extends Scope {

	public GlobalScope(Context context) {
		super(context, null);
		idAllocator_ = new IDAllocator();
		globals_ = new LookupTable<Symbol>();
		definedFunctions_ = new ArrayList<FunctionSymbol>();
		definedGlobals_ = new ArrayList<Symbol>();
	}
	
	private IDAllocator idAllocator_;
	private LookupTable<Symbol> globals_;
	private ArrayList<FunctionSymbol> definedFunctions_;
	private ArrayList<Symbol> definedGlobals_;
	
	public ArrayList<FunctionSymbol> functionSymbols() {
		return definedFunctions_;
	}
	
	public ArrayList<Symbol> globalSymbols() {
		return definedGlobals_;
	}

	@Override
	public int allocateSymbolID() {
		return idAllocator_.allocate();
	}

	@Override
	public ArgumentSymbol defineArgument(String t) throws SyntaxError {
		reportError(String.format(
				"cannot define an argument \"%s\" in a global scope", t));
		return null; // UNREACHABLE code, make compiler happy
	}

	@Override
	public ConstantSymbol defineConstant(String t) throws SyntaxError {
		if (globals_.contains(t)) {
			reportError(String.format("duplicated constant name: \"%s\"", t));
			return null;
		} else {
			ConstantSymbol symb =
					new ConstantSymbol(this, allocateSymbolID(), t);
			globals_.insert(symb);
			definedGlobals_.add(symb);
			return symb;
		}
	}

	@Override
	public FunctionSymbol defineFunction(String t) throws SyntaxError {
		if (globals_.contains(t)) {
			reportError(String.format("duplicated function name: \"%s\"", t));
			return null;
		} else {
			FunctionSymbol symb =
					new FunctionSymbol(this, allocateSymbolID(), t);
			globals_.insert(symb);
			definedFunctions_.add(symb);
			return symb;
		}
	}

	@Override
	public VariableSymbol defineVariable(String t) throws SyntaxError {
		if (globals_.contains(t)) {
			reportError(String.format("duplicated variable name: \"%s\"", t));
			return null;
		} else {
			VariableSymbol symb =
					new VariableSymbol(this, allocateSymbolID(), t);
			globals_.insert(symb);
			definedGlobals_.add(symb);
			return symb;
		}
	}

	@Override
	public Symbol lookup(String t) {
		return globals_.lookup(t);
	}

	@Override
	public boolean isGlobalScope() {
		return true;
	}

	@Override
	public void registerSymbol(Symbol symbol) {
		return;
	}

}
