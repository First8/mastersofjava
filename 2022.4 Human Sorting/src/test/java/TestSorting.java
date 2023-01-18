import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestSorting {

	private Comparator<String> comparator;

	@Before
	public void setUp() {
		comparator = new HumanSensibleSorter();
	}

	@Test
	public void test() {
		List<String> expected = List.of("1", "2", "A", "AA", "B");
		List<String> input = Arrays.asList("2", "1", "AA", "B", "A");
		Collections.sort(input, comparator);
		assertEquals(expected, input);
	}

	@Test
	public void test2() {

		List<String> expected = List.of("Alpha 2A-900", "Alpha 2A-8000", "Callisto Morphamax 700",
				"Callisto Morphamax 6000 SE", "Callisto Morphamax 6000 SE2", "Callisto Morphamax 7000",
				"Xiph Xlater 300", "Xiph Xlater 300+", "Xiph Xlater 2000", "Xiph Xlater 10000");
		List<String> input = Arrays.asList("Xiph Xlater 300", "Alpha 2A-8000", "Callisto Morphamax 6000 SE",
				"Callisto Morphamax 700", "Callisto Morphamax 6000 SE2", "Xiph Xlater 300+", "Xiph Xlater 10000",
				"Callisto Morphamax 7000", "Xiph Xlater 2000", "Alpha 2A-900");

		Collections.sort(input, comparator);
		System.out.println(input);
		assertEquals(expected, input);
	}

	@Test
	public void test3() {

		List<String> expected = List.of("FireBolt", "FireBolt 15CE", "FireBolt 15ME", "Nimbus 600", "Nimbus 600XP",
				"Nimbus 2000", "Nimbus 2001");
		List<String> input = Arrays.asList("Nimbus 2000", "FireBolt 15ME", "Nimbus 600XP", "FireBolt", "FireBolt 15CE",
				"Nimbus 600", "Nimbus 2001");

		Collections.sort(input, comparator);
		assertEquals(expected, input);
	}

	@Test
	public void test4() {

		List<String> expected = List.of("Windows 7", "Windows 8", "Windows 10", "Windows 95", "Windows 98",
				"Windows 2000", "Windows ME", "Windows Vista", "Windows XP");
		List<String> input = Arrays.asList("Windows 95", "Windows 98", "Windows 2000", "Windows ME", "Windows XP",
				"Windows Vista", "Windows 7", "Windows 8", "Windows 10");

		Collections.sort(input, comparator);
		assertEquals(expected, input);
	}

	@Test
	public void testLong() {
		List<String> expected = List.of("Serial 9223372036854775807", "Serial 19223372036854775807",
				"Serial 299223372036854775807");
		List<String> input = Arrays.asList("Serial 19223372036854775807", "Serial 299223372036854775807",
				"Serial 9223372036854775807");
		Collections.sort(input, comparator);
		assertEquals(expected, input);
	}

}
