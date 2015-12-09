package ast;

import java.util.ArrayList;

import token.Identifier;
import type.Type;
import utils.Pair;

public class ConstantDeclaration extends Declaration {

	public ConstantDeclaration(Type ty, ArrayList<Pair<String, Expression>> cs) {
		theType_ = ty;
		assignments_ = cs;
	}
	
	private Type theType_;
	private ArrayList<Pair<String, Expression>> assignments_;

}
