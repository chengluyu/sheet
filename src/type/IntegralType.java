package type;

public class IntegralType extends PrimitiveType {

	protected IntegralType(String name) {
		super(name);
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		return thatType instanceof RealNumberType ||
				thatType instanceof IntegralType;
	}
	
	@Override
	public boolean isIntegral() {
		return true;
	}


}
