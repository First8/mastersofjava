import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class WieIsDeMolImpl {

	private final Set<Order> orders;
	private final Map<Contestant, Route> log;
	
	private final PriorityQueue<Contestant> contestants = new PriorityQueue<Contestant>(6, new WieIsDeMolComparator());
	
	public WieIsDeMolImpl(Set<Order> orders, Map<Contestant, Route> log) {
		this.orders = orders;
		this.log = log;
	}
	
	/**
	 * Method to return the MOLE!
	 * @return The mole contestant!
	 */
	public Contestant wieIsDeMol() {
		contestants.addAll(log.keySet());
		
		for(Contestant aContestant : log.keySet()) {
			try {
				determineMoleRating(aContestant);
			} catch(MoleDetectedException e) {
				System.out.println("Mole detected ! [theMole="+e.getTheMole().getName()+"]");
				return e.getTheMole();
			}
		}
		
		printContestantsQueue();
		
		// Return the contestant with the highest mole rating!
		PriorityQueue<Contestant> queue = new PriorityQueue<Contestant>(6, new WieIsDeMolComparator());
		queue.addAll(contestants);
		return queue.poll();
	}
	
	private void printContestantsQueue() {
		PriorityQueue<Contestant> printQueue = new PriorityQueue<Contestant>(6, new WieIsDeMolComparator());
		printQueue.addAll(contestants);
		while(printQueue.peek() != null) {
			Contestant aCont = printQueue.poll();
			System.out.println(aCont.getName() + ": " + aCont.getMoleRating());
		}
	}

	private void determineMoleRating(Contestant contestant) throws MoleDetectedException {
		Route route = log.get(contestant);
		Map<Parcel, List<Location>> parcelRoutes = getParcelRoutesForContestant(contestant, route);
		
		// Tip: check parcel routes against orders to detect wrong delivered parcels.
		
		double moleRating = getParcelRoutesRating(contestant, parcelRoutes);
		System.out.println("moleRating(" + contestant.getName() + ") = " + moleRating);

		//set mole rating for contestant!
		contestant.setMoleRating(moleRating);
	}
	
	private double getParcelRoutesRating(Contestant contestant, Map<Parcel, List<Location>> parcelRoutes) throws MoleDetectedException {
		int moleRating = 0;
		
		//
		// Implement your solution here!
		//
		
		return moleRating;
	}
	
	/**
	 * Obtain parcel routes for given contestant.
	 */
	private Map<Parcel, List<Location>> getParcelRoutesForContestant(Contestant contestant, Route route) {
		Map<Parcel, List<Location>> parcelRoutes = new HashMap<Parcel, List<Location>>();
		for(RouteLocation aRouteLocation : route.getRouteLocations()) {
			for(Parcel aParcel : aRouteLocation.getParcelsAtLocation()) {
				if(contestant.hasCarriedParcel(aParcel)) {
					if(parcelRoutes.containsKey(aParcel)) {
						parcelRoutes.get(aParcel).add(aRouteLocation.getLocation());
					} else {
						List<Location> newParcelRoute = new ArrayList<Location>();
						newParcelRoute.add(aRouteLocation.getLocation());
						parcelRoutes.put(aParcel, newParcelRoute);
					}
				}
			}
		}
		
		return parcelRoutes;
	}
	
	private Order findOrderForParcel(Parcel parcel) {
		Order result = null;
		for(Order anOrder : orders) {
			if(anOrder.getParcel().equals(parcel)) {
				result = anOrder;
				break;
			}
		}
		
		return result;
	}
}