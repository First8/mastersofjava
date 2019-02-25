import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCase2 implements ITestCase {
	
	public String getTestCaseName() { return "Test Case 2"; }
	public String getTestCaseDescription() {
		return "Network with 13 nodes, 2 parcels and 2 contestants.\n"
			+ "The Mole has choosen the least feavarouble routes.";
	}
	
	private final Set<Order> orders = new HashSet<Order>();
	private final Map<Contestant, Route> log = new HashMap<Contestant, Route>();
	
	private final Parcel[] parcels = new Parcel[2];
	private final Location[] locations = new Location[14];
	private final Contestant[] contestants = new Contestant[2];
	
	private Contestant theMole;
	
	public TestCase2() {
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
		locations[4] = new Location("E");
		locations[5] = new Location("F");
		locations[6] = new Location("G");
		locations[7] = new Location("H");
		locations[8] = new Location("I");
		locations[9] = new Location("J");
		locations[10] = new Location("K");
		locations[11] = new Location("L");
		locations[12] = new Location("M");
		locations[13] = new Location("X");
		
		connectLocations(locations[0], locations[1], 1);
		connectLocations(locations[0], locations[2], 2);
		connectLocations(locations[0], locations[3], 3);
		connectLocations(locations[0], locations[4], 4);
		
		connectLocations(locations[1], locations[5], 1);
		connectLocations(locations[5], locations[6], 1);
		connectLocations(locations[6], locations[7], 1);
		connectLocations(locations[7], locations[8], 1);
		connectLocations(locations[8], locations[9], 1);
		
		connectLocations(locations[2], locations[5], 1);
		connectLocations(locations[3], locations[5], 1);
		connectLocations(locations[4], locations[5], 1);
		
		connectLocations(locations[2], locations[13], 2);
		
		connectLocations(locations[13], locations[6], 1);
		connectLocations(locations[13], locations[10], 2);
		
		connectLocations(locations[10], locations[7], 1);
		connectLocations(locations[10], locations[11], 2);
		
		connectLocations(locations[11], locations[8], 1);
		connectLocations(locations[11], locations[12], 2);
		
		connectLocations(locations[12], locations[9], 1);
		
	}
	
	private void createShortestPaths() {
		Map<Location, Location> path0 = new HashMap<Location, Location>();
		Map<Location, Integer> costs0 = new HashMap<Location, Integer>();
		path0.put(locations[0], locations[0]); costs0.put(locations[0], 0);
		path0.put(locations[1], locations[1]); costs0.put(locations[1], 1);
		path0.put(locations[2], locations[2]); costs0.put(locations[2], 2);
		path0.put(locations[3], locations[3]); costs0.put(locations[3], 3);
		path0.put(locations[4], locations[1]); costs0.put(locations[4], 3);
		path0.put(locations[5], locations[1]); costs0.put(locations[5], 2);
		path0.put(locations[6], locations[1]); costs0.put(locations[6], 3);
		path0.put(locations[7], locations[1]); costs0.put(locations[7], 4);
		path0.put(locations[8], locations[1]); costs0.put(locations[8], 5);
		path0.put(locations[9], locations[1]); costs0.put(locations[9], 6);
		path0.put(locations[10], locations[1]); costs0.put(locations[10], 5);
		path0.put(locations[11], locations[1]); costs0.put(locations[11], 6);
		path0.put(locations[12], locations[1]); costs0.put(locations[12], 7);
		path0.put(locations[13], locations[2]); costs0.put(locations[13], 4);
		locations[0].setShortestPaths(path0, costs0);
		Map<Location, Location> path1 = new HashMap<Location, Location>();
		Map<Location, Integer> costs1 = new HashMap<Location, Integer>();
		path1.put(locations[0], locations[0]); costs1.put(locations[0], 1);
		path1.put(locations[1], locations[1]); costs1.put(locations[1], 0);
		path1.put(locations[2], locations[5]); costs1.put(locations[2], 2);
		path1.put(locations[3], locations[5]); costs1.put(locations[3], 2);
		path1.put(locations[4], locations[5]); costs1.put(locations[4], 2);
		path1.put(locations[5], locations[5]); costs1.put(locations[5], 1);
		path1.put(locations[6], locations[5]); costs1.put(locations[6], 2);
		path1.put(locations[7], locations[5]); costs1.put(locations[7], 3);
		path1.put(locations[8], locations[5]); costs1.put(locations[8], 4);
		path1.put(locations[9], locations[5]); costs1.put(locations[9], 5);
		path1.put(locations[10], locations[5]); costs1.put(locations[10], 4);
		path1.put(locations[11], locations[5]); costs1.put(locations[11], 5);
		path1.put(locations[12], locations[5]); costs1.put(locations[12], 6);
		path1.put(locations[13], locations[5]); costs1.put(locations[13], 3);
		locations[1].setShortestPaths(path1, costs1);
		Map<Location, Location> path2 = new HashMap<Location, Location>();
		Map<Location, Integer> costs2 = new HashMap<Location, Integer>();
		path2.put(locations[0], locations[0]); costs2.put(locations[0], 2);
		path2.put(locations[1], locations[5]); costs2.put(locations[1], 2);
		path2.put(locations[2], locations[2]); costs2.put(locations[2], 0);
		path2.put(locations[3], locations[5]); costs2.put(locations[3], 2);
		path2.put(locations[4], locations[5]); costs2.put(locations[4], 2);
		path2.put(locations[5], locations[5]); costs2.put(locations[5], 1);
		path2.put(locations[6], locations[5]); costs2.put(locations[6], 2);
		path2.put(locations[7], locations[5]); costs2.put(locations[7], 3);
		path2.put(locations[8], locations[5]); costs2.put(locations[8], 4);
		path2.put(locations[9], locations[5]); costs2.put(locations[9], 5);
		path2.put(locations[10], locations[13]); costs2.put(locations[10], 4);
		path2.put(locations[11], locations[5]); costs2.put(locations[11], 5);
		path2.put(locations[12], locations[5]); costs2.put(locations[12], 6);
		path2.put(locations[13], locations[13]); costs2.put(locations[13], 2);
		locations[2].setShortestPaths(path2, costs2);
		Map<Location, Location> path3 = new HashMap<Location, Location>();
		Map<Location, Integer> costs3 = new HashMap<Location, Integer>();
		path3.put(locations[0], locations[0]); costs3.put(locations[0], 3);
		path3.put(locations[1], locations[5]); costs3.put(locations[1], 2);
		path3.put(locations[2], locations[5]); costs3.put(locations[2], 2);
		path3.put(locations[3], locations[3]); costs3.put(locations[3], 0);
		path3.put(locations[4], locations[5]); costs3.put(locations[4], 2);
		path3.put(locations[5], locations[5]); costs3.put(locations[5], 1);
		path3.put(locations[6], locations[5]); costs3.put(locations[6], 2);
		path3.put(locations[7], locations[5]); costs3.put(locations[7], 3);
		path3.put(locations[8], locations[5]); costs3.put(locations[8], 4);
		path3.put(locations[9], locations[5]); costs3.put(locations[9], 5);
		path3.put(locations[10], locations[5]); costs3.put(locations[10], 4);
		path3.put(locations[11], locations[5]); costs3.put(locations[11], 5);
		path3.put(locations[12], locations[5]); costs3.put(locations[12], 6);
		path3.put(locations[13], locations[5]); costs3.put(locations[13], 3);
		locations[3].setShortestPaths(path3, costs3);
		Map<Location, Location> path4 = new HashMap<Location, Location>();
		Map<Location, Integer> costs4 = new HashMap<Location, Integer>();
		path4.put(locations[0], locations[5]); costs4.put(locations[0], 3);
		path4.put(locations[1], locations[5]); costs4.put(locations[1], 2);
		path4.put(locations[2], locations[5]); costs4.put(locations[2], 2);
		path4.put(locations[3], locations[5]); costs4.put(locations[3], 2);
		path4.put(locations[4], locations[4]); costs4.put(locations[4], 0);
		path4.put(locations[5], locations[5]); costs4.put(locations[5], 1);
		path4.put(locations[6], locations[5]); costs4.put(locations[6], 2);
		path4.put(locations[7], locations[5]); costs4.put(locations[7], 3);
		path4.put(locations[8], locations[5]); costs4.put(locations[8], 4);
		path4.put(locations[9], locations[5]); costs4.put(locations[9], 5);
		path4.put(locations[10], locations[5]); costs4.put(locations[10], 4);
		path4.put(locations[11], locations[5]); costs4.put(locations[11], 5);
		path4.put(locations[12], locations[5]); costs4.put(locations[12], 6);
		path4.put(locations[13], locations[5]); costs4.put(locations[13], 3);
		locations[4].setShortestPaths(path4, costs4);
		Map<Location, Location> path5 = new HashMap<Location, Location>();
		Map<Location, Integer> costs5 = new HashMap<Location, Integer>();
		path5.put(locations[0], locations[1]); costs5.put(locations[0], 2);
		path5.put(locations[1], locations[1]); costs5.put(locations[1], 1);
		path5.put(locations[2], locations[2]); costs5.put(locations[2], 1);
		path5.put(locations[3], locations[3]); costs5.put(locations[3], 1);
		path5.put(locations[4], locations[4]); costs5.put(locations[4], 1);
		path5.put(locations[5], locations[5]); costs5.put(locations[5], 0);
		path5.put(locations[6], locations[6]); costs5.put(locations[6], 1);
		path5.put(locations[7], locations[6]); costs5.put(locations[7], 2);
		path5.put(locations[8], locations[6]); costs5.put(locations[8], 3);
		path5.put(locations[9], locations[6]); costs5.put(locations[9], 4);
		path5.put(locations[10], locations[6]); costs5.put(locations[10], 3);
		path5.put(locations[11], locations[6]); costs5.put(locations[11], 4);
		path5.put(locations[12], locations[6]); costs5.put(locations[12], 5);
		path5.put(locations[13], locations[6]); costs5.put(locations[13], 2);
		locations[5].setShortestPaths(path5, costs5);
		Map<Location, Location> path6 = new HashMap<Location, Location>();
		Map<Location, Integer> costs6 = new HashMap<Location, Integer>();
		path6.put(locations[0], locations[5]); costs6.put(locations[0], 3);
		path6.put(locations[1], locations[5]); costs6.put(locations[1], 2);
		path6.put(locations[2], locations[5]); costs6.put(locations[2], 2);
		path6.put(locations[3], locations[5]); costs6.put(locations[3], 2);
		path6.put(locations[4], locations[5]); costs6.put(locations[4], 2);
		path6.put(locations[5], locations[5]); costs6.put(locations[5], 1);
		path6.put(locations[6], locations[6]); costs6.put(locations[6], 0);
		path6.put(locations[7], locations[7]); costs6.put(locations[7], 1);
		path6.put(locations[8], locations[7]); costs6.put(locations[8], 2);
		path6.put(locations[9], locations[7]); costs6.put(locations[9], 3);
		path6.put(locations[10], locations[7]); costs6.put(locations[10], 2);
		path6.put(locations[11], locations[7]); costs6.put(locations[11], 3);
		path6.put(locations[12], locations[7]); costs6.put(locations[12], 4);
		path6.put(locations[13], locations[13]); costs6.put(locations[13], 1);
		locations[6].setShortestPaths(path6, costs6);
		Map<Location, Location> path7 = new HashMap<Location, Location>();
		Map<Location, Integer> costs7 = new HashMap<Location, Integer>();
		path7.put(locations[0], locations[6]); costs7.put(locations[0], 4);
		path7.put(locations[1], locations[6]); costs7.put(locations[1], 3);
		path7.put(locations[2], locations[6]); costs7.put(locations[2], 3);
		path7.put(locations[3], locations[6]); costs7.put(locations[3], 3);
		path7.put(locations[4], locations[6]); costs7.put(locations[4], 3);
		path7.put(locations[5], locations[6]); costs7.put(locations[5], 2);
		path7.put(locations[6], locations[6]); costs7.put(locations[6], 1);
		path7.put(locations[7], locations[7]); costs7.put(locations[7], 0);
		path7.put(locations[8], locations[8]); costs7.put(locations[8], 1);
		path7.put(locations[9], locations[8]); costs7.put(locations[9], 2);
		path7.put(locations[10], locations[10]); costs7.put(locations[10], 1);
		path7.put(locations[11], locations[8]); costs7.put(locations[11], 2);
		path7.put(locations[12], locations[8]); costs7.put(locations[12], 3);
		path7.put(locations[13], locations[6]); costs7.put(locations[13], 2);
		locations[7].setShortestPaths(path7, costs7);
		Map<Location, Location> path8 = new HashMap<Location, Location>();
		Map<Location, Integer> costs8 = new HashMap<Location, Integer>();
		path8.put(locations[0], locations[7]); costs8.put(locations[0], 5);
		path8.put(locations[1], locations[7]); costs8.put(locations[1], 4);
		path8.put(locations[2], locations[7]); costs8.put(locations[2], 4);
		path8.put(locations[3], locations[7]); costs8.put(locations[3], 4);
		path8.put(locations[4], locations[7]); costs8.put(locations[4], 4);
		path8.put(locations[5], locations[7]); costs8.put(locations[5], 3);
		path8.put(locations[6], locations[7]); costs8.put(locations[6], 2);
		path8.put(locations[7], locations[7]); costs8.put(locations[7], 1);
		path8.put(locations[8], locations[8]); costs8.put(locations[8], 0);
		path8.put(locations[9], locations[9]); costs8.put(locations[9], 1);
		path8.put(locations[10], locations[7]); costs8.put(locations[10], 2);
		path8.put(locations[11], locations[11]); costs8.put(locations[11], 1);
		path8.put(locations[12], locations[9]); costs8.put(locations[12], 2);
		path8.put(locations[13], locations[7]); costs8.put(locations[13], 3);
		locations[8].setShortestPaths(path8, costs8);
		Map<Location, Location> path9 = new HashMap<Location, Location>();
		Map<Location, Integer> costs9 = new HashMap<Location, Integer>();
		path9.put(locations[0], locations[8]); costs9.put(locations[0], 6);
		path9.put(locations[1], locations[8]); costs9.put(locations[1], 5);
		path9.put(locations[2], locations[8]); costs9.put(locations[2], 5);
		path9.put(locations[3], locations[8]); costs9.put(locations[3], 5);
		path9.put(locations[4], locations[8]); costs9.put(locations[4], 5);
		path9.put(locations[5], locations[8]); costs9.put(locations[5], 4);
		path9.put(locations[6], locations[8]); costs9.put(locations[6], 3);
		path9.put(locations[7], locations[8]); costs9.put(locations[7], 2);
		path9.put(locations[8], locations[8]); costs9.put(locations[8], 1);
		path9.put(locations[9], locations[9]); costs9.put(locations[9], 0);
		path9.put(locations[10], locations[8]); costs9.put(locations[10], 3);
		path9.put(locations[11], locations[8]); costs9.put(locations[11], 2);
		path9.put(locations[12], locations[12]); costs9.put(locations[12], 1);
		path9.put(locations[13], locations[8]); costs9.put(locations[13], 4);
		locations[9].setShortestPaths(path9, costs9);
		Map<Location, Location> path10 = new HashMap<Location, Location>();
		Map<Location, Integer> costs10 = new HashMap<Location, Integer>();
		path10.put(locations[0], locations[7]); costs10.put(locations[0], 5);
		path10.put(locations[1], locations[7]); costs10.put(locations[1], 4);
		path10.put(locations[2], locations[13]); costs10.put(locations[2], 4);
		path10.put(locations[3], locations[7]); costs10.put(locations[3], 4);
		path10.put(locations[4], locations[7]); costs10.put(locations[4], 4);
		path10.put(locations[5], locations[7]); costs10.put(locations[5], 3);
		path10.put(locations[6], locations[7]); costs10.put(locations[6], 2);
		path10.put(locations[7], locations[7]); costs10.put(locations[7], 1);
		path10.put(locations[8], locations[7]); costs10.put(locations[8], 2);
		path10.put(locations[9], locations[7]); costs10.put(locations[9], 3);
		path10.put(locations[10], locations[10]); costs10.put(locations[10], 0);
		path10.put(locations[11], locations[11]); costs10.put(locations[11], 2);
		path10.put(locations[12], locations[11]); costs10.put(locations[12], 4);
		path10.put(locations[13], locations[13]); costs10.put(locations[13], 2);
		locations[10].setShortestPaths(path10, costs10);
		Map<Location, Location> path11 = new HashMap<Location, Location>();
		Map<Location, Integer> costs11 = new HashMap<Location, Integer>();
		path11.put(locations[0], locations[8]); costs11.put(locations[0], 6);
		path11.put(locations[1], locations[8]); costs11.put(locations[1], 5);
		path11.put(locations[2], locations[8]); costs11.put(locations[2], 5);
		path11.put(locations[3], locations[8]); costs11.put(locations[3], 5);
		path11.put(locations[4], locations[8]); costs11.put(locations[4], 5);
		path11.put(locations[5], locations[8]); costs11.put(locations[5], 4);
		path11.put(locations[6], locations[8]); costs11.put(locations[6], 3);
		path11.put(locations[7], locations[8]); costs11.put(locations[7], 2);
		path11.put(locations[8], locations[8]); costs11.put(locations[8], 1);
		path11.put(locations[9], locations[8]); costs11.put(locations[9], 2);
		path11.put(locations[10], locations[10]); costs11.put(locations[10], 2);
		path11.put(locations[11], locations[11]); costs11.put(locations[11], 0);
		path11.put(locations[12], locations[12]); costs11.put(locations[12], 2);
		path11.put(locations[13], locations[10]); costs11.put(locations[13], 4);
		locations[11].setShortestPaths(path11, costs11);
		Map<Location, Location> path12 = new HashMap<Location, Location>();
		Map<Location, Integer> costs12 = new HashMap<Location, Integer>();
		path12.put(locations[0], locations[9]); costs12.put(locations[0], 7);
		path12.put(locations[1], locations[9]); costs12.put(locations[1], 6);
		path12.put(locations[2], locations[9]); costs12.put(locations[2], 6);
		path12.put(locations[3], locations[9]); costs12.put(locations[3], 6);
		path12.put(locations[4], locations[9]); costs12.put(locations[4], 6);
		path12.put(locations[5], locations[9]); costs12.put(locations[5], 5);
		path12.put(locations[6], locations[9]); costs12.put(locations[6], 4);
		path12.put(locations[7], locations[9]); costs12.put(locations[7], 3);
		path12.put(locations[8], locations[9]); costs12.put(locations[8], 2);
		path12.put(locations[9], locations[9]); costs12.put(locations[9], 1);
		path12.put(locations[10], locations[11]); costs12.put(locations[10], 4);
		path12.put(locations[11], locations[11]); costs12.put(locations[11], 2);
		path12.put(locations[12], locations[12]); costs12.put(locations[12], 0);
		path12.put(locations[13], locations[9]); costs12.put(locations[13], 5);
		locations[12].setShortestPaths(path12, costs12);
		Map<Location, Location> path13 = new HashMap<Location, Location>();
		Map<Location, Integer> costs13 = new HashMap<Location, Integer>();
		path13.put(locations[0], locations[2]); costs13.put(locations[0], 4);
		path13.put(locations[1], locations[6]); costs13.put(locations[1], 3);
		path13.put(locations[2], locations[2]); costs13.put(locations[2], 2);
		path13.put(locations[3], locations[6]); costs13.put(locations[3], 3);
		path13.put(locations[4], locations[6]); costs13.put(locations[4], 3);
		path13.put(locations[5], locations[6]); costs13.put(locations[5], 2);
		path13.put(locations[6], locations[6]); costs13.put(locations[6], 1);
		path13.put(locations[7], locations[6]); costs13.put(locations[7], 2);
		path13.put(locations[8], locations[6]); costs13.put(locations[8], 3);
		path13.put(locations[9], locations[6]); costs13.put(locations[9], 4);
		path13.put(locations[10], locations[10]); costs13.put(locations[10], 2);
		path13.put(locations[11], locations[10]); costs13.put(locations[11], 4);
		path13.put(locations[12], locations[6]); costs13.put(locations[12], 5);
		path13.put(locations[13], locations[13]); costs13.put(locations[13], 0);
		locations[13].setShortestPaths(path13, costs13);
	}
	
	private void connectLocations(Location a, Location b, int weight) {
		a.addNeighbor(b, weight);
		b.addNeighbor(a, weight);
	}
	
	private void createOrders() {
		orders.add(new Order(parcels[0], locations[5]));
		orders.add(new Order(parcels[1], locations[9]));
	}
	
	private void createContestants() {
		contestants[0] = new Contestant("Klaas", parcels[0]);
		contestants[1] = new Contestant("Jan", parcels[1]);
	}
	
	private void createLog() {
		List<RouteLocation> routeLocations1 = new ArrayList<RouteLocation>();
		routeLocations1.add(new RouteLocation(locations[0], parcels[0], parcels[1]));
		routeLocations1.add(new RouteLocation(locations[4], parcels[0]));
		routeLocations1.add(new RouteLocation(locations[5], parcels[0]));
		Route route1 = new Route(routeLocations1);
		
		List<RouteLocation> routeLocations2 = new ArrayList<RouteLocation>();
		routeLocations2.add(new RouteLocation(locations[0], parcels[1], parcels[0]));
		routeLocations2.add(new RouteLocation(locations[2], parcels[1]));
		routeLocations2.add(new RouteLocation(locations[13], parcels[1]));
		routeLocations2.add(new RouteLocation(locations[10], parcels[1]));
		routeLocations2.add(new RouteLocation(locations[11], parcels[1]));
		routeLocations2.add(new RouteLocation(locations[12], parcels[1]));
		routeLocations2.add(new RouteLocation(locations[9], parcels[1]));
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