package runtime;

import java.util.ArrayList;
import java.util.Iterator;

public class RuntimeArray extends RuntimeObject {

	public RuntimeArray() {
		elems_ = null;
	}
	
	public RuntimeArray(ArrayList<RuntimeObject> elems) {
		elems_ = new ArrayList<RuntimeObject>();
		elems_.addAll(elems);
	}
	
	private ArrayList<RuntimeObject> elems_;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Iterator<? extends RuntimeObject> it = elems_.iterator();
		if (it.hasNext()) {
			sb.append(it.next().toString());
			while (it.hasNext()) {
				sb.append(", ");
				sb.append(it.next().toString());
			}
		}
		sb.append(']');
		return sb.toString();
	}
	
	@Override
	public boolean isArray() {
		return true;
	}

}
