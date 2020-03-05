import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestNotAnagramSingleWord {

    @Test
    public void testIsNotAnagramSingleWord() {
    	AnagramCheckerImpl checker = new AnagramCheckerImpl();
        assertFalse(checker.isAnagram("stream", "mastera"));
        assertFalse(checker.isAnagram("anagrama", "ragaman"));
        assertFalse(checker.isAnagram("strexm", "master"));
        assertFalse(checker.isAnagram("anagrama", "ragamax"));
    }
}
