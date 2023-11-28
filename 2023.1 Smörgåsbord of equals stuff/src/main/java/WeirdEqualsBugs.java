import java.net.URL;

public class WeirdEqualsBugs {

	public static boolean equals(String a, String b) {
		return a.equals(b);
	}

	public static boolean equals(Object[] a, Object[] b) {
		return a.equals(b);
	}

	public static boolean equals(URL a, URL b) {
		return a.equals(b);
	}

}
