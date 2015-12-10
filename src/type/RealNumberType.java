package type;

public class RealNumberType extends PrimitiveType {

	protected RealNumberType(String name) {
		super(name);
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		return thatType instanceof RealNumberType ||
				thatType instanceof IntegralType;
	}
	
	@Override
	public boolean isRealDomain() {
		return true;
	}

}
