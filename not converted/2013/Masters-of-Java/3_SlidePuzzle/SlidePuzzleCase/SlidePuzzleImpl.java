import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidePuzzleImpl implements SlidePuzzle {

	private final Map<List<Swap>, Integer[][]> state = new HashMap();

	@Override
	public Swap[] solvePuzzle(Integer[][] puzzle) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This helper method check's if the puzzle is solved.
	 * 
	 * @param puzzle
	 *          A two dimensional Integer array who should have a number of rows equal to the number of columns.
	 */
	private boolean testPuzzle(Integer[][] puzzle) {
		int size = puzzle.length;

		int currentValue = 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (puzzle[i][j] == null) {

					if (i != size - 1 || j != size - 1) {
						return false;
					}
				} else if (puzzle[i][j] == currentValue) {
					currentValue++;

				} else {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Performs a swap on the given array. Returns the resulting puzzle array.
	 * 
	 * @param puzzle
	 *          The puzzle array
	 * @param swap
	 *          The swap operation
	 * @return The puzzle array that represents the result of the swap operation.
	 */
	private Integer[][] executeSwap(Integer[][] puzzle, SlidePuzzle.Swap swap) {
		Integer[][] result = this.clonePuzzle(puzzle);

		int[] nullPosition = this.findNull(result);

		int row = nullPosition[0];
		int column = nullPosition[1];

		switch (swap) {
			case LEFT:
				if (column <= 0) {
					throw new IllegalStateException("Couldn't execute swap with left because we would swap outside the puzzle");
				}
				result[row][column] = result[row][column - 1];
				result[row][column - 1] = null;
				break;
			case DOWN:
				if (row >= result.length) {
					throw new IllegalStateException("Couldn't execute swap with down because we would swap outside the puzzle");
				}
				result[row][column] = result[row + 1][column];
				result[row + 1][column] = null;

				break;

			case RIGHT:
				if (column >= result.length) {
					throw new IllegalStateException("Couldn't execute swap with right because we would swap outside the puzzle");
				}
				result[row][column] = result[row][column + 1];
				result[row][column + 1] = null;
				break;

			case UP:
				if (row <= 0) {
					throw new IllegalStateException("Couldn't execute swap with up because we would swap outside the puzzle");
				}
				result[row][column] = result[row - 1][column];
				result[row - 1][column] = null;
				break;

		}

		return result;
	}

	/**
	 * Determines whether it is possible to perform the given swap operation on a puzzle.
	 * 
	 * @param puzzle
	 *          The puzzle array
	 * @param swap
	 *          The swap operation to perform.
	 * @return true if the swap can be performed within the bounds of the puzzle array, false otherwise.
	 */
	private boolean isSwapPossible(Integer[][] puzzle, SlidePuzzle.Swap swap) {
		int[] nullPosition = this.findNull(puzzle);

		int row = nullPosition[0];
		int column = nullPosition[1];
		switch (swap) {
			case LEFT:
				return column > 0;
			case DOWN:
				return row < puzzle.length;
			case RIGHT:
				return column < puzzle.length;
			case UP:
				return row > 0;
			default:
				throw new IllegalArgumentException("Unknown Swap value: " + swap);
		}
	}

	/**
	 * Creates a clone of the given puzzle array.
	 * 
	 * @param original
	 *          The puzzle array to clone
	 * @return The clone of the original
	 */
	private Integer[][] clonePuzzle(Integer[][] original) {
		int size = original.length;
		Integer[][] clone = new Integer[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				clone[i][j] = original[i][j];
			}
		}

		return clone;
	}

	/**
	 * Finds the current null value in a two dimensional array.
	 * 
	 * @param puzzle
	 *          The two dimensional array
	 * @return An integer array with to values or null if no null is found. The first value in the return array
	 *         corresponds with first index in the puzzle array.
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

		throw new IllegalStateException("Invalid puzzle state. No null field found");
	}

}
