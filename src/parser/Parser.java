
import java.util.ArrayList;

import ast.*;
import lexer.*;
import utils.LexicalError;
import utils.Position;
import utils.SyntaxError;
import utils.UnimplementedError;

public class Parser {

	public Parser(Lexer lex) {
		initialize(lex);
	}

	private void initialize(Lexer lex) {
		peek = null;
		lex_ = lex;
		astNodeFactory_ = new AstNodeFactory();
		scope_ = new ScopeManager(this);
		advance();
	}

	private AstNodeFactory astNodeFactory_;
	private ScopeManager scope_;

	// Lexical fields and functions

	private Token peek;
	private Lexer lex_;
	
	public Position position() {
		return lex_.position();
	}

	private Token expect(Tag wish) throws SyntaxError {
		if (peek.tag() == wish)
			return next();
		else
			throw new SyntaxError(position(), String.format(
					"expect %s instead of %s", wish.literal(), peek.literal()));
	}

	private void expectSemicolon() throws SyntaxError {
		expect(Tag.SEMICOLON);
	}

	private String expectIdentifier() throws SyntaxError {
		if (peek.isIdentifier())
			return (String) next().data();
		throw new SyntaxError(position(), String.format(
				"expect an identifier instead of %s", peek.literal()));
	}

	private boolean match(Tag tokenTag) {
		if (peek.tag() == tokenTag) {
			advance();
			return true;
		}
		return false;
	}

	private Token next() {
		Token save = peek;
		advance();
		return save;
	}

	private void advance() throws LexicalError {
		peek = lex_.advance();
	}

	// Parse a program in a file

	private void parseProgram() {
		// Program ::
		//	Declaration*
		while (!match(Tag.EOS))
			parseDeclaration();
	}

	// Parse declarations.
	// Declarations will modify the context.

	private InitializationGroup parseDeclaration() throws SyntaxError {
		// Declaration ::
		//	ConstantDeclaration |
		//	FunctionDeclaration |
		//	ImportDeclaration |
		//	VariableDeclaration
		switch (peek.tag()) {
		case CONST:
			return parseConstantDeclaration();
		case FUNCTION:
			return parseFunctionDeclaration();
		case IMPORT:
			throw new UnimplementedError(position(), 
					"unimplemented parsing routine: import");
			// return parseImportDeclaration();
		case LET:
			return parseVariableDeclaration();
		default:
			throw new SyntaxError(position(), "expect declarations");
		}
	}
	
	// Constant declarations
	
	private Initialization parseConstantDeclaration() throws SyntaxError {
		ArrayList<Initialization> group = new ArrayList<Initialization>();
		expect(Tag.CONST);
		group.add(parseSingleConstantDeclaration());
		while (match(Tag.COMMA)) {
			group.add(parseSingleConstantDeclaration());
		}
		return astNodeFactory_.newConstantInitializationGroup(group);
	}

	private Assignment parseSingleConstantDeclaration() throws SyntaxError {
		// SingleConstantDeclaration ::
		//	Identifier '=' Expression
		String id = expectIdentifier();
		expect(Tag.ASSIGN);
		Expression expr = parseExpression();
		scope_.current().defineConstant(id);
		return astNodeFactory_.newConstantInitialization(id, expr);
	}
	
	// Variable declarations

	private InitializationGroup parseVariableDeclaration() throws SyntaxError {
		ArrayList<Initialization> group = new ArrayList<Initialization>();
		expect(Tag.LET);
		group.add(parseSingleVariableDeclaration());
		while (match(Tag.COMMA)) {
			group.add(parseSingleVariableDeclaration());
		}
		return astNodeFactory_.newVariableInitializationGroup(group);
	}

	private Assignment parseSingleVariableDeclaration() throws SyntaxError {
		// SingleVariableDeclaration ::
		//	Identifier ('=' Expression)?
		String id = expectIdentifier();
		Expression expr = null;
		if (match(Tag.ASSIGN))
			expr = parseExpression();
		scope_.current().defineVariable(id);
		return astNodeFactory_.newVariableInitialization(id, expr);
	}

	private void parseForEachVariableDeclaration(Scope loopScope) {
		// ForEachVariableDeclaration ::
		//	'let' Identifier
		expect(Tag.LET);
		String id = expectIdentifier();
		loopScope.define(id);
	}

	private <?> parseImportDeclaration() {
		// ImportDeclaration ::
		//	DirectlyImportDeclaration |
		//	SelectiveImportDeclaration
		// DirectlyImportDeclaration ::
		//	'import' Identifier ';'
		expect(Tag.IMPORT);

	}
	
	private void parseSingleFunctionArguments() throws SyntaxError {
		String id = expectIdentifier();
		FunctionScope fscope = (FunctionScope) scope_.current();
		fscope.defineArgument(id);
	}
	
	private void parseFunctionArguments() throws SyntaxError {
		expect(Tag.LPAREN);
		if (!match(Tag.RPAREN)) {
			do
				parseSingleFunctionArguments();
			while (match(Tag.COMMA));
			expect(Tag.RPAREN);
		}
	}

	private Initialization parseFunctionDeclaration() throws SyntaxError {
		// FunctionDeclaration ::
		//	'function' Identifier '(' Arguments ')' '{' Statement* '}'
		FunctionScope funcScope = scope_.newFunctionScope();
		expect(Tag.FUNCTION);
		String funcName = expectIdentifier();
		parseFunctionArguments();
		
	}

	// Parse statements
	// Statements will not modify the context

	private Statement parseStatement() {
		return parseStatement(true);
	}
	
	private Statement parseStatement(boolean canCreateNewScope)
			throws SyntaxError {
		// Statement ::
		//	BreakStatement |
		//	ContinueStatement |
		//	DoWhileStatement |
		//	ForStatement |
		//	ForEachStatement |
		//	IfStatement |
		//	ReturnStatement |
		//	StatementBlock |
		//	SwitchStatement |
		//	WhileStatement
		switch (peek.tag()) {
		case BREAK:
			return parseBreakStatement();
		case CONTINUE:
			return parseContinueStatement();
		case DO:
			return parseDoWhileStatement();
		case FOR:
			return parseForStatement();
		case FOREACH:
			return parseForEachStatement();
		case IF:
			return parseIfStatement();
		case LBRACE:
			return parseStatementBlock(canCreateNewScope);
		case RETURN:
			return parseReturnStatement();
		case SWITCH:
			return parseSwitchStatement();
		case WHILE:
			return parseWhileStatement();
		default:
			throw new SyntaxError(position(), "expect statements");
		}
	}

	private BreakStatement parseBreakStatement() throws SyntaxError {
		// BreakStatement ::
		//	'break' ';'
		expect(Tag.BREAK);
		expectSemicolon();
		return astNodeFactory_.newBreakStatement();
	}

	private ContinueStatement parseContinueStatement() throws SyntaxError {
		// ContinueStatement ::
		//	'continue' ';'
		expect(Tag.CONTINUE);
		expectSemicolon();
		return astNodeFactory_.newContinueStatement();
	}

	private DoWhileStatement parseDoWhileStatement() throws SyntaxError {
		// DoWhileStatement ::
		//	'do' Statement 'while' '(' Expression ')' ';'
		expect(Tag.DO);
		Statement loopBody = parseStatement();
		expect(Tag.WHILE);
		Expression cond = parseParenthesisExpression();
		expectSemicolon();
		return astNodeFactory_.newDoWhileStatement(cond, loopBody);
	}

	private ForStatement parseForStatement() throws SyntaxError {
		// ForStatement ::
		//	'for' '(' (Expression | VariableDeclaration)
		//	';' Expression ';' Expression ')' Statement
		enterScope();
		expect(Tag.FOR);
		expect(Tag.LPAREN);
		Expression initExpr = null;
		if (peek().tag() == Tag.LET) {
			initExpr = parseVariableDeclaration();
		} else {
			initExpr = parseExpression();
			expectSemicolon();
		}
		Expression condExpr = parseExpression();
		expectSemicolon();
		Expression incrExpr = parseExpression();
		expect(Tag.RPAREN);
		Statement loopBody = parseStatement(false);
		return astNodeFactory_.newForStatement(
				initExpr, condExpr, incrExpr, loopBody, exitScope());
	}

	private ForEachStatement parseForEachStatement() throws SyntaxError {
		enterScope();
		expect(Tag.FOREACH);
		expect(Tag.LPAREN);
		<?> id = parseForEachVariableDeclaration();
		expect(Tag.IN);
		Expression expr = parseExpression();
		expect(Tag.RPAREN);
		Statement loopBody = parseStatement(false);
		return astNodeFactory_.newForEachStatement(
				id, expr, loopBody, exitScope());
	}

	private IfStatement parseIfStatement() {
		// IfStatement ::
		//	'if' '(' Expression ')' Statement ('else' Statement)?
		expect(Tag.IF);
		Expression cond = parseParenthesisExpression();
		Statement then = parseStatement(), otherwise = null;
		if (match(Tag.ELSE)) {
			otherwise = parseStatement();
		}
		return astNodeFactory_.newIfStatement(cond, then, otherwise);
	}

	private StatementBlock parseStatementBlock(boolean canCreateNewScope) {
		if (canCreateNewScope) enterScope();
		expect(Tag.LBRACE);
		
		expect(Tag.RBRACE);
		if (canCreateNewScope) exitScope();
	}

	private ReturnStatement parseReturnStatement() {
		// ReturnStatement ::
		//	'return' Expression ';'
		expect(Tag.RETURN);
		Expression retExpr = null;
		if (!match(Tag.SEMICOLON)) {
			retExpr = parseExpression();
		}
		return astNodeFactory_.newReturnStatement(retExpr);
	}

	private Statement parseSwitchStatement() {
		expect(Tag.SWITCH);

		return null;
	}

	private WhileStatement parseWhileStatement() {
		// WhileStatement ::
		//	'while' '(' Expression ')' Statement
		expect(Tag.WHILE);
		Expression cond = parseParenthesisExpression();
		Statement loopBody = parseStatement();
		return astNodeFactory_.newWhileStatement(cond, loopBody);
	}


	// Parse a variety of expressions.
	// We use top-down operator precedence method.
	// See further information here: 
	// http://javascript.crockford.com/tdop/tdop.html

	private Expression parseExpression() throws SyntaxError {
		return parseExpression(0);
	}

	private Expression parseExpression(int rbp) throws SyntaxError {
		Expression left = parseNullDenotation();
		while (rbp < peek.lbp()) {
			left = parseLeftDenotation(left);
		}
		return left;
	}

	private Expression parseNullDenotation() throws SyntaxError {
		switch (peek.tag()) {
		case CHAR_LITERAL:
		case STRING_LITERAL:
		case INTEGER:
		case NUMBER:
		case NULL_LITERAL:
		case TRUE_LITERAL:
		case FALSE_LITERAL:
			return astNodeFactory_.newLiteral(next());
		case LPAREN:
			return parseParenthesisExpression();
		case LBRACK:
			return parseArrayLiteral();
		case ADD:
		case SUB:
		case NOT:
		case BIT_NOT:
		case INC:
		case DEC:
			return parseUnaryOperation();
		case IDENTIFIER:
			return parseReference();
		default:
			throw new SyntaxError(position(), "undefined value");
		}
	}

	private Expression parseParenthesisExpression() throws SyntaxError {
		expect(Tag.LPAREN);
		Expression save = parseExpression();
		expect(Tag.RPAREN);
		return save;
	}

	private ArrayLiteral parseArrayLiteral() throws SyntaxError {
		// ArrayLiteral ::
		//	'[' (Expression (',' Expression)*)? ']'
		ArrayList<Expression> elems = new ArrayList<Expression>();
		expect(Tag.LBRACK);
		if (peek.tag() != Tag.RBRACK) {
			elems.add(parseExpression());
			while (match(Tag.COMMA))
				elems.add(parseExpression());
		}
		expect(Tag.RBRACK);
		return astNodeFactory_.newLiteral(elems);
	}

	private UnaryOperation parseUnaryOperation() throws SyntaxError {
		Token op = next();
		Expression expr = parseExpression(op.lbp());
		return astNodeFactory_.newUnaryOperation(op.tag(), expr);
	}
	
	private Reference parseReference() throws SyntaxError {
		// Reference :: Identifier
		String id = expectIdentifier();
		Symbol symb = scope_.current().find(id);
		if (symb == null)
			throw new SyntaxError(position(),  "undefiend reference to " + id);
		return astNodeFactory_.newReference(symb);
	}

	private Expression parseLeftDenotation(Expression left) throws SyntaxError {
		switch (peek.tag()) {
		case CONDITIONAL:
			return parseConditional(left);
		case PERIOD:
			return parseProperty(left);
		case LBRACK:
			return parseIndex(left);
		case INC:
		case DEC:
			return parsePostfixOperation(left);
		case COMMA:
			return parseExpressionGroup(left);
		case LT:
		case LTE:
		case GT:
		case GTE:
		case EQ:
		case NE:
			return parseCompareOperation(left);
		case BIT_AND:
		case BIT_OR:
		case BIT_XOR:
		case AND:
		case OR:
		case SHL:
		case SHR:
		case SAR:
		case ADD:
		case SUB:
		case MUL:
		case DIV:
		case MOD:
			return parseBianryOperation(left);
		case ASSIGN:
		case ASSIGN_BIT_OR:
		case ASSIGN_BIT_XOR:
		case ASSIGN_BIT_AND:
		case ASSIGN_SHL:
		case ASSIGN_SHR:
		case ASSIGN_SAR:
		case ASSIGN_ADD:
		case ASSIGN_SUB:
		case ASSIGN_MUL:
		case ASSIGN_DIV:
		case ASSIGN_MOD:
			return parseAssignment(left);
		default:
			throw new SyntaxError(position(), "missing an operator");
		}
	}

	private Conditional parseConditional(Expression cond) throws SyntaxError {
		expect(Tag.CONDITIONAL);
		Expression then = parseExpression();
		expect(Tag.COLON);
		Expression otherwise = parseExpression();
		return astNodeFactory_.newConditional(cond, then, otherwise);
	}

	private Property parseProperty(Expression left) throws SyntaxError {
		expect(Tag.PERIOD);
		String property = expectIdentifier();
		return astNodeFactory_.newProperty(left, property);
	}

	private Index parseIndex(Expression left) throws SyntaxError {
		expect(Tag.LBRACK);
		Expression index = parseExpression();
		expect(Tag.RBRACK);
		return astNodeFactory_.newIndex(left, index);
	}

	private UnaryOperation parsePostfixOperation(Expression left) throws SyntaxError {
		Token op = next();
		return astNodeFactory_.newPostifxOperation(op.tag(), left);
	}
	
	private ExpressionGroup parseExpressionGroup(Expression first)
			throws SyntaxError {
		ArrayList<Expression> group = new ArrayList<Expression>();
		Token comma = expect(Tag.COMMA);
		
		group.add(first);
		while (match(Tag.COMMA)) {
			group.add(parseExpression(comma.lbp()));
		}
		return astNodeFactory_.newExpressionGroup(group);
	}

	private CompareOperation parseCompareOperation(Expression left)
			throws SyntaxError {
		Token op = next();
		Expression right = parseExpression(op.lbp()); // left associative
		return astNodeFactory_.newCompareOperation(op.tag(), left, right);
	}

	private BinaryOperation parseBianryOperation(Expression left)
			 throws SyntaxError {
		Token op = next();
		Expression right = parseExpression(op.lbp()); // left associative
		return astNodeFactory_.newBinaryOperation(op.tag(), left, right);
	}

	private Assignment parseAssignment(Expression left) throws SyntaxError {
		Token op = next();
		Expression right = parseExpression(op.lbp() - 1); // right associative
		return astNodeFactory_.newAssignment(op.tag(), left, right);
	}

}