package type;

public class AliasType extends Type {

	public AliasType(String newName, Type origin) {
		newName_ = newName;
		origin_ = origin;
	}
	
	private String newName_;
	private Type origin_;

	@Override
	public String getName() {
		return newName_;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof AliasType) {
			AliasType ty = (AliasType)o;
			return ty.origin_.equals(origin_);
		} else if (o instanceof Type) {
			Type ty = (Type) o;
			return ty.equals(origin_);
		} else {
			return false;
		}
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		if (this == thatType)
			return true;
		return origin_.canBeConvert(thatType);
	}

}
