
/**
 * Exception can be thrown if the Mole is found!
 * 
 * @author Bart Postma (Sogeti)
 */
public class MoleDetectedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private final Contestant theMole;
	
	/**
	 * Constructor
	 * @param theMole
	 */
	public MoleDetectedException(Contestant theMole) {
		this.theMole = theMole;
	}
	
	public Contestant getTheMole() {
		return theMole;
	}
}
