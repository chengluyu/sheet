package ast;

public class ReturnStatement extends Statement {

	public ReturnStatement(Expression retValue) {
		retValue_ = retValue;
	}
	
	private Expression retValue_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		if (retValue_ == null) {
			printer.titleOnly("return statement");
		} else {
			printer.title("return statement");
			printer.begin();
			printer.child("return value", retValue_);
			printer.end();
		}
	}

}
