package type;

public class StringType extends PrimitiveType {

	public StringType(String name) {
		super(name);
	}
	
	@Override
	public boolean isString() {
		return true;
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		return thatType instanceof StringType;
	}

}
