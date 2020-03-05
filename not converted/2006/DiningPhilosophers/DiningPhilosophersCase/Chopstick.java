import java.util.concurrent.locks.Lock;

/**
 * A chopstick is a useful tool to take noodles out of a bowl.
 * Polite users of this tool acquire a lock on it first before
 * actually using it. 
 */

public interface Chopstick {

	/**
	 * The lock can be used to prevent multiple Philosophers from
	 * using the chopstick at the same time.
	 * @return the Lock.
	 */
	public Lock getLock();
	
}
