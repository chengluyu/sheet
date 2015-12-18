package ast;

public class Property extends Expression {

	public Property(Expression expr, String prop) {
		expr_ = expr;
		prop_ = prop;
	}
	
	private Expression expr_;
	private String prop_;

}
