import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHardPalindromeFinder {

    private final EmojiPalindromeFinder finder = new EmojiPalindromeFinder();

    @Test
    public void testPalindromeOne() {
        String actual = finder.findLongestPalindrome("💃🏿💃");

        String expected = "💃🏿💃";

        assertEquals(expected, actual.trim());
    }


    @Test
    public void testPalindromeTwo() {
        String actual = finder.findLongestPalindrome("We're all equal 👦👧👦🏻👧🏻👦🏼👧🏼👦🏽👧🏽👦🏾👧🏾👦🏿👧🏿😀👧🏿👦🏿👧🏾👦🏾👧🏽👦🏽👧🏼👦🏼👧🏻👦🏻👧👦");

        String expected = "👦👧👦🏻👧🏻👦🏼👧🏼👦🏽👧🏽👦🏾👧🏾👦🏿👧🏿😀👧🏿👦🏿👧🏾👦🏾👧🏽👦🏽👧🏼👦🏼👧🏻👦🏻👧👦";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeThree() {
        String actual = finder.findLongestPalindrome("💃🏿💃🏾💃🏽💃🏼💃🏻💃🏿💃🏾💃🏽💃🏼💃🏻💃 It's an epic dance off 🕺🏿");

        String expected = "💃🏿💃🏾💃🏽💃🏼💃🏻💃🏿💃🏾💃🏽💃🏼💃🏻💃";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeFour() {
        String actual = finder.findLongestPalindrome("Random difficult emojis ♂️♥️♥️♂️");

        String expected = "♂️♥️♥️♂️";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeUltimate() {
        String actual = finder.findLongestPalindrome("♥️👌 Ultimate test: ♥️👌👌🏼👌🏽👌🏾♥️");

        String expected = "♥️👌👌🏼👌🏽👌🏾♥️";

        assertEquals(expected, actual.trim());
    }


}
