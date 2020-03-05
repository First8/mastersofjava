package nl.equens.tester;
import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
	private static final int VALUE_MAX = 9;
	private static final int VALUE_MIN = 1;
	private static final int VALUES_COUNT = VALUE_MAX - VALUE_MIN + 1;
	private static final int FIELD_WIDTH = VALUES_COUNT;
	private static final int FIELD_HEIGHT = FIELD_WIDTH;
	private static final int BOX_HEIGHT = (int) Math.round(Math
			.sqrt(VALUES_COUNT));
	private static final int BOX_WIDTH = BOX_HEIGHT;
	private Integer[][] field;

	public SudokuValidator(Integer[][] field) {
		this.field = field;

	}

	public boolean isValid() {
		boolean isValid = true;

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
		if (field.length != FIELD_HEIGHT) {
			return false;
		}

		if (field[0].length != FIELD_WIDTH) {
			return false;
		}

		if (validateCells()) {
			return false;
		}

		return true;
	}

	
	/**
	 * Validate whether the provided cells are valid.
	 * 
	 * @return true if valid, false otherwise
	 */
	private boolean validateCells() {
		for (Integer[] row : field) {
			for (Integer value : row) {
				if (value == null || value < VALUE_MIN || value > VALUE_MAX) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Validate the grid rows
	 * 
	 * @return true if all rows are valid, false otherwise
	 */
	private boolean validateRows() {
		for (Integer[] row : field) {
			boolean isValidRow = validateRow(row);
			if (!isValidRow) {
				return false;
			}
		}

		return true;
	}

	private boolean validateRow(Integer[] row) {
		Set<Integer> hits = new HashSet<>();
		for (int value : row) {
			hits.add(value);
		}
		return hits.size() == VALUES_COUNT;
	}

	/**
	 * Validate the grid columns
	 * 
	 * @return true if all columns are valid, false otherwise
	 */
	private boolean validateColumns() {
		for (int colIndex = 0; colIndex < FIELD_WIDTH; colIndex++) {
			boolean isValidColumn = validateColumn(colIndex);
			if (!isValidColumn) {
				return false;
			}
		}

		return true;
	}

	private boolean validateColumn(final int colIndex) {
		Set<Integer> hits = new HashSet<>();
		for (int rowIndex = 0; rowIndex < FIELD_HEIGHT; rowIndex++) {
			hits.add(field[rowIndex][colIndex]);
		}
		return hits.size() == VALUES_COUNT;
	}

	/**
	 * Validate the grid boxes
	 * 
	 * @return true if all boxes are valid, false otherwise
	 */
	private boolean validateBoxes() {
		for (int cellStartRow = 0; cellStartRow < FIELD_HEIGHT; cellStartRow += BOX_HEIGHT) {
			for (int cellStartCol = 0; cellStartCol < FIELD_WIDTH; cellStartCol += BOX_WIDTH) {
				boolean isValidCell = validateBox(cellStartRow, cellStartCol);
				if (!isValidCell) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean validateBox(final int cellStartRow, final int cellStartCol) {
		Set<Integer> hits = new HashSet<>();
		final int cellEndRow = cellStartRow + BOX_HEIGHT;
		final int cellEndCol = cellStartCol + BOX_WIDTH;

		for (int rowIndex = cellStartRow; rowIndex < cellEndRow; rowIndex++) {
			for (int colIndex = cellStartCol; colIndex < cellEndCol; colIndex++) {
				hits.add(field[rowIndex][colIndex]);
			}
		}
		return hits.size() == VALUES_COUNT;
	}

	
	public boolean matchesSeed(Integer[][] seed) {
		int height = seed.length;
		if (height != field.length) {
			return false;
		}

		int width = seed[0].length;
		if (width != field[0].length) {
			return false;
		}

		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int colIndex = 0; colIndex < width; colIndex++) {
				final Integer seedValue = seed[rowIndex][colIndex];
				if (seedValue != null) {
					final int fieldValue = field[rowIndex][colIndex];
					if (seedValue != fieldValue) {
						return false;
					}
				}
			}
		}

		return true;
	}

}