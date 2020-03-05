import java.util.Map;

/**
 * Representation of a test case.
 * 
 * @author Patrick Kik
 */
class TestCase {
	/** Non-null. Input for the test case. Map */
	private final Map<String, Object> map;

	/** Non-null. Input for the test case. Key. */
	private final String key;

	/** Valid output value of this test case. */
	private final String value;
	
	/** Name of the test case. */
	private final String name;

	/**
	 * Constructor: create a new TestCase.
	 * 
	 * @param input
	 *            Input.
	 * @param firstExpectedOutput
	 *            First expected output value.
	 * @param expectedOutput
	 *            Optional additional expected output valeues.
	 */
	TestCase(String name, Map<String, Object> map, String key, String value) {
		super();
		this.name = name;
		this.map = map;
		this.key = key;
		this.value = value;
	}

	/**
	 * Performs the actual test.
	 * 
	 * @return whether the test case completed succesfully.
	 */
	boolean performTest() {
		boolean success;
		try {
			System.out.println("Map: " + this.map);
			System.out.println("Key: " + this.key);
			MapTraversal traversal = new MapTraversalImpl();
			String output = traversal.getValue(map, key);
			System.out.println("Output: " + output);
			success = (this.value == null && output == null) || this.value.equals(output);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	/**
	 * @return name of the test case.
	 */
	String getName() {
		return name;
	}

}