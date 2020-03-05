import java.util.concurrent.locks.Lock;

/**
 * "My advice to you is not to inquire why or whither, 
 *  but just enjoy your noodle while it's on your plate
 *  — that's my philosophy." 
 */
public class PhilosopherImpl implements Philosopher {

	private String name;
	private Chopstick left;
	private Chopstick right;
	private BowlOfNoodles bowl;

	/**
	 * constructs a new Philosopher with a name, two chopsticks and a bowl with
	 * an endless supply of noodles.
	 */
	public PhilosopherImpl(String name,Chopstick left,Chopstick right,BowlOfNoodles bowl) {
		this.name=name;
		this.left=left;
		this.right=right;
		this.bowl=bowl;
	}
	/**
	 * Before serious thinking one has to eat. So here the
	 * philosopher take his chopsticks, acquires some food
	 * and eats it. Not eating something is not an option 
	 * unless an exception occurs.
	 */
	public void eat() {
		//
		// TODO: Implement politely.  
		//
		bowl.take(left, right).eat();
		//
	}
	
	/**
	 * The philosopher meanders about the meaning of life, 
	 * the universe and the rest  
	 */
	public void think() {
		System.out.println(this+" thinks.");		
	}

	/**
	 * Utility method to wait for a while.
	 */
	private void pause() { 
		try {
			Thread.sleep((long)(100+100*Math.random()));
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * renders a string representation.
	 */
	public String toString() {
		return name;
	}
	
}
