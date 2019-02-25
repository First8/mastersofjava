import java.util.Comparator;

/**
 * Comparator.
 * @author Bart Postma (Sogeti)
 */
public class ShortestPathLocationComparator implements Comparator<Location> {
	
	private Location targetLocation;
	private Location prevLocation;
	
	public ShortestPathLocationComparator(Location prevLocation, Location targetLocation) {
		this.prevLocation = prevLocation;
		this.targetLocation =  targetLocation;
	}
	
	@Override
	public int compare(Location arg0, Location arg1) {
		return Integer.compare(arg0.getTravelCostsToLocation(targetLocation) + prevLocation.getTravelCostsToNeighbor(arg0),
				arg1.getTravelCostsToLocation(targetLocation) + prevLocation.getTravelCostsToNeighbor(arg1));
	}
}