package runtime;

import java.util.Iterator;

import compiler.ByteCode;
import compiler.Instruction;

public class VirtualMachine {

	public VirtualMachine() {
		module_ = null;
		globals_ = null;
		frame_ = null;
	}
	
	private ModuleInfo module_;
	private RuntimeObject[] globals_;
	private StackFrame frame_;
	
	public void load(ModuleInfo module) {
		module_ = module;
		globals_ = new RuntimeObject[module_.getGlobalFieldCount()];
		frame_ = new StackFrame();
	}
	
	public void run() {
		ByteCode prelogue = module_.prologue();
		execute(prelogue.iterator());
		
		FunctionInfo entry = module_.entryPoint();
		if (entry == null) {
			System.out.println("No entry point");
			// TODO raise error here
		} else {
			invoke(entry, null);
		}
	}
	
	/**
	 * Create and enter a new stack frame.
	 */
	private void enter(int localCount, RuntimeObject[] args) {
		frame_ = new StackFrame(frame_, localCount, args);
	}
	
	/**
	 * Leave current stack frame.
	 */
	private void leave() {
		frame_ = frame_.previous();
	}
	
	private void invoke(FunctionInfo fn, RuntimeObject[] args) {
		enter(fn.localCount(), args);
		execute(fn.iterator());
		RuntimeObject ret = frame_.empty() ? new RuntimeNull() : frame_.pop();
		leave();
		frame_.push(ret);
	}
	
	/**
	 * Collects arguments from evaluation stack.
	 * @param fn The function will be applied.
	 * @return Arguments.
	 */
	private RuntimeObject[] collectArguments(FunctionInfo fn) {
		int count = fn.argumentCount();
		RuntimeObject[] args = new RuntimeObject[count];
		for (int i = count - 1; i >= 0; i--) {
			args[i] = frame_.pop();
		}
		return args;
	}
	
	private void execute(Iterator<Instruction> it) {
		StackFrame stack = frame_;
		RuntimeObject[] locals = frame_.locals();
		RuntimeObject[] args = frame_.arguments();
		while (it.hasNext()) {
			Instruction ins = it.next();
			switch (ins.opcode()) {
			case ADD:
				break;
			case AND:
				break;
			case BR:
				break;
			case BRFALSE:
				break;
			case BRTRUE:
				break;
			case CALL: {
				FunctionInfo fn = module_.getFunctionByIndex(ins.operand());
				RuntimeObject[] newArgs = collectArguments(fn);
				invoke(fn, newArgs);
				}
				break;
			case DIV:
				break;
			case EQ:
				break;
			case GT:
				break;
			case GTE:
				break;
			case LDARG:
				break;
			case LDGLOB:
				break;
			case LDLOC:
				break;
			case LDSTATIC:
				break;
			case LT:
				break;
			case LTE:
				break;
			case MOD:
				break;
			case MUL:
				break;
			case NE:
				break;
			case NOP:
				break;
			case OR:
				break;
			case POP:
				break;
			case RET:
				return;
			case SAR:
				break;
			case SHL:
				break;
			case SHR:
				break;
			case STARG:
				break;
			case STGLOB:
				break;
			case STLOC:
				break;
			case SUB:
				break;
			default:
				break;
			
			}
		}
	}

}
