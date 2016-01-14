package ast;

import java.util.ArrayList;

import compiler.Blank;

public abstract class IterationStatement extends BreakableStatement {

	public IterationStatement() {
		blanks_ = new ArrayList<Blank>();
	}
	
	private ArrayList<Blank> blanks_;
	
	public void addContinue(Blank blank) {
		blanks_.add(blank);
	}
	
	public void fillContinue(int pos) {
		blanks_.forEach(x -> {
			x.fill(pos);
		});
		blanks_.clear();
	}

}
