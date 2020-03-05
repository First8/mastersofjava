/**
 * A Bowl of delicious noodles. 
 */

public interface BowlOfNoodles {

	/**
	 * takes a noodle out of the bowl using chopsticks.
	 * @param first the first chopstick to use.
	 * @param second the second chopstick to use.
	 * @return a noodle, ready for consumption.
	 */
	public Noodle take(Chopstick first,Chopstick second);
	
}
