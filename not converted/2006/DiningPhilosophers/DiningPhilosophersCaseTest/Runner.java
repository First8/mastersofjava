
public class Runner implements Runnable {

	private Philosopher p;
	private BowlOfNoodlesImpl bowl;
	private boolean stopped;
	private Exception failed;
	private Thread active;
	private String name;
	
	public Runner(Philosopher p,BowlOfNoodlesImpl bowl,String name) {
		this.p=p;
		this.name=name;
		this.bowl=bowl;
	}
	
	public void run() {
		active=Thread.currentThread();
		int cycle=0;
		try {
			while(!stopped) {
				//
				Thread.yield();
				bowl.clearNoodle();
				p.eat();
				cycle++;
				if (stopped) return;
				if (bowl.getNoodle()==null) throw new NullPointerException(name+" did not eat a noodle when he decided he wanted one.");
				if (!bowl.getNoodle().isEaten()) throw new NullPointerException(name+" took a noodle but did not eat it.");
				//
				Thread.yield();
				delay((long)(50+100.0*Math.random()));
				if (stopped) return;
				//
				Thread.yield();
				p.think();
				if (stopped) return;
				//
				Thread.yield();
				delay((long)(50+100.0*Math.random()));
				if (stopped) return;
				//
			}
		} catch (Exception ex) {
			if (ex.getCause() instanceof InterruptedException) {
				System.out.println(name+" was interrupted.");
			} else {
				System.out.println(ex.getMessage());
				failed=ex;
			}
		} finally {
			if (failed==null) {
				if (cycle==0) {
					failed=new RuntimeException(name+" did not eat anything.");								
				}
			}
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
	
	public Thread getThread() {
		return active;
	}
	
	public String toString() {
		return name;
	}
	
}