import java.util.HashSet;
import java.util.Set;

public class SudokuImpl {
	private static final int VALUE_MAX = 9;
	private static final int VALUE_MIN = 1;
	private static final int VALUES_COUNT = VALUE_MAX - VALUE_MIN + 1;
	private static final int FIELD_WIDTH = VALUES_COUNT;
	private static final int FIELD_HEIGHT = FIELD_WIDTH;
	private static final int BOX_HEIGHT = (int) Math.round(Math
			.sqrt(VALUES_COUNT));
	private static final int BOX_WIDTH = BOX_HEIGHT;
	private Integer[][] field;

	public SudokuImpl(Integer[][] field) {
		this.field = field;

	}

	public boolean isValid() {
		boolean isValid = false;

		isValid = validateGrid();
		if (isValid) {
			isValid = validateRows();
		}

		if (isValid) {
			isValid = validateColumns();
		}

		if (isValid) {
			isValid = validateBoxes();
		}

		return isValid;

	}

	/**
	 * Validate whether the provided Grid is valid.
	 * 
	 * Checks the height, width and cell values
	 * 
	 * @return true if valid, false otherwise
	 */
	private boolean validateGrid() {
		// implement me

		return false;
	}

	/**
	 * Validate the grid rows
	 * 
	 * @return true if all rows are valid, false otherwise
	 */
	private boolean validateRows() {
		// implement me

		return false;
	}

	/**
	 * Validate the grid columns
	 * 
	 * @return true if all columns are valid, false otherwise
	 */
	private boolean validateColumns() {
		// implement me

		return false;
	}

	/**
	 * Validate the grid boxes
	 * 
	 * @return true if all boxes are valid, false otherwise
	 */
	private boolean validateBoxes() {
		// implement me

		return false;
	}
}