import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEasyPalindromeFinder {

    private final EmojiPalindromeFinder finder = new EmojiPalindromeFinder();

    @Test
    public void testPalindromeOne() {
        String actual = finder.findLongestPalindrome("i drove your racecar into a wall");

        String expected = "racecar";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeTwo() {
        String actual = finder.findLongestPalindrome("dora was diagnosed with aibohphobia at the Mcdonalds");

        String expected = "aibohphobia";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeThree() {
        String actual = finder.findLongestPalindrome("I deified dora at church");

        String expected = "deified";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeFour() {
        String actual = finder.findLongestPalindrome("I got kicked out of the bus while trying to repaper the wall");

        String expected = "repaper";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeFive() {
        String actual = finder.findLongestPalindrome("wo that is a cool rotavator");

        String expected = "rotavator";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeSix() {
        String actual = finder.findLongestPalindrome("My bike was used to commit a murdrum");

        String expected = "murdrum";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeSeven() {
        String actual = finder.findLongestPalindrome("mis you are not allowed to tattarrattat in public");

        String expected = "tattarrattat";

        assertEquals(expected, actual.trim());
    }
    @Test
    public void testPalindromeEight() {
        String actual = finder.findLongestPalindrome("Ostan saippuani osoitteessa saippuakivikauppias");

        String expected = "saippuakivikauppias";

        assertEquals(expected, actual.trim());
    }

}
