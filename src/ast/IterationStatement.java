package ast;

public abstract class IterationStatement extends BreakableStatement {

	public IterationStatement() {
		
	}
	
	private int start_;
	
	public void setStartPosition(int start) {
		start_ = start;
	}
	
	public int startPosition() {
		return start_;
	}

}
