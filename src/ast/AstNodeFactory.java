package ast;

import java.util.ArrayList;
import java.util.HashMap;

import token.BinaryOp;
import token.Tag;
import utils.Pair;

public class AstNodeFactory {

	public AstNodeFactory() {
		// TODO Auto-generated constructor stub
	}
	
	// Statement creators
	
	public BreakStatement createBreakStmt(BreakableStatement target) {
		return new BreakStatement(target);
	}
	
	public ContinueStatement createContinueStmt(IterationStatement target) {
		return new ContinueStatement(target);
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
	
	public StatementBlock createStatementBlock(ArrayList<Statement> stmts) {
		return new StatementBlock(stmts);
	}
	
	public ExpressionStatement createExpressionStmt(Expression expr) {
		return new ExpressionStatement(expr);
	}
	
	public ForStatement createForStatement(Expression init,
			Expression cond,
			Expression incr,
			Statement body) {
		return new ForStatement(init, cond, incr, body);
	}
	
	public EmptyStatement createEmptyStmt() {
		return new EmptyStatement();
	}
	
	// Type specifier creators
	
	public ArrayTypeNode createArrayTypeSpec(TypeSpecifier elemType) {
		return new ArrayTypeNode(elemType);
	}
	
	// Expression creators
	

	
}
