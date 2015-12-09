package ast;

public class Argument {

	public Argument(String argName, TypeSpecifier type) {
		argName_ = argName;
		argType_ = type;
	}
	
	
	private String argName_;
	private TypeSpecifier argType_;

}
