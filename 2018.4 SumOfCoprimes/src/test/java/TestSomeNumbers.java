import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSomeNumbers {
	private static final SumOfCoprimes INSTANCE = new SumOfCoprimes();

	@Test
	public void test42() {
		assertEquals(251, INSTANCE.execute(42));
	}

	@Test
	public void test41() {
		assertEquals(819, INSTANCE.execute(41));
	}

	@Test
	public void test8() {
		assertEquals(15, INSTANCE.execute(8));
	}

	@Test
	public void test11() {
		assertEquals(54, INSTANCE.execute(11));
	}

	@Test
	public void test13() {
		assertEquals(77, INSTANCE.execute(13));
	}


}