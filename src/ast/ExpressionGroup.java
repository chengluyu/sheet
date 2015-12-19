package ast;

import java.util.ArrayList;

public class ExpressionGroup extends Expression {

	public ExpressionGroup(ArrayList<Expression> exprs) {
		exprs_ = exprs;
	}
	
	private ArrayList<Expression> exprs_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("expression group");
		printer.begin();
		printer.property("length", String.valueOf(exprs_.size()));
		for (int i = 0; i < exprs_.size(); i++)
			printer.child(String.valueOf(i), exprs_.get(i));
		printer.end();
	}
	
}
