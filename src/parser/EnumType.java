package parser;

import java.util.ArrayList;
import token.Identifier;

public class EnumType extends UserType {

	public EnumType(String typeName) {
		super(typeName);
		enumValues_ = new ArrayList<Identifier>();
	}
	
	public void addEnumValue(Identifier id) {
		enumValues_.add(id);
	}
	
	public void addEnumValue(ArrayList<Identifier> idList) {
		idList.addAll(idList);
	}
	
	private ArrayList<Identifier> enumValues_;

}
