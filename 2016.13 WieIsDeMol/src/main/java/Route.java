import java.util.List;

/**
 * Class representing a contestants route.
 * A route consists of a list of RouteLocations the contestant has traveled.
 * @author Bart Postma (Sogeti)
 */
public class Route {

	private List<RouteLocation> routeLocations;
	
	public Route(List<RouteLocation> routeLocations) {
		this.routeLocations = routeLocations;
	}
	
	public List<RouteLocation> getRouteLocations() {
		return routeLocations;
	}
}
