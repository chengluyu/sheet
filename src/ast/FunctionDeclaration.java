package ast;

import type.FunctionType;

public class FunctionDeclaration extends Declaration {

	public FunctionDeclaration(FunctionType fty, Statement body) {
		theType_ = fty;
		body_ = body;
	}
	
	private FunctionType theType_;
	private Statement body_;
}
