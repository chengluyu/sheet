package runtime;

import java.util.Iterator;

import compiler.CodeSegment;
import compiler.Instruction;

public class VirtualMachine {

	public VirtualMachine() {
		// TODO Auto-generated constructor stub
	}
	
	private ModuleInfo module_;
	private RuntimeObject[] globals_;
	private StackFrame frame_;
	
	public void load(ModuleInfo module) {
		module_ = module;
		stack_ = new EvaluationStack();
		globals_ = new RuntimeObject[module_.getGlobalFieldCount()];
		frame_ = new StackFrame();
	}
	
	public void run() {
		CodeSegment prelogue = module_.prelogue();
		execute(prelogue.iterator());
		
		FunctionInfo entry = module_.entryPoint();
		if (entry == null) {
			System.out.println("No entry point");
			// TODO raise error here
		} else {
			invoke(entry, null);
		}
	}
	
	private void invoke(FunctionInfo fn, RuntimeObject[] args) {
		// stage a new function environment in following steps:
		// 1. create a new stack frame
		// 2. 
		stack_.enter();
		
		stack_.leave();
	}
	
	private void execute(Iterator<Instruction> it) {
		while (it.hasNext()) {
			Instruction ins = it.next();
			switch (ins.opcode()) {
			case ADD:
				
				break;
			case AND:
				break;
			case BIT_AND:
				break;
			case BIT_OR:
				break;
			case BIT_XOR:
				break;
			case BR:
				break;
			case BREQ:
				break;
			case BRFALSE:
				break;
			case BRGT:
				break;
			case BRGTE:
				break;
			case BRLT:
				break;
			case BRLTE:
				break;
			case BRNE:
				break;
			case BRTRUE:
				break;
			case CALL:
				invoke(module_.getFunctionByIndex(ins.operand()));
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
				break;
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
