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
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("statement block");
		printer.begin();
		printer.property("has scope bound",
				String.valueOf(bundledScope_ != null));
		printer.property("statement count", String.valueOf(stmts_.size()));
		for (int i = 0; i < stmts_.size(); i++)
			printer.child(String.valueOf(i), stmts_.get(i));
		printer.end();
	}

}
