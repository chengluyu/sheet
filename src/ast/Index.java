package ast;

public class Index extends Expression {
	
	public Index(Expression left, Expression right) {
		value_ = left;
		refinement_ = right;
	}
	
	private Expression value_;
	private Expression refinement_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("index operation");
		printer.begin();
		printer.child("indexed object", value_);
		printer.child("index value", refinement_);
		printer.end();
	}
	
}
