import java.util.ArrayList;
import java.util.List;

public class SushiBeltImpl implements SushiBelt {

	private List<Sushi> belt=new ArrayList<Sushi>();
	private List<BeltWatcher> watchers=new ArrayList<BeltWatcher>();	
	
	public SushiBeltImpl() {		
	}
	
	public SushiBeltImpl(BeltWatcher[] bw) {
		for (int t=0;t<bw.length;t++) addBeltWatcher(bw[t]);
	}
	
	protected void addBeltWatcher(BeltWatcher bw) {
		watchers.add(bw);
	}
	
	public synchronized void place(Sushi s) {
		belt.add(s);
		BeltWatcher[] bw=watchers.toArray(new BeltWatcher[watchers.size()]);
		for (BeltWatcher w:bw) w.sushiPlaced(s);
	}
	
	public synchronized void take(Sushi s) {
		//
		Thread.yield();
		//
		if (!belt.remove(s)) {
			throw new RuntimeException(s+" is no longer on the belt.");
		} else {
			BeltWatcher[] bw=watchers.toArray(new BeltWatcher[watchers.size()]);
			for (BeltWatcher w:bw) w.sushiTaken(s);			
		}
	}
	
}
