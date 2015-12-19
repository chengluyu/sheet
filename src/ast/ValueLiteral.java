package ast;

import lexer.Tag;

public class ValueLiteral extends Literal {

	public ValueLiteral(Tag type, Object data) {
		type_ = type;
		data_ = data;
	}
	
	private Tag type_;
	private Object data_;

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("value literal");
		printer.begin();
		printer.property("type", type_.literal());
		printer.property("literal", data_.toString());
		printer.end();
	}

}
