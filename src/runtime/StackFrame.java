package runtime;

import java.util.Stack;

public class StackFrame {

	public StackFrame() {
		stack_ = new Stack<RuntimeObject>();
	}
	
	private Stack<RuntimeObject> stack_;
	
	public void push(RuntimeObject obj) {
		stack_.push(obj);
	}
	
	public RuntimeObject pop() {
		return stack_.pop();
	}
	
	public boolean empty() {
		return stack_.isEmpty();
	}

}
