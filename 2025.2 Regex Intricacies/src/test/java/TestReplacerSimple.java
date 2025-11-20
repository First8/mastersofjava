import org.junit.Test;

public class TestReplacerSimple extends BaseTest {

    @Test
    public void testReplacements() {
        // given
        String inputSentence = "a " + testWord + " text";
        String expectedOutput = "a " + replacementWord + " text";

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, inputSentence, expectedOutput);
    }


    @Test
    public void testMultipleOccurrences() {
        // given
        String shouldReplaceOnce = "this " + testWord + " occurs twice: see " + testWord;
        String shouldReplaceExpected = "this " + replacementWord + " occurs twice: see " + testWord;

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, shouldReplaceOnce, shouldReplaceExpected);
    }
}
