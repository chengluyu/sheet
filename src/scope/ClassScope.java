package scope;

public class ClassScope extends SubScope {

	public ClassScope(ModuleScope parent, ClassScope base) {
		super(parent);
		base_ = base;
	}
	
	private ClassScope base_;

}
