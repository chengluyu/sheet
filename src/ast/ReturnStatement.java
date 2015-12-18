package ast;

public class ReturnStatement extends Statement {

	public ReturnStatement(Expression retValue) {
		retValue_ = retValue;
	}
	
	private Expression retValue_;

}
