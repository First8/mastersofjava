/**
 * Interface for the implementation of Tetris.
 */
public interface Tetris {

	/**
	 * Gets largest amount of rows that will collapse when the given block is
	 * put in the given game.
	 * 
	 * @param game
	 *            A game.
	 * @param block
	 *            A block.
	 * @return The number of rows.
	 */
	public int getMaxNumOfCollapsingRows(Game game, Block block);

}
