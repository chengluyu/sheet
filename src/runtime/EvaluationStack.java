package runtime;

import java.util.Stack;

public class EvaluationStack {

	public EvaluationStack() {
		stack_ = new Stack<StackFrame>();
		enter();
	}
	
	private Stack<StackFrame> stack_;
	private StackFrame current_;
	
	public void enter() {
		current_ = new StackFrame();
		stack_.push(current_);
	}
	
	public void leave() {
		stack_.pop();
		current_ = stack_.peek();
	}
	
	public void push(RuntimeObject obj) {
		current_.push(obj);
	}
	
	public RuntimeObject pop() {
		return current_.pop();
	}

}
