package nl.equens.tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SudokuSolver {

	private static final int SIZE = 9;

	private static final int CELL_SIZE = 3;

	private int remainingCount;

	private boolean change;

	private List<Integer>[][] options;

	private Integer[][] field;

	private int[][] rowRemaining;
	private int[][] colRemaining;
	private int[][] cellRemaining;

	private int iteration;

	public SudokuSolver(Integer[][] seed) {
		this.field = seed;

	}

	/**
	 * Solve the given seed.
	 * 
	 * @param seed
	 *            the start of the sudoku
	 * @return the solved sudoku
	 */
	public Integer[][] solve() {
		System.out.println("\n\nIntializing ...");

		// initialize
		initialize();

		// iterate while remaining>0

		System.out.println("\n\nSolving ...");
		boolean isChanged;
		do {
			iteration++;
			isChanged = false;
			// while change && remaining >0
			// horizontal purging choices
			// filter where single choice

			System.out
					.println("Step 1: purging and filtering by row and column");
			printStatus();
			do {
				change = false;
				fillFieldsByRowAndColumn();
				fillFieldsByCell();
				fillFieldsSingleOptions();
				isChanged |= change;
			} while (change);

			filterTwinOptions();

			printStatus();
		} while (isChanged && remainingCount > 0);
		return field;
	}

	private void filterTwinOptions() {
		for (int cell = 0; cell < SIZE; cell++) {
			filterTwinOptionsForCell(cell);
		}
	}

	private void filterTwinOptionsForCell(final int cell) {
		final int cellRowStart = CELL_SIZE * (cell / CELL_SIZE);
		final int cellRowEnd = cellRowStart + CELL_SIZE;
		final int cellColStart = CELL_SIZE * (cell % CELL_SIZE);
		final int cellColEnd = cellColStart + CELL_SIZE;

		for (int row = cellRowStart; row < cellRowEnd; row++) {
			for (int col = cellColStart; col < cellColEnd; col++) {
				filterTwinFiledOptionsInCell(col,row,cell);
			}
		}
	}

	private void filterTwinFiledOptionsInCell(int col, int row, int cell) {
		// TODO Auto-generated method stub
		
	}

	private void printStatus() {

		StringBuilder titleBuilder = new StringBuilder("\n");
		titleBuilder.append("Current solving status.\n")
				//
				.append("Iteration: ").append(iteration).append("\n")
				//
				.append("Remaining fields: ").append(remainingCount)
				.append("\n"); //

		System.out.println(titleBuilder.toString());

		StringBuilder matrixBuilder = new StringBuilder("\n");

		for (int row = 0; row < SIZE; row++) {
			matrixBuilder.append("| ");
			for (int col = 0; col < SIZE; col++) {
				if (options[row][col].isEmpty()) {
					matrixBuilder.append(field[row][col]);
				} else {
					matrixBuilder.append(options[row][col]);
				}
				matrixBuilder.append(" | ");
			}

			matrixBuilder.append(" -- ").append(rowRemaining[row]).append("\n");
		}

		matrixBuilder.append("----------------------------------------------")
				.append("\n");

		for (int col = 0; col < SIZE; col++) {
			matrixBuilder.append(colRemaining[col]).append(" | ");
		}
		matrixBuilder.append("\n");
		System.out.println(matrixBuilder.toString());

	}

	private void fillFieldsByRowAndColumn() {
		fillFieldsByRow();
		fillFieldsByCol();
	}

	private void fillFieldsByCol() {
		for (int col = 0; col < SIZE; col++) {
			for (int i = 0; i < SIZE; i++) {
				if (colRemaining[col][i] == 1) {
					final Integer value = i + 1;
					for (int row = 0; row < SIZE; row++) {
						if (field[row][col] == null
								&& options[row][col].contains(value)) {
							pickChoice(row, col, value);
						}
					}
				}
			}
		}
	}

	private void fillFieldsByRow() {
		for (int row = 0; row < SIZE; row++) {
			for (int i = 0; i < SIZE; i++) {
				if (rowRemaining[row][i] == 1) {
					final Integer value = i + 1;
					for (int col = 0; col < SIZE; col++) {
						if (field[row][col] == null
								&& options[row][col].contains(value)) {
							pickChoice(row, col, value);
						}
					}
				}
			}
		}
	}

	private void fillFieldsByCell() {
		for (int cell = 0; cell < SIZE; cell++) {
			for (int i = 0; i < SIZE; i++) {
				if (cellRemaining[cell][i] == 1) {
					final Integer value = i + 1;
					fillFieldOfCell(cell, value);
				}
			}
		}
	}

	private void fillFieldOfCell(final int cell, final Integer value) {
		final int cellRowStart = CELL_SIZE * (cell / CELL_SIZE);
		final int cellRowEnd = cellRowStart + CELL_SIZE;
		final int cellColStart = CELL_SIZE * (cell % CELL_SIZE);
		final int cellColEnd = cellColStart + CELL_SIZE;

		System.out.println("FillFieldOfCell: " + cell + ", " + value + " - "
				+ cellRowEnd + "," + cellColEnd);

		for (int row = cellRowStart; row < cellRowEnd; row++) {
			for (int col = cellColStart; col < cellColEnd; col++) {
				System.out.println("FillFieldOfCell row/col: " + row + ", "
						+ col);
				if (field[row][col] == null
						&& options[row][col].contains(value)) {
					pickChoice(row, col, value);
				}
			}
		}

	}

	private void fillFieldsSingleOptions() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (field[row][col] == null && options[row][col].size() == 1) {
					pickChoice(row, col, options[row][col].get(0));
				}
			}
		}
	}

	private void pickChoice(final int row, final int col, final Integer chosen) {
		// System.out.println("Picking choice at [" + row + "," + col + "]: "
		// + chosen);

		final int cell = determineCell(row, col);

		field[row][col] = chosen;

		for (Integer option : options[row][col]) {
			if (rowRemaining[row][option - 1] > 0) {
				rowRemaining[row][option - 1]--;
			}

			if (colRemaining[col][option - 1] > 0) {
				colRemaining[col][option - 1]--;
			}

			if (cellRemaining[cell][option - 1] > 0) {
				cellRemaining[cell][option - 1]--;
			}
		}

		removeOptionFromRow(row, chosen);

		removeOptionFromCol(col, chosen);

		removeOptionFromCell(cell, chosen);

		options[row][col].clear();

		rowRemaining[row][chosen - 1] = 0;
		colRemaining[col][chosen - 1] = 0;
		cellRemaining[cell][chosen - 1] = 0;

		if (remainingCount > 0) {
			remainingCount--;
		}

		change = true;
	}

	private void removeOptionFromCell(final int cell, final Integer chosen) {
		final int cellRowStart = CELL_SIZE * (cell / CELL_SIZE);
		final int cellRowEnd = cellRowStart + CELL_SIZE;
		final int cellColStart = CELL_SIZE * (cell % CELL_SIZE);
		final int cellColEnd = cellColStart + CELL_SIZE;

		for (int row = cellRowStart; row < cellRowEnd; row++) {
			for (int col = cellColStart; col < cellColEnd; col++) {
				if (options[row][col].contains(chosen)) {
					removeOption(row, col, chosen);
				}
			}
		}

	}

	private void removeOptionFromRow(final int row, final Integer chosen) {
		for (int tCol = 0; tCol < SIZE; tCol++) {
			if (options[row][tCol].contains(chosen)) {
				removeOption(row, tCol, chosen);
			}
		}
	}

	private void removeOptionFromCol(final int col, final Integer chosen) {
		for (int tRow = 0; tRow < SIZE; tRow++) {
			if (options[tRow][col].contains(chosen)) {
				removeOption(tRow, col, chosen);
			}
		}
	}

	private void removeOption(final int row, int col, final Integer value) {
		int cell = determineCell(row, col);
		options[row][col].remove(value);
		rowRemaining[row][value - 1]--;
		colRemaining[col][value - 1]--;
		cellRemaining[cell][value - 1]--;
	}

	private int determineCell(int row, int col) {
		return CELL_SIZE * (row / CELL_SIZE) + (col / CELL_SIZE);
	}

	private void initialize() {
		iteration = 0;
		change = true;
		remainingCount = 0;
		options = new List[SIZE][SIZE];
		rowRemaining = new int[SIZE][SIZE];
		colRemaining = new int[SIZE][SIZE];
		cellRemaining = new int[SIZE][SIZE];

		for (int cell = 0; cell < SIZE; cell++) {
			Arrays.fill(cellRemaining[cell], 9);
		}

		for (int col = 0; col < SIZE; col++) {
			Arrays.fill(colRemaining[col], 9);
		}

		for (int row = 0; row < SIZE; row++) {
			Arrays.fill(rowRemaining[row], 9);
		}

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				initializeEmptyField(row, col);
			}
		}

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				initializeEmptyField(row, col);
			}
		}

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Integer fieldValue = field[row][col];
				if (fieldValue != null) {
					pickChoice(row, col, fieldValue);
				}
			}
		}
	}

	private void initializeEmptyField(int row, int col) {
		options[row][col] = list(1, 2, 3, 4, 5, 6, 7, 8, 9);
		remainingCount++;
	}

	private Map<Integer, Integer> initializeRemaining() {
		Map<Integer, Integer> remain = new HashMap<Integer, Integer>();
		for (int i = 1; i <= 9; i++) {
			remain.put(i, 9);
		}
		return remain;
	}

	private <T> List<T> list(T... elements) {
		return new ArrayList<>(Arrays.asList(elements));
	}
}
