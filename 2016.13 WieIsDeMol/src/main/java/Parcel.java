
/**
 * Class representing a Parcel
 * @author Bart Postma (Sogeti)
 */
public class Parcel {
	
	private final String id;
	
	/**
	 * Constructor
	 * @param id
	 */
	public Parcel(String id) {
		this.id = id;
	}
	
	public String toString() {
		return "<parcel:"+id+">";
	}
}