package ast;

public class Initialization extends AstNode {

	public Initialization(LocalReferecne ref, Expression expr) {
		ref_ = ref;
		expr_ = expr;
	}

	private LocalReferecne ref_;
	private Expression expr_;

}