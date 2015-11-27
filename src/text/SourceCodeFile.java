package text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SourceCodeFile extends SourceCode {
	
	public SourceCodeFile(String path) throws FileNotFoundException {
		srcFile_ = new File(path);
		reader_ = new FileReader(srcFile_);
	}	
	
	private File srcFile_;
	private FileReader reader_;
	private boolean eof_ = false;
	
	@Override
	public char advance() {
		return 0;
	}
	
	
	@Override
	public boolean eof() {
		return eof_;
	}
}
