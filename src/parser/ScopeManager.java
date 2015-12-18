package parser;

public class ScopeManager {

	public ScopeManager(Parser parser) {
		parser_ = parser;
		current_ = null;
	}
	
	private final Parser parser_;
	private Scope current_;
	
	// Properties
	
	public Parser parser() {
		return parser_;
	}
	
	public Scope current() {
		return current_;
	}
	
	// Create
	
	private Scope enterScope(Scope scope) {
		return current_ = scope;
	}
	
	public ModuleScope newModuleScope() {
		ModuleScope ref = new ModuleScope();
		current_ = ref;
		return ref;
	}
	
	public FunctionScope newFunctionScope() {
		FunctionScope save = new FunctionScope(current_);
		current_ = save;
		return save;
	}
	
	public IterationScope newIterationScope() {
		IterationScope save = new IterationScope(current_);
		current_ = save;
		return save;
	}
	
	public void exitScope() {
		current_ = current_.parent();
	}

}
