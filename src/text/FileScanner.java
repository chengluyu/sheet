package text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileScanner extends Scanner {

	public FileScanner(String srcFile) throws FileNotFoundException {
		metError_ = false;
		fileReader_ = new FileReader(srcFile);
	}

	private final FileReader fileReader_;
	private boolean metError_;

	@Override
	public char advance() {
		if (metError_) return Scanner.EOF;
		char ch = Scanner.EOF;
		try {
			ch = (char) fileReader_.read();
		} catch (IOException e) {
			metError_ = true;
		}
		return ch;
	}

}