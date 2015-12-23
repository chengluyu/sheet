package ast;

public class UnsolvedReference extends Reference {

	public UnsolvedReference(String name) {
		refName_ = name;
	}
	
	private String refName_;

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("unsolved reference");
		printer.property("name", refName_);
		printer.endBlock();
	}

}
