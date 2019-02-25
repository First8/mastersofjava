import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class LambdaPuzzleImpl {

	public static int primeAfter(final int number) {
		if (isPrime(number + 1))
			return number + 1;
		else
			return primeAfter(number + 1);
	}

	/**
	 * Calculates and returns a list of prime numbers based on a start and the number of primes to calculate.
	 * 
	 * @param fromNumber the number to start calculating primes, inclusive
	 * @param count the number of primes to find
	 * @return  a list of prime numbers, or an empty list if no primes could be found
	 */
	public List<Integer> primes(final int fromNumber, final int count) {
		// TODO: implement this method
		return new ArrayList<Integer>();
	}

	public static boolean isPrime(final int number) {
		return number > 1
				&& IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(
						divisor -> number % divisor == 0);
	}
}