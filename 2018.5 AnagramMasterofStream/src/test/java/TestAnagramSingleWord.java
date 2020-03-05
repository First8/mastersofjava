import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAnagramSingleWord {
    @Test
    public void testIsAnagramSingleWord() {
        AnagramCheckerImpl checker = new AnagramCheckerImpl();
        assertTrue(checker.isAnagram("stream", "master"));
        assertTrue(checker.isAnagram("anagram", "ragaman"));
        assertTrue(checker.isAnagram("anagram", "anagram"));
    }
}
