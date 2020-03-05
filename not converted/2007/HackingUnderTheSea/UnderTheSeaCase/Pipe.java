/**
 * The six different pipes and a block.  
 */
public enum Pipe {
    
	/** a block blocks the flow. Blocks cannot be swapped. */
	BLOCK,
	/** a horizontal pipe. */
	HORIZONTAL,
	/** a bended pipe connecting the left with the top sides. */
	LEFT2TOP,
	/** a bended pipe connecting the left with the bottom sides. */
	LEFT2BOTTOM,
	/** a bended pipe connecting the right with the top sides. */
	RIGHT2TOP,
	/** a bended pipe connecting the right with the bottom sides. */
	RIGHT2BOTTOM,
	/** a vertical pipe. */
	VERTICAL;
	
}
