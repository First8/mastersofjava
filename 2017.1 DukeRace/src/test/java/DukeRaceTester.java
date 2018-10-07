import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DukeRaceTester {

	DukeRace instance;

	@Before
	public void setup() {
		instance = new DukeRace();
	}

	@Test
	public void test10_2_2and2_4() {
		assertTrue(instance.equalsAndPrint(instance.racers(10, new Integer[] { 2, 2 }, new Integer[] { 2, 4 }),
				new Integer[] { 1, 0 }));
	}

	@Test
	public void test10_2_2and2_2() {
		assertTrue(instance.equalsAndPrint(instance.racers(10, new Integer[] { 2, 2 }, new Integer[] { 2, 2 }),
				new Integer[] { 0, 1 }));
	}

	@Test
	public void test10_2_4and2_2() {
		assertTrue(instance.equalsAndPrint(instance.racers(10, new Integer[] { 2, 2 }, new Integer[] { 2, 4 }),
				new Integer[] { 1, 0 }));
	}

	@Test
	public void test1_5and20() {
		assertTrue(instance.equalsAndPrint(instance.racers(1, new Integer[] { 5, 10, 15 }, new Integer[] { 20, 10, 5 }),
				new Integer[] { 2, 1, 0 }));
	}

	@Test
	public void test27() {
		assertTrue(instance.equalsAndPrint(instance.racers(27, new Integer[] { 3, 2, 2, 18, 14, 9 },
				new Integer[] { 151, 236, 152, 15, 163, 275 }), new Integer[] { 3, 4, 5, 1, 0, 2 }));
	}
}