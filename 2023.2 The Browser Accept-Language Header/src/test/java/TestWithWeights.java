import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestWithWeights {

	@Test
	public void preferAmericanEnglishButAcceptBritish() {
		List<String> supportedLocales = List.of("en-us", "en-gb");
		String acceptHeader = "en-us, en-gb;q=0.5";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en-us", result);
	}

	@Test
	public void preferAmericanEnglishButAcceptGenericEnglish() {
		List<String> supportedLocales = List.of("en-us", "en");
		String acceptHeader = "en-us,en;q=0.8";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en-us", result);
	}

	@Test
	public void preferFrenchAboveEnglish() {
		List<String> supportedLocales = List.of("en", "fr");
		String acceptHeader = "fr;q=1.0,en;q=0.5";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("fr", result);
	}

	@Test
	public void preferFrenchAboveEnglishAlsoIfSpecifiedInWrongOrder() {
		List<String> supportedLocales = List.of("en", "fr");
		String acceptHeader = "en;q=0.5,fr;q=1.0";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("fr", result);
	}

	@Test
	public void preferFrenchAboveEnglishWithDefaultWeight() {
		List<String> supportedLocales = List.of("en", "fr");
		String acceptHeader = "en;q=0.5,fr";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("fr", result);
	}

	private void assertEqualsIgnoreCase(String a, String b) {
		assertEquals(a.toLowerCase(), b.toLowerCase());
	}
	
	
	
}
