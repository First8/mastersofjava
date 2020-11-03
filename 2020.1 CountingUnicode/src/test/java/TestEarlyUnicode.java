import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestEarlyUnicode {

	@Test
	public void testUnicode() {
		assertEquals(1, CountingUnicode.countCharacters("🦄"));
	}

	@Test
	public void testUnicode2() {
		assertEquals(10, CountingUnicode.countCharacters("a unicorn! 🦄"));
	}

}
