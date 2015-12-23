package ast;

public class ReturnStatement extends Statement {

	public ReturnStatement(Expression retValue) {
		retValue_ = retValue;
	}
	
	private Expression retValue_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		if (retValue_ == null) {
			printer.text("return statement");
		} else {
			printer.beginBlock("return statement");
			printer.child("return value", retValue_);
			printer.endBlock();
		}
	}

}
