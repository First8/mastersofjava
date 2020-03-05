import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAnagramMultipleWord {
    @Test
    public void testIsAnagramMultipleWords() {
        AnagramCheckerImpl checker = new AnagramCheckerImpl();

        assertTrue(checker.isAnagram("anagram", "raga man"));
        assertTrue(checker.isAnagram("anagram", "a nag mar"));
        assertTrue(checker.isAnagram("raga man", "gar am an"));
    }
}
