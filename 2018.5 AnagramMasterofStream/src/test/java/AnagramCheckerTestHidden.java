import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiConsumer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramCheckerTestHidden {

    private static final class ActualAnagramChecker implements AnagramChecker {

        @Override
        public boolean isAnagram(String subject, String possibility) {
            return Arrays.equals(anagramize(subject), anagramize(possibility));
        }
        
        public static BiConsumer<Long[], Long[]> combiner(){
            return (map1, map2) -> {  };
        }

        private static Long[] anagramize(String input) {
            return input.chars().collect(AnagramCheckerHelper.supplier(), AnagramCheckerHelper.accumulator(), combiner());
        }

    }

    @Test
    public void testIsAnagramSingleWord() {
        ActualAnagramChecker checker = new ActualAnagramChecker();

        assertTrue(checker.isAnagram("restful", "fluster"));
        assertFalse(checker.isAnagram("restfull", "fluster"));
    }

    @Test
    public void testIsAnagramMultipleWords() {
        ActualAnagramChecker checker = new ActualAnagramChecker();

        assertTrue(checker.isAnagram("funeral", "real fun"));
        assertTrue(checker.isAnagram("funeral", "real  fun"));
        assertTrue(checker.isAnagram("customers", "store scum"));
        assertTrue(checker.isAnagram("customers", "store  scum"));

        assertFalse(checker.isAnagram("funerall", "real fun"));
        assertFalse(checker.isAnagram("customers", "store scums"));
    }

    @Test
    public void testIsAnagramCapital() {
        ActualAnagramChecker checker = new ActualAnagramChecker();

        assertTrue(checker.isAnagram("restful", "FLUSTER"));
    }
}
