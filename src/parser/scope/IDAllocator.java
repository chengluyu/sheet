package parser.scope;

public class IDAllocator {

	public IDAllocator() {
		nextID_ = 0;
	}
	
	private int nextID_;
	
	public int allocate() {
		return nextID_++;
	}

}
