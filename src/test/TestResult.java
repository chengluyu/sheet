package test;

public class TestResult {

	public TestResult(TestStatus status, String info) {
		status_ = status;
		info_ = info;
	}

	public TestStatus status() {
		return status_;
	}

	public String info() {
		return info_;
	}

	private final TestStatus status_;
	private final String info_;

}