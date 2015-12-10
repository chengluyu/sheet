package type;

import java.util.ArrayList;
import java.util.HashSet;

public class EnumType extends Type {

	public EnumType(String enumName, ArrayList<String> enumValues) {
		enumName_ = enumName;
		enumValues_ = enumValues;
		valueSet_ = new HashSet<String>();
		
		for (String str : enumValues) {
			valueSet_.add(str);
		}
	}
	
	public ArrayList<String> getValues() {
		return enumValues_;
	}
	
	public boolean containsValue(String value) {
		return valueSet_.contains(value);
	}
	
	private final String enumName_;
	private final ArrayList<String> enumValues_;
	private final HashSet<String> valueSet_;
	
	@Override
	public String getName() {
		return enumName_;
	}
	
	@Override
	public boolean equals(Object o) {
		return o == this;
	}

	@Override
	public boolean canBeConvert(Type thatType) {
		return this == thatType;
	}

}
