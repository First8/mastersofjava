import org.junit.Test;

public class TestReplacerCaseInsensitive extends BaseTest {

    @Test
    public void replaceExactWord() {
        // given
        String inputSentence = "a " + testWord + " text";
        String expectedOutput = "a " + replacementWord + " text";

        // when
        String regex = Replacer.createRegex(testWord);

        //then
        testRegex(regex, inputSentence, expectedOutput);
    }

    @Test
    public void replaceUppercase() {
        // given
        String inputSentence = "a " + testWord.toUpperCase() + " text";
        String expectedOutput = "a " + replacementWord + " text";

        // when
        String regex = Replacer.createRegex(testWord);

        //then
        testRegex(regex, inputSentence, expectedOutput);
    }

    @Test
    public void replaceLowercase() {
        // given
        String inputSentence = "a " + testWord.toLowerCase() + " text";
        String expectedOutput = "a " + replacementWord + " text";

        // when
        String regex = Replacer.createRegex(testWord);

        //then
        testRegex(regex, inputSentence, expectedOutput);
    }

}
