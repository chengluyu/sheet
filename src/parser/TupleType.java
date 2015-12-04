package parser;

import java.util.ArrayList;
import java.util.Iterator;

public class TupleType extends Type {

	public TupleType() {
		types_ = new ArrayList<Type>();
	}
	
	public TupleType(ArrayList<Type> types) {
		types_ = types;
	}
	
	private ArrayList<Type> types_;

	@Override
	public String getTypeName() {
		Iterator<Type> iter = types_.iterator();
		StringBuilder sb = new StringBuilder();
		
		sb.append('(');
		if (iter.hasNext()) {
			sb.append(iter.next().getTypeName());
			while (iter.hasNext()) {
				sb.append(", ");
				sb.append(iter.next().getTypeName());
			}
		}
		sb.append(')');
		return sb.toString();
	}
	
	@Override
	public boolean isTuple() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TupleType)) return false;
		
		TupleType rhs = (TupleType) o;
		return types_.equals(rhs.types_);
	}

}
