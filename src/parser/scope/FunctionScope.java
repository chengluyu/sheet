package parser.scope;

import java.util.ArrayList;

import parser.Context;
import parser.symbol.ArgumentSymbol;
import parser.symbol.ConstantSymbol;
import parser.symbol.FunctionSymbol;
import parser.symbol.Symbol;
import parser.symbol.VariableSymbol;
import utils.SyntaxError;

public class FunctionScope extends Scope {

	public FunctionScope(Context context, Scope parent) {
		super(context, parent);
		assert parent instanceof GlobalScope; // TODO check
		argIDAllocator_ = new IDAllocator();
		localIDAllocator_ = new IDAllocator();
		arguments_ = new LookupTable<ArgumentSymbol>();
		baseScope_ = new ReferenceScope(context, this);
		definedArguments_ = new ArrayList<ArgumentSymbol>();
		definedLocals_ = new ArrayList<Symbol>();
	}
	
	private IDAllocator argIDAllocator_;
	private IDAllocator localIDAllocator_;
	private LookupTable<ArgumentSymbol> arguments_;
	private ReferenceScope baseScope_;
	
	private ArrayList<ArgumentSymbol> definedArguments_;
	private ArrayList<Symbol> definedLocals_;
	
	public ArrayList<ArgumentSymbol> argumentSymbols() {
		return definedArguments_;
	}
	
	public ArrayList<Symbol> localSymbols() {
		return definedLocals_;
	}

	@Override
	public int allocateSymbolID() {
		return localIDAllocator_.allocate();
	}

	@Override
	public ArgumentSymbol defineArgument(String t) throws SyntaxError {
		if (arguments_.contains(t)) {
			reportError(String.format("duplicated argument name: \"%s\"", t));
			return null;
		} else {
			ArgumentSymbol symb = 
					new ArgumentSymbol(this, argIDAllocator_.allocate(), t);
			arguments_.insert(symb);
			definedArguments_.add(symb);
			return symb;
		}
	}

	@Override
	public ConstantSymbol defineConstant(String t) throws SyntaxError {
		return baseScope_.defineConstant(t);
	}

	@Override
	public FunctionSymbol defineFunction(String t) throws SyntaxError {
		reportError(String.format(
				"cannot define an function \"%s\" in a function scope", t));
		return null; // UNREACHABLE code, make compiler happy
	}

	@Override
	public VariableSymbol defineVariable(String t) throws SyntaxError {
		return baseScope_.defineVariable(t);
	}

	@Override
	public Symbol lookup(String t) {
		Symbol symb = baseScope_.lookup(t);
		if (symb == null)
			symb = arguments_.lookup(t);
		return symb;
	}

	@Override
	public boolean isGlobalScope() {
		return false;
	}

	@Override
	public void registerSymbol(Symbol symbol) {
		definedLocals_.add(symbol);
	}

}
