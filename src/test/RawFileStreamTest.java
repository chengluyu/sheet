package test;

import text.RawFileStream;

public class RawFileStreamTest extends TestUnit {

	public RawFileStreamTest() {
		super("RawFileStream");
	}

	@Override
	public void run() {
		RawFileStream ss = new RawFileStream("path here");
		char ch;
		do {
			ch = ss.advance();
			System.out.print(ch);
		} while (ch != RawFileStream.EOF);
	}

}
