package ast;

import java.util.ArrayList;

import parser.LocalScope;

public class StatementBlock extends Statement {

	public StatementBlock(ArrayList<Statement> stmts, LocalScope bundledScope) {
		stmts_ = stmts;
		bundledScope_ = bundledScope;
	}
	
	private ArrayList<Statement> stmts_;
	private LocalScope bundledScope_;

}
