import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestZero {

	private static final SumOfCoprimes INSTANCE = new SumOfCoprimes();

	@Test
	public void testZero() {
		assertEquals(0, INSTANCE.execute(0));
		assertEquals(0, INSTANCE.execute(1));
		assertEquals(0, INSTANCE.execute(2));
		assertTrue(INSTANCE.execute(3)>0);
	}
}