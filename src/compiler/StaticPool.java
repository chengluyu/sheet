package compiler;

import java.util.ArrayList;

import parser.scope.IDAllocator;
import runtime.RuntimeObject;

public class StaticPool {

	public StaticPool() {
		idAllocator_ = new IDAllocator();
		objects_ = new ArrayList<RuntimeObject>();
	}
	
	private IDAllocator idAllocator_;
	private ArrayList<RuntimeObject> objects_;
	
	public int add(RuntimeObject obj) {
		objects_.add(obj);
		return idAllocator_.allocate();
	}
	
	public RuntimeObject get(int index) {
		return objects_.get(index);
	}

}
