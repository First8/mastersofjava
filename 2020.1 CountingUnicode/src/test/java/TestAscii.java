import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAscii {

	@Test
	public void testAscii1() {
        assertEquals(3, CountingUnicode.countCharacters("abc"));
	}

	@Test
	public void testAscii2() {
		assertEquals(3, CountingUnicode.countCharacters("aabcbc"));
	}
}
