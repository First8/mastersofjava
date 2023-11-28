import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

public class WeirdEqualsBugs {

	public static boolean equals(String a, String b) {
		a = a.indent(3);
		return a.equals(b+"\n");
	}

	public static boolean equals(Object[] a, Object[] b) {
		return Arrays.deepEquals(a, b);
	}

	public static boolean equals(URL a, URL b) {
		try {
			return a.toURI().equals(b.toURI());
		} catch (URISyntaxException e) {
			return false;
		}
	}

}
