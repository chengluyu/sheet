package compiler;

import java.util.ArrayList;

import ast.Function;
import ast.Module;
import parser.symbol.Symbol;
import runtime.FieldInfo;
import runtime.FunctionInfo;
import runtime.ModuleInfo;
import runtime.RuntimeObject;
import utils.CompileError;

public class Compiler {

	public Compiler(Module module) {
		module_ = module;
		success_ = false;
		
		staticPool_ = new StaticPool();
		globals_ = null;
		prologue_ = null;
		functions_ = null;
		entryPoint_ = null;
	}
	
	private Module module_;
	private boolean success_;
	
	private StaticPool staticPool_;
	private FieldInfo[] globals_;
	private ByteCode prologue_;
	private FunctionInfo[] functions_;
	private FunctionInfo entryPoint_;
	
	public Module module() {
		return module_;
	}
	
	public void compile() throws CompileError {
		compileGlobals();
		compilePrologue();
		compileFunctions();
		success_ = true;
	}
	
	private void compileGlobals() {
		ArrayList<Symbol> globals = module_.globals();
		globals_ = new FieldInfo[globals.size()];
		for (int i = 0; i < globals.size(); i++) {
			Symbol symbol = globals.get(i);
			globals_[i] = new FieldInfo(symbol.id(), symbol.name());
		}
	}
	
	private void compilePrologue() throws CompileError {
		ByteCodeCompiler compiler = getByteCodeCompiler();
		module_.initialzations().compile(compiler);
		prologue_ = compiler.getByteCode();
	}
	
	private void compileFunctions() throws CompileError {
		ArrayList<Function> functions = module_.functions();
		functions_ = new FunctionInfo[functions.size()];
		for (int i = 0; i < functions.size(); i++) {
			Function func = functions.get(i);
			functions_[i] = func.compile(this);
			
			// check if it is entry function
			if (func.name().equals("main"))
				entryPoint_ = functions_[i];
		}
	}
	
	public ModuleInfo result() {
		if (success_)
			return new ModuleInfo(
					staticPool_,
					globals_,
					prologue_,
					functions_,
					entryPoint_);
		return null;
	}
	
	public ByteCodeCompiler getByteCodeCompiler() {
		return new ByteCodeCompiler(this);
	}
	
	public int addStatic(RuntimeObject object) {
		return staticPool_.add(object);
	}
	
	

}
