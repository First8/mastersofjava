import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestHidden {
	private static final SumOfCoprimes INSTANCE = new SumOfCoprimes();

	@Test
	public void test16() {
		assertEquals(63, INSTANCE.execute(16));
	}

	@Test
	public void test19() {
		assertEquals(170, INSTANCE.execute(19));
	}

	@Test
	public void test199() {
		assertEquals(19700, INSTANCE.execute(199));
	}

}