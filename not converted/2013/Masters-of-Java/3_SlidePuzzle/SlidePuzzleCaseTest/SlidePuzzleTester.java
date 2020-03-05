import nl.moj.model.Tester;

/**
 * Tester class.
 * 
 * @author Ron Bierman
 */
abstract class SlidePuzzleTester implements Tester.Testable {
	/**
	 * Getter for the actual test cases to be performed.
	 * 
	 * @return Array of test cases.
	 */
	protected abstract TestCase[] getTestCases();

	/** {@inheritDoc} */
	@Override
	public final int getTestCount() {
		return this.getTestCases().length;
	}

	/** {@inheritDoc} */
	@Override
	public final String getTestName(int nr) {
		return "At least: " + (nr + 1) + " move(s)";
	}

	/** {@inheritDoc} */
	@Override
	public final String getTestDescription(int nr) {
		StringBuffer sb = new StringBuffer();
		sb.append("Puzzle matrix: \r\n");
		sb.append(this.getTestCases()[nr].toString());
		return sb.toString();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performTest(int nr) throws Throwable {
		return this.getTestCases()[nr].performTest();
	}
}
