import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class HiddenTests {

	   @Test
	    public void test2() throws UnsupportedEncodingException {
	    	test("mqa7u7Dl6YDpr6a7rqa96b2m6ai9vaiqoem9oazpqLq6oK6npKynvbrn6Z6gpaXpuqynrem9oayk6aessb3pvqysouc=", "Sorry, I forgot to attach the assignments. Will send them next week.");
	    }

	    private void test(String b64eInput, String expected) {
			Decrypt d = new Decrypt();
			String plaintext = d.decrypt(b64eInput);
			assertEquals( plaintext, expected); 
	    }

}
