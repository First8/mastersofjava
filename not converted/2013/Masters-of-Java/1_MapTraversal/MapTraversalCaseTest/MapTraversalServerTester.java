import java.util.HashMap;
import java.util.Map;


public class MapTraversalServerTester extends MapTraversalTester {

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
		Map<String, Object> capitalMap = new HashMap<String, Object>();
		capitalMap.put("capital", "Amsterdam");
		map = new HashMap<String, Object>();
		map.put("country", capitalMap);
		key = "country.capital";
		value = "Amsterdam";
		testCase = new TestCase(name, map, key, value);
		testCases[index++] = testCase;
		
		name = "Large map.";
		Map<String, Object> amsterdamMap = new HashMap<String, Object>();
		amsterdamMap.put("count", "801542");
		Map<String, Object> netherlandsMap = new HashMap<String, Object>();
		netherlandsMap.put("amsterdam", amsterdamMap);
		Map<String, Object> worldMap = new HashMap<String, Object>();
		worldMap.put("netherlands", netherlandsMap);
		map = new HashMap<String, Object>();
		map.put("world", worldMap);
		key = "world.netherlands.amsterdam.count";
		value = "801542";
		testCase = new TestCase(name, map, key, value);
		testCases[index++] = testCase;		
		
		return testCases;
	}

}
