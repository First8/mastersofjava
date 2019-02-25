import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SmallTestCase implements ITestCase {
	
	public String getTestCaseName() { return "Small test case"; }
	public String getTestCaseDescription() {
		return "Network with 7 nodes, 3 parcels and 3 contestants.\n"
			+ "The Mole has choosen the least feavarouble routes.";
	}
	
	private final Set<Order> orders = new HashSet<Order>();
	private final Map<Contestant, Route> log = new HashMap<Contestant, Route>();
	
	private final Parcel[] parcels = new Parcel[3];
	private final Location[] locations = new Location[7];
	private final Contestant[] contestants = new Contestant[3];
	
	private Contestant theMole;
	
	public SmallTestCase() {
		createParcels();
		createLocations();
//		printShortestPaths();
		createShortestPaths();
		createOrders();
		createContestants();
		createLog();
		
		theMole = contestants[2];
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
		parcels[2] = new Parcel("p3");
	}
	
	private void createLocations() {
		locations[0] = new Location("A");
		locations[1] = new Location("B");
		locations[2] = new Location("C");
		locations[3] = new Location("D");
		locations[4] = new Location("E");
		locations[5] = new Location("F");
		locations[6] = new Location("G");
		
		connectLocations(locations[0], locations[1], 1); //A-B
		connectLocations(locations[1], locations[2], 1); //B-C
		connectLocations(locations[1], locations[3], 4); //B-D
		connectLocations(locations[2], locations[4], 1); //C-E
		connectLocations(locations[2], locations[5], 3); //C-F
		connectLocations(locations[2], locations[3], 2); //C-D
		connectLocations(locations[3], locations[6], 10);//D-G
		connectLocations(locations[5], locations[6], 3); //F-G
	}
	
	private void createShortestPaths() {
		// Location 0 (A)
		Map<Location, Location> path0 = new HashMap<Location, Location>();
		Map<Location, Integer> costs0 = new HashMap<Location, Integer>();
		path0.put(locations[0], locations[0]); costs0.put(locations[0], 0);
		path0.put(locations[1], locations[1]); costs0.put(locations[1], 1);
		path0.put(locations[2], locations[1]); costs0.put(locations[2], 2);
		path0.put(locations[3], locations[1]); costs0.put(locations[3], 4);
		path0.put(locations[4], locations[1]); costs0.put(locations[4], 3);
		path0.put(locations[5], locations[1]); costs0.put(locations[5], 5);
		path0.put(locations[6], locations[1]); costs0.put(locations[6], 8);
		locations[0].setShortestPaths(path0, costs0);
		
		// Location 1 (B)
		Map<Location, Location> path1 = new HashMap<Location, Location>();
		Map<Location, Integer> costs1 = new HashMap<Location, Integer>();
		path1.put(locations[0], locations[0]); costs1.put(locations[0], 1);
		path1.put(locations[1], locations[1]); costs1.put(locations[1], 0);
		path1.put(locations[2], locations[2]); costs1.put(locations[2], 1);
		path1.put(locations[3], locations[2]); costs1.put(locations[3], 3);
		path1.put(locations[4], locations[2]); costs1.put(locations[4], 2);
		path1.put(locations[5], locations[2]); costs1.put(locations[5], 4);
		path1.put(locations[6], locations[2]); costs1.put(locations[6], 7);
		locations[1].setShortestPaths(path1, costs1);
		
		// Location 2 (C)
		Map<Location, Location> path2 = new HashMap<Location, Location>();
		Map<Location, Integer> costs2 = new HashMap<Location, Integer>();
		path2.put(locations[0], locations[1]); costs2.put(locations[0], 2);
		path2.put(locations[1], locations[1]); costs2.put(locations[1], 1);
		path2.put(locations[2], locations[2]); costs2.put(locations[2], 0);
		path2.put(locations[3], locations[3]); costs2.put(locations[3], 2);
		path2.put(locations[4], locations[4]); costs2.put(locations[4], 1);
		path2.put(locations[5], locations[5]); costs2.put(locations[5], 3);
		path2.put(locations[6], locations[5]); costs2.put(locations[6], 6);
		locations[2].setShortestPaths(path2, costs2);
		
		// Location 3 (D)
		Map<Location, Location> path3 = new HashMap<Location, Location>();
		Map<Location, Integer> costs3 = new HashMap<Location, Integer>();
		path3.put(locations[0], locations[2]); costs3.put(locations[0], 4);
		path3.put(locations[1], locations[2]); costs3.put(locations[1], 3);
		path3.put(locations[2], locations[2]); costs3.put(locations[2], 2);
		path3.put(locations[3], locations[3]); costs3.put(locations[3], 0);
		path3.put(locations[4], locations[2]); costs3.put(locations[4], 3);
		path3.put(locations[5], locations[2]); costs3.put(locations[5], 5);
		path3.put(locations[6], locations[2]); costs3.put(locations[6], 8);
		locations[3].setShortestPaths(path3, costs3);
		
		// Location 4 (E)
		Map<Location, Location> path4 = new HashMap<Location, Location>();
		Map<Location, Integer> costs4 = new HashMap<Location, Integer>();
		path4.put(locations[0], locations[2]); costs4.put(locations[0], 3);
		path4.put(locations[1], locations[2]); costs4.put(locations[1], 2);
		path4.put(locations[2], locations[2]); costs4.put(locations[2], 1);
		path4.put(locations[3], locations[2]); costs4.put(locations[3], 3);
		path4.put(locations[4], locations[4]); costs4.put(locations[4], 0);
		path4.put(locations[5], locations[2]); costs4.put(locations[5], 4);
		path4.put(locations[6], locations[2]); costs4.put(locations[6], 7);
		locations[4].setShortestPaths(path4, costs4);
		
		// Location 5 (F)
		Map<Location, Location> path5 = new HashMap<Location, Location>();
		Map<Location, Integer> costs5 = new HashMap<Location, Integer>();
		path5.put(locations[0], locations[2]); costs5.put(locations[0], 5);
		path5.put(locations[1], locations[2]); costs5.put(locations[1], 4);
		path5.put(locations[2], locations[2]); costs5.put(locations[2], 3);
		path5.put(locations[3], locations[2]); costs5.put(locations[3], 5);
		path5.put(locations[4], locations[2]); costs5.put(locations[4], 4);
		path5.put(locations[5], locations[5]); costs5.put(locations[5], 0);
		path5.put(locations[6], locations[6]); costs5.put(locations[6], 3);
		locations[5].setShortestPaths(path5, costs5);
		
		// Location 6 (G)
		Map<Location, Location> path6 = new HashMap<Location, Location>();
		Map<Location, Integer> costs6 = new HashMap<Location, Integer>();
		path6.put(locations[0], locations[5]); costs6.put(locations[0], 8);
		path6.put(locations[1], locations[5]); costs6.put(locations[1], 7);
		path6.put(locations[2], locations[5]); costs6.put(locations[2], 6);
		path6.put(locations[3], locations[5]); costs6.put(locations[3], 8);
		path6.put(locations[4], locations[5]); costs6.put(locations[4], 7);
		path6.put(locations[5], locations[5]); costs6.put(locations[5], 6);
		path6.put(locations[6], locations[6]); costs6.put(locations[6], 0);
		locations[6].setShortestPaths(path6, costs6);
	}
	
	private void connectLocations(Location a, Location b, int weight) {
		a.addNeighbor(b, weight);
		b.addNeighbor(a, weight);
	}
	
	private void createOrders() {
		orders.add(new Order(parcels[0], locations[0]));
		orders.add(new Order(parcels[1], locations[4]));
		orders.add(new Order(parcels[2], locations[6]));
	}
	
	private void createContestants() {
		contestants[0] = new Contestant("Piet", parcels[0]);
		contestants[1] = new Contestant("Kees", parcels[1]);
		contestants[2] = new Contestant("Harry", parcels[2]);
	}
	
	private void createLog() {
		List<RouteLocation> routeLocations1 = new ArrayList<RouteLocation>();
		routeLocations1.add(new RouteLocation(locations[6]));
		routeLocations1.add(new RouteLocation(locations[3], parcels[0]));
		routeLocations1.add(new RouteLocation(locations[2], parcels[0]));
		routeLocations1.add(new RouteLocation(locations[4], parcels[0]));
		routeLocations1.add(new RouteLocation(locations[2], parcels[0]));
		routeLocations1.add(new RouteLocation(locations[1], parcels[0]));
		routeLocations1.add(new RouteLocation(locations[0], parcels[0]));
		Route route1 = new Route(routeLocations1);
		
		List<RouteLocation> routeLocations2 = new ArrayList<RouteLocation>();
		routeLocations2.add(new RouteLocation(locations[1]));
		routeLocations2.add(new RouteLocation(locations[2]));
		routeLocations2.add(new RouteLocation(locations[3], parcels[1], parcels[2]));
		routeLocations2.add(new RouteLocation(locations[2], parcels[1]));
		routeLocations2.add(new RouteLocation(locations[4], parcels[1]));
		Route route2 = new Route(routeLocations2);
		
		List<RouteLocation> routeLocations3 = new ArrayList<RouteLocation>();
		routeLocations3.add(new RouteLocation(locations[5]));
		routeLocations3.add(new RouteLocation(locations[2]));
		routeLocations3.add(new RouteLocation(locations[4], parcels[2], parcels[1]));
		routeLocations3.add(new RouteLocation(locations[2], parcels[2]));
		routeLocations3.add(new RouteLocation(locations[3], parcels[2]));
		routeLocations3.add(new RouteLocation(locations[6], parcels[2]));
		
		Route route3 = new Route(routeLocations3);
		
		log.put(contestants[0], route1);
		log.put(contestants[1], route2);
		log.put(contestants[2], route3);
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