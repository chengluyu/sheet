package ast;

import java.util.ArrayList;

import token.Identifier;
import utils.Pair;

public class VariableDeclaration extends Declaration {

	public VariableDeclaration(TypeSpecifier ty, ArrayList<Pair<String, Expression>> vs) {
		theType_ = ty;
		values_ = vs;
	}
	
	private TypeSpecifier theType_;
	private ArrayList<Pair<String, Expression>> values_;

}
