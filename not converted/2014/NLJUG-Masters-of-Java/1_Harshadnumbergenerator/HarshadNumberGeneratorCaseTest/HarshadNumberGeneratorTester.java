import java.util.Set;

import nl.moj.model.Tester;

public class HarshadNumberGeneratorTester implements Tester.Testable {


	private static final String[] NAMES = new String[] {
			"TestCase #1 - Check number of results",
			"TestCase #2 - perform checksum", 
			"TestCase #3 - Check each number" };
	
	private static final int EXPECTED_SIZE = 11872;
	private static final int EXPECTED_CHECKSUM = 533350649;

	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}

	public String getTestDescription(int nr) {
		StringBuffer sb = new StringBuffer();
		sb.append("");
		return sb.toString();
	}

	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result = false;
		//
		// Create a new Instance for each test.
		//
		HarshadNumberGeneratorImpl instance = new HarshadNumberGeneratorImpl();
		Set<Integer> actualResult = instance.generateSequence();
		try {
			switch (nr) {
			case 0:
				if (actualResult != null){
					result = actualResult.size() == EXPECTED_SIZE;
					if (!result) {
						System.out.println("The set does not match the expected size");
					} else {
						System.out.println("The result contains the expected count of numbers");
					}
				} else {
					System.out.println("The result may not be null");
				}
				break;
			case 1:
				int sum = 0;
				for (Integer integer : actualResult) {
					sum += integer;
				}
				
				if (sum == EXPECTED_CHECKSUM){
					result = true;
					System.out.println("Checksum matches");
				} else {
					System.out.println("The result checksum is not expected");					
				}
				break;
			case 2:
				boolean allHarshad = true;
				for (Integer integer : actualResult) {
					if (!checkHarshadNumber(integer)){
						allHarshad = false;
						System.out.println("One of the numbers in the result is not a harshad number");
						break;
					}
				}
				if (allHarshad){
					System.out.println("All numbers in set are harshad number");
				}
				result = allHarshad;
				break;
			default:
				break;
			}
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
	
	private boolean checkHarshadNumber(int number){
		int sum = 0;
		String s = "" + number;
		for (int j = 0; j < s.length(); j++) {
			sum += Integer.parseInt("" + s.charAt(j));
		}
		return number % sum == 0;
	}

}
