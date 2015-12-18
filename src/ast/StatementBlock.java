package ast;

import java.util.ArrayList;

import parser.Scope;

public class StatementBlock extends Statement {

	public StatementBlock(ArrayList<Statement> stmts, Scope bundledScope) {
		stmts_ = stmts;
		bundledScope_ = bundledScope;
	}
	
	private ArrayList<Statement> stmts_;
	private Scope bundledScope_;

}
