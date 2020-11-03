import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class HiddenTests {
	@Test
	public void testMethod3EmojiModifier () {
		assertEquals(2, CountingUnicode.countCharacters("👍🏾"));
	}
	@Test
	public void testAscii() {
        assertEquals(4, CountingUnicode.countCharacters("fghi"));
	}

}
