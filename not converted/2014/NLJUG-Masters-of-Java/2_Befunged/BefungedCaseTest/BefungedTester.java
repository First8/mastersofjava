import java.util.ArrayList;
import java.util.List;

import nl.moj.model.Tester;

public class BefungedTester implements Tester.Testable {
	private List<TestCase> testCases = new ArrayList<TestCase>();

	public BefungedTester() {
		this.populateTestCases(this.testCases);
	}

	@Override
	public final int getTestCount() {
		return this.testCases.size();
	}

	@Override
	public final String getTestName(int nr) {
		return this.testCases.get(nr).getName();
	}

	@Override
	public final String getTestDescription(int nr) {
		return this.testCases.get(nr).getDescription();
	}

	@Override
	public final boolean performTest(int nr) throws Throwable {
		boolean result = false;
		TestCase testCase = this.testCases.get(nr);

		try {
			BefungedImpl instance = new BefungedImpl();
			instance.addProgramListener(new MaximumProgramStepsListener());

			result = testCase.execute(instance);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private void populateTestCases(List<TestCase> testCases) {
		StringBuilder program = new StringBuilder();
		program.append(">\"!dlrow ,olleH\":v ").append("\n");
		program.append("              v:,_@").append("\n");
		program.append("              >  ^ ").append("\n");
		TestCase helloWorld = new TestCase("Hello World", program.toString(), "Hello, world!");
		testCases.add(helloWorld);

		String quineProgram = "\"48*2+,>:#,_@                                                       @_,#:>,+2*84";
		testCases.add(new TestCase("Quine", quineProgram, quineProgram));

		testCases.add(new TestCase("Infinite loop", "> v\n @ \n^ <", true));

		testCases.add(new TestCase("Infinite loop", "> ...\"12345_@", true));

		testCases.add(new TestCase("Oops, little mistake", ">\"a\":,v \n^     _@", true));
	}
}
