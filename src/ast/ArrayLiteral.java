package ast;

import java.util.ArrayList;

public class ArrayLiteral extends Literal {

	public ArrayLiteral(ArrayList<Expression> elems) {
		elems_ = elems;
	}
	
	private ArrayList<Expression> elems_;
	
	public ArrayList<Expression> elements() {
		return elems_;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("array literal");
		printer.property("element count", String.valueOf(elems_.size()));
		for (int i = 0; i < elems_.size(); i++)
			printer.child(String.valueOf(i), elems_.get(i));
		printer.endBlock();
	}

}