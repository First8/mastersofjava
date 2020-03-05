
/**
 * Implements the rules of Conway's Game of Life:
 * - Any live cell with fewer than two live neighbours dies, as if by loneliness.
 * - Any live cell with more than three live neighbours dies, as if by overcrowding.
 * - Any live cell with two or three live neighbours lives, unchanged, to the next generation.
 * - Any dead cell with exactly three live neighbours comes to life.
 */
public class LifeImpl implements Life {

    /**
     * produces the next generation based upon the current generation.
     * @param current the current generation.
     * @return the next generation.
     */
	public boolean[][] getNextGeneration(boolean[][] current) {
	    //
	    // TODO: Implement
	    //
		return current;
	}

}
