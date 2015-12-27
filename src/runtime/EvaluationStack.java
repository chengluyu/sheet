package runtime;

import java.util.Stack;

public class EvaluationStack {

	public EvaluationStack() {
		stack_ = new Stack<RuntimeObject>();
	}
	
	private Stack<RuntimeObject> stack_;
	
	public void push(RuntimeObject obj) {
		stack_.push(obj);
		
	}

}
