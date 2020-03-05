import java.util.Deque;

public interface PalindromeTest {

	/**
	 * Determines if the given values form a Palindrome,
	 * something that can be read from left to right and from
	 * right to left. 
	 * @param values the data set to evaluate.
	 * @return true if the values form a palindrome.
	 */
	boolean isPalindrome(Deque values);
	
}
