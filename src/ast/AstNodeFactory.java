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
	
	// Declaration creators
	
	public ConstantDeclaration createConstantDecl(TypeSpecifier ty, ArrayList<Pair<String, Expression>> cs) {
		return new ConstantDeclaration(ty, cs);
	}
	
	public VariableDeclaration createVariableDecl(TypeSpecifier ty, ArrayList<Pair<String, Expression>> vs) {
		return new VariableDeclaration(ty, vs);
	}
	
	public FunctionDeclaration createFucntionDecl(String name, FunctionSpecifier fty, Statement body) {
		return new FunctionDeclaration(fty, body);
	}
	
	public EnumDeclaration createEnumDecl(String name, ArrayList<String> values) {
		return new EnumDeclaration(name, values);
	}
	
	public TypeAliasDeclaration createTypeAliasDecl(String typeName, TypeSpecifier ty) {
		return new TypeAliasDeclaration(typeName, ty);
	}
	
	// Type specifier creators
	
	public ArrayTypeNode createArrayTypeSpec(TypeSpecifier elemType) {
		return new ArrayTypeNode(elemType);
	}
	
	// Expression creators
	
	public BinaryOpNode createBinaryOpNode(BinaryOp opToken, Expression lhs, Expression rhs) {
		BinaryOperator op = conversion_.get(opToken.getTag());
		if (op == null) {
			// TODO raise a error
			return null;
		} else {
			return new BinaryOpNode(op, lhs, rhs);
		}
	}
	
	private static final HashMap<Tag, BinaryOperator> conversion_ = new HashMap<Tag, BinaryOperator>();
	
	static {
		conversion_.put(Tag.AND, BinaryOperator.AND);
		conversion_.put(Tag.OR, BinaryOperator.OR);
		conversion_.put(Tag.BIT_AND, BinaryOperator.BIT_AND);
		conversion_.put(Tag.BIT_OR, BinaryOperator.BIT_OR);
		conversion_.put(Tag.BIT_XOR, BinaryOperator.BIT_XOR);
		conversion_.put(Tag.SHL, BinaryOperator.SHL);
		conversion_.put(Tag.SHR, BinaryOperator.SHR);
		conversion_.put(Tag.ADD, BinaryOperator.ADD);
		conversion_.put(Tag.SUB, BinaryOperator.SUB);
		conversion_.put(Tag.MUL, BinaryOperator.MUL);
		conversion_.put(Tag.DIV, BinaryOperator.DIV);
		conversion_.put(Tag.MOD, BinaryOperator.MOD);
		conversion_.put(Tag.EQ, BinaryOperator.EQ);
		conversion_.put(Tag.NE, BinaryOperator.NE);
		conversion_.put(Tag.LT, BinaryOperator.LT);
		conversion_.put(Tag.GT, BinaryOperator.GT);
		conversion_.put(Tag.LTE, BinaryOperator.LTE);
		conversion_.put(Tag.GTE, BinaryOperator.GTE);
		conversion_.put(Tag.ASSIGN, BinaryOperator.ASSIGN);
		conversion_.put(Tag.ASSIGN_BIT_OR, BinaryOperator.ASSIGN_BIT_OR);
		conversion_.put(Tag.ASSIGN_BIT_XOR, BinaryOperator.ASSIGN_BIT_XOR);
		conversion_.put(Tag.ASSIGN_BIT_AND, BinaryOperator.ASSIGN_BIT_AND);
		conversion_.put(Tag.ASSIGN_SHL, BinaryOperator.ASSIGN_SHL);
		conversion_.put(Tag.ASSIGN_SHR, BinaryOperator.ASSIGN_SHR);
		conversion_.put(Tag.ASSIGN_ADD, BinaryOperator.ASSIGN_ADD);
		conversion_.put(Tag.ASSIGN_SUB, BinaryOperator.ASSIGN_SUB);
		conversion_.put(Tag.ASSIGN_MUL, BinaryOperator.ASSIGN_MUL);
		conversion_.put(Tag.ASSIGN_DIV, BinaryOperator.ASSIGN_DIV);
		conversion_.put(Tag.ASSIGN_MOD, BinaryOperator.ASSIGN_MOD);
	}
	
}
