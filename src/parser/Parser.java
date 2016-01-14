package parser;

import java.util.ArrayList;

import ast.*;
import lexer.*;
import parser.scope.FunctionScope;
import parser.symbol.*;
import utils.LexicalError;
import utils.Pair;
import utils.Position;
import utils.SyntaxError;

public class Parser {

	public Parser(Lexer lex) throws LexicalError {
		initialize(lex);
	}
	
	public Module parse() throws LexicalError, SyntaxError {
		return parseModule();
	}

	private void initialize(Lexer lex) throws LexicalError {
		peek = null;
		lex_ = lex;
		
		astNodeFactory_ = new AstNodeFactory();
		
		globalInits_ = new ArrayList<Statement>();
		context_ = new Context(this);
		
		lowestBreakable = null;
		lowestIteration = null;
		
		advance();
	}

	private AstNodeFactory astNodeFactory_;
	
	private ArrayList<Statement> globalInits_;
	private Context context_;
	
	private BreakableStatement lowestBreakable;
	private IterationStatement lowestIteration;

	// Lexical fields and functions

	private Token peek;
	private Lexer lex_;

	private Token expect(Tag wish) throws LexicalError, SyntaxError {
		if (peek.tag() == wish)
			return next();
		else
			throw new SyntaxError(peek.position(), String.format(
					"expect %s instead of %s", wish.literal(), peek.literal()));
	}

	private void expectSemicolon() throws LexicalError, SyntaxError {
		expect(Tag.SEMICOLON);
	}

	private String expectIdentifier() throws LexicalError, SyntaxError {
		if (peek.isIdentifier())
			return (String) next().data();
		throw new SyntaxError(peek.position(), String.format(
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
	
	public Position currentPosition() {
		return peek.position();
	}

	// Parse a program in a file

	private Module parseModule() throws LexicalError, SyntaxError {
		// Module ::
		//	ModuleDeclaration*
		ArrayList<Function> functions = new ArrayList<Function>();
		while (!match(Tag.EOS)) {
			// ModuleDeclaration ::
			//	ClassDeclaration |
			//	ConstantDeclaration |
			//	ExportDeclaration |
			//	ModuleFunctionDeclaration |
			//	ImportDeclaration |
			//	VariableDeclaration
			switch (peek.tag()) {
			case CLASS:
				throw new SyntaxError(peek.position(), 
						"unimplemented parsing routine: class");
			case CONST:
				parseConstantDeclaration().forEach(assign -> {
					globalInits_.add(astNodeFactory_.newExpressionStatement(
							assign));
				});
				break;
			case EXPORT:
				throw new SyntaxError(peek.position(), 
						"unimplemented parsing routine: export");
			case FUNCTION:
				functions.add(parseFunctionDeclaration());
				break;
			case IMPORT:
				throw new SyntaxError(peek.position(), 
						"unimplemented parsing routine: import");
			case LET:
				parseVariableDeclaration().forEach(assign -> {
					globalInits_.add(astNodeFactory_.newExpressionStatement(
							assign));
				});
				break;
			default:
				throw new SyntaxError(peek.position(), String.format(
						"error token %s, expect declarations", peek.literal()));
			}
		}
		return astNodeFactory_.newModule(
				context_.globalScope(),
				astNodeFactory_.newStatementBlock(globalInits_),
				functions
				);
	}
	
	// Constant declarations
	
	private ArrayList<Assignment> parseConstantDeclaration()
			throws LexicalError, SyntaxError {
		// ConstantDeclaration ::
		//	'const' SingleConstantDeclaration
		//	(',' SingleConstantDeclaration)* ';'
		ArrayList<Assignment> assignments =
				new ArrayList<Assignment>();
		expect(Tag.CONST);
		assignments.add(parseSingleConstantDeclaration());
		while (match(Tag.COMMA)) {
			assignments.add(parseSingleConstantDeclaration());
		}
		expectSemicolon();
		return assignments;
	}

	private Assignment parseSingleConstantDeclaration()
			throws LexicalError, SyntaxError {
		// SingleConstantDeclaration ::
		//	Identifier '=' Expression
		String name = expectIdentifier();
		expect(Tag.ASSIGN);
		ConstantSymbol symb = context_.current().defineConstant(name);
		Assignment assign = astNodeFactory_.newAssignment(
				Tag.INIT_CONST,
				astNodeFactory_.newReference(symb),
				parseExpression());
		return assign;
	}
	
	// Variable declarations

	private ArrayList<Assignment> parseVariableDeclaration()
			throws LexicalError, SyntaxError {
		// VariableDeclaration ::
		//	'let' SingleVariableDeclaration
		//	(',' SingleVariableDeclaration)* ';'
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		expect(Tag.LET);
		Assignment assignment = parseSingleVariableDeclaration();
		if (assignment != null)
			assignments.add(assignment);
		while (match(Tag.COMMA)) {
			assignment = parseSingleVariableDeclaration();
			if (assignment != null)
				assignments.add(assignment);
		}
		expectSemicolon();
		return assignments;
	}

	private Assignment parseSingleVariableDeclaration()
			throws LexicalError, SyntaxError {
		// SingleVariableDeclaration ::
		//	Identifier ('=' Expression)?
		String name = expectIdentifier();
		VariableSymbol symb = context_.current().defineVariable(name);
		Assignment assign = null;
		if (match(Tag.ASSIGN)) {
			assign = astNodeFactory_.newAssignment(
				Tag.INIT_CONST,
				astNodeFactory_.newReference(symb),
				parseExpression());
		}
		return assign;
	}

//	private void parseForEachVariableDeclaration(Scope loopScope)
//			throws LexicalError, SyntaxError {
//		// ForEachVariableDeclaration ::
//		//	'let' Identifier
//		expect(Tag.LET);
//		String id = expectIdentifier();
//		loopScope.defineVariable(id);
//	}

//	private void parseImportDeclaration() throws LexicalError, SyntaxError {
//		// ImportDeclaration ::
//		//	DirectlyImportDeclaration |
//		//	SelectiveImportDeclaration
//		// DirectlyImportDeclaration ::
//		//	'import' Identifier ';'
//		expect(Tag.IMPORT);
//
//	}

	private Function parseFunctionDeclaration()
			throws LexicalError, SyntaxError {
		// FunctionDeclaration ::
		//	'function' Identifier '(' Arguments ')' FunctionBody
		
		expect(Tag.FUNCTION);
		String name = expectIdentifier();
		FunctionSymbol symb = context_.current().defineFunction(name);
		
		context_.enterFunctionScope();
		
		expect(Tag.LPAREN);
		if (!match(Tag.RPAREN)) {
			do {
				String argName = expectIdentifier();
				context_.current().defineArgument(argName);
				if (match(Tag.RPAREN))
					break;
			} while (match(Tag.COMMA));
		}
		
		StatementBlock body = parseFunctionBody();
		
		// leave scope
		FunctionScope scope = context_.leaveFunctionScope();
		
		return astNodeFactory_.newFunction(symb, scope, body);
	}

	private StatementBlock parseFunctionBody()
			throws LexicalError, SyntaxError {
		// FunctionBody ::
		//	'{' (Statement | VariableDeclaration | ConstantDeclaration)* '}'
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
		ArrayList<Statement> stmts = new ArrayList<Statement>();
		expect(Tag.LBRACE);
		while (!match(Tag.RBRACE)) {
			stmts.add(parseStatement());
		}
		return astNodeFactory_.newStatementBlock(stmts);
	}
	
	private Statement parseStatement() throws LexicalError, SyntaxError {
		switch (peek.tag()) {
		case BREAK:
			return parseBreakStatement();
		case CONST:
			return astNodeFactory_.newExpressionStatement(
					astNodeFactory_.newExpressionGroup(
							parseConstantDeclaration()));
		case CONTINUE:
			return parseContinueStatement();
		case DO:
			return parseDoWhileStatement();
		case EOS:
			throw new SyntaxError(peek.position(), "unexpected end of source");
		case FOR:
			return parseForStatement();
		case FOREACH:
			throw new SyntaxError(peek.position(), 
					"unimplemented parsing routine: foreach");
		case IF:
			return parseIfStatement();
		case LBRACE:
			return parseNakeStatementBlock();
		case LET:
			return astNodeFactory_.newExpressionStatement(
					astNodeFactory_.newExpressionGroup(
							parseVariableDeclaration()));
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

	private Statement parseNakeStatementBlock()
			throws LexicalError, SyntaxError {
		context_.enterLocalScope();
		ArrayList<Statement> stmts = new ArrayList<Statement>();
		expect(Tag.LBRACE);
		while (!match(Tag.RBRACE)) {
			stmts.add(parseStatement());
		}
		context_.leaveLocalScope();
		return astNodeFactory_.newStatementBlock(stmts);
	}

	private BreakStatement parseBreakStatement()
			throws LexicalError, SyntaxError {
		// BreakStatement ::
		//	'break' ';'
		if (lowestBreakable == null)
			throw new SyntaxError(peek.position(),
					"break statement outside a breakable scope");
		expect(Tag.BREAK);
		expectSemicolon();
		return astNodeFactory_.newBreakStatement(lowestBreakable);
	}

	private ContinueStatement parseContinueStatement()
			throws LexicalError, SyntaxError {
		// ContinueStatement ::
		//	'continue' ';'
		if (lowestIteration == null)
			throw new SyntaxError(peek.position(),
					"continue statement outside any iteration");
		expect(Tag.CONTINUE);
		expectSemicolon();
		return astNodeFactory_.newContinueStatement(lowestIteration);
	}

	private DoWhileStatement parseDoWhileStatement()
			throws LexicalError, SyntaxError {
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
			throws LexicalError, SyntaxError {
		Expression expr = parseExpression();
		expectSemicolon();
		return astNodeFactory_.newExpressionStatement(expr);
	}

	private ForStatement parseForStatement() throws LexicalError, SyntaxError {
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
		context_.enterLocalScope();
		
		expect(Tag.FOR);
		expect(Tag.LPAREN);
		Expression initExpr = null;
		if (peek.tag() == Tag.LET) {
			initExpr = astNodeFactory_.newExpressionGroup(
					parseVariableDeclaration());
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
		context_.leaveLocalScope();
		
		loop.setup(initExpr, condExpr, incrExpr, loopBody);
		return loop;
	}

//	private ForEachStatement parseForEachStatement()
//			throws LexicalError, SyntaxError {
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

	private IfStatement parseIfStatement() throws LexicalError, SyntaxError {
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

	private ReturnStatement parseReturnStatement()
			throws LexicalError, SyntaxError {
		// ReturnStatement ::
		//	'return' Expression ';'
		expect(Tag.RETURN);
		Expression retExpr = null;
		if (!match(Tag.SEMICOLON)) {
			retExpr = parseExpression();
		}
		expectSemicolon();
		return astNodeFactory_.newReturnStatement(retExpr);
	}

	private Statement parseSwitchStatement() throws SyntaxError {
		throw new SyntaxError(peek.position(),
				"unimplemented parsing routine: switch");
	}
	
	private WhileStatement parseWhileStatement()
			throws LexicalError, SyntaxError {
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
		Statement body = parseStatement();
		
		// restore
		lowestIteration = saveIter;
		lowestBreakable = saveBreak;
		
		loop.setup(cond, body);
		return loop;
	}


	// Parse a variety of expressions.
	// We use top-down operator precedence method.
	// See further information here: 
	// http://javascript.crockford.com/tdop/tdop.html

	private Expression parseExpression() throws LexicalError, SyntaxError {
		return parseExpression(0);
	}

	private Expression parseExpression(int rbp)
			throws LexicalError, SyntaxError {
		Expression left = parseNullDenotation();
		while (rbp < peek.lbp()) {
			left = parseLeftDenotation(left);
		}
		return left;
	}

	private Expression parseNullDenotation() throws LexicalError, SyntaxError {
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
			throw new SyntaxError(peek.position(), String.format(
					"error token %s: undefined value", peek.literal()));
		}
	}

	private Expression parseParenthesisExpression()
			throws LexicalError, SyntaxError {
		expect(Tag.LPAREN);
		Expression save = parseExpression();
		expect(Tag.RPAREN);
		return save;
	}

	private ArrayLiteral parseArrayLiteral() throws LexicalError, SyntaxError {
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

	private UnaryOperation parseUnaryOperation()
			throws LexicalError, SyntaxError {
		Token op = next();
		Expression expr = parseExpression(op.lbp());
		return astNodeFactory_.newUnaryOperation(op.tag(), expr);
	}
	
	private SymbolReference parseReference() throws LexicalError, SyntaxError {
		// Reference :: Identifier
		String name = expectIdentifier();
		Symbol symb = context_.lookup(name);
		return symb == null 
				? astNodeFactory_.newUnsolvedReference(name) 
				: astNodeFactory_.newReference(symb);
	}

	private Expression parseLeftDenotation(Expression left)
			throws LexicalError, SyntaxError {
		switch (peek.tag()) {
		case CONDITIONAL:
			return parseConditional(left);
		case PERIOD:
			return parseProperty(left);
		case LPAREN:
			return parseInvoke(left);
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
			throw new SyntaxError(peek.position(), String.format(
					"error token %s, missing an operator", peek.literal()));
		}
	}

	private Expression parseInvoke(Expression left)
			throws LexicalError, SyntaxError {
		ExpressionGroup eg = null;
		expect(Tag.LPAREN);
		if (match(Tag.RPAREN)) {
			eg = new ExpressionGroup(new ArrayList<Expression>());
		} else {
			Expression first = parseExpression();
			if (match(Tag.COMMA)) {
				eg = parseExpressionGroup(first);
			} else {
				ArrayList<Expression> lst = new ArrayList<Expression>();
				lst.add(first);
				eg = astNodeFactory_.newExpressionGroup(lst);
			}
			expect(Tag.RPAREN);
		}
		return astNodeFactory_.newInvoke(left, eg);
	}

	private Conditional parseConditional(Expression cond)
			throws LexicalError, SyntaxError {
		expect(Tag.CONDITIONAL);
		Expression then = parseExpression();
		expect(Tag.COLON);
		Expression otherwise = parseExpression();
		return astNodeFactory_.newConditional(cond, then, otherwise);
	}

	private Property parseProperty(Expression left)
			throws LexicalError, SyntaxError {
		expect(Tag.PERIOD);
		String property = expectIdentifier();
		return astNodeFactory_.newProperty(left, property);
	}

	private Index parseIndex(Expression left)
			throws LexicalError, SyntaxError {
		expect(Tag.LBRACK);
		Expression index = parseExpression();
		expect(Tag.RBRACK);
		return astNodeFactory_.newIndex(left, index);
	}

	private UnaryOperation parsePostfixOperation(Expression left)
			throws LexicalError, SyntaxError {
		Token op = next();
		Tag postop = op.tag() == Tag.INC ? Tag.POSTFIX_INC : Tag.POSTFIX_DEC;
		return astNodeFactory_.newUnaryOperation(postop, left);
	}
	
	private ExpressionGroup parseExpressionGroup(Expression first)
			throws LexicalError, SyntaxError {
		ArrayList<Expression> group = new ArrayList<Expression>();
		Token comma = expect(Tag.COMMA);
		
		group.add(first);
		while (match(Tag.COMMA)) {
			group.add(parseExpression(comma.lbp()));
		}
		return astNodeFactory_.newExpressionGroup(group);
	}

	private CompareOperation parseCompareOperation(Expression left)
			throws LexicalError, SyntaxError {
		Token op = next();
		Expression right = parseExpression(op.lbp()); // left associative
		return astNodeFactory_.newCompareOperation(op.tag(), left, right);
	}

	private BinaryOperation parseBianryOperation(Expression left)
			 throws LexicalError, SyntaxError {
		Token op = next();
		Expression right = parseExpression(op.lbp()); // left associative
		return astNodeFactory_.newBinaryOperation(op.tag(), left, right);
	}

	private Assignment parseAssignment(Expression left)
			throws LexicalError, SyntaxError {
		Token op = next();
		Expression right = parseExpression(op.lbp() - 1); // right associative
		return astNodeFactory_.newAssignment(op.tag(), left, right);
	}

}