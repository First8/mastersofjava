/**
 * Describes a vehicle on the parking area. 
 */
public interface Vehicle {
    
	/** @return the x position of the vehicle. */
	int getX();
	
	/** @return the y position of the vehicle. */
	int getY();
	
	/** @return the width of the vehicle. */
	int getLength();
	
	/** @return true if the vehicle moves horizontally. */
	boolean isHorizontal();
	
	/** @return true if the vehicle moves vertically. */
	boolean isVertical();
	
	/** @return true if this is the player vehicle. */
	boolean isPlayer();
	
	/** @return true if the vehicle can move the specified number of steps.
	 * positive numbers indicate forward movement, negative indicate backward
	 * movement. */
	boolean canMove(int steps);
	
	/** @return true if the vehicle can be moved at all. */
	boolean canMove();
	
	/** 
	 * Moves the vehicle the indicated number of steps. 
	 * Positive is forward, negative is backward.
	 * @param steps the number of steps.
	 */
	void move(int steps);
}
