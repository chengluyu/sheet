package ast;

public class FunctionDeclaration extends Declaration {

	public FunctionDeclaration(FunctionSpecifier fty, Statement body) {
		theType_ = fty;
		body_ = body;
	}
	
	private FunctionSpecifier theType_;
	private Statement body_;
}
