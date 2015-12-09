package ast;

import java.util.ArrayList;

import token.Identifier;
import utils.Pair;

public class ConstantDeclaration extends Declaration {

	public ConstantDeclaration(TypeSpecifier ty, ArrayList<Pair<String, Expression>> cs) {
		theType_ = ty;
		assignments_ = cs;
	}
	
	private TypeSpecifier theType_;
	private ArrayList<Pair<String, Expression>> assignments_;

}
