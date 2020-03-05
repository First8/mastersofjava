import nl.moj.model.Tester;

public class TemplateBuilderTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"TestCase #1"
			//
			// TODO: Add more tests.
			//
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		//
		// TODO: Generate useful test description.
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result=false;
		//
		// Create a new Instance for each test.
		//
		try {
			TemplateBuilderImpl instance=new TemplateBuilderImpl();
			//
			// TODO: Perform the specified test.
			//
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
