import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class OwnedReentrantLock extends ReentrantLock {

	private static final long serialVersionUID = -7688599450972320183L;

	public OwnedReentrantLock() {
		super();
	}

	public OwnedReentrantLock(boolean fair) {
		super(fair);
	}
	
	@Override
	public Thread getOwner() {
		return super.getOwner();
	}
	
	@Override
	public void lock() {
		Runner.delay(42);
		super.lock();
	}
	
	public boolean tryLock() {
		Runner.delay(42);
		return super.tryLock();
	}
	
	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		Runner.delay(42);
		return super.tryLock(timeout, unit);
	}

}
