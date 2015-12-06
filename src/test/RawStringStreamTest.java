package test;

import text.RawStringStream;

public class RawStringStreamTest extends TestUnit {

	public RawStringStreamTest() {
		super("RawStringStream");
	}

	@Override
	public void run() {
		String text = "abcdefghijklmn";
		RawStringStream ss = new RawStringStream(text);
		
		for (int i = 0; i < text.length(); i++) { 
			if (ss.advance() != text.charAt(i)) {
				log("Stage 1 - " + i);
				fail();
				return;
			}
		}
		
		for (int i = 0; i < 3; i++) { // three times check
			if (ss.advance() != RawStringStream.EOF) {
				log("Stage 2");
				fail();
				return;
			}
		}
		success();
	}

}
