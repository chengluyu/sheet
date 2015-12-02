package text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RawFileStream extends RawStream {
	
	public RawFileStream(String filePath) {
		try {
			reader_ = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			error_ = true;
		}
	}	
	
	private FileReader reader_;
	private boolean error_ = false;
	
	@Override
	public char advance() {
		if (error_)
			return RawStream.EOF;
		
		int read = RawStream.EOF;
		
		try {
			read = reader_.read();
		} catch (IOException e) {
			error_ = true;
		}
		
		return (char) read;
	}
}
