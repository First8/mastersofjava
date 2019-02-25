import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NetherlandsTestCase implements ITestCase {
	
	public String getTestCaseName() { return "Delivering parcels between the 20 largest cities of the Netherlands."; }
	public String getTestCaseDescription() {
		return "Network with 20 nodes, 15 parcels and 6 contestants.\n"
			+ "The Mole has choosen the least feavarouble routes.";
	}
	
	private final Set<Order> orders = new HashSet<Order>();
	private final Map<Contestant, Route> log = new HashMap<Contestant, Route>();
	
	private final Parcel[] parcels = new Parcel[15];
	private final Location[] locations = new Location[20];
	private final Contestant[] contestants = new Contestant[6];
	
	private Contestant theMole;
	
	public NetherlandsTestCase() {
		createParcels();
		createLocations();
		//printShortestPaths();
		createShortestPaths();
		createOrders();
		createContestants();
		createLog();
		
		theMole = contestants[3]; // Trudy
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
		parcels[0] = new Parcel("dvd - de leeuwenkoning, disney film");
		parcels[1] = new Parcel("tijdschrift - vrouw online");
		parcels[2] = new Parcel("tijdschrift - de revu");
		parcels[3] = new Parcel("tijdschrift - donald duck");
		parcels[4] = new Parcel("dvd - 50 shades of grey");
		parcels[5] = new Parcel("ikea - tv meubel");
		parcels[6] = new Parcel("ikea - boxspring");
		parcels[7] = new Parcel("pepermolen");
		parcels[8] = new Parcel("40l zak potgrond");
		parcels[9] = new Parcel("ikea - bureaulampje");
		parcels[10] = new Parcel("messenset");
		parcels[11] = new Parcel("wc papier");
		parcels[12] = new Parcel("big mac");
		parcels[13] = new Parcel("dakkapel");
		parcels[14] = new Parcel("zeilboot");
	}
	
	private void createLocations() {
		locations[0] = new Location("Delfzijl");
		locations[1] = new Location("Groningen");
		locations[2] = new Location("Leeuwarden");
		locations[3] = new Location("Assen");
		locations[4] = new Location("Den Helder");
		locations[5] = new Location("Zwolle");
		locations[6] = new Location("IJmuiden");
		locations[7] = new Location("Haarlem");
		locations[8] = new Location("Amsterdam");
		locations[9] = new Location("Utrecht");
		locations[10] = new Location("Arnhem");
		locations[11] = new Location("Nijmegen");
		locations[12] = new Location("Den Haag");
		locations[13] = new Location("Rotterdam");
		locations[14] = new Location("Europoort");
		locations[15] = new Location("Dordrecht");
		locations[16] = new Location("Tilburg");
		locations[17] = new Location("Terneuzen");
		locations[18] = new Location("Eindhoven");
		locations[19] = new Location("Maastricht");
		
		connectLocations(locations[0], locations[1], 1);
		connectLocations(locations[1], locations[2], 2);
		connectLocations(locations[1], locations[3], 3);
		connectLocations(locations[1], locations[5], 4);
		connectLocations(locations[2], locations[3], 5);
		connectLocations(locations[2], locations[4], 6);
		connectLocations(locations[2], locations[5], 7);
		connectLocations(locations[3], locations[10], 8);
		connectLocations(locations[4], locations[6], 9);
		connectLocations(locations[4], locations[7], 10);
		connectLocations(locations[4], locations[8], 11);
		connectLocations(locations[5], locations[9], 12);
		connectLocations(locations[5], locations[10], 13);
		connectLocations(locations[6], locations[7], 14);
		connectLocations(locations[6], locations[8], 15);
		connectLocations(locations[6], locations[12], 16);
		connectLocations(locations[7], locations[8], 17);
		connectLocations(locations[7], locations[9], 18);
		connectLocations(locations[7], locations[12], 19);
		connectLocations(locations[8], locations[9], 20);
		connectLocations(locations[8], locations[12], 21);
		connectLocations(locations[9], locations[10], 22);
		connectLocations(locations[9], locations[13], 23);
		connectLocations(locations[10], locations[11], 24);
		connectLocations(locations[11], locations[16], 25);
		connectLocations(locations[11], locations[18], 26);
		connectLocations(locations[12], locations[13], 27);
		connectLocations(locations[12], locations[14], 28);
		connectLocations(locations[13], locations[14], 29);
		connectLocations(locations[13], locations[15], 30);
		connectLocations(locations[14], locations[17], 31);
		connectLocations(locations[15], locations[16], 32);
		connectLocations(locations[16], locations[17], 33);
		connectLocations(locations[16], locations[18], 34);
		connectLocations(locations[18], locations[19], 35);
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
	
	private void connectLocations(Location a, Location b, int weight) {
		a.addNeighbor(b, weight);
		b.addNeighbor(a, weight);
	}
	
	private void createOrders() {
		orders.add(new Order(parcels[0], locations[0]));
		orders.add(new Order(parcels[1], locations[4]));
		orders.add(new Order(parcels[2], locations[6]));
		orders.add(new Order(parcels[3], locations[6]));
		orders.add(new Order(parcels[4], locations[6]));
		orders.add(new Order(parcels[5], locations[6]));
		orders.add(new Order(parcels[6], locations[6]));
		orders.add(new Order(parcels[7], locations[6]));
		orders.add(new Order(parcels[8], locations[6]));
		orders.add(new Order(parcels[9], locations[6]));
		orders.add(new Order(parcels[10], locations[6]));
		orders.add(new Order(parcels[11], locations[6]));
		orders.add(new Order(parcels[12], locations[6]));
		orders.add(new Order(parcels[13], locations[6]));
		orders.add(new Order(parcels[14], locations[6]));
	}
	
	private void createContestants() {
		contestants[0] = new Contestant("Piet", parcels[0], parcels[6]);
		contestants[1] = new Contestant("Kees", parcels[1], parcels[7]);
		contestants[2] = new Contestant("Harry", parcels[2], parcels[8], parcels[12]);
		contestants[3] = new Contestant("Trudy", parcels[3], parcels[9], parcels[13]); // THE MOLE!
		contestants[4] = new Contestant("Claire", parcels[4], parcels[10]);
		contestants[5] = new Contestant("Anita", parcels[5], parcels[11], parcels[14]);
	}
	
	private void createLog() {
		List<RouteLocation> routeLocations1 = new ArrayList<RouteLocation>();
		routeLocations1.add(new RouteLocation(locations[6]));
		routeLocations1.add(new RouteLocation(locations[3], parcels[0]));
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
		
		List<RouteLocation> routeLocations4 = new ArrayList<RouteLocation>();
		routeLocations4.add(new RouteLocation(locations[6]));
		routeLocations4.add(new RouteLocation(locations[3], parcels[0]));
		routeLocations4.add(new RouteLocation(locations[2], parcels[0]));
		routeLocations4.add(new RouteLocation(locations[1], parcels[0]));
		routeLocations4.add(new RouteLocation(locations[0], parcels[0]));
		Route route4 = new Route(routeLocations4);
		
		List<RouteLocation> routeLocations5 = new ArrayList<RouteLocation>();
		routeLocations5.add(new RouteLocation(locations[1]));
		routeLocations5.add(new RouteLocation(locations[2]));
		routeLocations5.add(new RouteLocation(locations[3], parcels[1], parcels[2]));
		routeLocations5.add(new RouteLocation(locations[2], parcels[1]));
		routeLocations5.add(new RouteLocation(locations[4], parcels[1]));
		Route route5 = new Route(routeLocations5);
		
		List<RouteLocation> routeLocations6 = new ArrayList<RouteLocation>();
		routeLocations6.add(new RouteLocation(locations[5]));
		routeLocations6.add(new RouteLocation(locations[2]));
		routeLocations6.add(new RouteLocation(locations[4], parcels[2], parcels[1]));
		routeLocations6.add(new RouteLocation(locations[2], parcels[2]));
		routeLocations6.add(new RouteLocation(locations[3], parcels[2]));
		routeLocations6.add(new RouteLocation(locations[6], parcels[2]));
		Route route6 = new Route(routeLocations6);
		
		log.put(contestants[0], route1);
		log.put(contestants[1], route2);
		log.put(contestants[2], route3);
		log.put(contestants[3], route4);
		log.put(contestants[4], route5);
		log.put(contestants[5], route6);
	}
	
	private void createShortestPaths() {
		Map<Location, Location> path0 = new HashMap<Location, Location>();
		Map<Location, Integer> costs0 = new HashMap<Location, Integer>();
		path0.put(locations[0], locations[0]); costs0.put(locations[0], 0);
		path0.put(locations[1], locations[1]); costs0.put(locations[1], 1);
		path0.put(locations[2], locations[1]); costs0.put(locations[2], 3);
		path0.put(locations[3], locations[1]); costs0.put(locations[3], 4);
		path0.put(locations[4], locations[1]); costs0.put(locations[4], 9);
		path0.put(locations[5], locations[1]); costs0.put(locations[5], 5);
		path0.put(locations[6], locations[1]); costs0.put(locations[6], 18);
		path0.put(locations[7], locations[1]); costs0.put(locations[7], 19);
		path0.put(locations[8], locations[1]); costs0.put(locations[8], 20);
		path0.put(locations[9], locations[1]); costs0.put(locations[9], 17);
		path0.put(locations[10], locations[1]); costs0.put(locations[10], 12);
		path0.put(locations[11], locations[1]); costs0.put(locations[11], 36);
		path0.put(locations[12], locations[1]); costs0.put(locations[12], 34);
		path0.put(locations[13], locations[1]); costs0.put(locations[13], 40);
		path0.put(locations[14], locations[1]); costs0.put(locations[14], 62);
		path0.put(locations[15], locations[1]); costs0.put(locations[15], 70);
		path0.put(locations[16], locations[1]); costs0.put(locations[16], 61);
		path0.put(locations[17], locations[1]); costs0.put(locations[17], 93);
		path0.put(locations[18], locations[1]); costs0.put(locations[18], 62);
		path0.put(locations[19], locations[1]); costs0.put(locations[19], 97);
		locations[0].setShortestPaths(path0, costs0);
		
		Map<Location, Location> path1 = new HashMap<Location, Location>();
		Map<Location, Integer> costs1 = new HashMap<Location, Integer>();
		path1.put(locations[0], locations[0]); costs1.put(locations[0], 1);
		path1.put(locations[1], locations[1]); costs1.put(locations[1], 0);
		path1.put(locations[2], locations[2]); costs1.put(locations[2], 2);
		path1.put(locations[3], locations[3]); costs1.put(locations[3], 3);
		path1.put(locations[4], locations[2]); costs1.put(locations[4], 8);
		path1.put(locations[5], locations[5]); costs1.put(locations[5], 4);
		path1.put(locations[6], locations[2]); costs1.put(locations[6], 17);
		path1.put(locations[7], locations[2]); costs1.put(locations[7], 18);
		path1.put(locations[8], locations[2]); costs1.put(locations[8], 19);
		path1.put(locations[9], locations[5]); costs1.put(locations[9], 16);
		path1.put(locations[10], locations[3]); costs1.put(locations[10], 11);
		path1.put(locations[11], locations[3]); costs1.put(locations[11], 35);
		path1.put(locations[12], locations[2]); costs1.put(locations[12], 33);
		path1.put(locations[13], locations[5]); costs1.put(locations[13], 39);
		path1.put(locations[14], locations[2]); costs1.put(locations[14], 61);
		path1.put(locations[15], locations[5]); costs1.put(locations[15], 69);
		path1.put(locations[16], locations[3]); costs1.put(locations[16], 60);
		path1.put(locations[17], locations[2]); costs1.put(locations[17], 92);
		path1.put(locations[18], locations[3]); costs1.put(locations[18], 61);
		path1.put(locations[19], locations[3]); costs1.put(locations[19], 96);
		locations[1].setShortestPaths(path1, costs1);
		
		Map<Location, Location> path2 = new HashMap<Location, Location>();
		Map<Location, Integer> costs2 = new HashMap<Location, Integer>();
		path2.put(locations[0], locations[1]); costs2.put(locations[0], 3);
		path2.put(locations[1], locations[1]); costs2.put(locations[1], 2);
		path2.put(locations[2], locations[2]); costs2.put(locations[2], 0);
		path2.put(locations[3], locations[3]); costs2.put(locations[3], 5);
		path2.put(locations[4], locations[4]); costs2.put(locations[4], 6);
		path2.put(locations[5], locations[1]); costs2.put(locations[5], 6);
		path2.put(locations[6], locations[4]); costs2.put(locations[6], 15);
		path2.put(locations[7], locations[4]); costs2.put(locations[7], 16);
		path2.put(locations[8], locations[4]); costs2.put(locations[8], 17);
		path2.put(locations[9], locations[1]); costs2.put(locations[9], 18);
		path2.put(locations[10], locations[3]); costs2.put(locations[10], 13);
		path2.put(locations[11], locations[3]); costs2.put(locations[11], 37);
		path2.put(locations[12], locations[4]); costs2.put(locations[12], 31);
		path2.put(locations[13], locations[1]); costs2.put(locations[13], 41);
		path2.put(locations[14], locations[4]); costs2.put(locations[14], 59);
		path2.put(locations[15], locations[1]); costs2.put(locations[15], 71);
		path2.put(locations[16], locations[3]); costs2.put(locations[16], 62);
		path2.put(locations[17], locations[4]); costs2.put(locations[17], 90);
		path2.put(locations[18], locations[3]); costs2.put(locations[18], 63);
		path2.put(locations[19], locations[3]); costs2.put(locations[19], 98);
		locations[2].setShortestPaths(path2, costs2);
		
		Map<Location, Location> path3 = new HashMap<Location, Location>();
		Map<Location, Integer> costs3 = new HashMap<Location, Integer>();
		path3.put(locations[0], locations[1]); costs3.put(locations[0], 4);
		path3.put(locations[1], locations[1]); costs3.put(locations[1], 3);
		path3.put(locations[2], locations[2]); costs3.put(locations[2], 5);
		path3.put(locations[3], locations[3]); costs3.put(locations[3], 0);
		path3.put(locations[4], locations[2]); costs3.put(locations[4], 11);
		path3.put(locations[5], locations[1]); costs3.put(locations[5], 7);
		path3.put(locations[6], locations[2]); costs3.put(locations[6], 20);
		path3.put(locations[7], locations[2]); costs3.put(locations[7], 21);
		path3.put(locations[8], locations[2]); costs3.put(locations[8], 22);
		path3.put(locations[9], locations[1]); costs3.put(locations[9], 19);
		path3.put(locations[10], locations[10]); costs3.put(locations[10], 8);
		path3.put(locations[11], locations[10]); costs3.put(locations[11], 32);
		path3.put(locations[12], locations[2]); costs3.put(locations[12], 36);
		path3.put(locations[13], locations[1]); costs3.put(locations[13], 42);
		path3.put(locations[14], locations[2]); costs3.put(locations[14], 64);
		path3.put(locations[15], locations[1]); costs3.put(locations[15], 72);
		path3.put(locations[16], locations[10]); costs3.put(locations[16], 57);
		path3.put(locations[17], locations[10]); costs3.put(locations[17], 90);
		path3.put(locations[18], locations[10]); costs3.put(locations[18], 58);
		path3.put(locations[19], locations[10]); costs3.put(locations[19], 93);
		locations[3].setShortestPaths(path3, costs3);
		
		Map<Location, Location> path4 = new HashMap<Location, Location>();
		Map<Location, Integer> costs4 = new HashMap<Location, Integer>();
		path4.put(locations[0], locations[2]); costs4.put(locations[0], 9);
		path4.put(locations[1], locations[2]); costs4.put(locations[1], 8);
		path4.put(locations[2], locations[2]); costs4.put(locations[2], 6);
		path4.put(locations[3], locations[2]); costs4.put(locations[3], 11);
		path4.put(locations[4], locations[4]); costs4.put(locations[4], 0);
		path4.put(locations[5], locations[2]); costs4.put(locations[5], 12);
		path4.put(locations[6], locations[6]); costs4.put(locations[6], 9);
		path4.put(locations[7], locations[7]); costs4.put(locations[7], 10);
		path4.put(locations[8], locations[8]); costs4.put(locations[8], 11);
		path4.put(locations[9], locations[2]); costs4.put(locations[9], 24);
		path4.put(locations[10], locations[2]); costs4.put(locations[10], 19);
		path4.put(locations[11], locations[2]); costs4.put(locations[11], 43);
		path4.put(locations[12], locations[6]); costs4.put(locations[12], 25);
		path4.put(locations[13], locations[2]); costs4.put(locations[13], 47);
		path4.put(locations[14], locations[6]); costs4.put(locations[14], 53);
		path4.put(locations[15], locations[2]); costs4.put(locations[15], 77);
		path4.put(locations[16], locations[2]); costs4.put(locations[16], 68);
		path4.put(locations[17], locations[6]); costs4.put(locations[17], 84);
		path4.put(locations[18], locations[2]); costs4.put(locations[18], 69);
		path4.put(locations[19], locations[2]); costs4.put(locations[19], 104);
		locations[4].setShortestPaths(path4, costs4);
		
		Map<Location, Location> path5 = new HashMap<Location, Location>();
		Map<Location, Integer> costs5 = new HashMap<Location, Integer>();
		path5.put(locations[0], locations[1]); costs5.put(locations[0], 5);
		path5.put(locations[1], locations[1]); costs5.put(locations[1], 4);
		path5.put(locations[2], locations[1]); costs5.put(locations[2], 6);
		path5.put(locations[3], locations[1]); costs5.put(locations[3], 7);
		path5.put(locations[4], locations[1]); costs5.put(locations[4], 12);
		path5.put(locations[5], locations[5]); costs5.put(locations[5], 0);
		path5.put(locations[6], locations[1]); costs5.put(locations[6], 21);
		path5.put(locations[7], locations[1]); costs5.put(locations[7], 22);
		path5.put(locations[8], locations[1]); costs5.put(locations[8], 23);
		path5.put(locations[9], locations[9]); costs5.put(locations[9], 12);
		path5.put(locations[10], locations[10]); costs5.put(locations[10], 13);
		path5.put(locations[11], locations[10]); costs5.put(locations[11], 37);
		path5.put(locations[12], locations[1]); costs5.put(locations[12], 37);
		path5.put(locations[13], locations[9]); costs5.put(locations[13], 35);
		path5.put(locations[14], locations[9]); costs5.put(locations[14], 64);
		path5.put(locations[15], locations[9]); costs5.put(locations[15], 65);
		path5.put(locations[16], locations[10]); costs5.put(locations[16], 62);
		path5.put(locations[17], locations[10]); costs5.put(locations[17], 95);
		path5.put(locations[18], locations[10]); costs5.put(locations[18], 63);
		path5.put(locations[19], locations[10]); costs5.put(locations[19], 98);
		locations[5].setShortestPaths(path5, costs5);
		
		Map<Location, Location> path6 = new HashMap<Location, Location>();
		Map<Location, Integer> costs6 = new HashMap<Location, Integer>();
		path6.put(locations[0], locations[4]); costs6.put(locations[0], 18);
		path6.put(locations[1], locations[4]); costs6.put(locations[1], 17);
		path6.put(locations[2], locations[4]); costs6.put(locations[2], 15);
		path6.put(locations[3], locations[4]); costs6.put(locations[3], 20);
		path6.put(locations[4], locations[4]); costs6.put(locations[4], 9);
		path6.put(locations[5], locations[4]); costs6.put(locations[5], 21);
		path6.put(locations[6], locations[6]); costs6.put(locations[6], 0);
		path6.put(locations[7], locations[7]); costs6.put(locations[7], 14);
		path6.put(locations[8], locations[8]); costs6.put(locations[8], 15);
		path6.put(locations[9], locations[7]); costs6.put(locations[9], 32);
		path6.put(locations[10], locations[4]); costs6.put(locations[10], 28);
		path6.put(locations[11], locations[4]); costs6.put(locations[11], 52);
		path6.put(locations[12], locations[12]); costs6.put(locations[12], 16);
		path6.put(locations[13], locations[12]); costs6.put(locations[13], 43);
		path6.put(locations[14], locations[12]); costs6.put(locations[14], 44);
		path6.put(locations[15], locations[12]); costs6.put(locations[15], 73);
		path6.put(locations[16], locations[4]); costs6.put(locations[16], 77);
		path6.put(locations[17], locations[12]); costs6.put(locations[17], 75);
		path6.put(locations[18], locations[4]); costs6.put(locations[18], 78);
		path6.put(locations[19], locations[4]); costs6.put(locations[19], 113);
		locations[6].setShortestPaths(path6, costs6);
		
		Map<Location, Location> path7 = new HashMap<Location, Location>();
		Map<Location, Integer> costs7 = new HashMap<Location, Integer>();
		path7.put(locations[0], locations[4]); costs7.put(locations[0], 19);
		path7.put(locations[1], locations[4]); costs7.put(locations[1], 18);
		path7.put(locations[2], locations[4]); costs7.put(locations[2], 16);
		path7.put(locations[3], locations[4]); costs7.put(locations[3], 21);
		path7.put(locations[4], locations[4]); costs7.put(locations[4], 10);
		path7.put(locations[5], locations[4]); costs7.put(locations[5], 22);
		path7.put(locations[6], locations[6]); costs7.put(locations[6], 14);
		path7.put(locations[7], locations[7]); costs7.put(locations[7], 0);
		path7.put(locations[8], locations[8]); costs7.put(locations[8], 17);
		path7.put(locations[9], locations[9]); costs7.put(locations[9], 18);
		path7.put(locations[10], locations[4]); costs7.put(locations[10], 29);
		path7.put(locations[11], locations[4]); costs7.put(locations[11], 53);
		path7.put(locations[12], locations[12]); costs7.put(locations[12], 19);
		path7.put(locations[13], locations[9]); costs7.put(locations[13], 41);
		path7.put(locations[14], locations[12]); costs7.put(locations[14], 47);
		path7.put(locations[15], locations[9]); costs7.put(locations[15], 71);
		path7.put(locations[16], locations[4]); costs7.put(locations[16], 78);
		path7.put(locations[17], locations[12]); costs7.put(locations[17], 78);
		path7.put(locations[18], locations[4]); costs7.put(locations[18], 79);
		path7.put(locations[19], locations[4]); costs7.put(locations[19], 114);
		locations[7].setShortestPaths(path7, costs7);
		
		Map<Location, Location> path8 = new HashMap<Location, Location>();
		Map<Location, Integer> costs8 = new HashMap<Location, Integer>();
		path8.put(locations[0], locations[4]); costs8.put(locations[0], 20);
		path8.put(locations[1], locations[4]); costs8.put(locations[1], 19);
		path8.put(locations[2], locations[4]); costs8.put(locations[2], 17);
		path8.put(locations[3], locations[4]); costs8.put(locations[3], 22);
		path8.put(locations[4], locations[4]); costs8.put(locations[4], 11);
		path8.put(locations[5], locations[4]); costs8.put(locations[5], 23);
		path8.put(locations[6], locations[6]); costs8.put(locations[6], 15);
		path8.put(locations[7], locations[7]); costs8.put(locations[7], 17);
		path8.put(locations[8], locations[8]); costs8.put(locations[8], 0);
		path8.put(locations[9], locations[9]); costs8.put(locations[9], 20);
		path8.put(locations[10], locations[4]); costs8.put(locations[10], 30);
		path8.put(locations[11], locations[4]); costs8.put(locations[11], 54);
		path8.put(locations[12], locations[12]); costs8.put(locations[12], 21);
		path8.put(locations[13], locations[9]); costs8.put(locations[13], 43);
		path8.put(locations[14], locations[12]); costs8.put(locations[14], 49);
		path8.put(locations[15], locations[9]); costs8.put(locations[15], 73);
		path8.put(locations[16], locations[4]); costs8.put(locations[16], 79);
		path8.put(locations[17], locations[12]); costs8.put(locations[17], 80);
		path8.put(locations[18], locations[4]); costs8.put(locations[18], 80);
		path8.put(locations[19], locations[4]); costs8.put(locations[19], 115);
		locations[8].setShortestPaths(path8, costs8);
		
		Map<Location, Location> path9 = new HashMap<Location, Location>();
		Map<Location, Integer> costs9 = new HashMap<Location, Integer>();
		path9.put(locations[0], locations[5]); costs9.put(locations[0], 17);
		path9.put(locations[1], locations[5]); costs9.put(locations[1], 16);
		path9.put(locations[2], locations[5]); costs9.put(locations[2], 18);
		path9.put(locations[3], locations[5]); costs9.put(locations[3], 19);
		path9.put(locations[4], locations[5]); costs9.put(locations[4], 24);
		path9.put(locations[5], locations[5]); costs9.put(locations[5], 12);
		path9.put(locations[6], locations[7]); costs9.put(locations[6], 32);
		path9.put(locations[7], locations[7]); costs9.put(locations[7], 18);
		path9.put(locations[8], locations[8]); costs9.put(locations[8], 20);
		path9.put(locations[9], locations[9]); costs9.put(locations[9], 0);
		path9.put(locations[10], locations[10]); costs9.put(locations[10], 22);
		path9.put(locations[11], locations[10]); costs9.put(locations[11], 46);
		path9.put(locations[12], locations[7]); costs9.put(locations[12], 37);
		path9.put(locations[13], locations[13]); costs9.put(locations[13], 23);
		path9.put(locations[14], locations[13]); costs9.put(locations[14], 52);
		path9.put(locations[15], locations[13]); costs9.put(locations[15], 53);
		path9.put(locations[16], locations[10]); costs9.put(locations[16], 71);
		path9.put(locations[17], locations[13]); costs9.put(locations[17], 83);
		path9.put(locations[18], locations[10]); costs9.put(locations[18], 72);
		path9.put(locations[19], locations[10]); costs9.put(locations[19], 107);
		locations[9].setShortestPaths(path9, costs9);
		
		Map<Location, Location> path10 = new HashMap<Location, Location>();
		Map<Location, Integer> costs10 = new HashMap<Location, Integer>();
		path10.put(locations[0], locations[3]); costs10.put(locations[0], 12);
		path10.put(locations[1], locations[3]); costs10.put(locations[1], 11);
		path10.put(locations[2], locations[3]); costs10.put(locations[2], 13);
		path10.put(locations[3], locations[3]); costs10.put(locations[3], 8);
		path10.put(locations[4], locations[3]); costs10.put(locations[4], 19);
		path10.put(locations[5], locations[5]); costs10.put(locations[5], 13);
		path10.put(locations[6], locations[3]); costs10.put(locations[6], 28);
		path10.put(locations[7], locations[3]); costs10.put(locations[7], 29);
		path10.put(locations[8], locations[3]); costs10.put(locations[8], 30);
		path10.put(locations[9], locations[9]); costs10.put(locations[9], 22);
		path10.put(locations[10], locations[10]); costs10.put(locations[10], 0);
		path10.put(locations[11], locations[11]); costs10.put(locations[11], 24);
		path10.put(locations[12], locations[3]); costs10.put(locations[12], 44);
		path10.put(locations[13], locations[9]); costs10.put(locations[13], 45);
		path10.put(locations[14], locations[3]); costs10.put(locations[14], 72);
		path10.put(locations[15], locations[9]); costs10.put(locations[15], 75);
		path10.put(locations[16], locations[11]); costs10.put(locations[16], 49);
		path10.put(locations[17], locations[11]); costs10.put(locations[17], 82);
		path10.put(locations[18], locations[11]); costs10.put(locations[18], 50);
		path10.put(locations[19], locations[11]); costs10.put(locations[19], 85);
		locations[10].setShortestPaths(path10, costs10);
		
		Map<Location, Location> path11 = new HashMap<Location, Location>();
		Map<Location, Integer> costs11 = new HashMap<Location, Integer>();
		path11.put(locations[0], locations[10]); costs11.put(locations[0], 36);
		path11.put(locations[1], locations[10]); costs11.put(locations[1], 35);
		path11.put(locations[2], locations[10]); costs11.put(locations[2], 37);
		path11.put(locations[3], locations[10]); costs11.put(locations[3], 32);
		path11.put(locations[4], locations[10]); costs11.put(locations[4], 43);
		path11.put(locations[5], locations[10]); costs11.put(locations[5], 37);
		path11.put(locations[6], locations[10]); costs11.put(locations[6], 52);
		path11.put(locations[7], locations[10]); costs11.put(locations[7], 53);
		path11.put(locations[8], locations[10]); costs11.put(locations[8], 54);
		path11.put(locations[9], locations[10]); costs11.put(locations[9], 46);
		path11.put(locations[10], locations[10]); costs11.put(locations[10], 24);
		path11.put(locations[11], locations[11]); costs11.put(locations[11], 0);
		path11.put(locations[12], locations[10]); costs11.put(locations[12], 68);
		path11.put(locations[13], locations[10]); costs11.put(locations[13], 69);
		path11.put(locations[14], locations[16]); costs11.put(locations[14], 89);
		path11.put(locations[15], locations[16]); costs11.put(locations[15], 57);
		path11.put(locations[16], locations[16]); costs11.put(locations[16], 25);
		path11.put(locations[17], locations[16]); costs11.put(locations[17], 58);
		path11.put(locations[18], locations[18]); costs11.put(locations[18], 26);
		path11.put(locations[19], locations[18]); costs11.put(locations[19], 61);
		locations[11].setShortestPaths(path11, costs11);
		
		Map<Location, Location> path12 = new HashMap<Location, Location>();
		Map<Location, Integer> costs12 = new HashMap<Location, Integer>();
		path12.put(locations[0], locations[6]); costs12.put(locations[0], 34);
		path12.put(locations[1], locations[6]); costs12.put(locations[1], 33);
		path12.put(locations[2], locations[6]); costs12.put(locations[2], 31);
		path12.put(locations[3], locations[6]); costs12.put(locations[3], 36);
		path12.put(locations[4], locations[6]); costs12.put(locations[4], 25);
		path12.put(locations[5], locations[6]); costs12.put(locations[5], 37);
		path12.put(locations[6], locations[6]); costs12.put(locations[6], 16);
		path12.put(locations[7], locations[7]); costs12.put(locations[7], 19);
		path12.put(locations[8], locations[8]); costs12.put(locations[8], 21);
		path12.put(locations[9], locations[7]); costs12.put(locations[9], 37);
		path12.put(locations[10], locations[6]); costs12.put(locations[10], 44);
		path12.put(locations[11], locations[6]); costs12.put(locations[11], 68);
		path12.put(locations[12], locations[12]); costs12.put(locations[12], 0);
		path12.put(locations[13], locations[13]); costs12.put(locations[13], 27);
		path12.put(locations[14], locations[14]); costs12.put(locations[14], 28);
		path12.put(locations[15], locations[13]); costs12.put(locations[15], 57);
		path12.put(locations[16], locations[13]); costs12.put(locations[16], 89);
		path12.put(locations[17], locations[14]); costs12.put(locations[17], 59);
		path12.put(locations[18], locations[6]); costs12.put(locations[18], 94);
		path12.put(locations[19], locations[6]); costs12.put(locations[19], 129);
		locations[12].setShortestPaths(path12, costs12);
		
		Map<Location, Location> path13 = new HashMap<Location, Location>();
		Map<Location, Integer> costs13 = new HashMap<Location, Integer>();
		path13.put(locations[0], locations[9]); costs13.put(locations[0], 40);
		path13.put(locations[1], locations[9]); costs13.put(locations[1], 39);
		path13.put(locations[2], locations[9]); costs13.put(locations[2], 41);
		path13.put(locations[3], locations[9]); costs13.put(locations[3], 42);
		path13.put(locations[4], locations[9]); costs13.put(locations[4], 47);
		path13.put(locations[5], locations[9]); costs13.put(locations[5], 35);
		path13.put(locations[6], locations[12]); costs13.put(locations[6], 43);
		path13.put(locations[7], locations[9]); costs13.put(locations[7], 41);
		path13.put(locations[8], locations[9]); costs13.put(locations[8], 43);
		path13.put(locations[9], locations[9]); costs13.put(locations[9], 23);
		path13.put(locations[10], locations[9]); costs13.put(locations[10], 45);
		path13.put(locations[11], locations[9]); costs13.put(locations[11], 69);
		path13.put(locations[12], locations[12]); costs13.put(locations[12], 27);
		path13.put(locations[13], locations[13]); costs13.put(locations[13], 0);
		path13.put(locations[14], locations[14]); costs13.put(locations[14], 29);
		path13.put(locations[15], locations[15]); costs13.put(locations[15], 30);
		path13.put(locations[16], locations[15]); costs13.put(locations[16], 62);
		path13.put(locations[17], locations[14]); costs13.put(locations[17], 60);
		path13.put(locations[18], locations[9]); costs13.put(locations[18], 95);
		path13.put(locations[19], locations[9]); costs13.put(locations[19], 130);
		locations[13].setShortestPaths(path13, costs13);
		
		Map<Location, Location> path14 = new HashMap<Location, Location>();
		Map<Location, Integer> costs14 = new HashMap<Location, Integer>();
		path14.put(locations[0], locations[12]); costs14.put(locations[0], 62);
		path14.put(locations[1], locations[12]); costs14.put(locations[1], 61);
		path14.put(locations[2], locations[12]); costs14.put(locations[2], 59);
		path14.put(locations[3], locations[12]); costs14.put(locations[3], 64);
		path14.put(locations[4], locations[12]); costs14.put(locations[4], 53);
		path14.put(locations[5], locations[13]); costs14.put(locations[5], 64);
		path14.put(locations[6], locations[12]); costs14.put(locations[6], 44);
		path14.put(locations[7], locations[12]); costs14.put(locations[7], 47);
		path14.put(locations[8], locations[12]); costs14.put(locations[8], 49);
		path14.put(locations[9], locations[13]); costs14.put(locations[9], 52);
		path14.put(locations[10], locations[12]); costs14.put(locations[10], 72);
		path14.put(locations[11], locations[17]); costs14.put(locations[11], 89);
		path14.put(locations[12], locations[12]); costs14.put(locations[12], 28);
		path14.put(locations[13], locations[13]); costs14.put(locations[13], 29);
		path14.put(locations[14], locations[14]); costs14.put(locations[14], 0);
		path14.put(locations[15], locations[13]); costs14.put(locations[15], 59);
		path14.put(locations[16], locations[17]); costs14.put(locations[16], 64);
		path14.put(locations[17], locations[17]); costs14.put(locations[17], 31);
		path14.put(locations[18], locations[17]); costs14.put(locations[18], 98);
		path14.put(locations[19], locations[17]); costs14.put(locations[19], 133);
		locations[14].setShortestPaths(path14, costs14);
		
		Map<Location, Location> path15 = new HashMap<Location, Location>();
		Map<Location, Integer> costs15 = new HashMap<Location, Integer>();
		path15.put(locations[0], locations[13]); costs15.put(locations[0], 70);
		path15.put(locations[1], locations[13]); costs15.put(locations[1], 69);
		path15.put(locations[2], locations[13]); costs15.put(locations[2], 71);
		path15.put(locations[3], locations[13]); costs15.put(locations[3], 72);
		path15.put(locations[4], locations[13]); costs15.put(locations[4], 77);
		path15.put(locations[5], locations[13]); costs15.put(locations[5], 65);
		path15.put(locations[6], locations[13]); costs15.put(locations[6], 73);
		path15.put(locations[7], locations[13]); costs15.put(locations[7], 71);
		path15.put(locations[8], locations[13]); costs15.put(locations[8], 73);
		path15.put(locations[9], locations[13]); costs15.put(locations[9], 53);
		path15.put(locations[10], locations[13]); costs15.put(locations[10], 75);
		path15.put(locations[11], locations[16]); costs15.put(locations[11], 57);
		path15.put(locations[12], locations[13]); costs15.put(locations[12], 57);
		path15.put(locations[13], locations[13]); costs15.put(locations[13], 30);
		path15.put(locations[14], locations[13]); costs15.put(locations[14], 59);
		path15.put(locations[15], locations[15]); costs15.put(locations[15], 0);
		path15.put(locations[16], locations[16]); costs15.put(locations[16], 32);
		path15.put(locations[17], locations[16]); costs15.put(locations[17], 65);
		path15.put(locations[18], locations[16]); costs15.put(locations[18], 66);
		path15.put(locations[19], locations[16]); costs15.put(locations[19], 101);
		locations[15].setShortestPaths(path15, costs15);
		
		Map<Location, Location> path16 = new HashMap<Location, Location>();
		Map<Location, Integer> costs16 = new HashMap<Location, Integer>();
		path16.put(locations[0], locations[11]); costs16.put(locations[0], 61);
		path16.put(locations[1], locations[11]); costs16.put(locations[1], 60);
		path16.put(locations[2], locations[11]); costs16.put(locations[2], 62);
		path16.put(locations[3], locations[11]); costs16.put(locations[3], 57);
		path16.put(locations[4], locations[11]); costs16.put(locations[4], 68);
		path16.put(locations[5], locations[11]); costs16.put(locations[5], 62);
		path16.put(locations[6], locations[11]); costs16.put(locations[6], 77);
		path16.put(locations[7], locations[11]); costs16.put(locations[7], 78);
		path16.put(locations[8], locations[11]); costs16.put(locations[8], 79);
		path16.put(locations[9], locations[11]); costs16.put(locations[9], 71);
		path16.put(locations[10], locations[11]); costs16.put(locations[10], 49);
		path16.put(locations[11], locations[11]); costs16.put(locations[11], 25);
		path16.put(locations[12], locations[15]); costs16.put(locations[12], 89);
		path16.put(locations[13], locations[15]); costs16.put(locations[13], 62);
		path16.put(locations[14], locations[17]); costs16.put(locations[14], 64);
		path16.put(locations[15], locations[15]); costs16.put(locations[15], 32);
		path16.put(locations[16], locations[16]); costs16.put(locations[16], 0);
		path16.put(locations[17], locations[17]); costs16.put(locations[17], 33);
		path16.put(locations[18], locations[18]); costs16.put(locations[18], 34);
		path16.put(locations[19], locations[18]); costs16.put(locations[19], 69);
		locations[16].setShortestPaths(path16, costs16);
		
		Map<Location, Location> path17 = new HashMap<Location, Location>();
		Map<Location, Integer> costs17 = new HashMap<Location, Integer>();
		path17.put(locations[0], locations[14]); costs17.put(locations[0], 93);
		path17.put(locations[1], locations[14]); costs17.put(locations[1], 92);
		path17.put(locations[2], locations[14]); costs17.put(locations[2], 90);
		path17.put(locations[3], locations[16]); costs17.put(locations[3], 90);
		path17.put(locations[4], locations[14]); costs17.put(locations[4], 84);
		path17.put(locations[5], locations[16]); costs17.put(locations[5], 95);
		path17.put(locations[6], locations[14]); costs17.put(locations[6], 75);
		path17.put(locations[7], locations[14]); costs17.put(locations[7], 78);
		path17.put(locations[8], locations[14]); costs17.put(locations[8], 80);
		path17.put(locations[9], locations[14]); costs17.put(locations[9], 83);
		path17.put(locations[10], locations[16]); costs17.put(locations[10], 82);
		path17.put(locations[11], locations[16]); costs17.put(locations[11], 58);
		path17.put(locations[12], locations[14]); costs17.put(locations[12], 59);
		path17.put(locations[13], locations[14]); costs17.put(locations[13], 60);
		path17.put(locations[14], locations[14]); costs17.put(locations[14], 31);
		path17.put(locations[15], locations[16]); costs17.put(locations[15], 65);
		path17.put(locations[16], locations[16]); costs17.put(locations[16], 33);
		path17.put(locations[17], locations[17]); costs17.put(locations[17], 0);
		path17.put(locations[18], locations[16]); costs17.put(locations[18], 67);
		path17.put(locations[19], locations[16]); costs17.put(locations[19], 102);
		locations[17].setShortestPaths(path17, costs17);
		
		Map<Location, Location> path18 = new HashMap<Location, Location>();
		Map<Location, Integer> costs18 = new HashMap<Location, Integer>();
		path18.put(locations[0], locations[11]); costs18.put(locations[0], 62);
		path18.put(locations[1], locations[11]); costs18.put(locations[1], 61);
		path18.put(locations[2], locations[11]); costs18.put(locations[2], 63);
		path18.put(locations[3], locations[11]); costs18.put(locations[3], 58);
		path18.put(locations[4], locations[11]); costs18.put(locations[4], 69);
		path18.put(locations[5], locations[11]); costs18.put(locations[5], 63);
		path18.put(locations[6], locations[11]); costs18.put(locations[6], 78);
		path18.put(locations[7], locations[11]); costs18.put(locations[7], 79);
		path18.put(locations[8], locations[11]); costs18.put(locations[8], 80);
		path18.put(locations[9], locations[11]); costs18.put(locations[9], 72);
		path18.put(locations[10], locations[11]); costs18.put(locations[10], 50);
		path18.put(locations[11], locations[11]); costs18.put(locations[11], 26);
		path18.put(locations[12], locations[11]); costs18.put(locations[12], 94);
		path18.put(locations[13], locations[11]); costs18.put(locations[13], 95);
		path18.put(locations[14], locations[16]); costs18.put(locations[14], 98);
		path18.put(locations[15], locations[16]); costs18.put(locations[15], 66);
		path18.put(locations[16], locations[16]); costs18.put(locations[16], 34);
		path18.put(locations[17], locations[16]); costs18.put(locations[17], 67);
		path18.put(locations[18], locations[18]); costs18.put(locations[18], 0);
		path18.put(locations[19], locations[19]); costs18.put(locations[19], 35);
		locations[18].setShortestPaths(path18, costs18);
		
		Map<Location, Location> path19 = new HashMap<Location, Location>();
		Map<Location, Integer> costs19 = new HashMap<Location, Integer>();
		path19.put(locations[0], locations[18]); costs19.put(locations[0], 97);
		path19.put(locations[1], locations[18]); costs19.put(locations[1], 96);
		path19.put(locations[2], locations[18]); costs19.put(locations[2], 98);
		path19.put(locations[3], locations[18]); costs19.put(locations[3], 93);
		path19.put(locations[4], locations[18]); costs19.put(locations[4], 104);
		path19.put(locations[5], locations[18]); costs19.put(locations[5], 98);
		path19.put(locations[6], locations[18]); costs19.put(locations[6], 113);
		path19.put(locations[7], locations[18]); costs19.put(locations[7], 114);
		path19.put(locations[8], locations[18]); costs19.put(locations[8], 115);
		path19.put(locations[9], locations[18]); costs19.put(locations[9], 107);
		path19.put(locations[10], locations[18]); costs19.put(locations[10], 85);
		path19.put(locations[11], locations[18]); costs19.put(locations[11], 61);
		path19.put(locations[12], locations[18]); costs19.put(locations[12], 129);
		path19.put(locations[13], locations[18]); costs19.put(locations[13], 130);
		path19.put(locations[14], locations[18]); costs19.put(locations[14], 133);
		path19.put(locations[15], locations[18]); costs19.put(locations[15], 101);
		path19.put(locations[16], locations[18]); costs19.put(locations[16], 69);
		path19.put(locations[17], locations[18]); costs19.put(locations[17], 102);
		path19.put(locations[18], locations[18]); costs19.put(locations[18], 35);
		path19.put(locations[19], locations[19]); costs19.put(locations[19], 0);
		locations[19].setShortestPaths(path19, costs19);
	}
}