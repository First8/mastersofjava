import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestTranslate {

	@Test
	public void testTrivial1() {
		LedMatrix matrix = new LedMatrix(1, 1);
		assertEquals(0, matrix.xyToLed(0, 0));
	}

	@Test
	public void testTrivial3() {
		LedMatrix matrix = new LedMatrix(3, 3);
		assertEquals(4, matrix.xyToLed(1, 1));
	}

	@Test
	public void testTrivial5() {
		LedMatrix matrix = new LedMatrix(5, 5);
		assertEquals(16, matrix.xyToLed(3, 3));
	}

	@Test
	public void testTrivial7() {
		LedMatrix matrix = new LedMatrix(7, 7);
		assertEquals(39, matrix.xyToLed(2, 5));
	}

	@Test
	public void testComplex() {
		LedMatrix matrix = new LedMatrix(16, 16);
		assertEquals(236, matrix.xyToLed(12, 14));
	}
}
