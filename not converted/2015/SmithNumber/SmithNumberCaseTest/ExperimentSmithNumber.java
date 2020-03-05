import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExperimentSmithNumber {

	private static final Integer[] PRIMES = new Integer[] { 2, 3, 5, 11, 13,
			17, 19, 23, 29, 31 };
	private static final Integer[] NON_PRIMES = new Integer[] { 4, 9, 12, 14,
			15, 20, 21, 60, 63, 100 };

	private static Set<Integer> SMITH_NUMBERS = new HashSet<Integer>();
	static {
		for (int i = 2; i < 2000; i++) {
			if (ExperimentSmithNumber.isSmithNumber(i)) {
				SMITH_NUMBERS.add(i);
			}
			;
		}
	}
	
	
	private static List<List<Integer>> CORRECT_PRIMEFACTORS;
	static {
		CORRECT_PRIMEFACTORS = new ArrayList<List<Integer>>();
		for (int i = 2; i < 2000; i++) {

			CORRECT_PRIMEFACTORS.add(PrimeFactorsEffective.primeFactors(i));
		}
	}

	@Test
	public void testSmithNumberMethod1() {
		for (int i = 2; i < 2000; i++) {
			if (isSmithNumber(i)) {
				System.out.println(i);
			}

		}
	}

	@Test
	public void testSmithNumber22() {

		if (isSmithNumber(22)) {
			System.out.println("22 is a smithNumber");
		}

	}

	@Test
	public void testIsPrimeNumber() {
		boolean primesTestOK = true;
		boolean nonPrimesTestOK = true;

		for (int number : PRIMES) {
			if (!isPrimeNumber(number)) {
				primesTestOK = false;
			}
		}

		for (int number : NON_PRIMES) {
			if (isPrimeNumber(number)) {
				nonPrimesTestOK = false;
			}
		}

		assertTrue(primesTestOK);
		assertTrue(nonPrimesTestOK);
	}

	@Test
	public void testPrimeFactors() {

		List<List<Integer>> primeFactors = new ArrayList<List<Integer>>();
		for (int i = 2; i < 2000; i++) {

			primeFactors.add(primeFactors(i));
		}

		assertTrue(CORRECT_PRIMEFACTORS.equals(primeFactors));

	}

	@Test
	public void testIsSmithNumber() {

		int smithNumber1 = 22;
		assertTrue(isSmithNumber(smithNumber1));

		int smithNumber2 = 378;
		assertTrue(isSmithNumber(smithNumber2));

	}

	/*
	 * Java method to check if an integer number is prime or not.
	 * 
	 * @return true if number is prime, else false
	 */
	public static boolean isPrime(int number) {
		int sqrt = (int) Math.sqrt(number) + 1;
		for (int i = 2; i < sqrt; i++) {
			if (number % i == 0) {
				// number is perfectly divisible - no prime
				return false;
			}
		}
		return true;
	}

	/*
	 * Second version of isPrimeNumber method, with improvement like not
	 * checking for division by even number, if its not divisible by 2.
	 */
	public static boolean isPrimeNumber(int number) {
		if (number == 2 || number == 3) {
			return true;
		}
		if (number % 2 == 0) {
			return false;
		}
		int sqrt = (int) Math.sqrt(number) + 1;
		for (int i = 3; i < sqrt; i += 2) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isSmithNumber(final int number) {

		if (isPrimeNumber(number)) {
			return false;
		} else {
			String numberChars = "" + number;
			char[] digits = numberChars.toCharArray();
			int sumNumberDigits = 0;
			int sumPrimeDigits = 0;
			for (char digit : digits) {
				sumNumberDigits += Character.getNumericValue(digit);
			}
			List<Integer> primefactors = PrimeFactorsEffective
					.primeFactors(number);
			for (Integer primefactor : primefactors) {
				char[] primedigits = primefactor.toString().toCharArray();
				for (char digit : primedigits) {
					sumPrimeDigits += Character.getNumericValue(digit);
				}
			}
			return sumNumberDigits == sumPrimeDigits;
		}
	}

	/**
	 * Generates a list forming the complete Prime factorization of the number
	 * that is provided as a parameter
	 * 
	 * For example 12 in prime factorization is 2 * 2 * 3. The List to return
	 * would be List(2,2,3).
	 * 
	 * 
	 * @param number
	 */
	public List<Integer> primeFactors(int number) {
		int n = number;
		List<Integer> factors = new ArrayList<Integer>();
		/*
		 * Implement your algorithm for generating a list of containing all
		 * Primefactors of a number underneath here
		 */

		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}
		return factors;
	}

}
