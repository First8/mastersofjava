import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class IntegerWalkTest {

	IntegerWalk instance;

	@Before
	public void setup() {
		instance = new IntegerWalk();
	}

	@Test
	public void testX_equals_Y_always_False() {
		boolean allFalse = true;
		for (int x = 1; x < 10; x++) {
			allFalse &= !instance.evenOdd(x, x);
		}
		assertTrue(allFalse);
	}

	@Test
	public void testStart_always_True() {
		boolean allTrue = true;
		for (int x = 1; x < 10; x++) {
			allTrue &= instance.evenOdd(1, x) == (x % 2 == 0);
		}
		assertTrue(allTrue);
	}

}