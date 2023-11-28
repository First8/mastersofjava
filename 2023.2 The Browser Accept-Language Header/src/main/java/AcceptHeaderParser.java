import java.util.List;

public class AcceptHeaderParser {

	/**
	 * Given a browser's Accept-Language header, and a list of supported locales,
	 * return the best matching locale. Or null if no match is found.
	 * 
	 * @param acceptLanguageHeader (e.g. "nl,en");
	 * @param supportedLocales     (e.g. "en,fr")
	 * @return the best match (e.g. "en")
	 */
	public String match(String acceptLanguageHeader, List<String> supportedLocales) {
		return null;
	}

}
