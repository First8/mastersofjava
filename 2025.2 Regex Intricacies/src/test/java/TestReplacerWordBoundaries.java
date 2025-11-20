import org.junit.Test;


public class TestReplacerWordBoundaries extends BaseTest {

    @Test
    public void testWithoutWordBoundaries() {
        // given
        String noReplacementSentence = "thisis" + testWord + "notreplaced";

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, noReplacementSentence, noReplacementSentence);
    }

    @Test
    public void testMultipleOccurrences() {
        // given
        String inputSentence = "this " + testWord + " occurs twice: see " + testWord;
        String expectedOutput = "this " + replacementWord + " occurs twice: see " + testWord;

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, inputSentence, expectedOutput);
    }

    @Test
    public void testEndOfSentence() {
        // given
        String inputSentence = "a text with an end " + testWord;
        String expectedOutput = "a text with an end " + replacementWord;

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, inputSentence, expectedOutput);
    }

    @Test
    public void testSurroundedWithNewlines() {
        // given
        String inputSentence = "\n" + testWord + "\n with newlines";
        String expectedOutput = "\n" + replacementWord + "\n with newlines";

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, inputSentence, expectedOutput);
    }

    @Test
    public void testBetweenBrackets() {
        // given
        String inputSentence = "(" + testWord + ") between brackets";
        String expectedOutput = "(" + replacementWord + ") between brackets";

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, inputSentence, expectedOutput);
    }

    @Test
    public void testWithBracketsAndApostrophes() {
        // given
        String inputSentence = "(" + testWord + "'s with apostrophe and bracket";
        String expectedOutput = "(" + replacementWord + "'s with apostrophe and bracket";

        // when
        String regex = Replacer.createRegex(testWord);

        // then
        testRegex(regex, inputSentence, expectedOutput);
    }

    @Test
    public void testWordContainsSpaces() {
        // given
        String wordWithSpaces = "s i m p l e";
        String inputSentence = "a test " + wordWithSpaces + " with spaces";
        String expectedOutput = "a test " + replacementWord + " with spaces";

        // when
        String regex = Replacer.createRegex(wordWithSpaces);

        // then
        testRegex(regex, inputSentence, expectedOutput);
    }
}
