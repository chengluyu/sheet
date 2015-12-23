package ast;

import lexer.Tag;

public class NullLiteral extends ValueLiteral {

	public NullLiteral() {
		super(Tag.NULL_LITERAL, null);
	}
	
	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.text("null");
	}

}
