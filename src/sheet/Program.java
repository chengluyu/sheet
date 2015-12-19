package sheet;

import text.*;

import java.io.FileNotFoundException;

import lexer.*;
import utils.*;

public class Program {

	public static void main(String[] args) {
		if (args[0].equals("lex")) {
			lexTest(args[1]);
		} else if (args[0].equals("parse")) {
			parseTest(args[1]);
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
		
	}

}
