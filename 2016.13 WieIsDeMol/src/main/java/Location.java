import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a location.
 * @author Bart Postma (Sogeti)
 */
public class Location {

	private final String name;
	private Map<Location, Integer> neighbors = new HashMap<Location, Integer>();
	
	private Map<Location, Location> shortestPathToLocation = new HashMap<Location, Location>();
	private Map<Location, Integer> cheapestCostsToLocation = new HashMap<Location, Integer>();
	
	/**
	 * Constructor
	 * @param name Name of the location
	 */
	public Location(String name) {
		this.name = name;
	}
	
	/**
	 * @return Name of the location
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method is used to initialize the assignment, not to be used in your solution!
	 */
	public void addNeighbor(Location neighbor, int weight) {
		neighbors.put(neighbor, weight);
	}
	
	/**
	 * Getter for obtaining all neighbour locations.
	 * @return Set with all neighbour locations.
	 */
	public Set<Location> getNeighbors() {
		return neighbors.keySet();
	}
	
	/**
	 * Method for retreiving the travel costs to the given neighbour location.
	 * Throws IllegalArgumentException if the given location is not a neighbour.
	 * @param location Location to obtain the travel costs of
	 * @return travel costs
	 */
	public int getTravelCostsToNeighbor(Location location) {
		if(neighbors.containsKey(location)) {
			return neighbors.get(location);
		} else {
			throw new IllegalArgumentException("Location " + location.getName() + " is not directly reachable from " + getName());
		}
	}
	
	/**
	 * Method for obtaining the cheapest travel costs to the given location.
	 * @param targetLocation
	 * @return travel costs
	 */
	public int getTravelCostsToLocation(Location location) {
		return cheapestCostsToLocation.get(location);
	}
	
	/**
	 * Method for obtaining the neighbour to travel to
	 * for the cheapest path to the given location.
	 * @param location end location
	 * @return next neighbour location to travel to.
	 */
	public Location getShortestPathToLocation(Location location) {
		return shortestPathToLocation.get(location);
	}
	
	/**
	 * Method for setting up the assignment, not to be used in your solution!
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public void setShortestPaths(Map path, Map costs) {
//		System.out.println("setShortestsPaths : " + this.getName() + ", " + printPath(path) + ", " + printCosts(costs));
		this.shortestPathToLocation = path;
		this.cheapestCostsToLocation = costs;
	}
	
	// Debug
	public String printPath(Map<Location,Location> path) {
		String result = "[";
		for(Map.Entry<Location, Location> pathEntry : path.entrySet()) {
			result += pathEntry.getKey().getName() + ":" + pathEntry.getValue().getName() + ", ";
		}
		result += "]";
		return result;
	}
	
	// Debug
	public String printCosts(Map<Location,Integer> costs) {
		String result = "[";
		for(Map.Entry<Location, Integer> costEntry : costs.entrySet()) {
			result += costEntry.getKey().getName() + ":" + costEntry.getValue() + ", ";
		}
		result += "]";
		return result;
	}
}
