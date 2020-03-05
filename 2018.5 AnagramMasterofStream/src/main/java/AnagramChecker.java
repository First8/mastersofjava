
public interface AnagramChecker {

	/**
	 * Tests if the provided possibility is an anagram of subject.
	 *
	 * @return True if and only if the provided possibility is an anagram of
	 *         subject, otherwise false
	 */
	boolean isAnagram(String subject, String possibility);
}
