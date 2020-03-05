import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIgnoreCapitals {


    @Test
    public void testIsAnagramCapital() {
    	AnagramCheckerImpl checker = new AnagramCheckerImpl();
        assertTrue(checker.isAnagram("anagram", "AnAGrAm"));
    }
}
