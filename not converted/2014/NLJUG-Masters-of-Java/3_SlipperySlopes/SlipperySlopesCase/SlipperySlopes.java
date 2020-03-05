import java.util.List;

public interface SlipperySlopes {

	/**
	 * Returns the flattest simple path from {@code start} to {@code finish}. It is guaranteed
	 * that such a path indeed exists and no {@link WayPoint} is its own neighbor. The flattest simple path has the following properties:
	 * <ul>
	 *  <li>
	 *   It is a connected path from {@code start} (the first {@link WayPoint}) to {@code finish} (the last {@link WayPoint}). This means
	 *   that if {@code a} is followed by {@code b} in the path, then {@code a.getNeighbors().contains(b)} holds.
	 * 
	 * </li>
	 *  <li>It is a simple path: the return path must not contain a cycle</li> 
	 *  <li> The maximum absolute slope between any two neighboring {@link WayPoint}s in the flattest path 
	 *       is not greater than the maximum absolute slope of any other simple path from {@code start} to {@code finish}. 
	 *  </li>
	 * </ul> 
	 * 
	 * @param start the first {@link WayPoint} on the returned path
	 * @param finish the last {@link WayPoint} on the returned path
	 * @return the flattest path from {@code start} up to and including {@code finish}
	 */
	public abstract List<WayPoint> getFlattestSimplePath(WayPoint start, WayPoint finish);

}