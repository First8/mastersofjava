import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a RouteLocation.
 * A RouteLocation is a location that is part of a contestants route, i.e., the path a contestant has traveled.
 * Besides a location a RouteLocation also contains all the parcels that were on that location at the time the contestant
 * was at that location.
 * 
 * @author Bart Postma (Sogeti)
 */
public class RouteLocation {
	
	private final Location location;
	private final Set<Parcel> parcelsAtLocation = new HashSet<Parcel>();
	
	public RouteLocation(Location location, Parcel... parcelsAtLocation) {
		this.location = location;
		this.parcelsAtLocation.addAll(Arrays.asList(parcelsAtLocation));
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Set<Parcel> getParcelsAtLocation() {
		return parcelsAtLocation;
	}
}
