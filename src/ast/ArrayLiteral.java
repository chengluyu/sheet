package ast;

import java.util.ArrayList;
import java.util.Iterator;

import compiler.ByteCodeCompiler;
import runtime.RuntimeArray;
import runtime.RuntimeObject;
import utils.CompileError;

public class ArrayLiteral extends Literal {

	public ArrayLiteral(ArrayList<Expression> elems) {
		elems_ = elems;
	}
	
	private ArrayList<Expression> elems_;
	
	public ArrayList<Expression> elements() {
		return elems_;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("array literal");
		printer.property("element count", String.valueOf(elems_.size()));
		for (int i = 0; i < elems_.size(); i++)
			printer.child(String.valueOf(i), elems_.get(i));
		printer.endBlock();
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		ArrayList<RuntimeObject> elems = new ArrayList<RuntimeObject>();
		Iterator<Expression> it = elems_.iterator();
		while (it.hasNext()) {
			Expression expr = it.next();
			if (expr instanceof ValueLiteral) {
				elems.add(((ValueLiteral) expr).toRuntimeObject());
			} else {
				throw new CompileError("only constant array is supported");
			}
		}
		RuntimeArray array = new RuntimeArray(elems);
		int id = compiler.addStatic(array);
		compiler.loadStatic(id);
	}

}
