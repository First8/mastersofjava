import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapTraversalClientTester extends MapTraversalTester {

	@Override
	protected TestCase[] getTestCases() {

		// create Map0
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("name", "Michael Jackson");

		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("first", "Armin van Buuren");

		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("name", nameMap);

		// create Map1
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("person", personMap);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("city", map1);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("amsterdam", map1);
		map3.put("rotterdam", new LinkedHashMap<>(map1));
		
		
		TestCase[] testCases = new TestCase[4];
		TestCase testCase0 = new TestCase("Position 1: Tiesto", map0, "name",
				"Michael Jackson");
		TestCase testCase1 = new TestCase("Position 2: Armin van Buuren ", map1,
				"person.name.first", "Armin van Buuren");
		
		TestCase testCase2 = new TestCase("Position 3: Armin van Buuren", map2,
				"city.person.name.first", "Armin van Buuren");
		
		TestCase testCase3 = new TestCase("Position 4: Armin van Buuren", map3,
				"amsterdam.person.name.first", "Armin van Buuren");

		testCases[0] = testCase0;
		testCases[1] = testCase1;
		testCases[2] = testCase2;
		testCases[3] = testCase3;
		
		return testCases;
	}

}
