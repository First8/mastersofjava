/**
 * A game is a representation of the state a game of Tetris is in.
 */
public class Game extends Table {

	/**
	 * Creates a game.
	 * 
	 * @param rows
	 *            The rows for this game.
	 */
	public Game(Row[] rows) {
		super(rows);
	}

	/**
	 * Creates a copy of this game. Tip: use this method in combination with
	 * {@link #placeBlock(Block, int, int)}.
	 * 
	 * @return A copy of this game.
	 */
	public Game copy() {
		Row[] newRows = new Row[this.getHeight()];
		for (int y = 0; y < this.getHeight(); y++) {
			Cell[] newCells = new Cell[this.getWidth()];
			for (int x = 0; x < this.getWidth(); x++) {
				Cell newCell = new Cell(
						this.getRows()[y].getCells()[x].getFilling());
				newCells[x] = newCell;
			}
			Row newRow = new Row(newCells);
			newRows[y] = newRow;
		}
		Game newGame = new Game(newRows);
		return newGame;
	}

	/**
	 * Puts a block in this game. Tip: use {@link #copy()} before this method.
	 * 
	 * @param block
	 *            The block to put in this game.
	 * @param colNr
	 *            Where to put the block.
	 * @param rowNr
	 *            Where to put the block.
	 */
	public void placeBlock(Block block, int colNr, int rowNr) {
		for (int y = 0; y < block.getHeight(); y++) {
			for (int x = 0; x < block.getWidth(); x++) {
				if (!block.getCell(x, y).isEmpty()) {
					this.getCell(colNr + x, rowNr + y).setFilling(Filling.FULL);
				}
			}
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Row row : getRows()) {
			sb.append('|');
			for (Cell cell : row.getCells()) {
				sb.append(cell.getFilling().getChar());
			}
			sb.append("|\n");
		}
		sb.append('+');
		for (int i = 0; i < getWidth(); i++) {
			sb.append("-");
		}
		sb.append('+');
		return sb.toString();
	}

}
