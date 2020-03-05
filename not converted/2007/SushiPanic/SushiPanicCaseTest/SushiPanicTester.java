import nl.ctrlaltdev.io.OutputRedirector;
import nl.moj.model.Tester;

public class SushiPanicTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"A quiet day..",
			"Fighting Guests",
			"Rush Hour",
			"Bad Planning"
	};
	
	private static final String[][] GUEST_NAMES=new String[][] {
		{ "Lee" },
		{ "Lee","Hai" },
		{ "Anderson","vanderBerg","Lee","Chong" },
		{ "Wong" }
	};
	
	private static final int[] NR_OF_CHEFS=new int[] {
		1,1,2,3
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		int cnt=GUEST_NAMES[nr].length;
		sb.append("There ");
		sb.append(cnt==1?"is one guest":"are "+cnt+" guests");
		sb.append(" in the Sushi Restaurant.\n");
		sb.append(NR_OF_CHEFS[nr]==1?"A Single chef attempts":"Multiple chefs attempt");
		sb.append(" to keep up with demand.\n");
		sb.append("Make sure the guests do not try to steal or eat each others Sushi.\n");
		sb.append("Succes if :\n");
		sb.append("- Each guest eats at least 1 Sushi.\n");
		sb.append("Failure if a guest :\n");
		sb.append("- leaves the restaurant with an Exception.\n");
		sb.append("- tries to take a Sushi that is already taken.\n");
		sb.append("- cannot make up his mind and waits forever.\n");
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		int delay=250/NR_OF_CHEFS[nr];
		//
		SushiBeltImpl belt=new SushiBeltImpl();
		int nrOfGuests=GUEST_NAMES[nr].length;
		Thread[] tr=new Thread[nrOfGuests];
		SushiEaterRunner[] runners=new SushiEaterRunner[nrOfGuests];
		for (int t=0;t<nrOfGuests;t++) {
			SushiEaterImpl e=new SushiEaterImpl(GUEST_NAMES[nr][t]);
			belt.addBeltWatcher(e);
			runners[t]=new SushiEaterRunner(e,belt);
		}
		RealSushiChef chef=new RealSushiChef(belt);
		//
		//
		for (int t=0;t<runners.length;t++) {
			tr[t]=new Thread(Thread.currentThread().getThreadGroup(),runners[t]);
			tr[t].setDaemon(true);
			tr[t].setName(runners[t].getEater().toString());
			tr[t].setPriority(Thread.MIN_PRIORITY);
			// Redirect output of the new thread to the console of the player.
			OutputRedirector.getSingleton().redirectToSame(tr[t],Thread.currentThread());
			tr[t].start();
		}
		//
		boolean failed=false;
		//
		try {
			for (int t=0;t<10;t++) {
				//
				for (int y=0;y<NR_OF_CHEFS[nr];y++) {
					//
					chef.makeAnotherSushiAndPlaceItOnTheBelt();
					//
					SushiEaterRunner.delay(delay);
					//
				}
				//
			}
		} finally {
			//
			for (SushiEaterRunner s:runners) {
				s.stop();
			}
			for (SushiEaterRunner s:runners) {
				chef.makeAnotherSushiAndPlaceItOnTheBelt();
			}
			//
			SushiEaterRunner.delay(500);
			//
			System.out.println("------------------------------------------");
			for (SushiEaterRunner s:runners) {
				Exception aborted=s.stop();
				if (aborted==null) {
					if (s.getNrOfSushisEaten()==0) {
						System.out.println("X : "+s.getEater()+" did not eat any Sushi.");
						failed=true;
					} else {
						System.out.println("v : "+s.getEater()+" ate "+s.getNrOfSushisEaten()+" Sushi.");
					}
				} else {
					System.out.println("X : "+s.getEater()+" left the restaurant with an "+aborted+".");
					aborted.printStackTrace();
					failed=true;
				}
			}
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
			//
		}
		//
		return !failed;
	}
	
}
