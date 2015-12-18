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

}
