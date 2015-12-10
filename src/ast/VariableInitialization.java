package ast;

import java.util.ArrayList;

import utils.Pair;

public class VariableInitialization extends Statement {

	public VariableInitialization() {
		
	}
	
	private ArrayList<Pair<ValueSymbolProxy, Expression>> inits_;

}
