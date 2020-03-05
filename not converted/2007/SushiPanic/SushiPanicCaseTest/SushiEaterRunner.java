
public class SushiEaterRunner implements Runnable {

	private SushiEater eater;
	private SushiBelt belt;
	private boolean stopped;
	private Exception failed;
	private int nrOfSushisEaten;
	private Thread active;
	
	public SushiEaterRunner(SushiEater e,SushiBelt belt) {
		this.eater=e;
		this.belt=belt;
	}
	
	public void run() {
		active=Thread.currentThread();
		try {
			while(!stopped) {
				//
				Sushi s=eater.select();
				if (stopped) return;
				if (s==null) throw new NullPointerException(eater+" did not pick a Sushi.");
				Thread.yield();
				//
				eater.take(belt,s);
				if (stopped) return;
				Thread.yield();
				//
				eater.eat(s);
				if (stopped) return;
				if (s.isEaten()) nrOfSushisEaten++;
				Thread.yield();
				//
			}
		} catch (Exception ex) {
			failed=ex;
		}
	}
	
	public Exception stop() {	
		stopped=true;
		if (active!=null) active.interrupt();
		return failed;
	}
	
	public static void delay(long d) {
		try {
			Thread.sleep(d);
		} catch (InterruptedException ex) {
			return;
		}
	}
	
	public int getNrOfSushisEaten() {
		return nrOfSushisEaten;
	}
	
	public SushiEater getEater() {
		return eater;
	}
}
