package ast;

public class AstNodePrinter {

	public AstNodePrinter(boolean useTab, int tabSize) {
		tabStr_ = useTab ? "\t" : AstNodePrinter.repeat(" ", tabSize);
		currentLevel_ = 0;
		indent_ = "";
		text_ = new StringBuilder();
	}
	
	private final String tabStr_;
	
	private int currentLevel_;
	private String indent_;
	
	private StringBuilder text_;
	
	private void indent() {
		currentLevel_++;
	}
	
	private void unindent() {
		currentLevel_--;
	}
	
	private void makeIndent() {
		indent_ = AstNodePrinter.repeat(tabStr_, currentLevel_);
	}
	
	private void beginLine() {
		text_.append(indent_);
	}
	
	private void endLine() {
		text_.append('\n');
	}
	
	private void print(char c) {
		text_.append(c);
	}
	
	private void print(String str) {
		text_.append(str);
	}
	
	public void begin() {
		indent();
		makeIndent();
		beginLine();
		print('{');
		endLine();
	}
	
	public void title(String name) {
		print(name);
		print(' ');
	}
	
	public void titleOnly(String name) {
		title(name);
		endLine();
	}
	
	public void property(String property, String value) {
		beginLine();
		print(property);
		print(" = ");
		print(value);
		endLine();
	}
	
	public void child(String property, AstNode child) {
		beginLine();
		print(property);
		print(" :: ");
		child.inspect(this);
		endLine();
	}
	
	public void end() {
		unindent();
		makeIndent();
		beginLine();
		print('}');
		endLine();
	}
	
	public String result() {
		return text_.toString();
	}
	
	private static String repeat(String str, int n) {
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) sb.append(str);
		return sb.toString();
	}

}
