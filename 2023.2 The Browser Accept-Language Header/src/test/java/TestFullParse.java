import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestFullParse {
	
	@Test
	public void preferEnglish() {
		List<String> supportedLocales = List.of("en", "fr");
		String acceptHeader = "en;q=0.8,en-ca,en-us;q=0.6,fr-ca;q=0.4,fr;q=0.2";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en", result);
		
	}

	@Test
	public void preferEnglishButSupportOnlyFrench() {
		List<String> supportedLocales = List.of("fr");
		String acceptHeader = "en;q=0.8,en-ca,en-us;q=0.6,fr-ca;q=0.4,fr;q=0.2";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("fr", result);
	}

	@Test
	public void preferUsEnglish() {
		List<String> supportedLocales = List.of("en-us", "en-ca");
		String acceptHeader = "en;q=0.8,en-ca,en-us;q=0.6,fr-ca;q=0.4,fr;q=0.2";
		String result = new AcceptHeaderParser().match(acceptHeader, supportedLocales);
		assertEqualsIgnoreCase("en-ca", result);
	}

	private void assertEqualsIgnoreCase(String a, String b) {
		assertEquals(a.toLowerCase(), b.toLowerCase());
	}
	
	
	
}
