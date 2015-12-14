
# Informal Backus-Naur Form of The Sheet Language

## Declarations

```
Declaration ::
	VariableDeclaration |
	ConstantDeclaration |
	FunctionDeclaration

VariableDeclaration ::
	'let' SingleVariableDeclaration (',' VariableDeclaration)* ';'

SingleVariableDeclaration ::
	Identifier ('=' Expression)?

ConstantDeclaration ::
	'const' SingleConstantDeclaration (',' SingleConstantDeclaration)* ';'

SingleConstantDeclaration ::
	Identifier '=' Expression

FunctionDeclaration ::
	'function' Identifier '(' ArgumentList ')' StatementBlock

ArugumentList ::
	Identifier (',' Identifier)*

ForEachVariableDeclaration ::
	'let' Identifier
```

## Statements

```
Statement ::
	BreakStatement |
	ContinueStatement |
	DoWhileStatement |
	ForStatement |
	ForEachStatement |
	IfStatement |
	ReturnStatement |
	StatementBlock |
	SwitchStatement |
	WhileStatement |

BreakStatement ::
	'break' ';'

ContinueStatement ::
	'continue' ';'

DoWhileStatement ::
	'do' Statement 'while' '(' Expression ')' ';'

ForStatement ::
	'for' '(' (Expression | VariableDeclaration)
	';' Expression ';' Expression ')' Statement

ForEachStatement ::
	'foreach' '(' ForEachVariableDeclaration 'in' Expression ')' Statement

IfStatement ::
	'if' '(' Expression ')' Statement ('else' Statement)?

ReturnStatement ::
	'return' Expression ';'

StatementBlock ::
	'{' (Statement | Declaration)* '}'

SwitchStatement ::
	TODO

WhileStatement ::
	'while' '(' Expression ')' Statement
```

# Expressions

```
Expression ::
	Assignment |
	BinaryOperation |
	CompareOperation |
	Conditional |
	Index |
	Invoke |
	NewInvoke |
	Property |
	This |
	UnaryOperation

Assignment ::
	Expression AssignmentOp Expression

BinaryOperation ::
	Expression BinaryOp Expression

CompareOperation ::
	Expression CompareOp Expression

Conditional ::
	Expression '?' Expression ':' Expression

Index ::
	Expression '[' Expression ']'

Invoke ::
	Expression '('  ')'

NewInvoke ::
	'new' Invoke

Property ::
	Expression '.' Identifier

UnaryOperation ::
	UnaryOp Expression
```