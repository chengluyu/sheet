package ast;

import java.util.ArrayList;

import lexer.Tag;
import lexer.Token;
import parser.scope.FunctionScope;
import parser.scope.GlobalScope;
import parser.symbol.FunctionSymbol;
import parser.symbol.Symbol;

public class AstNodeFactory {

	public AstNodeFactory() {
		
	}

	public Literal newLiteral(Token tok) {
		Object data = null;
		switch (tok.tag()) {
		case CHAR_LITERAL:
		case STRING_LITERAL:
		case INTEGER:
		case NUMBER:
			data = tok.data(); // tok.data() are primitive types
			break;
		case NULL_LITERAL:
		case TRUE_LITERAL:
		case FALSE_LITERAL:
			data = null;
			break;
		default:
			return null;
		}
		return new ValueLiteral(tok.tag(), data);
	}
	
	public ArrayLiteral newLiteral(ArrayList<Expression> elems) {
		return new ArrayLiteral(elems);
	}
	
	public SymbolReference newReference(Symbol symb) {
		return new SymbolReference(symb);
	}

	public SymbolReference newUnsolvedReference(String name) {
		return new SymbolReference(name);
	}
	
	// Expressions
	
	public Assignment newAssignment(Tag op, Expression left,
			Expression right) {
		return new Assignment(op, left, right);
	}
	
	public BinaryOperation newBinaryOperation(Tag op, Expression left,
			Expression right) {
		return new BinaryOperation(op, left, right);
	}

	public CompareOperation newCompareOperation(Tag op, Expression left,
			Expression right) {
		return new CompareOperation(op, left, right);
	}
	
	public Conditional newConditional(Expression cond, Expression then,
			Expression otherwise) {
		return new Conditional(cond, then, otherwise);
	}
	
	public ExpressionGroup newExpressionGroup(
			ArrayList<? extends Expression> exprs) {
		return new ExpressionGroup(exprs);
	}
	
	public Index newIndex(Expression obj, Expression index) {
		return new Index(obj, index);
	}
	
	public Invoke newInvoke(Expression func, ExpressionGroup args) {
		return new Invoke(func, args);
	}
	
	public Property newProperty(Expression obj, String prop) {
		return new Property(obj, prop);
	}
	
	public UnaryOperation newUnaryOperation(Tag op, Expression operand) {
		return new UnaryOperation(op, operand);
	}
	
	// Statements
	
	public BreakStatement newBreakStatement(BreakableStatement target) {
		return new BreakStatement(target);
	}
	
	public ContinueStatement newContinueStatement(IterationStatement target) {
		return new ContinueStatement(target);
	}
	
	public DoWhileStatement newDoWhileStatement() {
		return new DoWhileStatement();
	}
	
	public ExpressionStatement newExpressionStatement(Expression expr) {
		return new ExpressionStatement(expr);
	}
	
	public ForStatement newForStatement() {
		return new ForStatement();
	}
	
	public IfStatement newIfStatement(Expression cond, Statement then,
			Statement otherwise) {
		return new IfStatement(cond, then, otherwise);
	}
	
	public ReturnStatement newReturnStatement(Expression ret) {
		return new ReturnStatement(ret);
	}
	
	public StatementBlock newStatementBlock(ArrayList<Statement> stmts) {
		return new StatementBlock(stmts);
	}
	
	public WhileStatement newWhileStatement() {
		return new WhileStatement();
	}
	
	public Module newModule(
			GlobalScope scope,
			StatementBlock globalInits,
			ArrayList<Function> functions) {
		return new Module(scope, globalInits, functions);
	}

	public Function newFunction(
			FunctionSymbol symb,
			FunctionScope scope,
			StatementBlock body) {
		return new Function(symb, scope, body);
	}

}