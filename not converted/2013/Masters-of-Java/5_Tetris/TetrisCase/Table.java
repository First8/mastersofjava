/**
 * Both a game and a block are pretty much the same. They contains rows and
 * cells. This Table class is the parent for both of them.
 */
public class Table {

	/** The rows in this table. */
	private Row[] rows;

	/**
	 * Constructs a table.
	 * 
	 * @param rows
	 *            The rows.
	 */
	public Table(Row[] rows) {
		if (rows == null) {
			throw new IllegalArgumentException("Argument rows can not be null.");
		}
		if (rows.length == 0) {
			throw new IllegalArgumentException(
					"There must be any rows in a table.");
		}
		this.rows = rows;
	}

	/**
	 * Gets a specific cell.
	 * 
	 * @param x
	 *            The index of the cell in the row.
	 * @param y
	 *            The index of the row in this table.
	 * @return The cell.
	 */
	public Cell getCell(int x, int y) {
		return getRow(y).getCell(x);
	}

	/**
	 * @return The height of this table, or the number of rows.
	 */
	public int getHeight() {
		return rows.length;
	}

	/**
	 * Gets a specific row.
	 * 
	 * @param index
	 *            The index of the row that must be returned.
	 * @return The row.
	 */
	public Row getRow(int index) {
		return this.rows[index];
	}

	/**
	 * @return The rows.
	 */
	public Row[] getRows() {
		return this.rows;
	}

	/**
	 * @return The widht of this table, or the number of cells in a row.
	 */
	public int getWidth() {
		return rows[0].getWidth();
	}

}
