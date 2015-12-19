package parser;

import java.util.ArrayList;

import ast.*;
import lexer.*;
import utils.LexicalError;
import utils.ParseException;
import utils.Position;
import utils.SyntaxError;

public class Parser {

	public Parser(Lexer lex) throws LexicalError {
		initialize(lex);
	}
	
	public void parse() throws ParseException {
		parseProgram();
	}

	private void initialize(Lexer lex) throws LexicalError {
		peek = null;
		lex_ = lex;
		
		astNodeFactory_ = new AstNodeFactory();
		scope_ = new ScopeManager(this);
		
		lowestBreakable = null;
		lowestIteration = null;
		
		advance();
	}

	private AstNodeFactory astNodeFactory_;
	private ScopeManager scope_;
	
	private BreakableStatement lowestBreakable;
	private IterationStatement lowestIteration;

	// Lexical fields and functions

	private Token peek;
	private Lexer lex_;
	
	public Position position() {
		return lex_.position();
	}

	private Token expect(Tag wish) throws ParseException {
		if (peek.tag() == wish)
			return next();
		else
			throw new SyntaxError(position(), String.format(
					"expect %s instead of %s", wish.literal(), peek.literal()));
	}

	private void expectSemicolon() throws ParseException {
		expect(Tag.SEMICOLON);
	}

	private String expectIdentifier() throws ParseException {
		if (peek.isIdentifier())
			return (String) next().data();
		throw new SyntaxError(position(), String.format(
				"expect an identifier instead of %s", peek.literal()));
	}

	private boolean match(Tag tokenTag) throws LexicalError {
		if (peek.tag() == tokenTag) {
			advance();
			return true;
		}
		return false;
	}

	private Token next() throws LexicalError {
		Token save = peek;
		advance();
		return save;
	}

	private void advance() throws LexicalError {
		peek = lex_.advance();
	}

	// Parse a program in a file

	private void parseProgram() throws ParseException {
		// Program ::
		//	Declaration*
		while (!match(Tag.EOS))
			parseDeclaration();
	}

	// Parse declarations.
	// Declarations will modify the context.

	private void parseDeclaration() throws ParseException {
		// Declaration ::
		//	ConstantDeclaration |
		//	FunctionDeclaration |
		//	ImportDeclaration |
		//	VariableDeclaration
		switch (peek.tag()) {
		case CONST:
			parseConstantDeclaration();
		case FUNCTION:
			parseFunctionDeclaration();
		case IMPORT:
			throw new SyntaxError(position(), 
					"unimplemented parsing routine: import");
			// return parseImportDeclaration();
		case LET:
			parseVariableDeclaration(); // TODO change here
		default:
			throw new SyntaxError(position(), "expect declarations");
		}
	}
	
	// Constant declarations
	
	private ExpressionGroup parseConstantDeclaration() throws ParseException {
		ArrayList<Expression> group = new ArrayList<Expression>();
		expect(Tag.CONST);
		group.add(parseSingleConstantDeclaration());
		while (match(Tag.COMMA)) {
			group.add(parseSingleConstantDeclaration());
		}
		return astNodeFactory_.newExpressionGroup(group);
	}

	private Assignment parseSingleConstantDeclaration() throws ParseException {
		// SingleConstantDeclaration ::
		//	Identifier '=' Expression
		String id = expectIdentifier();
		expect(Tag.ASSIGN);
		Expression expr = parseExpression();
		
		Symbol symb = scope_.current().defineConstant(id);
		Reference symbRef = astNodeFactory_.newReference(symb);
		return astNodeFactory_.newAssignment(Tag.INIT_CONST, symbRef, expr);
	}
	
	// Variable declarations

	private ExpressionGroup parseVariableDeclaration() throws ParseException {
		ArrayList<Expression> group = new ArrayList<Expression>();
		expect(Tag.LET);
		group.add(parseSingleVariableDeclaration());
		while (match(Tag.COMMA)) {
			group.add(parseSingleVariableDeclaration());
		}
		return astNodeFactory_.newExpressionGroup(group);
	}

	private Assignment parseSingleVariableDeclaration() throws ParseException {
		// SingleVariableDeclaration ::
		//	Identifier ('=' Expression)?
		String id = expectIdentifier();
		Expression expr = null;
		if (match(Tag.ASSIGN))
			expr = parseExpression();
		Symbol symb = scope_.current().defineVariable(id);
		
		if (expr == null) {
			return null;
		} else {
			Reference symbRef = astNodeFactory_.newReference(symb);
			return astNodeFactory_.newAssignment(Tag.INIT_LET, symbRef, expr);
		}
	}

//	private void parseForEachVariableDeclaration(Scope loopScope)
//			throws ParseException {
//		// ForEachVariableDeclaration ::
//		//	'let' Identifier
//		expect(Tag.LET);
//		String id = expectIdentifier();
//		loopScope.defineVariable(id);
//	}

//	private void parseImportDeclaration() throws ParseException {
//		// ImportDeclaration ::
//		//	DirectlyImportDeclaration |
//		//	SelectiveImportDeclaration
//		// DirectlyImportDeclaration ::
//		//	'import' Identifier ';'
//		expect(Tag.IMPORT);
//
//	}
	
	private void parseSingleFunctionArguments() throws ParseException {
		String id = expectIdentifier();
		FunctionScope fscope = (FunctionScope) scope_.current();
		fscope.defineArgument(id);
	}
	
	private void parseFunctionArguments() throws ParseException {
		expect(Tag.LPAREN);
		if (!match(Tag.RPAREN)) {
			do
				parseSingleFunctionArguments();
			while (match(Tag.COMMA));
			expect(Tag.RPAREN);
		}
	}

	private void parseFunctionDeclaration() throws ParseException {
		// FunctionDeclaration ::
		//	'function' Identifier '(' Arguments ')' '{' 
		//	(Statement | Declaration)* '}'
		expect(Tag.FUNCTION);
		// scope preparation
		FunctionScope funcScope = scope_.newFunctionScope();
		
		String funcName = expectIdentifier();
		parseFunctionArguments();
		StatementBlock funcBody = parseStatementBlock(funcScope);
		FunctionSymbol funcSymb = new FunctionSymbol(funcName, funcBody);
		
		// scope clean up
		scope_.leaveScope(funcScope);
		ModuleScope topScope = (ModuleScope) scope_.current();
		topScope.defineFunction(funcName, funcSymb);
	}

	// Parse statements
	// Statements will not modify the context
	
	private Statement parseStatement() throws ParseException {
		// Statement ::
		//	BreakStatement |
		//	ContinueStatement |
		//	DoWhileStatement |
		//	ExpressionStatement |
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
			throw new SyntaxError(position(), 
					"unimplemented parsing routine: foreach");
			// return parseForEachStatement();
		case IF:
			return parseIfStatement();
		case RETURN:
			return parseReturnStatement();
		case SWITCH:
			return parseSwitchStatement();
		case WHILE:
			return parseWhileStatement();
		default:
			return parseExpressionStatement();
		}
	}

	private BreakStatement parseBreakStatement() throws ParseException {
		// BreakStatement ::
		//	'break' ';'
		if (lowestBreakable == null)
			throw new SyntaxError(position(),
					"break statement outside a breakable scope");
		expect(Tag.BREAK);
		expectSemicolon();
		return astNodeFactory_.newBreakStatement(lowestBreakable);
	}

	private ContinueStatement parseContinueStatement() throws ParseException {
		// ContinueStatement ::
		//	'continue' ';'
		if (lowestIteration == null)
			throw new SyntaxError(position(),
					"continue statement outside any iteration");
		expect(Tag.CONTINUE);
		expectSemicolon();
		return astNodeFactory_.newContinueStatement(lowestIteration);
	}

	private DoWhileStatement parseDoWhileStatement() throws ParseException {
		// DoWhileStatement ::
		//	'do' LoopBody 'while' '(' Expression ')' ';'
		DoWhileStatement loop = astNodeFactory_.newDoWhileStatement();
		
		// store
		IterationStatement saveIter = lowestIteration;
		BreakableStatement saveBreak = lowestBreakable;
		saveIter = loop;
		saveBreak = loop;
		
		expect(Tag.DO);
		Statement loopBody = parseStatement();
		expect(Tag.WHILE);
		Expression cond = parseParenthesisExpression();
		expectSemicolon();
		
		// restore
		lowestIteration = saveIter;
		lowestBreakable = saveBreak;
		
		loop.setup(cond, loopBody);
		return loop;
	}
	
	private ExpressionStatement parseExpressionStatement()
			throws ParseException {
		Expression expr = parseExpression();
		expectSemicolon();
		return astNodeFactory_.newExpressionStatement(expr);
	}

	private ForStatement parseForStatement() throws ParseException {
		// ForStatement ::
		//	'for' '(' (Expression | VariableDeclaration)
		//	';' Expression ';' Expression ')' LoopBody
		ForStatement loop = astNodeFactory_.newForStatement();
		
		// store
		IterationStatement saveIter = lowestIteration;
		BreakableStatement saveBreak = lowestBreakable;
		saveIter = loop;
		saveBreak = loop;
		
		// scope preparation
		LocalScope loopScope = scope_.newLocalScope();
		
		expect(Tag.FOR);
		expect(Tag.LPAREN);
		Expression initExpr = null;
		if (peek.tag() == Tag.LET) {
			initExpr = parseVariableDeclaration();
		} else {
			initExpr = parseExpression();
			expectSemicolon();
		}
		Expression condExpr = parseExpression();
		expectSemicolon();
		Expression incrExpr = parseExpression();
		expect(Tag.RPAREN);
		Statement loopBody = parseStatement();
		
		// restore
		lowestIteration = saveIter;
		lowestBreakable = saveBreak;
		
		// scope clean up
		scope_.leaveScope(loopScope);
		
		loop.setup(initExpr, condExpr, incrExpr, loopBody, loopScope);
		return loop;
	}

//	private ForEachStatement parseForEachStatement() throws ParseException {
//		enterScope();
//		expect(Tag.FOREACH);
//		expect(Tag.LPAREN);
//		<?> id = parseForEachVariableDeclaration();
//		expect(Tag.IN);
//		Expression expr = parseExpression();
//		expect(Tag.RPAREN);
//		Statement loopBody = parseStatement(false);
//		return astNodeFactory_.newForEachStatement(
//				id, expr, loopBody, exitScope());
//	}

	private IfStatement parseIfStatement() throws ParseException {
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

	private ReturnStatement parseReturnStatement() throws ParseException {
		// ReturnStatement ::
		//	'return' Expression ';'
		expect(Tag.RETURN);
		Expression retExpr = null;
		if (!match(Tag.SEMICOLON)) {
			retExpr = parseExpression();
		}
		return astNodeFactory_.newReturnStatement(retExpr);
	}

	private Statement parseSwitchStatement() throws SyntaxError {
		throw new SyntaxError(position(),
				"unimplemented parsing routine: switch");
	}

	private WhileStatement parseWhileStatement() throws ParseException {
		// WhileStatement ::
		//	'while' '(' Expression ')' LoopBody
		WhileStatement loop = astNodeFactory_.newWhileStatement();
		// store
		IterationStatement saveIter = lowestIteration;
		BreakableStatement saveBreak = lowestBreakable;
		saveIter = loop;
		saveBreak = loop;
		
		expect(Tag.WHILE);
		Expression cond = parseParenthesisExpression();
		Statement loopBody = parseLoopBody(null);
		
		// restore
		lowestIteration = saveIter;
		lowestBreakable = saveBreak;
		
		loop.setup(cond, loopBody);
		return loop;
	}
	
	/**
	 * Parse a body of loop statement.
	 * @param loopScope The scope where loop body bound to, can be null.
	 * @return Abstract syntax tree node of loop body statement(s).
	 * @throws SyntaxError
	 */
	private Statement parseLoopBody(LocalScope loopScope) throws ParseException {
		// LoopBody ::
		//	Statement |	StatementBlock
		if (match(Tag.LBRACE))
			return parseStatementBlock(loopScope);
		return parseStatement();
	}
	
	private StatementBlock parseStatementBlock(LocalScope loopScope)
			throws ParseException {
		// StatementBlock ::
		//	('{' Statement* '}')
		ArrayList<Statement> stmts = new ArrayList<Statement>();
		expect(Tag.LBRACE);
		while (!match(Tag.RBRACE)) {
			stmts.add(parseStatement());
		}
		return astNodeFactory_.newStatementBlock(stmts, loopScope);
	}


	// Parse a variety of expressions.
	// We use top-down operator precedence method.
	// See further information here: 
	// http://javascript.crockford.com/tdop/tdop.html

	private Expression parseExpression() throws ParseException {
		return parseExpression(0);
	}

	private Expression parseExpression(int rbp) throws ParseException {
		Expression left = parseNullDenotation();
		while (rbp < peek.lbp()) {
			left = parseLeftDenotation(left);
		}
		return left;
	}

	private Expression parseNullDenotation() throws ParseException {
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

	private Expression parseParenthesisExpression() throws ParseException {
		expect(Tag.LPAREN);
		Expression save = parseExpression();
		expect(Tag.RPAREN);
		return save;
	}

	private ArrayLiteral parseArrayLiteral() throws ParseException {
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

	private UnaryOperation parseUnaryOperation() throws ParseException {
		Token op = next();
		Expression expr = parseExpression(op.lbp());
		return astNodeFactory_.newUnaryOperation(op.tag(), expr);
	}
	
	private Reference parseReference() throws ParseException {
		// Reference :: Identifier
		String id = expectIdentifier();
		Symbol symb = scope_.current().find(id);
		if (symb == null)
			throw new SyntaxError(position(),  "undefiend reference to " + id);
		return astNodeFactory_.newReference(symb);
	}

	private Expression parseLeftDenotation(Expression left)
			throws ParseException {
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

	private Conditional parseConditional(Expression cond)
			throws ParseException {
		expect(Tag.CONDITIONAL);
		Expression then = parseExpression();
		expect(Tag.COLON);
		Expression otherwise = parseExpression();
		return astNodeFactory_.newConditional(cond, then, otherwise);
	}

	private Property parseProperty(Expression left) throws ParseException {
		expect(Tag.PERIOD);
		String property = expectIdentifier();
		return astNodeFactory_.newProperty(left, property);
	}

	private Index parseIndex(Expression left) throws ParseException {
		expect(Tag.LBRACK);
		Expression index = parseExpression();
		expect(Tag.RBRACK);
		return astNodeFactory_.newIndex(left, index);
	}

	private UnaryOperation parsePostfixOperation(Expression left)
			throws ParseException {
		Token op = next();
		Tag postop = op.tag() == Tag.INC ? Tag.POSTFIX_INC : Tag.POSTFIX_DEC;
		return astNodeFactory_.newUnaryOperation(postop, left);
	}
	
	private ExpressionGroup parseExpressionGroup(Expression first)
			throws ParseException {
		ArrayList<Expression> group = new ArrayList<Expression>();
		Token comma = expect(Tag.COMMA);
		
		group.add(first);
		while (match(Tag.COMMA)) {
			group.add(parseExpression(comma.lbp()));
		}
		return astNodeFactory_.newExpressionGroup(group);
	}

	private CompareOperation parseCompareOperation(Expression left)
			throws ParseException {
		Token op = next();
		Expression right = parseExpression(op.lbp()); // left associative
		return astNodeFactory_.newCompareOperation(op.tag(), left, right);
	}

	private BinaryOperation parseBianryOperation(Expression left)
			 throws ParseException {
		Token op = next();
		Expression right = parseExpression(op.lbp()); // left associative
		return astNodeFactory_.newBinaryOperation(op.tag(), left, right);
	}

	private Assignment parseAssignment(Expression left) throws ParseException {
		Token op = next();
		Expression right = parseExpression(op.lbp() - 1); // right associative
		return astNodeFactory_.newAssignment(op.tag(), left, right);
	}

}