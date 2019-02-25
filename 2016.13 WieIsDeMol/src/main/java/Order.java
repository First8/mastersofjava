
/**
 * Class representing an order. An order is a parcel and its destined location.
 * @author Bart Postma (Sogeti)
 */
public class Order {

	private final Parcel parcel;
	private final Location targetLocation;
	
	/**
	 * Constructor
	 * @param parcel
	 * @param targetLocation
	 */
	public Order(Parcel parcel, Location targetLocation) {
		this.parcel = parcel;
		this.targetLocation = targetLocation;
	}
	
	public Parcel getParcel() {
		return parcel;
	}
	
	public Location getTargetLocation() {
		return targetLocation;
	}
}
