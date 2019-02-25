/**
 * The Parking Area. 
 */
public interface ParkingArea {

    /** @return all the vehicles on the parking area. */
	Vehicle[] getAllVehicles();
	
	/** @return true if the player vehicle is removed from the parking area. */
	boolean isCompleted();
	
	/** @return the width of the parking area. */
	int getWidth();
	
    /** @return the height of the parking area. */
	int getHeight();
	
	/** @return the vehicle at the specified coordinates or null. */
	Vehicle getVehicleAt(int x,int y);
	
	/** @return the player vehicle. */
	Vehicle getPlayerVehicle();
	
	/** @return a throw away copy of the parking area, including its vehicles. */ 
	ParkingArea copy();
	
}
