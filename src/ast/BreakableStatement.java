package ast;

import java.util.ArrayList;

import compiler.Blank;

public abstract class BreakableStatement extends Statement {

	public BreakableStatement() {
		blanks_ = new ArrayList<Blank>();
	}
	
	private ArrayList<Blank> blanks_;
	
	public void addBreak(Blank blank) {
		blanks_.add(blank);
	}
	
	public void fillBreak(int pos) {
		blanks_.forEach(x -> {
			x.fill(pos);
		});
		blanks_.clear();
	}

}
