package test;

import java.util.ArrayList;

public abstract class TestUnit {
	
	public TestUnit(String testName) {
		testName_ = testName;
		logger_ = new StringBuilder();
	}

	public abstract void run();
	
	public void report() {
		System.out.print(String.format("Running test on %s:\n", testName_));
		System.out.print(logger_.toString());
	}
	
	protected void fail() {
		log("Failed.");
	}
	
	protected void success() {
		log("Success.");
	}
	
	protected void log(String message) {
		logger_.append(message);
		logger_.append('\n');
	}
	
	private final String testName_;
	private StringBuilder logger_;

}
