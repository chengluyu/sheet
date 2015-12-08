package ast;

public class IfStatement extends Statement {

	public IfStatement(Expression cond, Statement then, Statement otherwise) {
		cond_ = cond;
		then_ = then;
		else_ = otherwise;
	}
	
	private Expression cond_;
	private Statement then_;
	private Statement else_;

}
