/**
 * The revolving Sushi belt - just have a peek to see if something
 * nice is available and then take it from the belt.
 * You may assume this belt is thread safe.  
 */

public interface SushiBelt {
	
	/**
	 * places a Sushi on the belt.
	 * @param s the sushi to place.
	 */
	void place(Sushi s);
	
	/**
	 * Takes a Sushi from the Belt.
	 * Throws an exception if the Sushi is not there, so make
	 * sure the sushi is yours to take. 
	 * @param s the Sushi to take.
	 */
	void take(Sushi s); 
	
}
