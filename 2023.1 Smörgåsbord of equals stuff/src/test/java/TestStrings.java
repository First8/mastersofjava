import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStrings {

	private static final String HTML = """
			<html>
			   <body>Hello world!</body>
			</html>
			""";

	private static final String HTML2 = 
		"   <html>\n" + 
		"      <body>Hello world!</body>\n" + 
		"   </html>";
	

	@Test
	public void stringsShouldBeEqual() {
		assertTrue(WeirdEqualsBugs.equals(HTML, HTML2));
	}

}
