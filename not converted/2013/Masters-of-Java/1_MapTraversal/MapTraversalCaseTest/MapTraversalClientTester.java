import java.util.HashMap;
import java.util.Map;

public class MapTraversalClientTester extends MapTraversalTester {

	@Override
	protected TestCase[] getTestCases() {
		TestCase[] testCases = new TestCase[2];

		String name;
		Map<String, Object> map;
		String key;
		String value;
		TestCase testCase;
		int index = 0;
		
		name = "Small map.";
		map = new HashMap<String, Object>();
		map.put("name", "Patrick");
		key = "name";
		value = "Patrick";
		testCase = new TestCase(name, map, key, value);
		testCases[index++] = testCase;
		
		name = "Large map.";
		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("first", "Patrick");
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("name", nameMap);
		map = new HashMap<String, Object>();
		map.put("person", personMap);
		key = "person.name.first";
		value = "Patrick";
		testCase = new TestCase(name, map, key, value);
		testCases[index++] = testCase;		
		
		return testCases;
	}

}
