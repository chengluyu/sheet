package ast;

import token.Value;

public class AstNodeFactory {

	public AstNodeFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public IfStatement createIfStmt(Expression cond, Statement then, Statement otherwise) {
		return new IfStatement(cond, then, otherwise);
	}
	
	public WhileStatement createWhileStmt(Expression cond, Statement body) {
		return new WhileStatement(cond, body);
	}
	
	public DoWhileStatement createDoWhileStmt(Expression cond, Statement body) {
		return new DoWhileStatement(cond, body);
	}
	
	public ValueNode createValueNode(Value valueToken) {
		
	}

}
