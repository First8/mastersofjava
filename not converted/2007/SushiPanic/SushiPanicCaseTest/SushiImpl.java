import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SushiImpl implements Sushi {
	
	private String name;
	private boolean isEaten;
	private boolean isOnBelt;
	private ReentrantLock lock=new ReentrantLock();
	
	public SushiImpl(String name) {
		this.name=name;
	}
	
	public Lock getReservationLock() {
		// This increases the probability of a deadlock.
		// Which is what we want because its a purpose of
		// the assignment to write deadlock free code.
		Thread.yield();
		//
		return lock;
	}
	
	public void eat() {
		if (isOnBelt) throw new RuntimeException("But the Sushi is still on the belt.");
		if (isEaten) throw new RuntimeException("This Sushi is already eaten ?!");
		isEaten=true;
	}
	
	public boolean isEaten() {
		return isEaten;
	}

	protected void setOnBelt(boolean b) {
		isOnBelt=b;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
