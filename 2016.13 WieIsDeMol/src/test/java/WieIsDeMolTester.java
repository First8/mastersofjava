import nl.moj.model.Tester;

public class WieIsDeMolTester implements Tester.Testable {
	
	private static ITestCase[] DEFINED_TESTCASES = new ITestCase[] {
		new WrongParcelDeliveryTestCase(),
		new SmallTestCase(),
		new TestCase1(),
		new TestCase2(),
		new TestCase3(),
		//new NetherlandsTestCase(),
	};
	
	public WieIsDeMolTester() {
	}
	public WieIsDeMolTester(ITestCase[] input) {
		DEFINED_TESTCASES  = input; 
	}
	public int getTestCount() {
		return DEFINED_TESTCASES.length;
	}

	public String getTestName(int nr) {
		return DEFINED_TESTCASES[nr].getTestCaseName();
	}
	
	public String getTestDescription(int nr) {
		return DEFINED_TESTCASES[nr].getTestCaseDescription();
	}
	
	public boolean performTest(int nr) throws Throwable {
		// Assume the worst
		boolean result=true;
		// Create a new Instance for each test.
		try {
			WieIsDeMolImpl instance = new WieIsDeMolImpl(DEFINED_TESTCASES[nr].getOrders(), DEFINED_TESTCASES[nr].getLog());

			Contestant awnser = instance.wieIsDeMol();
			result = result&&  DEFINED_TESTCASES[nr].validateAwnser(awnser);
			System.out.println("validatingAwnser [awnser="+awnser.getName()+"]: " + (result?"OK":"NOK"));
		} catch (Exception ex) {
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			ex.printStackTrace();
			return false;
		}
		return result;
	}
}