import java.util.concurrent.locks.Lock;

public class ChopstickImpl implements Chopstick {

	private Lock innerLock=new OwnedReentrantLock();
	private Lock outerLock=new OwnedReentrantLock();
	
	public ChopstickImpl() {
		//
	}

	public Lock getLock() {
		return outerLock;
	}
	
	Lock getOuterLock() {
		return outerLock;
	}
	
	Lock getInnerLock() {
		return innerLock;
	}
	
}
