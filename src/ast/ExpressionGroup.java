package ast;

import java.util.ArrayList;

public class ExpressionGroup extends Expression {

	public ExpressionGroup(ArrayList<Expression> exprs) {
		exprs_ = exprs;
	}
	
	private ArrayList<Expression> exprs_;

}
