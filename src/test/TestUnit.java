package test;

public abstract class TestUnit {

	public TestUnit(String testUnitName) {
		testUnitName_ = testUnitName;
	}

	private String testUnitName_;

	public String name() {
		return testUnitName_;
	}

	public abstract void input(Object obj);
	public abstract void run();
	public abstract TestResult result();

}