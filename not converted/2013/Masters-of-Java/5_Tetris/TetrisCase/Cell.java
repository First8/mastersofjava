/**
 * A cell is the thing rows and blocks are made of.
 */
public class Cell {

	/** The inside of a cell. */
	private Filling filling = Filling.EMPTY;

	/**
	 * Creates a cell.
	 * 
	 * @param ch
	 *            The graphical representation of the filling of the cell.
	 */
	public Cell(char ch) {
		Filling filling = Filling.valueOf(ch);
		validateAndSetFilling(filling);
	}

	/**
	 * Creates a cell.
	 * 
	 * @param filling
	 *            The inside of a cell.
	 */
	public Cell(Filling filling) {
		validateAndSetFilling(filling);
	}

	/**
	 * @return The inside of a cell.
	 */
	public Filling getFilling() {
		return filling;
	}

	/**
	 * @return true when the cell is not filled.
	 */
	public boolean isEmpty() {
		return Filling.EMPTY.equals(filling);
	}

	/**
	 * @param filling
	 *            The inside of a cell.
	 */
	public void setFilling(Filling filling) {
		validateAndSetFilling(filling);
	}

	/**
	 * Validates before setting the filling because a cell must have a filling.
	 * 
	 * @param filling
	 *            The inside of a cell.
	 */
	private void validateAndSetFilling(Filling filling) {
		if (filling == null) {
			throw new IllegalArgumentException(
					"The filling of a cell can not be null.");
		}
		this.filling = filling;
	}

}
