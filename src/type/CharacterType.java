package type;

public class CharacterType extends PrimitiveType {

	protected CharacterType(String name) {
		super(name);
	}
	
	@Override
	public boolean isCharacter() {
		return true;
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		return thatType instanceof CharacterType ||
				thatType instanceof IntegralType;
	}

}
