import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Test;

public class TestUrls {

	@Test
	public void testUrlsAreDifferent1() throws Exception {
		assertFalse(compare("https://nljug.org", "https://101wasmachines.nl"));
	}

	@Test
	public void testUrlsAreDifferent2() throws Exception {
		assertFalse(compare("https://www.first8.nl", "https://www.amis.nl"));
	}

	@Test
	public void testUrlsAreEqual1() throws Exception {
		assertTrue(compare("https://first8.nl", "https://first8.nl"));
	}

	@Test
	public void testUrlsAreEqual2() throws Exception {
		assertTrue(compare("https://first8.nl", "https://first8.nl:"));
	}

	@Test
	public void testUrlsAreEqual3() throws Exception {
		assertTrue(compare("https://first8.nl", "https://first8.nl:"));
	}

	@Test
	public void testUrlsAreEqual4() throws Exception {
		assertTrue(compare("file:///tmp/ ", "file:/tmp/ "));
	}
	
	private boolean compare(String u1, String u2) throws Exception {
		URL url1 = new URL(u1);
		URL url2 = new URL(u2);
		
		boolean result = WeirdEqualsBugs.equals(url1, url2);
		System.out.println("Comparing: " + u1 + " with " + u2 + " yields: " + result);
		return result;
	}

}
