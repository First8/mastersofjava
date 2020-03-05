
import nl.ctrlaltdev.io.OutputRedirector;
import nl.moj.client.anim.Anim;
import nl.moj.client.anim.LayeredAnim;
import nl.moj.model.Tester;

public class DiningPhilosophersTester implements Tester.AnimatedTestable {
	
	private static final String[] NAMES=new String[] {
			"A lone night",
			"Dinner for two",
			"Proper Company",
			"Dinner Party"
	};
	
	private static final String[][] PHILOSOPHERS=new String[][] {
		{ "Socrates" },
		{ "Plato","Aristotle"},
		{ "Plotinus","Descartes","Machiavelli" },
		{ "Plotinus","Descartes","Machiavelli","Sartre","Hume" },
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}

	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		String[] names=PHILOSOPHERS[nr];
		if (names.length==1) {
			sb.append(names[0]+" is by himself tonight. He borrowed Plato's chopstick so that he can still eat noodles.\n");
			sb.append("Make sure that:\n");
			sb.append("- for each call to eat() a noodle gets eaten.\n");
		} else {
			sb.append(names.length+" philosophers sit around a big bowl of noodles. Each philosopher has one chopstick.\n");
			sb.append("To take a noodle from the bowl they need two chopsticks so they decide that each philosopher\n");
			sb.append("shares his chopstick with his right neighbour.\n");
			sb.append("Make sure that:\n");
			sb.append("- for each call to eat() a noodle gets eaten.\n");
			sb.append("- no philosopher tries to use a chopstick that is in use by another philosopher.\n");
			sb.append("- the philosophers do not end up waiting for each other (deadlock).\n");
		}
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		return performTest(nr,new Anim[NAMES.length]);
	}

	public boolean performTest(int nr, Anim[] a) throws Throwable {
		//
		// Set up the animation
		//
		LayeredAnim anim=new LayeredAnim();
		a[nr]=anim;
		//
		// Create a new Instance for each test.
		//
		try {
			BowlOfNoodlesImpl bowl=new BowlOfNoodlesImpl(anim);
			Runner[] r=new Runner[PHILOSOPHERS[nr].length];
			Philosopher[] ph=new Philosopher[PHILOSOPHERS[nr].length];
			ChopstickImpl[] chops=new ChopstickImpl[r.length+1];
			for (int t=0;t<chops.length;t++) {
				chops[t]=new ChopstickImpl();
			}
			if (r.length>1) {
				// If more than 1 eater, make the chopsticks circular.
				chops[chops.length-1]=chops[0];
			}
			//
			for (int t=0;t<r.length;t++) {
				String name=PHILOSOPHERS[nr][t];
				ph[t]=new PhilosopherImpl(name,chops[t],chops[t+1],bowl);
				r[t]=new Runner(ph[t],bowl,name);
			}
			bowl.setRunners(r,chops);
			//
			// Redirect
			//
			Thread[] tr=new Thread[r.length];
			for (int t=0;t<r.length;t++) {
				tr[t]=new Thread(Thread.currentThread().getThreadGroup(),r[t]);
				tr[t].setDaemon(true);
				tr[t].setName(PHILOSOPHERS[nr][t]);
				tr[t].setPriority(Thread.MIN_PRIORITY);
				// Redirect output of the new thread to the console of the player.
				OutputRedirector.getSingleton().redirectToSame(tr[t],Thread.currentThread());
				tr[t].start();
			}
			//
			boolean failed=false;
			//
			try {
				//
				Runner.delay(3000);
				//
			} finally {
				//
				//
				for (Runner rr:r) {
					rr.stop();
				}
				//
				Runner.delay(1000);
				//
				System.out.println("------------------------------------------");
				for (Runner s:r) {
					Exception aborted=s.stop();
					if (aborted!=null) {
						System.out.println("X : "+s+" left the table with an "+aborted+".");
						aborted.printStackTrace(System.out);
						failed=true;
					}
				}
				//
				bowl.render();
				//
				for (Thread t:tr) {
					if (t.isAlive()) {
						System.out.println("X : "+t.getName()+" is deadlocked.");
						failed=true;
						t.stop();
					}
					// Cancel the redirection.
					OutputRedirector.getSingleton().cancel(t);				
				}				
			}
			//
			return !failed;
			//
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
			ex.printStackTrace();
			//
			return false;
		}
		//
	}
	
}
