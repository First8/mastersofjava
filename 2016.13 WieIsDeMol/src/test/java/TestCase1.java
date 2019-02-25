import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCase1 implements ITestCase {
	
	public String getTestCaseName() { return "Test Case 1"; }
	public String getTestCaseDescription() {
		return "Network with 4 nodes, 1 parcels and 2 contestants.\n"
			+ "The Mole has choosen the least feavarouble routes.";
	}
	
	private final Set<Order> orders = new HashSet<Order>();
	private final Map<Contestant, Route> log = new HashMap<Contestant, Route>();
	
	private final Parcel[] parcels = new Parcel[2];
	private final Location[] locations = new Location[4];
	private final Contestant[] contestants = new Contestant[2];
	
	private Contestant theMole;
	
	public TestCase1() {
		createParcels();
		createLocations();
//		printShortestPaths();
		createShortestPaths();
		createOrders();
		createContestants();
		createLog();
		
		theMole = contestants[1];
	}
	
	public Set<Order> getOrders() {
		return orders;
	}
	
	public Map<Contestant, Route> getLog() {
		return log;
	}
	
	public boolean validateAwnser(Contestant awnser) {
		return theMole.equals(awnser);
	}
	
	private void createParcels() {
		parcels[0] = new Parcel("p1");
		parcels[1] = new Parcel("p2");
	}
	
	private void createLocations() {
		locations[0] = new Location("A");
		locations[1] = new Location("B");
		locations[2] = new Location("C");
		locations[3] = new Location("D");
		
		connectLocations(locations[0], locations[1], 1); //A-B
		connectLocations(locations[0], locations[2], 2); //A-C
		connectLocations(locations[1], locations[3], 1); //B-D
		connectLocations(locations[2], locations[3], 1); //C-D
	}
	
	private void createShortestPaths() {
		// Location 0 (A)
		Map<Location, Location> path0 = new HashMap<Location, Location>();
		Map<Location, Integer> costs0 = new HashMap<Location, Integer>();
		path0.put(locations[0], locations[0]); costs0.put(locations[0], 0);
		path0.put(locations[1], locations[1]); costs0.put(locations[1], 1);
		path0.put(locations[2], locations[2]); costs0.put(locations[2], 2);
		path0.put(locations[3], locations[1]); costs0.put(locations[3], 2);
		locations[0].setShortestPaths(path0, costs0);
		
		// Location 1 (B)
		Map<Location, Location> path1 = new HashMap<Location, Location>();
		Map<Location, Integer> costs1 = new HashMap<Location, Integer>();
		path1.put(locations[0], locations[0]); costs1.put(locations[0], 1);
		path1.put(locations[1], locations[1]); costs1.put(locations[1], 0);
		path1.put(locations[2], locations[3]); costs1.put(locations[2], 2);
		path1.put(locations[3], locations[3]); costs1.put(locations[3], 1);
		locations[1].setShortestPaths(path1, costs1);
		
		// Location 2 (C)
		Map<Location, Location> path2 = new HashMap<Location, Location>();
		Map<Location, Integer> costs2 = new HashMap<Location, Integer>();
		path2.put(locations[0], locations[1]); costs2.put(locations[0], 2);
		path2.put(locations[1], locations[3]); costs2.put(locations[1], 2);
		path2.put(locations[2], locations[2]); costs2.put(locations[2], 0);
		path2.put(locations[3], locations[3]); costs2.put(locations[3], 1);
		locations[2].setShortestPaths(path2, costs2);
		
		// Location 3 (D)
		Map<Location, Location> path3 = new HashMap<Location, Location>();
		Map<Location, Integer> costs3 = new HashMap<Location, Integer>();
		path3.put(locations[0], locations[1]); costs3.put(locations[0], 2);
		path3.put(locations[1], locations[1]); costs3.put(locations[1], 1);
		path3.put(locations[2], locations[2]); costs3.put(locations[2], 1);
		path3.put(locations[3], locations[3]); costs3.put(locations[3], 0);
		locations[3].setShortestPaths(path3, costs3);
	}
	
	private void connectLocations(Location a, Location b, int weight) {
		a.addNeighbor(b, weight);
		b.addNeighbor(a, weight);
	}
	
	private void createOrders() {
		orders.add(new Order(parcels[0], locations[3]));
		orders.add(new Order(parcels[1], locations[3]));
	}
	
	private void createContestants() {
		contestants[0] = new Contestant("Henriet", parcels[0]);
		contestants[1] = new Contestant("Kees", parcels[1]);
	}
	
	private void createLog() {
		List<RouteLocation> routeLocations1 = new ArrayList<RouteLocation>();
		routeLocations1.add(new RouteLocation(locations[0], parcels[0], parcels[1]));
		routeLocations1.add(new RouteLocation(locations[1], parcels[0]));
		routeLocations1.add(new RouteLocation(locations[3], parcels[0]));
		Route route1 = new Route(routeLocations1);
		
		List<RouteLocation> routeLocations2 = new ArrayList<RouteLocation>();
		routeLocations2.add(new RouteLocation(locations[0], parcels[1], parcels[0]));
		routeLocations2.add(new RouteLocation(locations[2], parcels[1]));
		routeLocations2.add(new RouteLocation(locations[3], parcels[1]));
		Route route2 = new Route(routeLocations2);
		
		log.put(contestants[0], route1);
		log.put(contestants[1], route2);
	}
	
	private void printShortestPaths() {
		// Dijkstra at work
		DijkstraUtil.processLocations(locations);

		// Print java output!
		for (int i = 0; i < locations.length; i++) {
			System.out.println("Map<Location, Location> path"+i+" = new HashMap<Location, Location>();");
			System.out.println("Map<Location, Integer> costs"+i+" = new HashMap<Location, Integer>();");
			for(int j = 0; j < locations.length; j++) {
				int n = index(locations[i].getShortestPathToLocation(locations[j]));
				int c = locations[i].getTravelCostsToLocation(locations[j]);
				System.out.println("path"+i+".put(locations["+j+"], locations["+n+"]); costs"+i+".put(locations["+j+"], "+c+");");
			}
			System.out.println("locations["+i+"].setShortestPaths(path"+i+", costs"+i+");");
		}
	}
	
	private int index(Location location) {
		for(int i = 0; i < locations.length; i++) {
			if(locations[i].equals(location)) {
				return i;
			}
		}
		throw new RuntimeException("Shouldn't happen!");
	}
}