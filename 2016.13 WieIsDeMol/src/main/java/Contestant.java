import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a Contestant.
 * 
 * @author Bart Postma (Sogeti)
 */
public class Contestant {

	private final String name;
	private double moleRating = 0;
	private Set<Parcel> parcelsCarried = new HashSet<Parcel>();
	
	/**
	 * Constructor
	 * @param name Contestants name
	 * @param parcelsCarried Parcels the contestant has carried
	 */
	public Contestant(String name, Parcel... parcelsCarried) {
		this.name = name;
		this.parcelsCarried.addAll(Arrays.asList(parcelsCarried));
	}
	
	/**
	 * @return Contestants name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Rate indication of the likeliness of this contestant being the mole. The higher the value, the more probable the contestant is the mole.
	 * @return Mole rating
	 */
	public double getMoleRating() {
		return moleRating;
	}
	
	/**
	 * Mole rating setter
	 * @param moleRating Mole rating
	 */
	public void setMoleRating(double moleRating) {
		this.moleRating = moleRating;
	}
	
	/**
	 * Method for querying if the given parcel has been carried by the contestant.
	 */
	public boolean hasCarriedParcel(Parcel parcel) {
		return parcelsCarried.contains(parcel);
	}

}
