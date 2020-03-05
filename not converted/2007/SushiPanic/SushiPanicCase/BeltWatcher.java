
public interface BeltWatcher {

	/**
	 * called when a sushi is taken from the belt.
	 * @param s the sushi that was taken.
	 */
	void sushiTaken(Sushi s);

	/**
	 * called when a new sushi is placed on the belt.
	 * @param s the sushi.
	 */
	void sushiPlaced(Sushi s);
	
}
