
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HiddenTests {

	@Test
	public void testHiddenStrip1() {

		LedMatrix matrix = new LedMatrix(3, 3);

		boolean[][] image = { { true, true, true }, { true, false, false }, { true, false, true } };
		boolean[] expected = new boolean[] { true, true, true, false, false, true, true, false, true };

		assertArrayEquals(matrix.getStrip(image), expected);
		matrix.plot(image);
	}

	@Test
	public void testHiddenStrip2() {

		LedMatrix matrix = new LedMatrix(4, 4);

		boolean[][] image = { { false, false, true, true }, { false, true, false, true }, { false, true, false, true },
				{ false, false, true, true } };
		boolean[] expected = new boolean[] { false, false, true, true, true, false, true, false, false, true, false,
				true, true, true, false, false };

		assertArrayEquals(matrix.getStrip(image), expected);
		matrix.plot(image);
	}

	@Test
	public void testHiddenTrivial1() {
		LedMatrix matrix = new LedMatrix(6, 6);
		assertEquals(34, matrix.xyToLed(1, 5));

	}

	@Test
	public void testHiddenTrivial2() {
		LedMatrix matrix = new LedMatrix(7, 7);
		assertEquals(22, matrix.xyToLed(5, 3));
	}
}
