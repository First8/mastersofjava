import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.moj.model.Tester;

public class SmithNumberTester implements Tester.Testable {
	
	private static final Integer [] PRIMES = new Integer[] {2,3,5,11,13,17,19,23,29,31};
	private static final Integer [] NON_PRIMES = new Integer[] {4,9,12,14,15,20,21,60,63,100};
	
	private static Set<Integer> SMITH_NUMBERS = new HashSet<Integer>();
	static {
		for (int i = 2; i < 2000; i++) {
			if (ExperimentSmithNumber.isSmithNumber(i)) {
				SMITH_NUMBERS.add(i);
			};
		}
	}
	private static List<List<Integer>> CORRECT_PRIMEFACTORS;
	static {
	CORRECT_PRIMEFACTORS = new ArrayList<List<Integer>>();
		for (int i = 2; i < 2000; i++) {

			CORRECT_PRIMEFACTORS.add(PrimeFactorsEffective.primeFactors(i));
		}
	}
	
	private static final String[] NAMES=new String[] {
		    "TestIsPrimeNumber #1",	
		    "TestPrimeFactorization #2",
		    "TestIsSmithNumber #3"
			
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
		switch (nr) {
		case 0:
			sb.append("Test for the method isPrimeNumber(int number)");
			break;
		case 1:
			sb.append("Test for primeFactors(int number)");
			break;
		case 2:
			sb.append("Test for isSmithNumber");
			break;
		}
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
			SmithNumberImpl instance=new SmithNumberImpl();
			switch (nr) {
			case 0:
				result = testIsPrimeNumber(instance);
				break;
			case 1:
				result = testPrimeFactors(instance);
				break;
			case 2:
				result = testIsSmithNumber(instance);
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
	
	public boolean testIsPrimeNumber(SmithNumberImpl instance) {
			
		for (int number :  PRIMES) {
			if (!SmithNumberImpl.isPrimeNumber(number)) {
				return false;
			}
		 }
		
		for (int number :  NON_PRIMES) {
			if (SmithNumberImpl.isPrimeNumber(number)) {
				return false;
			}
		 }
		
		return true;
		}
		


	public boolean testPrimeFactors(SmithNumberImpl instance) {
		
		List<List<Integer>> primeFactors = new ArrayList<List<Integer>>();
		for (int i = 2; i < 2000; i++) {
			 
			primeFactors.add(instance.primeFactors(i));
		}

		return CORRECT_PRIMEFACTORS.equals(primeFactors);
		
	}
	
	public boolean testIsSmithNumber(SmithNumberImpl instance) {
						
		int number1 = 22;
		boolean shouldBeTrue = instance.isSmithNumber(number1);
		
		int number2 = 378;
		boolean shouldBeTrue2 = instance.isSmithNumber(number2);

		int number3 = 1086;
		boolean shouldBeTrue3 = instance.isSmithNumber(number3);
		
		
		int number4 = 379;
		boolean shouldBeFalse = instance.isSmithNumber(number4);
		
		// Only composite numbers are allowed. So 4 is no smithnumber
		int number5 = 4;
		boolean shouldBeFalse2 = instance.isSmithNumber(number5);
		
		return (shouldBeTrue && shouldBeTrue2 && shouldBeTrue3 && !shouldBeFalse && !shouldBeFalse2);
		
	}
	
	public boolean testListGeneration(SmithNumberImpl instance) {
		
		Set<Integer> smithNumbers = instance.generateSmithNumberList(2000);
		
		return SMITH_NUMBERS.equals(smithNumbers);
	}
	
}
