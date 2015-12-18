package ast;

import java.util.ArrayList;

public class ArrayLiteral extends Literal {

	public ArrayLiteral() {
		elems_ = new ArrayList<Expression>();
	}
	
	private ArrayList<Expression> elems_;
	
	public void append(Expression elem) {
		elems_.add(elem);
	}

}
