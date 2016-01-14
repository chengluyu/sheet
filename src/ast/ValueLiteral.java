package ast;

import compiler.ByteCodeCompiler;
import lexer.Tag;
import runtime.*;
import utils.CompileError;

public class ValueLiteral extends Literal {

	public ValueLiteral(Tag type, Object data) {
		type_ = type;
		data_ = data;
	}
	
	private Tag type_;
	private Object data_;

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("value literal");
		printer.property("type", type_.literal());
		printer.property("literal", data_.toString());
		printer.endBlock();
	}
	
	public RuntimeObject toRuntimeObject() throws CompileError {
		RuntimeObject obj;
		switch (type_) {
		case CHAR_LITERAL:
			obj = new RuntimeCharacter((char) data_);
			break;
		case STRING_LITERAL:
			obj = new RuntimeString((String) data_);
			break;
		case INTEGER:
			obj = new RuntimeInteger((int) data_);
			break;
		case NUMBER:
			obj = new RuntimeNumber((double) data_);
			break;
		case NULL_LITERAL:
			obj = new RuntimeNull();
			break;
		case TRUE_LITERAL:
			obj = new RuntimeBoolean(true);
			break;
		case FALSE_LITERAL:
			obj = new RuntimeBoolean(false);
			break;
		default:
			throw new CompileError("wrong literal tag type");
		}
		return obj;
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		RuntimeObject obj = toRuntimeObject();
		int id = compiler.addStatic(obj);
		compiler.loadStatic(id);
	}

}
