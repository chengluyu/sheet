package ast;

import java.util.ArrayList;
import java.util.Iterator;

import compiler.ByteCodeCompiler;
import compiler.OpCode;
import utils.CompileError;

public class ExpressionGroup extends Expression {

	public ExpressionGroup(ArrayList<? extends Expression> exprs) {
		exprs_ = exprs;
	}
	
	private ArrayList<? extends Expression> exprs_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("expression group");
		printer.property("length", String.valueOf(exprs_.size()));
		for (int i = 0; i < exprs_.size(); i++)
			printer.child(String.valueOf(i), exprs_.get(i));
		printer.endBlock();
	}
	
	public ArrayList<? extends Expression> expressions() {
		return exprs_;
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		Iterator<? extends Expression> it = exprs_.iterator();
		while (it.hasNext()) {
			it.next().compile(compiler);
			if (it.hasNext())
				compiler.emit(OpCode.POP);
		}
	}
	
}
