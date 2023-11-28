import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

public class TestWithCountry {
	
	@Test
	public void preferAmericanEnglish() {
		List<String> supportedLocales = List.of("en", "en-us");
		String acceptHeader = "en-us";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en-us", result);
	}

	@Test
	public void preferAmericEnglishSupportGeneric() {
		List<String> supportedLocales = List.of("en");
		String acceptHeader = "en-us";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en", result);
	}

	@Test
	public void americansOnlySoNoGenericEnglish() {
		List<String> supportedLocales = List.of("en-us");
		String acceptHeader = "en";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertNull(result);
	}

	private void assertEqualsIgnoreCase(String a, String b) {
		assertEquals(a.toLowerCase(), b.toLowerCase());
	}
	
	
	
}
