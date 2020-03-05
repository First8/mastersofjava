/**
 * Games and blocks are made of rows, which are made out of cells.
 */
public class Row {

	/** The cells of this row. */
	private Cell[] cells;

	/**
	 * Creates a row.
	 * 
	 * @param cells
	 *            The cells that make up this row.
	 */
	public Row(Cell[] cells) {
		if (cells == null) {
			throw new IllegalArgumentException(
					"Argument cells can not be null.");
		}
		if (cells.length == 0) {
			throw new IllegalArgumentException(
					"There must be any cells in a row.");
		}
		this.cells = cells;
	}

	/**
	 * Creates a row.
	 * 
	 * @param string
	 *            Something like <code>&quot;X&nbsp;XXX&nbsp;X&quot;</code>.
	 */
	public Row(String string) {
		if (string == null) {
			throw new IllegalArgumentException(
					"Argument cells can not be null.");
		}
		if (string.length() == 0) {
			throw new IllegalArgumentException(
					"There must be any cells in a row.");
		}
		this.cells = new Cell[string.length()];
		int i = 0;
		for (char c : string.toCharArray()) {
			this.cells[i++] = new Cell(c);
		}
	}

	/**
	 * Gets a specific cell.
	 * 
	 * @param index
	 *            The index of the cell to get.
	 * @return The cell.
	 */
	public Cell getCell(int index) {
		return this.cells[index];
	}

	/**
	 * @return The cells that make up this row.
	 */
	public Cell[] getCells() {
		return cells;
	}

	/**
	 * @return The width of this row, the same as the number of cells.
	 */
	public int getWidth() {
		return this.cells.length;
	}

	/**
	 * @return True when all cells of this row are full.
	 */
	public boolean isFull() {
		for (Cell cell : cells) {
			if (cell.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Replaces a cell in this row.
	 * 
	 * @param index
	 *            The index of the cell to replace.
	 * @param cell
	 *            The replacement.
	 */
	public void setCell(int index, Cell cell) {
		if (cell == null) {
			throw new IllegalArgumentException("The cell can not be null.");
		}
		this.cells[index] = cell;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('"');
		for (Cell cell : cells) {
			sb.append(cell.getFilling().getChar());
		}
		sb.append('"');
		return sb.toString();
	}

}
