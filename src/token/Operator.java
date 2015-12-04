package token;

public class Operator extends Token {

	public Operator(Tag tag, String literal, int precedence) {
		super(tag, literal);
		
	}
	
	@Override
	public boolean isOperator() {
		return true;
	}
	
	private int precedence_;

}
