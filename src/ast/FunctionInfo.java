package ast;

import java.util.ArrayList;

import parser.LocalScope;

public class FunctionInfo {

	public FunctionInfo() {
		
	}
	
	private String name_;
	private ArrayList<String> args_;
	private StatementBlock body_;
	private LocalScope scope_;
	private LocalScope parentScope_;

}
