import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

public class TestSimple {
	
	@Test
	public void preferEnglishOrDutchNoFrench() {
		List<String> supportedLocales = List.of("en", "fr");
		String acceptHeader = "nl,en";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en", result);
	}

	@Test
	public void noMatch() {
		List<String> supportedLocales = List.of("en", "fr");
		String acceptHeader = "nl";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertNull(result);
	}

	@Test
	public void americaFirst() {
		List<String> supportedLocales = List.of("en");
		String acceptHeader = "en";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en", result);
	}

	@Test
	public void french() {
		List<String> supportedLocales = List.of("en","fr");
		String acceptHeader = "fr";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("fr", result);
	}

	private void assertEqualsIgnoreCase(String a, String b) {
		assertEquals(a.toLowerCase(), b.toLowerCase());
	}
	
	
	
}
