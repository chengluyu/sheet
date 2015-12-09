package ast;

public class TypeAliasDeclaration extends Declaration {

	public TypeAliasDeclaration(String newType, TypeSpecifier origin) {
		newType_ = newType;
		originType_ = origin;
	}
	
	private String newType_;
	private TypeSpecifier originType_;

}
