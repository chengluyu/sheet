package sheet;

import test.*;

public class Program {

	public static void main(String[] args) {
		System.out.println("Hello, sheet!");
		
		RawStringStreamTest t = new RawStringStreamTest();
		t.run();
		t.report();
	}

}
