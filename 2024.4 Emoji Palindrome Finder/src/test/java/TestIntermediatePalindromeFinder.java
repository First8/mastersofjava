import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestIntermediatePalindromeFinder {

    private final EmojiPalindromeFinder finder = new EmojiPalindromeFinder();
    @Test
    public void testPalindromeOne() {
        String actual = finder.findLongestPalindrome("A found a pile of 💩poop💩 in my car😤");

        String expected = "💩poop💩";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeTwo() {
        String actual = finder.findLongestPalindrome("look, an alien invasion 👽🚀👽🚀👽🚀👽🚀👽🚀👽🚀👽🚀👽");

        String expected = "👽🚀👽🚀👽🚀👽🚀👽🚀👽🚀👽🚀👽";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeThree() {
        String actual = finder.findLongestPalindrome("I accidentally injured an animal with my lawnmower at the farm. 🦄🐮🐂🐷🐪🐘🐘🐪🐷🐂🐮🦄");

        String expected = "🦄🐮🐂🐷🐪🐘🐘🐪🐷🐂🐮🦄";

        assertEquals(expected, actual.trim());
    }

    @Test
    public void testPalindromeFour() {
        String actual = finder.findLongestPalindrome("A nice day at the aquarium 🐸🐠wow🐠🐸");

        String expected = "🐸🐠wow🐠🐸";

        assertEquals(expected, actual.trim());
    }
}
