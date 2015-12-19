package utils;

public class Position {

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public final int row;
	public final int column;
	
	public String toString() {
		return String.format("at line %d, column %d", row, column);
	}

}