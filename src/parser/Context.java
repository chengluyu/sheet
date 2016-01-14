package parser;

import parser.scope.*;
import parser.symbol.Symbol;

public class Context {

	public Context(Parser parser) {
		parser_ = parser;
		current_ = globalScope_ = new GlobalScope(this);
	}
	
	private final Parser parser_;
	private GlobalScope globalScope_;
	private Scope current_;
	
	public Scope current() {
		return current_;
	}
	
	public void enterFunctionScope() {
		current_ = new FunctionScope(this, current_);
	}
	
	public FunctionScope leaveFunctionScope() {
		assert current_ instanceof FunctionScope; // TODO check
		FunctionScope save = (FunctionScope) current_;
		current_ = current_.parent();
		return save;
	}
	
	public void enterLocalScope() {
		current_ = new ReferenceScope(this, current_);
	}
	
	public void leaveLocalScope() {
		assert current_ instanceof ReferenceScope; // TODO check
		current_ = current_.parent();
	}
	
	public Parser parser() {
		return parser_;
	}

	public Symbol lookup(String name) {
		Scope s = current_;
		while (s != null) {
			Symbol symb = s.lookup(name);
			if (symb != null)
				return symb;
			s = s.parent();
		}
		return null;
	}

	public GlobalScope globalScope() {
		return globalScope_;
	}

}
