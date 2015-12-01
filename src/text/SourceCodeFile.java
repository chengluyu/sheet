package text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SourceCodeFile extends SourceCode {
	
	public SourceCodeFile(String filePath) {
		try {
			reader_ = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			eof_ = true;
		}
	}	
	
	private FileReader reader_;
	private boolean eof_ = false;
	
	@Override
	public char advance() {
		if (eof_)
			return (char) -1;
		
		int read = -1;
		
		try {
			read = reader_.read();
		} catch (IOException e) {
			eof_ = true;
		}
		
		return (char) read;
	}
	
	
	@Override
	public boolean eof() {
		return eof_;
	}
}
