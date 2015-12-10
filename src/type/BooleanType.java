package type;

public class BooleanType extends PrimitiveType {

	protected BooleanType(String name) {
		super(name);
	}
	
	@Override
	public boolean isBoolean() {
		return true;
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		return thatType instanceof IntegralType ||
				thatType instanceof BooleanType;
	}

}
