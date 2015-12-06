package token;

public class CharValue extends Value {

	public CharValue(char ch) {
		super(Tag.CHAR_LITERAL, Character.toString(ch));
		ch_ = ch;
	}
	
	private char ch_;

	@Override
	public Object getNativeObject() {
		return new Character(ch_);
	}
	
	@Override
	public String toString() {
		return String.format("character [value='%c']", ch_);
	}

}
