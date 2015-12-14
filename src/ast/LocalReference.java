package ast;

public class LocalReference extends Reference {

	public LocalReference(String refName, Scope scope) {
		scope_ = scope;
	}

	private Scope scope_;

	@Override
	public boolean solved() {
		return true;
	}

}