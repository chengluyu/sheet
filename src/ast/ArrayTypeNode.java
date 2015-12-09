package ast;

public class ArrayTypeNode extends TypeSpecifier {

	public ArrayTypeNode(TypeSpecifier elemType) {
		elemType_ = elemType;
	}
	
	private TypeSpecifier elemType_;

}
