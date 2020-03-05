import java.util.LinkedList;

import nl.moj.model.Tester;

public class RouteplannerTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"TestCase Short",
			"TestCase Long1",
			"TestCase Long2",
			"TestCase Invalid"
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		//
		// TODO: Generate useful test description.
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result=false;
		//
		// Create a new Instance for each test.
		//
		try {	
			RouteplannerImpl instance = new RouteplannerImpl();
			createNetwork(instance);
			switch (nr) {
				case 0: result = testAsIs(instance); break;
				case 1: result = testTo5(instance); break;
				case 2: result = testTo10(instance); break;
				case 3: result = testInvalid(instance); break;
			}
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
			ex.printStackTrace();
			//
			result = false;
		}
		//
		return result;
	}
	
	private void createNetwork(RouteplannerImpl route) {
		for (int i = 0; i < 12; i++) {
			route.addNode("Node_" + i);
		}

		route.addLink("Node_0", "Node_1", 85);
		route.addLink("Node_0", "Node_2", 217);
		route.addLink("Node_0", "Node_4", 173);
		route.addLink("Node_1", "Node_10", 600);
		route.addLink("Node_2", "Node_6", 186);
		route.addLink("Node_2", "Node_7", 103);
		route.addLink("Node_3", "Node_7", 183);
		route.addLink("Node_4", "Node_9", 502);
		route.addLink("Node_5", "Node_8", 250);
		route.addLink("Node_8", "Node_9", 84);
		route.addLink("Node_7", "Node_9", 167);
		route.addLink("Node_9", "Node_10", 40);
	}
	
	private boolean testAsIs(RouteplannerImpl route) {
		return testPath(route, route.getNodes().get(0), null, 0);
	}

	private boolean testTo5(RouteplannerImpl route) {	
		LinkedList<Node> cor_path = new LinkedList<Node>();
		cor_path.add(new Node("Node_0"));
		cor_path.add(new Node("Node_2"));
		cor_path.add(new Node("Node_7"));
		cor_path.add(new Node("Node_9"));
		cor_path.add(new Node("Node_8"));
		cor_path.add(new Node("Node_5"));
		return testPath(route, route.getNodes().get(5), cor_path, 821);
	}
	
	private boolean testTo10(RouteplannerImpl route) {
		LinkedList<Node> cor_path = new LinkedList<Node>();
		cor_path.add(new Node("Node_0"));
		cor_path.add(new Node("Node_2"));
		cor_path.add(new Node("Node_7"));
		cor_path.add(new Node("Node_9"));
		cor_path.add(new Node("Node_10"));
		return testPath(route, route.getNodes().get(10), cor_path, 527);
	}
	
	private boolean testInvalid(RouteplannerImpl route) {
		return testPath(route, route.getNodes().get(11), null, Integer.MAX_VALUE);
	}
	
	private boolean testPath(RouteplannerImpl route, Node to, LinkedList<Node> cor_path, int distance) {
		LinkedList<Node> path;		
		// Lets check from location Node_0 to "to"
		route.execute(route.getNodes().get(0));
		path = route.getPath(to);
		if (route.getShortestDistance(to) != distance) {
			System.out.println("The distance is incorrect." + route.getShortestDistance(to));
		} else if ((cor_path == null && path == null)||(cor_path.equals(path))) {
			System.out.println("Test is succesfull");
			return true;
		} else {
			System.out.println("The path is incorrect." + route.getShortestDistance(to)); 
		}
		return false;	
	}
	
}
