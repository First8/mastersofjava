import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.moj.model.Tester;

public class SlipperySlopesTester implements Tester.Testable {
	
	private static final Solution SOLUTION = new Solution();
	
	private static final String[] NAMES=new String[]{
		"waypoints existence test",
		"simpleness test",
		"connectedness test",
		"from start to finish test",
		"least maximal absolute slope test",
	};	
	
	private static final WayPoint 	START = new WayPoint(2, 4, 0),
									A = new WayPoint(3, 3, -1),
									B = new WayPoint(4, 2, 2),
									C = new WayPoint(5, 1, 1),
									D = new WayPoint(3, 5, 0),
									E = new WayPoint(4, 7, 2),
									F = new WayPoint(5, 3, 3), 
									FINISH = new WayPoint(10, 4, 0);
	
	private static final Collection<WayPoint> WAYPOINTS = Arrays.asList(START,A,B,C,D,E,F,FINISH);
	private static final List<WayPoint> FLATTEST_SIMPLE_PATH;
	private static final double LEAST_MAX_ABSOLUTE_SLOPE;

	static {
		START.getNeighbors().add(A);
		START.getNeighbors().add(D);
		C.getNeighbors().add(FINISH);
		F.getNeighbors().add(FINISH);
		
		final WayPoint[] completeSubGraph = {A,B,C,D,E,F};
		for(WayPoint from : completeSubGraph){
			for(WayPoint to : completeSubGraph){
				if(from!=to){
					from.getNeighbors().add(to);
				}
			}
		}
		
		for(WayPoint wp : WAYPOINTS){
			wp.seal();
		}
		
		FLATTEST_SIMPLE_PATH = SOLUTION.getFlattestSimplePath(START, FINISH);
		LEAST_MAX_ABSOLUTE_SLOPE = Solution.maxAbsoluteSlope(FLATTEST_SIMPLE_PATH);
	}

	private static boolean wayPointsExist(List<WayPoint> path){
		return WAYPOINTS.containsAll(path);
	}
	
	private static boolean isSimple(List<WayPoint> path){
		return new HashSet<WayPoint>(path).size()==path.size();
	}
	
	private static boolean isConnected(List<WayPoint> path){
		WayPoint from = path.get(0);
		for(WayPoint to : path.subList(1, path.size())){
			if(!from.getNeighbors().contains(to)){
				return false;
			}
			from = to;
		}
		return true;
	}
	
	private static boolean isFromStartToFinish(List<WayPoint> path){
		return path.get(0).equals(START) && path.get(path.size()-1)==FINISH;
	}
	
	private static boolean hasLeastMaxAbsoluteSlope(List<WayPoint> path){
		return Solution.maxAbsoluteSlope(path) <= LEAST_MAX_ABSOLUTE_SLOPE;
	}
	
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		switch(nr){
			case 0 : return "tests that all waypoints in the path returned by your solution exist. Otherwise you could cheat by creating your own....";
			case 1 : return "tests that the path returned by your solution is simple: it must not contain cycles.";
			case 2 : return "tests that the path returned by your solution is connected.";
			case 3 : return "tests that the path returned by your solution is from start to finish.";
			case 4 : return "tests that the path returned by your solution has the least maximum slope out of all possible paths from start to finish.";
			default : throw new IllegalArgumentException("invalid test number");
		}
	}
	
	public boolean performTest(int nr) throws Throwable {
		try {
			final List<WayPoint> path = new SlipperySlopesImpl().getFlattestSimplePath(START, FINISH);
			switch(nr){
				case 0 : return wayPointsExist(path);
				case 1 : return isSimple(path);
				case 2 : return isConnected(path);
				case 3 : return isFromStartToFinish(path);
				case 4 : return hasLeastMaxAbsoluteSlope(path);
				default : throw new IllegalArgumentException("invalid test number");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) throws Throwable{
		for(int i=0; i< NAMES.length; i++){
			if(!new SlipperySlopesTester().performTest(i)){
				throw new IllegalStateException("test " + i + " failed");
			}
		}
	}
}
