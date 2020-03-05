import java.util.ArrayList;
import java.util.List;

import nl.moj.model.Tester;

public class IpRangeCalculatorTester implements Tester.Testable {
	private final List<IpRangeTestCase> testCases = new ArrayList<IpRangeTestCase>();

	public IpRangeCalculatorTester() {
		this.testCases.add(new IpRangeTestCase("Home sweet home", "127.0.0.1/8", "127.0.0.4", "80.12.10.128"));
		this.testCases.add(new IpRangeTestCase("Local network", "10.0.0.253/24", "10.0.0.54", "10.0.1.54"));
		this.testCases.add(new IpRangeTestCase("Some random numbers", "83.64.223.6/17", "83.64.255.18", "84.64.223.6"));
		this.testCases.add(new IpRangeTestCase("Class A IP", "66.249.66.204/8", "66.0.0.5", "67.0.0.5"));
		this.testCases.add(new IpRangeTestCase("Class B IP", "138.249.43.156/16", "138.249.1.1", "138.250.43.156"));
		this.testCases.add(new IpRangeTestCase("Class C IP", "192.168.0.1/24", "192.168.0.15", "192.168.1.15"));
		this.testCases.add(new IpRangeTestCase("Carnaval", "11.11.11.11/11", "11.31.0.11", "11.32.0.11"));
		this.testCases.add(new IpRangeTestCase("Largest possible subnet", "9.8.7.6/1", "0.0.0.1", "128.0.0.1"));
		this.testCases.add(new IpRangeTestCase("Smallest possible subnet", "80.0.115.6/30", "80.0.115.5", "80.0.115.8"));
	}

	@Override
	public int getTestCount() {
		return this.testCases.size();
	}

	@Override
	public String getTestName(int nr) {
		return this.testCases.get(nr).getCidr();
	}

	@Override
	public String getTestDescription(int nr) {
		return this.testCases.get(nr).getDescription();
	}

	@Override
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result = false;
		//
		// Create a new Instance for each test.
		//
		try {
			IpRangeCalculatorImpl testable = new IpRangeCalculatorImpl();
			IpRangeTestCase testCase = this.testCases.get(nr);

			result = testCase.performTest(testable);
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace.
			//
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
}
