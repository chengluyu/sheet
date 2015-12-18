package ast;

public class Initialization extends Statement {

	public Initialization(Reference ref, Expression expr) {
		ref_ = ref;
		expr_ = expr;
	}

	private Reference ref_;
	private Expression expr_;

}