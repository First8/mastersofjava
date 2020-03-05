import nl.moj.model.Tester;


public class SudokuTester implements Tester.Testable {
	public int getTestCount() {
		return TEST_CASE.values().length;
	}

	public String getTestName(int nr) {
		return TEST_CASE.values()[nr].name();
	}
	
	public String getTestDescription(int nr) {
		return TEST_CASE.values()[nr].message;
	}
	
	public boolean performTest(final int nr) throws Throwable {
		
		boolean hasPassed=false;
		try {
			
			TEST_CASE input = TEST_CASE.values()[nr];
			
			SudokuImpl instance= new SudokuImpl(input.field);
			
			hasPassed = (instance.isValid()== input.assertion);		
			
			if ( !hasPassed ){
				System.out.println(input.message);
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
			return false;
		}
		//
		return hasPassed;
	}
	
}
