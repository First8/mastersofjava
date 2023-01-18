import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HiddenTests {

	private Comparator<String> comparator;

	@Before
	public void setUp() {
		comparator = new HumanSensibleSorter();
	}

	@Test
	public void test() {
		List<String> expected = List.of("1", "A", "Alpha 2A-8000", "Callisto Morphamax 700", "FireBolt 15CE",
				"FireBolt 15ME", "Serial 19223372036854775807", "Serial 299223372036854775807");
		List<String> input = Arrays.asList("1",  "Callisto Morphamax 700", "FireBolt 15CE", "Serial 299223372036854775807",
				"FireBolt 15ME", "Serial 19223372036854775807", "A", "Alpha 2A-8000");
		Collections.sort(input, comparator);
		assertEquals(expected, input);
	}

}
