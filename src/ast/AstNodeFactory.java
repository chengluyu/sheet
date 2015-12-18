package ast;

import java.util.ArrayList;

import lexer.Tag;
import lexer.Token;
import parser.Symbol;

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
			data = tok.data();
		case NULL_LITERAL:
			data = null;
			break;
		case TRUE_LITERAL:
			data = new Boolean(true);
			break;
		case FALSE_LITERAL:
			data = new Boolean(false);
			break;
		default:
			
		}
		return new Literal();
	}
	
	public ArrayLiteral newLiteral(ArrayList<Expression> elems) {
		return new ArrayLiteral(elems);
	}

	public Reference newReference(Symbol symb) {
		return new Reference(symb);
	}
	
	// Expressions
	
	public BinaryOperation newBinaryOperation(Tag op, Expression left,
			Expression right) {
		return new BinaryOperation(op, left, right);
	}

	public Conditional newConditional(Expression cond, Expression then,
			Expression otherwise) {
		return new Conditional(cond, then, otherwise);
	}
	
	public CompareOperation newCompareOperation(Tag op, Expression left,
			Expression right) {
		return new CompareOperation(op, left, right);
	}
	
	// Statements
	
	public BreakStatement newBreakStatement(BreakableStatement target) {
		return new BreakStatement(target);
	}

}