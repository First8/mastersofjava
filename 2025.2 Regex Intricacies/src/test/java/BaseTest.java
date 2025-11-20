import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

abstract public class BaseTest {

    protected final String testWord = "simple";
    protected final String replacementWord = "found";

    protected void testRegex(String regex, String inputSentence, String expectedOutput) {
        // given
        Pattern pattern = Pattern.compile(regex);

        // when
        String result = pattern.matcher(inputSentence).replaceFirst(replacementWord);

        // then
        String failMessage = getFailMessage(inputSentence, expectedOutput, result);
        assertEquals(failMessage, expectedOutput, result);
    }

    private static String getFailMessage(String inputSentence, String expectedOutput, String result) {
        return "input: [" + inputSentence + "], expected: [" + expectedOutput + "], result: [" + result + "]";
    }

}
