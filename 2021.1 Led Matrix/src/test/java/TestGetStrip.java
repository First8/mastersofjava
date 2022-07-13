import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestGetStrip {

	@Test
	public void testStrip1() {
		LedMatrix matrix = new LedMatrix(2, 2);

		boolean[][] image = { { true, false }, { false, true } };
		boolean[] expected = new boolean[] { true, false, true, false };
		matrix.plot(image);

		assertArrayEquals(matrix.getStrip(image), expected);
	}

	@Test
	public void testStrip2() {
		LedMatrix matrix = new LedMatrix(3, 3);

		boolean[][] image = { { false, true, true }, { true, false, true }, { true, true, false } };
		boolean[] expected = new boolean[] { false, true, true, true, false, true, true, true, false };
		matrix.plot(image);

		assertArrayEquals(matrix.getStrip(image), expected);
	}

	@Test
	public void testStrip3() {
		LedMatrix matrix = new LedMatrix(4, 4);

		boolean[][] image = { { false, false, true, false }, { false, false, false, true },
				{ true, false, false, false }, { false, true, false, false } };
		boolean[] expected = new boolean[] { false, false, true, false, true, false, false, false, true, false, false,
				false, false, false, true, false };
		matrix.plot(image);

		assertArrayEquals(matrix.getStrip(image), expected);
	}

	@Test
	public void testStrip4() {
		LedMatrix matrix = new LedMatrix(16, 16);

		boolean[][] image = {
				{ false, false, false, false, false, false, false, false, true, false, false, false, false, false,
						false, false },
				{ false, false, false, false, false, false, false, true, true, false, false, false, false, false, false,
						false },
				{ false, false, false, false, false, false, true, true, true, true, false, false, false, false, false,
						false },
				{ false, false, false, false, false, true, true, true, true, true, true, false, false, false, false,
						false },
				{ false, false, false, false, false, false, true, true, true, true, false, false, false, false, false,
						false },
				{ false, false, false, false, false, true, true, true, true, true, true, false, false, false, false,
						false },
				{ false, false, false, false, true, true, true, true, true, true, true, true, false, false, false,
						false },
				{ false, false, false, false, false, true, true, true, true, true, true, false, false, false, false,
						false },
				{ false, false, false, false, true, true, true, true, true, true, true, true, false, false, false,
						false },
				{ false, false, false, true, true, true, true, true, true, true, true, true, true, false, false,
						false },
				{ false, false, false, false, true, true, true, true, true, true, true, true, false, false, false,
						false },
				{ false, false, false, true, true, true, true, true, true, true, true, true, true, false, false,
						false },
				{ false, false, true, true, true, true, true, true, true, true, true, true, true, true, false, false },
				{ false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false },
				{ false, false, false, false, false, false, false, true, true, false, false, false, false, false, false,
						false },
				{ false, false, false, false, false, false, false, true, true, false, false, false, false, false, false,
						false } };

		boolean[] expected = new boolean[] { false, false, false, false, false, false, false, false, true, false, false,
				false, false, false, false, false, false, false, false, false, false, false, false, true, true, false,
				false, false, false, false, false, false, false, false, false, false, false, false, true, true, true,
				true, false, false, false, false, false, false, false, false, false, false, false, true, true, true,
				true, true, true, false, false, false, false, false, false, false, false, false, false, false, true,
				true, true, true, false, false, false, false, false, false, false, false, false, false, false, true,
				true, true, true, true, true, false, false, false, false, false, false, false, false, false, true, true,
				true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, true,
				true, true, true, true, true, false, false, false, false, false, false, false, false, false, true, true,
				true, true, true, true, true, true, false, false, false, false, false, false, false, true, true, true,
				true, true, true, true, true, true, true, false, false, false, false, false, false, false, true, true,
				true, true, true, true, true, true, false, false, false, false, false, false, false, true, true, true,
				true, true, true, true, true, true, true, false, false, false, false, false, true, true, true, true,
				true, true, true, true, true, true, true, true, false, false, false, true, true, true, true, true, true,
				true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false,
				true, true, false, false, false, false, false, false, false, false, false, false, false, false, false,
				false, true, true, false, false, false, false, false, false, false };

		matrix.plot(image);

		assertArrayEquals(matrix.getStrip(image), expected);
	}
}
