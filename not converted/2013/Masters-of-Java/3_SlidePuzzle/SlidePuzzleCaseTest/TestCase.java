import java.util.Formatter;

/**
 * Representation of a test case.
 * 
 * @author Ron Bierman
 */
class TestCase {
	/** Non-null. Input for the test case. */
	private final Integer[][] puzzle;

	/**
	 * Creates a new testcase
	 * 
	 * @param puzzle
	 *            a two dimensional integer array that represents a puzzle.
	 */
	TestCase(Integer[][] puzzle) {
		super();
		this.puzzle = clone(puzzle);
	}

	/**
	 * Performs the actual test.
	 * 
	 * @return whether the test case completed succesfully.
	 */
	boolean performTest() {
		try {
			SlidePuzzle clientPuzzle = new SlidePuzzleImpl();
			SlidePuzzle.Swap[] clientOutput = clientPuzzle
					.solvePuzzle(this.puzzle);
			executeMoves(clientOutput);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void executeMoves(SlidePuzzle.Swap[] clientOutput) {
		for (SlidePuzzle.Swap swap : clientOutput) {
			int[] nullPosition = findNull(this.puzzle);
			executeSwap(swap, nullPosition);
		}

		testInputSequence();
	}

	private void testInputSequence() {
		int size = puzzle.length;

		int currentValue = 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (puzzle[i][j] == null) {
					if (i != size - 1 || j != size - 1) {
						throw new IllegalStateException(
								"Given puzzle is not solved with the specified moves. (Null value is not in the right bottom corner)");

					}
				} else if (puzzle[i][j] == currentValue) {
					currentValue++;

				} else {
					throw new IllegalStateException(
							"Given puzzle is not solved with the specified moves. ("
									+ i + "," + j + ") contains: "
									+ puzzle[i][j] + " but should contain: "
									+ currentValue);
				}
			}
		}

	}

	/**
	 * Swaps the tile in the nullPosition with the tile specified in swap
	 * 
	 * @param swap
	 * @param nullPosition
	 */
	private void executeSwap(SlidePuzzle.Swap swap, int[] nullPosition) {
		int row = nullPosition[0];
		int column = nullPosition[1];

		switch (swap) {
		case LEFT:
			if (column <= 0) {
				throw new IllegalStateException(
						"Couldn't execute swap with left because we would swap outside the puzzle");
			}
			puzzle[row][column] = puzzle[row][column - 1];
			puzzle[row][column - 1] = null;
			break;
		case DOWN:
			if (row >= puzzle.length) {
				throw new IllegalStateException(
						"Couldn't execute swap with down because we would swap outside the puzzle");
			}
			puzzle[row][column] = puzzle[row + 1][column];
			puzzle[row + 1][column] = null;

			break;

		case RIGHT:
			if (column >= puzzle.length) {
				throw new IllegalStateException(
						"Couldn't execute swap with right because we would swap outside the puzzle");
			}
			puzzle[row][column] = puzzle[row][column + 1];
			puzzle[row][column + 1] = null;
			break;

		case UP:
			if (row <= 0) {
				throw new IllegalStateException(
						"Couldn't execute swap with up because we would swap outside the puzzle");
			}
			puzzle[row][column] = puzzle[row - 1][column];
			puzzle[row - 1][column] = null;
			break;

		}

	}

	/**
	 * Finds the current null value in a two dimensional array.
	 * 
	 * @param puzzle
	 *            The two dimensional array
	 * @return An integer array with to values or null if no null is found. The
	 *         first value in the return array corresponds with first index in
	 *         the puzzle array.
	 */
	private int[] findNull(Integer[][] puzzle) {
		int size = puzzle.length;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (puzzle[i][j] == null) {
					return new int[] { i, j };
				}
			}
		}

		throw new IllegalStateException(
				"Invalid puzzle state. No null field found");
	}

	@Override
	public String toString() {
		String formatString = "|%1$4s";

		StringBuilder result = new StringBuilder();
		result.append("+----+----+----+----+\r\n");

		for (Integer[] row : puzzle) {
			for (Integer cell : row) {
				Formatter formatter = new Formatter();
				formatter.format(formatString, cell);
				result.append(formatter.toString());
			}
			result.append("|\r\n");
			result.append("+----+----+----+----+\r\n");
		}

		return result.toString();

	}

	private Integer[][] clone(Integer[][] source) {
		int length = source.length;
		Integer[][] result = new Integer[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				result[i][j] = source[i][j];
			}
		}

		return result;
	}
}