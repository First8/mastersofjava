
public interface Table {

	/** constant indicating the table head seat. (for leaders only) */
	int TABLE_HEAD=0;
	
	/** @return the number of seats on this table. */
	int getNumberOfSeats();
	
	/** 
	 * places a diplomat at the specified position.
	 * may also be used to remove a diplomat from its seat (use null). 
	 */
	void place(int position,Diplomat d);
	
	/** retrieves the diplomat at the specified position. */
	Diplomat getDiplomatAt(int position);

	/** @return true if the table is fully seated. */
	boolean isFull();
	
	/** @return true if the seat at the specified position is taken. */
	boolean isSeatTaken(int position);
	
}
