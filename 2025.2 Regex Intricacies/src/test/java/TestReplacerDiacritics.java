import org.junit.Test;

public class TestReplacerDiacritics extends BaseTest {

    @Test
    public void testDiacriticsInTheWord() {
        // given
        String testWord = "dïacrítîcs";
        String inputSentence = "a text with " + testWord;
        String expectedOutput = "a text with " + replacementWord;

        // when
        String patternStr = Replacer.createRegex(testWord);

        // then
        testRegex(patternStr, inputSentence, expectedOutput);
    }

    @Test
    public void testDiacriticsAtStartAndEnd() {
        // given
        String testWord = "ällö";
        String inputSentence = "a text with " + testWord;
        String expectedOutput = "a text with " + replacementWord;

        // when
        String patternStr = Replacer.createRegex(testWord);

        // then
        testRegex(patternStr, inputSentence, expectedOutput);
    }
}
