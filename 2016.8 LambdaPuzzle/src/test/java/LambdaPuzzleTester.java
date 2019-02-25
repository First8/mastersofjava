import java.util.List;

import nl.moj.model.Tester;

/**
 * Tester class of the puzzle, which contains a number of hidden tests via the interface HiddenTestable
 */
public class LambdaPuzzleTester implements Tester.Testable, Tester.HiddenTestable {
	
	private static final Integer[] INPUT_NUMBER =new Integer[] {
			1,
			2,
			3,
			4,
			5,
			6,
			7,
			8,
			9,
			10			
	};
	
	private static final Integer[] OUTPUT_PRIME =new Integer[] {
		2,
		3,
		5,
		7,
		11,
		13,
		17,
		19,
		23,
		29
	};
	private static final String[] TEST_NAME =new String[] {
		"The first prime",
		"Closest to the first",
		"High five",
		"Lucky number",
		"This number is mad",
		"This prime gives you problems",
		"Until prime 17",
		"Until prime 19",
		"Until prime 23",
		"Until prime 29"
	};
	
	/**
	 * the total amount of tests minus the last ones that are hidden.
	 */
	public int getTestCount() {
		return TEST_NAME.length-4;
	}
	
	/**
	 * the total amount of tests.
	 */
	@Override
	public int getTestCountIncludeHidden() {
		return TEST_NAME.length;
	}

	public String getTestName(int nr) {
		return TEST_NAME[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append("should be derived from " +OUTPUT_PRIME[nr]); 
		
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
		LambdaPuzzleImpl instance=new LambdaPuzzleImpl();
		
		try {
			List<Integer> list = instance.primes(0, INPUT_NUMBER[nr]);
			Integer max = list.get(list.size()-1); 
			boolean isOk = max==OUTPUT_PRIME[nr];  
			if (isOk) {
				result=true;
			} else {
				System.out.println("Failed: There seems to be a problem with your lambda expression.");
				result=false;
			} 
			if (!result) {
				System.out.println("Result   : '"+max+"'");
				System.out.println("Expected : '"+OUTPUT_PRIME[nr]+"'");
			}
		} catch (Exception ex) {
			if (nr<getTestCount()) {
				ex.printStackTrace();
			}
			
		}
		//
		return result;
	}
	public static void main(String args[] ){ 
		LambdaPuzzleImpl instance=new LambdaPuzzleSolution();
		for (int index=1;index<25;index++) {
			System.out.println(index + ". " + instance.primes(0, index));
		}
	}
}
