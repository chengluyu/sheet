package ast;

import java.util.ArrayList;

import token.Identifier;
import type.Type;
import utils.Pair;

public class VariableDeclaration extends Declaration {

	public VariableDeclaration(Type ty, ArrayList<Pair<String, Expression>> vs) {
		theType_ = ty;
		values_ = vs;
	}
	
	private Type theType_;
	private ArrayList<Pair<String, Expression>> values_;

}
