import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SwapPairBitsTest {

	SwapPairBits instance;

	@Before
	public void setup() {
		instance = new SwapPairBits();
	}

	@Test
	public void test565() {
		assertTrue(instance.execute(565, 9, 3) == 817);
	}

	@Test
	public void test1() {
		assertTrue(instance.execute(1, 1, 1) == 1);
	}

	@Test
	public void test2() {
		assertTrue(instance.execute(2, 2, 1) == 1);
	}

	@Test
	public void test512_as_output() {
		long temp = 4294967296L;
		assertTrue(instance.execute(temp, 33, 10) == 512);
	}

	@Test
	public void test512_as_input() {
		assertTrue(instance.execute(512, 33, 10) == 4294967296L);
	}

	@Test
	public void testBig_Numbers() {
		assertTrue(instance.execute(123456789, 20, 10) == 122933013);
	}

}