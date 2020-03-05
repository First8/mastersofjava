import nl.moj.client.anim.Anim;
import nl.moj.client.anim.LayeredAnim;
import nl.moj.model.Tester;

public class TrafficTester implements Tester.AnimatedTestable {
	
	private static final String[] NAMES=new String[] {
			"Easy does it",
			"Block Away!",
			"Double Blocked",
			"The More, The Messier",
			"[fuup]",
	};
	
	private static final int[][] BOARDS=new int[][] {
		{ 2,1 },
		{ 3,3 },
		{ 3,3 },
		{ 4,4 },
		{ 6,6 }
	};
	
	private static final int[][][] PIECES=new int[][][] {
		{ {0,0,2,0} },
		{ {0,0,2,0},{2,0,2,1} },
		{ {0,0,2,0},{2,0,2,1},{1,2,2,0} },
		{ {1,1,2,0},{0,0,3,0},{3,0,2,1},{0,2,2,1},{1,3,3,0} },
		{ {1,2,2,0},{0,0,2,0},{5,0,3,1},{0,1,3,1},{3,1,3,1},{0,4,2,1},{4,4,2,0},{2,5,3,0} }
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append("Drive the red car (#1) off the ParkingArea using the indicated exit.\n\n");
        ParkingAreaImpl pa=new ParkingAreaImpl(BOARDS[nr][0],BOARDS[nr][1],PIECES[nr]);
        sb.append(pa);
        sb.append("\nIf another car is blocking the exit, it needs to be moved out of the\n");
        sb.append("way first. Vehicles can only move in the direction they are parked in\n");
        sb.append("and cannot collide or jump over each other.\n\n");
        sb.append("The ParkingArea is considered solved when the red car is completely off\n");
        sb.append("parking area.");
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
		// Assume the worst
		//
		boolean result=false;
		//
		// Create a new Instance for each test.
		//
		try {
			//
			ParkingAreaImpl pa=new ParkingAreaImpl(BOARDS[nr][0],BOARDS[nr][1],PIECES[nr]);
			pa.createShapes(anim);
			pa.renderFrame(anim);
			new ValetParkingAssistantImpl().solve(pa);
			result=pa.isCompleted();
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
		return result;
	}
	
}
