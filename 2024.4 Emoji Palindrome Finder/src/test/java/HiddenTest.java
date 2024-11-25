import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HiddenTest {

    private final EmojiPalindromeFinder finder = new EmojiPalindromeFinder();

    @Test
    public void testPalindromeOne() {
        String actual = finder.findLongestPalindrome("test: 😎 ♥️👌👌🏼👌🏽👌🏾♥️");

        String expected = "♥️👌👌🏼👌🏽👌🏾♥️";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeTwo() {
        String actual = finder.findLongestPalindrome("this is not a palindrome: 😎 but this is: ♥️👌👌🏼👌🏽👌🏾♥️");

        String expected = "♥️👌👌🏼👌🏽👌🏾♥️";

        assertEquals(expected, actual.trim());
    }

}
