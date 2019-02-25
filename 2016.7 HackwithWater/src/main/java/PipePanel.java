import java.awt.Point;

public interface PipePanel {
	
	/** @return the width of the pipe panel. */
	int getWidth();
	/** @return the height of the pipe panel. */
	int getHeight();
	
	/** @return the pipe at the specified coordinates. */
	Pipe get(Point p);
	
	/** swaps the two pipes at the specified coordinates. */
	void swap(Point start,Point end);
	
	/**
	 * returns the next flow position given the current and previous positions.
	 * @param current the current position.
	 * @param previous the previous position (may be null).
	 * @return the next position.
	 * @throws DeadEndException if the flow hits a dead end.
	 */
	Point flow(Point current,Point previous);
	/**
	 * returns the next flow position with a hypothetical pipe.
	 * @param current the current position.
	 * @param previous the previous position (may be null).
	 * @param atCurrent the hypothetical pipe at the current position.
	 * @return the next position.
	 * @throws DeadEndException if the flow hits a dead end.
	 */
	Point flow(Point current,Point previous,Pipe atCurrent);
	/** @return true if the device is hacked. */
	boolean isHacked();
	
	/** @return the start point. */
	Point getStart();
	/** @return the end point. */
	Point getEnd();

	/** @return the number of pipes in the panel of the specified type. */
	int count(Pipe p);
}
