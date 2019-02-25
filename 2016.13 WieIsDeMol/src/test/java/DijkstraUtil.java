import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public final class DijkstraUtil {

	private DijkstraUtil() { }
	
	public static void processLocations(Location[] locations) {
		for(Location aLocation : locations) {
			processLocation(locations, aLocation);
		}
	}
	
	public static void processLocation(Location[] locations, Location location) {
		LocationVertex source = new LocationVertex(location);
		Set<LocationVertex> vertices = new HashSet<LocationVertex>();
				
		for(Location aLocation : locations) {
			if(!location.equals(aLocation)) {
				LocationVertex v = new LocationVertex(aLocation);
				vertices.add(v);
			}
		}
		source.minDistance = 0.0;
        PriorityQueue<LocationVertex> locationQueue = new PriorityQueue<LocationVertex>();
      	locationQueue.add(source);
      	vertices.add(source);

		while (!locationQueue.isEmpty()) {
		    LocationVertex u = locationQueue.poll();
	
	            // Visit each edge exiting u
	            for (Location e : u.orig.getNeighbors())
	            {
	            	if(!e.equals(u.orig)) {
		                LocationVertex v = getLocationVertex(e, vertices);
		                double weight = u.orig.getTravelCostsToNeighbor(e);
		                double distanceThroughU = u.minDistance + weight;
						if (distanceThroughU < v.minDistance) {
						    locationQueue.remove(v);
						    v.minDistance = distanceThroughU;
						    v.previous = u;
						    locationQueue.add(v);
						    System.out.println(locationQueue.size() + " - " + v.orig.getName() + " - " + v.minDistance);
						}
	            	}
	            }
	    }
		
		Map<Location, Location> shortestPathsToLocation = new HashMap<Location, Location>();
		Map<Location, Integer> cheapestCostsToLocation = new HashMap<Location, Integer>();
		
		for(Location aLocation : locations) {
			List<LocationVertex> pathList = getShortestPathTo(getLocationVertex(aLocation, vertices));
			System.out.print(location.getName() + ","+ aLocation.getName() + " - ");
			for(LocationVertex aVert : pathList) {
				System.out.print(aVert.orig.getName() + "-");
			}
			System.out.println("");
			
			shortestPathsToLocation.put(aLocation, (pathList.size()>1?pathList.get(1).orig:pathList.get(0).orig));
			cheapestCostsToLocation.put(aLocation, new Double(getLocationVertex(aLocation, vertices).minDistance).intValue());
		}
		
		location.setShortestPaths(shortestPathsToLocation, cheapestCostsToLocation);
	}
	
	private static LocationVertex getLocationVertex(Location location, Set<LocationVertex> vertices) {
		for(LocationVertex aVertex : vertices) {
			if (aVertex.orig == location) {
				return aVertex;
			}
		}
		LocationVertex newLocationVertex = new LocationVertex(location);
		vertices.add(newLocationVertex);
		return newLocationVertex;
	}
	
	public static List<LocationVertex> getShortestPathTo(LocationVertex target) {
	    List<LocationVertex> path = new ArrayList<LocationVertex>();
	    for (LocationVertex location = target; location != null; location = location.previous)
	        path.add(location);
	    Collections.reverse(path);
	    return path;
	}
}

class LocationVertex implements Comparable<LocationVertex>
{
	public Location orig;
	
    public double minDistance = Double.POSITIVE_INFINITY;
    public LocationVertex previous;
    
    public LocationVertex(Location argLocation) { orig = argLocation; }
    public String toString() { return orig.getName(); }
    
    public int compareTo(LocationVertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}