package ast.symbol;

import java.util.ArrayList;

import ast.AstNodePrinter;
import ast.StatementBlock;
import parser.FunctionEnv;
import runtime.FieldInfo;

public class FunctionSymbol extends Symbol {

	public FunctionSymbol(String name, FunctionEnv env,
			StatementBlock body, int id) {
		super(name, id);
		env_ = env;
		funcBody_ = body;
	}
	
	private FunctionEnv env_;
	private StatementBlock funcBody_;
	
	public StatementBlock body() {
		return funcBody_;
	}
	
	public FieldInfo[] arguments() {
		ArrayList<Symbol> argSymbs = env_.arguments();
		FieldInfo[] args = new FieldInfo[argSymbs.size()];
		for (int i = 0; i < args.length; i++) {
			Symbol symb = argSymbs.get(i);
			args[i] = new FieldInfo(symb.id(), symb.name());
		}
		return args;
	}
	
	public FieldInfo[] locals() {
		ArrayList<Symbol> localSymbs = env_.locals();
		FieldInfo[] locals = new FieldInfo[localSymbs.size()];
		for (int i = 0; i < locals.length; i++) {
			Symbol local = localSymbs.get(i);
			locals[i] = new FieldInfo(local.id(), local.name());
		}
		return locals;
	}
	
	@Override
	public boolean isFunction() {
		return true;
	}

	@Override
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
