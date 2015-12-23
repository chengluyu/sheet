package scope;

public class SubScope extends LocalScope {

	public SubScope(ModuleScope parent) {
		moduleScope_ = parent;
	}
	
	private ModuleScope moduleScope_;

}
