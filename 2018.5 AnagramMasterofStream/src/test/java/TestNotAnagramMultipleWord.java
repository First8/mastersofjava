import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestNotAnagramMultipleWord {

    @Test
    public void testIsNotAnagramMultipleWords() {
    	AnagramCheckerImpl checker = new AnagramCheckerImpl();
        assertFalse(checker.isAnagram("anagrm", "raga man"));
        assertFalse(checker.isAnagram("anagrama", "a nag mar"));
        assertFalse(checker.isAnagram("raga xan", "gar am an"));
    }

}
