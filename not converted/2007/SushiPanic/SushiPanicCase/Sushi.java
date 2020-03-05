import java.util.concurrent.locks.Lock;

/**
 * Describes Delicious Sushi in all its forms. 
 */
public interface Sushi {

	/**
	 * Eats the Sushi.
	 * If you want to eat the Sushi you must first take it from the belt. 
	 */
	void eat();
	
	/**
	 * @return true if the Sushi is eaten.
	 */
	boolean isEaten();
	
	/**
	 * @return a Lock which can be used to lock a Sushi to a specific SushiEater.
	 */
	Lock getReservationLock();
	
}
