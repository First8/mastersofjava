import nl.moj.model.Tester;

/**
 * Generic tester class.
 * 
 * @author Koen Aben
 * 
 */
public abstract class MapTraversalTester implements Tester.Testable {

	/**
	 * Getter for the actual test cases to be performed.
	 * 
	 * @return Array of test cases.
	 */
	protected abstract TestCase[] getTestCases();

	public int getTestCount() {
		return getTestCases().length;
	}

	public String getTestName(int nr) {
		String testName = "testName " + nr;
		if (nr< getTestCases().length) {
			testName = getTestCases()[nr].getName() ; 
		}
		return testName;
	}

	public String getTestDescription(int nr) {
		return getTestName(nr);
	}

	public boolean performTest(int nr) throws Throwable {
		return this.getTestCases()[nr].performTest();
	}

}
