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
	
	private void increaseIndent() {
		currentLevel_++;
		indent_ = AstNodePrinter.repeat(tabStr_, currentLevel_);
	}
	
	private void decreaseIndent() {
		currentLevel_--;
		indent_ = AstNodePrinter.repeat(tabStr_, currentLevel_);
	}
	
	private void printIndent() {
		text_.append(indent_);
	}
	
	private void nextLine() {
		text_.append('\n');
	}
	
	private void print(char c) {
		text_.append(c);
	}
	
	private void print(String str) {
		text_.append(str);
	}
	
	public void subBlock(String title) {
		printIndent();
		increaseIndent();
		print(title);
		print(' ');
		print('{');
		nextLine();
	}
	
	public void beginBlock(String title) {
		increaseIndent();
		print(title);
		print(' ');
		print('{');
		nextLine();
	}
	
	public void beginBlock() {
		increaseIndent();
		print('{');
		nextLine();
	}
	
	public void text(String name) {
		print(name);
		print(' ');
		nextLine();
	}
	
	public void property(String property, String value) {
		printIndent();
		print(property);
		print(" := ");
		print(value);
		nextLine();
	}
	
	public void child(String property, AstNode child) {
		printIndent();
		print(property);
		print(" := ");
		child.inspect(this);
	}
	
	public void endBlock() {
		decreaseIndent();
		printIndent();
		print('}');
		nextLine();
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
