package runtime;

public class FieldInfo {

	public FieldInfo(int id, String name) {
		id_ = id;
		name_ = name;
	}
	
	private int id_;
	private String name_;
	
	public int id() {
		return id_;
	}
	
	public String name() {
		return name_;
	}
	
	public String inspect() {
		return String.format("%d: %s", id_, name_);
	}

}
