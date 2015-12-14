package ast;

import parser.Environment;

public class AstNodeFactory {

	public AstNodeFactory(Environment env) {
		env_ = env;
	}

	private Environment env_;

}