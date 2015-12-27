package ast.symbol;

import ast.AstNodePrinter;
import ast.StatementBlock;
import parser.FunctionEnv;

public class FunctionSymbol extends Symbol {

	public FunctionSymbol(String name, FunctionEnv env,
			StatementBlock body, int id) {
		super(name, id);
		env_ = env;
		funcBody_ = body;
	}
	
	private FunctionEnv env_;
	private StatementBlock funcBody_;
	
	@Override
	public boolean isFunction() {
		return true;
	}

	public StatementBlock body() {
		return funcBody_;
	}
	
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("function");
		printer.subBlock("arguments");
		env_.arguments().forEach(x -> {
			printer.property(String.format("[%d]", x.id()), x.name());
		});
		printer.endBlock();
		printer.subBlock("locals");
		env_.locals().forEach(x -> {
			printer.property(String.format("[%d]", x.id()), x.name());
		});
		printer.endBlock();
		printer.child("statements", funcBody_);
		printer.endBlock();
	}

}
