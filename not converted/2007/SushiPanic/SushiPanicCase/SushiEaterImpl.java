import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
/**
 * Actual implementation of the SushiEater interface. 
 */
public class SushiEaterImpl implements SushiEater, BeltWatcher {

	private String name;
	
	private Set<Sushi> availableSushi=new HashSet<Sushi>(); 
	
	public SushiEaterImpl(String name) {
		this.name=name;
	}
	
	/** 
	 * Selects a sushi to take from the belt.
	 * If there are no sushi available it must wait until there are.
	 * @param belt the belt to select a sushi from.
	 * @return the picked sushi. 
	 */
	public synchronized Sushi select() {
		//
		// TODO : Make thread safe, idiot proof etc.
		//
		while (true) {
			Sushi[] s=availableSushi.toArray(new Sushi[availableSushi.size()]);
			for (int t=0;t<s.length;t++) {
				if (s[t]!=null) {
					if (s[t].getReservationLock().tryLock()) {
						return s[t];
					}
				}
			}
			try {
				this.wait();
			} catch (InterruptedException ex) {
				//ex.printStackTrace();
			}
		}		
	}
	
	/** 
	 * Takes a sushi from the belt. 
	 * @param belt the belt to take the sushi from.
	 * @param the previously picked sushi.
	 * @return the taken sushi.
	 */	
	public void take(SushiBelt belt,Sushi s) {
		belt.take(s);
	}
	
	/** 
	 * Eats the sushi (finally !).
	 * @param s the previously taken sushi. 
	 */	
	public void eat(Sushi s) {
		s.eat();
		System.out.println(name+" eats "+s);
	}

	public synchronized void sushiPlaced(Sushi s) {
		availableSushi.add(s);
		this.notifyAll();
	}
	
	public synchronized void sushiTaken(Sushi s) {
		availableSushi.remove(s);
	}
	
	/** renders a string representation. */ 
	public String toString() {
		return name;
	}

}
