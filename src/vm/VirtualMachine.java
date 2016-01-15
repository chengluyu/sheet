package vm;

import compiler.ByteCode;
import compiler.Instruction;
import compiler.StaticPool;
import runtime.FunctionInfo;
import runtime.ModuleInfo;
import runtime.RuntimeArray;
import runtime.RuntimeBoolean;
import runtime.RuntimeNull;
import runtime.RuntimeObject;
import runtime.RuntimeObjectType;
import utils.RuntimeError;

public class VirtualMachine {

	public VirtualMachine() {
		module_ = null;
		globals_ = null;
		frame_ = null;
	}
	
	private ModuleInfo module_;
	private RuntimeObject[] globals_;
	private StaticPool staticPool_;
	private StackFrame frame_;
	
	public void load(ModuleInfo module) {
		module_ = module;
		globals_ = new RuntimeObject[module_.getGlobalFieldCount()];
		frame_ = new StackFrame();
		staticPool_ = module.staticPool();
	}
	
	public void run() throws RuntimeError {
		ByteCode prelogue = module_.prologue();
		execute(prelogue);
		
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
	
	private void invoke(FunctionInfo fn, RuntimeObject[] args)
			throws RuntimeError {
		enter(fn.localCount(), args);
		RuntimeObject ret = execute(fn.byteCode());
		assert ret == null;
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
	
	private RuntimeObject execute(ByteCode byteCode) throws RuntimeError {
		int cursor = 0;
		StackFrame stack = frame_;
		RuntimeObject returnObj = null;
		RuntimeObject lhs, rhs;
		RuntimeObject value, index;
		RuntimeArray array;
		ExecuteLoop: while (cursor < byteCode.instructionCount()) {
			Instruction ins = byteCode.fetch(cursor);
			switch (ins.opcode()) {
			case ADD:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.add(rhs));
				break;
			case AND:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.and(rhs));
				break;
			case BR:
				cursor = ins.operand();
				break;
			case BRFALSE:
				if (stack.pop().isTruly())
					cursor = ins.operand();
				break;
			case BRTRUE:
				if (stack.pop().isFalsy())
					cursor = ins.operand();
				break;
			case CALL: {
				FunctionInfo fn = module_.getFunctionByIndex(ins.operand());
				RuntimeObject[] newArgs = collectArguments(fn);
				invoke(fn, newArgs);
				}
				break;
			case DIV:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.divide(rhs));
				break;
			case EQ:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(new RuntimeBoolean(lhs.equals(rhs)));
				break;
			case GT:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(new RuntimeBoolean(lhs.greatThan(rhs)));
				break;
			case GTE:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(new RuntimeBoolean(lhs.greatThanOrEqual(rhs)));
				break;
			case LDARG:
				stack.push(stack.arguments()[ins.operand()]);
				break;
			case LDGLOB:
				stack.push(globals_[ins.operand()]);
				break;
			case LDLOC:
				stack.push(stack.locals()[ins.operand()]);
				break;
			case LDSTATIC:
				stack.push(staticPool_.get(ins.operand()));
				break;
			case LDELEM:
				break;
			case LT:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(new RuntimeBoolean(lhs.lessThan(rhs)));
				break;
			case LTE:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(new RuntimeBoolean(lhs.lessThanOrEqual(rhs)));
				break;
			case MOD:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.modulus(rhs));
				break;
			case MUL:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.multiply(rhs));
				break;
			case NE:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(new RuntimeBoolean(!lhs.equals(rhs)));
				break;
			case NOP:
				break;
			case OR:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.or(rhs));
				break;
			case POP:
				stack.pop();
				break;
			case RET:
				returnObj = stack.pop();
				if (returnObj == null)
					returnObj = new RuntimeNull();
				break ExecuteLoop;
			case RETNULL:
				returnObj = new RuntimeNull();
				break ExecuteLoop;
			case SAR:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.sar(rhs));
				break;
			case SHL:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.shl(rhs));
				break;
			case SHR:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.shr(rhs));
				break;
			case STARG:
				stack.arguments()[ins.operand()] = stack.pop();
				break;
			case STGLOB:
				globals_[ins.operand()] = stack.pop();
				break;
			case STLOC:
				stack.locals()[ins.operand()] = stack.pop();
				break;
			case STELEM:
				index = stack.pop();
				value = stack.pop();
				rhs = stack.pop();
				index.requireType(RuntimeObjectType.INTEGER);
				value.requireType(RuntimeObjectType.ARRAY);
				break;
			case SUB:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.subtract(rhs));
				break;
			case BIT_NOT:
				rhs = stack.pop();
				stack.push(rhs.not());
				break;
			case COPY:
				stack.push(stack.top().copy());
				break;
			case DEC:
				rhs = stack.pop();
				stack.push(rhs.decrease());
				break;
			case INC:
				rhs = stack.pop();
				stack.push(rhs.increase());
				break;
			case LDNULL:
				stack.push(new RuntimeNull());
				break;
			case NEG:
				rhs = stack.pop();
				stack.push(rhs.negative());
				break;
			case NOT:
				rhs = stack.pop();
				stack.push(rhs.not());
				break;
			case XOR:
				rhs = stack.pop();
				lhs = stack.pop();
				stack.push(lhs.xor(rhs));
				break;
			default:
				break;
			}
		}
		return returnObj;
	}

}
