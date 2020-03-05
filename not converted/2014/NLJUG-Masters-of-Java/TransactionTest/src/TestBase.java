import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;

public class TestBase {
	public MojTest test;

	public void assertTestSucceeds(Class<?> type) {
		assertThat(test.performTestOn(type), is(true));
		assertThat(test.getErrorMessage(), is(nullValue()));
	}
	
	public void assertTestFails(Class<?> type, String expected) {
		assertThat(test.performTestOn(type), is(false));
		assertThat(test.getErrorMessage(), is(expected));
	}
	
	public void assertTestFails(Class<?> type, Matcher<String> expected) {
		assertThat(test.performTestOn(type), is(false));
		assertThat(test.getErrorMessage(), is(expected));
	}
}
