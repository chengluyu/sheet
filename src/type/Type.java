package type;

public abstract class Type extends Object {

	public abstract String getName();
	public abstract boolean canBeConvert(Type thatType);
	
}
