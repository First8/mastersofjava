import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestModernUnicode {

	@Test
	public void testZeroWidthJoiner0() {
		assertEquals(1, CountingUnicode.countCharacters("🏴‍☠️"));
	}

	@Test
	public void testZeroWidthJoiner1() {
		assertEquals(11, CountingUnicode.countCharacters("arrr 🏴‍☠️ a pirate flag‍️"));
	}

	@Test
	public void testZeroWidthJoiner2() {
		assertEquals(11, CountingUnicode.countCharacters("arrr 🏴‍☠️ two 🏴‍☠️ flags"));
	}

	@Test
	public void testZeroWidthJoiner3() {
		assertEquals(12, CountingUnicode.countCharacters("arrr 🏴‍☠️ a pirate flag‍️ 🦄"));
	}

}
