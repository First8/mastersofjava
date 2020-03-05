import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public final class Solution implements SlipperySlopes{
	
	
	/**
	 * Returns the maximum absolute slope on the path
	 * @param path
	 * @return the maximum absolute slope on the path
	 */
	public static double maxAbsoluteSlope(List<WayPoint> path){
		double maxSlope=Double.MIN_VALUE;
		WayPoint from = path.get(0);
		for(WayPoint to : path.subList(1, path.size())){
			maxSlope = Math.max(maxSlope, from.absoluteSlope(to));
			from = to;
		}
		return maxSlope;
	}
	
	/**
	 * Recursively collects all simple paths from {@code start} to {@code finish} in {@code collectedPaths}
	 * @param start
	 * @param finish
	 * @param collectedPaths
	 * @param currentPath
	 */
	private void collectSimplePaths(WayPoint start, WayPoint finish, List<List<WayPoint>> collectedPaths, List<WayPoint> currentPath){
		if(start==finish){
			collectedPaths.add(new ArrayList<>(currentPath));
		} else {
			for(WayPoint nextStart : start.getNeighbors()){
				if(!currentPath.contains(nextStart)){
					currentPath.add(nextStart);
					collectSimplePaths(nextStart, finish, collectedPaths, currentPath);
					currentPath.remove(nextStart);
				}
			}
		}
	}
	

	/**
	 * @param paths
	 * @return the mapping {@code maxAbsoluteSlope(path)} &#65515; {@code path} for each {@code path} in {@code paths}
	 */
	private TreeMap<Double, List<WayPoint>> byAbsoluteSlope(List<List<WayPoint>> paths){
		final TreeMap<Double, List<WayPoint>> retval = new TreeMap<>();
		for(List<WayPoint> path : paths){
			retval.put(maxAbsoluteSlope(path), path);
		}
		return retval;
	}
	
	

	@Override
	public List<WayPoint> getFlattestSimplePath(WayPoint start, WayPoint finish){
		//collect all simple paths
		final List<WayPoint> currentPath = new ArrayList<>();
		currentPath.add(start);
		final List<List<WayPoint>> collectedPaths = new ArrayList<>();
		collectSimplePaths(start, finish, collectedPaths, currentPath);
		
		//return the flattest path
		return byAbsoluteSlope(collectedPaths).firstEntry().getValue();
	}

}