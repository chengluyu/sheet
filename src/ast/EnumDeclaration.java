package ast;

import java.util.ArrayList;

public class EnumDeclaration extends Declaration {

	public EnumDeclaration(String enumName, ArrayList<String> values) {
		enumName_ = enumName;
		values_ = values;
	}
	
	private String enumName_;
	private ArrayList<String> values_;

}
