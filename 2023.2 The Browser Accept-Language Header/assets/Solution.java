import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;

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
		List<LanguageRange> a = Locale.LanguageRange.parse(acceptLanguageHeader);
		List<Locale> s = supportedLocales.stream().map(ls -> Locale.forLanguageTag(ls)).toList();
		Locale locale = Locale.lookup(a, s);
		return locale == null ? null : locale.toLanguageTag();
	}

}
