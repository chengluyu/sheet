package ast;

import java.util.ArrayList;

public class StatementBlock extends Statement {

	public StatementBlock(ArrayList<Statement> stmts) {
		stmts_ = stmts;
	}
	
	private ArrayList<Statement> stmts_;

}
