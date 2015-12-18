package parser;

import utils.ParseException;

public class ScopeManager {

	public ScopeManager(Parser parser) {
		parser_ = parser;
		topScope_ = (ModuleScope) enterScope(new ModuleScope(this));
		current_ = topScope_;
	}
	
	private final Parser parser_;
	private LocalScope current_;
	private final ModuleScope topScope_;
	
	// Properties
	
	public Parser parser() {
		return parser_;
	}
	
	public LocalScope current() {
		return current_;
	}
	
	// Enter
	
	public FunctionScope newFunctionScope() {
		return (FunctionScope) enterScope(new FunctionScope(this, current_));
	}
	
	public LocalScope newLocalScope() {
		return enterScope(new LocalScope(this, current_));
	}
	
	private LocalScope enterScope(LocalScope newScope) {
		LocalScope save = newScope;
		current_ = save;
		return save;
	}
	
	// Leave
	
	public void leaveScope() throws ParseException {
		if (current_.parent() == topScope_) // Internal error
			throw new ParseException(parser_.position(),
					"try to leave the top scope");
		current_ = current_.parent();
	}
	
	/**
	 * Same as `leaveScope()`, but with assertion guards.
	 * @param scope The scope you want to exit.
	 * @throws ParseException
	 */
	public void leaveScope(LocalScope scope) throws ParseException {
		if (current_ != scope)
			throw new ParseException(parser_.position(),
					"leave a wrong scope");
		leaveScope();
	}

}
