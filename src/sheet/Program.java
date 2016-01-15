package sheet;

import text.*;

import java.io.FileNotFoundException;

import ast.AstNodePrinter;
import ast.Module;
import compiler.Compiler;
import lexer.*;
import parser.Parser;
import runtime.ModuleInfo;
import utils.*;
import vm.VirtualMachine;

public class Program {

	public static void main(String[] args) {
		if (args[0].equals("lex")) {
			lexTest(args[1]);
		} else if (args[0].equals("parse")) {
			parseTest(args[1]);
		} else if (args[0].equals("compile")) {
			compileTest(args[1]);
		} else if (args[0].equals("run")) {
			compileTest(args[1]);
			runTest(args[1]);
		} else {
			System.out.println("Unknown command");
		}
	}
	
	public static void lexTest(String arg) {
		try {
			FileScanner fs = new FileScanner(arg);
			Lexer lex = new Lexer(fs);
			Token peek = lex.advance();
			while (!peek.isEndOfSource()) {
				System.out.println(peek.literal());;
				peek = lex.advance();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + arg);
		} catch (LexicalError e) {
			System.out.println("Lexical error: " + e.getMessage());
		}
		
	}
	
	public static void parseTest(String arg) {
		try {
			FileScanner fs = new FileScanner(arg);
			Lexer lex = new Lexer(fs);
			Parser parser = new Parser(lex);
			Module module = parser.parse();
			AstNodePrinter anp = new AstNodePrinter(false, 2);
			module.inspect(anp);
			System.out.print(anp.result());
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + arg);
		} catch (LexicalError e) {
			System.out.println("Lexical error: " + e.getMessage());
			e.printStackTrace();
		} catch (SyntaxError e) {
			System.out.println("Syntax error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void compileTest(String arg) {
		try {
			FileScanner fs = new FileScanner(arg);
			Lexer lex = new Lexer(fs);
			Parser parser = new Parser(lex);
			Module module = parser.parse();
			Compiler compiler = new Compiler(module);
			compiler.compile();
			System.out.print(compiler.result().inspect());
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + arg);
		} catch (LexicalError e) {
			System.out.println("Lexical error: " + e.getMessage());
			e.printStackTrace();
		} catch (SyntaxError e) {
			System.out.println("Syntax error: " + e.getMessage());
			e.printStackTrace();
		} catch (CompileError e) {
			System.out.println("Compile error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void runTest(String arg) {
		try {
			FileScanner fs = new FileScanner(arg);
			Lexer lex = new Lexer(fs);
			Parser parser = new Parser(lex);
			Module module = parser.parse();
			Compiler compiler = new Compiler(module);
			compiler.compile();
			ModuleInfo moduleInfo = compiler.result();
			VirtualMachine vm = new VirtualMachine();
			vm.load(moduleInfo);
			vm.run();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + arg);
		} catch (LexicalError e) {
			System.out.println("Lexical error: " + e.getMessage());
			e.printStackTrace();
		} catch (SyntaxError e) {
			System.out.println("Syntax error: " + e.getMessage());
			e.printStackTrace();
		} catch (CompileError e) {
			System.out.println("Compile error: " + e.getMessage());
			e.printStackTrace();
		} catch (RuntimeError e) {
			System.out.println("Runtime error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
