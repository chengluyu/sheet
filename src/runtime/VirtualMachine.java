package runtime;

import java.util.Iterator;

import compiler.Instruction;

public class VirtualMachine {

	public VirtualMachine() {
		// TODO Auto-generated constructor stub
	}
	
	private ModuleInfo module_;
	private EvaluationStack stack_;
	
	public void load(ModuleInfo module) {
		module_ = module;
	}
	
	public void run() {
		FunctionInfo entry = module_.entryPoint();
		
		if (entry == null) {
			return 
		}
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
