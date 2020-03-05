import java.util.List;

import nl.moj.model.Tester;

public class TransactionTester implements Tester.Testable {
	private final Class<?> type;
	private final List<MojTest> tests;
	
	public TransactionTester() {
		this(Transaction.class, MojTestHelper.buildListOfTests());
	}
	
	public TransactionTester(Class<?> type, List<MojTest> tests) {
		this.type = type;
		this.tests = tests;
	}

	@Override
	public int getTestCount() {
		return tests.size();
	}

	@Override
	public String getTestName(int nr) {
		return tests.get(nr).getName();
	}

	@Override
	public String getTestDescription(int nr) {
		return tests.get(nr).getDescription();
	}

	@Override
	public boolean performTest(int nr) throws Throwable {
		boolean result;
		try {
			MojTest test = tests.get(nr);
			result = test.performTestOn(type);
			if (result) {
				System.out.println("Test passed.");
			}
			else {
				System.out.println("Test failed.");
				System.out.println(test.getErrorMessage());
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
			result = false;
		}
		System.out.println("");
		return result;
	}
}
