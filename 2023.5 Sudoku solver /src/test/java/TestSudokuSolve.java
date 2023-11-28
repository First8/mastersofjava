import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests if we can actually solve a sudoku now
 */
public class TestSudokuSolve {

	@Test
	public void testTriple() {
		RegionReducer regionReducer = new RegionReducer();
		List<List<Integer>> region = toList(new int[][] { { 5 }, { 1, 7, 8 }, { 9 }, { 1, 4, 7 }, { 3 }, { 1, 4, 7, 6 },
				{ 1, 7, 8 }, { 1, 7, 8 }, { 2 } });

		List<List<Integer>> reducedRegion = regionReducer.reduce(region);

		List<List<Integer>> expectedRegion = toList(
				new int[][] { { 5 }, { 1, 7, 8 }, { 9 }, { 4 }, { 3 }, { 6 }, { 1, 7, 8 }, { 1, 7, 8 }, { 2 } });
		assertEquals(expectedRegion, reducedRegion);
	}

	@Test
	public void testHiddenTriple() {
		RegionReducer regionReducer = new RegionReducer();
		List<List<Integer>> region = toList(new int[][] { { 5 }, { 1, 8 }, { 9 }, { 1, 4, 7 }, { 3 }, { 1, 4, 7, 6 },
				{ 1, 7, 8 }, { 1, 7, 8 }, { 2 } });

		List<List<Integer>> reducedRegion = regionReducer.reduce(region);

		List<List<Integer>> expectedRegion = toList(
				new int[][] { { 5 }, { 1, 8 }, { 9 }, { 4 }, { 3 }, { 6 }, { 1, 7, 8 }, { 1, 7, 8 }, { 2 } });
		assertEquals(expectedRegion, reducedRegion);
	}

	@Test
	public void testQuadruple() {
		RegionReducer regionReducer = new RegionReducer();
		List<List<Integer>> region = toList(new int[][] { { 1, 2, 3, 4 }, { 1, 2, 3, 4 }, { 1, 2, 3, 4 },
				{ 1, 2, 3, 4 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 } });

		List<List<Integer>> reducedRegion = regionReducer.reduce(region);

		List<List<Integer>> expectedRegion = toList(
				new int[][] { { 1, 2, 3, 4 }, { 1, 2, 3, 4 }, { 1, 2, 3, 4 }, { 1, 2, 3, 4 }, { 5, 6, 7, 8, 9 },
						{ 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 }, { 5, 6, 7, 8, 9 } });
		assertEquals(expectedRegion, reducedRegion);
	}

	/**
	 * In this test there are 3 singles and 2 pairs that should be removed from the
	 * last two cells.
	 */
	@Test
	public void testSinglesAndPairs() {
		RegionReducer regionReducer = new RegionReducer();
		List<List<Integer>> region = toList(new int[][] { { 1 }, { 2, 3 }, { 2, 3 }, { 4, 5 }, { 4, 5 }, { 6 }, { 7 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 } });

		List<List<Integer>> reducedRegion = regionReducer.reduce(region);

		List<List<Integer>> expectedRegion = toList(
				new int[][] { { 1 }, { 2, 3 }, { 2, 3 }, { 4, 5 }, { 4, 5 }, { 6 }, { 7 }, { 8, 9 }, { 8, 9 } });
		assertEquals(expectedRegion, reducedRegion);
	}

	/**
	 * In this test the 9,8,7,6,5 and 4 are given and thus the last two cells can be
	 * simplified.
	 */
	@Test
	public void testSingle() {
		RegionReducer regionReducer = new RegionReducer();
		List<List<Integer>> region = toList(new int[][] { { 9 }, { 8 }, { 7 }, { 6 }, { 5 }, { 4 }, { 3 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 } });

		List<List<Integer>> reducedRegion = regionReducer.reduce(region);

		List<List<Integer>> expectedRegion = toList(
				new int[][] { { 9 }, { 8 }, { 7 }, { 6 }, { 5 }, { 4 }, { 3 }, { 1, 2 }, { 1, 2 } });
		assertEquals(expectedRegion, reducedRegion);
	}

	@Test
	public void testReduceSingle() {
		RegionReducer regionReducer = new RegionReducer();
		List<List<Integer>> testRegion = toList(
				new int[][] { { 1 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
						{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
						{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 } });

		List<List<Integer>> result = regionReducer.reduce(testRegion);

		List<List<Integer>> expectedResult = toList(
				new int[][] { { 1 }, { 2, 3, 4, 5, 6, 7, 8, 9 }, { 2, 3, 4, 5, 6, 7, 8, 9 }, { 2, 3, 4, 5, 6, 7, 8, 9 },
						{ 2, 3, 4, 5, 6, 7, 8, 9 }, { 2, 3, 4, 5, 6, 7, 8, 9 }, { 2, 3, 4, 5, 6, 7, 8, 9 },
						{ 2, 3, 4, 5, 6, 7, 8, 9 }, { 2, 3, 4, 5, 6, 7, 8, 9 } });
		assertEquals(expectedResult, result);
	}

	// This test requires you implemented reduction for single occurrences or for
	// pairs
	@Test
	public void testRegionReducer() {
		RegionReducer regionReducer = new RegionReducer();
		List<List<Integer>> region = toList(
				new int[][] { { 1 }, { 2 }, { 3 }, { 4, 5 }, { 4, 5 }, { 4, 5, 6 }, { 7 }, { 8 }, { 9 } });

		List<List<Integer>> reducedRegion = regionReducer.reduce(region);

		List<List<Integer>> expectedRegion = toList(
				new int[][] { { 1 }, { 2 }, { 3 }, { 4, 5 }, { 4, 5 }, { 6 }, { 7 }, { 8 }, { 9 } });
		assertEquals(expectedRegion, reducedRegion);
	}

	// This test requires you implemented reduction for singles, pairs and triples,
	// or singles and single occurrences
	@Test
	public void solveNotSoHard1() {
		Sudoku sudoku = new Sudoku(new Integer[][] { { 1, null, null, null, null, 6, null, 3, 2 },
				{ 2, null, 4, null, null, null, null, null, null }, { null, null, 6, 2, null, null, null, null, 5 },
				{ null, null, 2, 5, null, 1, null, 8, null }, { null, 3, null, null, 4, null, null, null, null },
				{ 5, null, null, 8, 2, null, null, null, null },
				{ null, null, null, null, null, null, null, null, null }, { 7, null, null, 1, null, null, 4, 2, null },
				{ null, null, null, null, 7, null, null, 6, 1 } });
		SudokuSolver solver = new SudokuSolver();
		System.out.println("Initial: " + sudoku);
		Sudoku solvedSudoku = solver.solve(sudoku);
		System.out.println("Solved: " + solvedSudoku);
		assertTrue(solvedSudoku.isSolved());
	}

	@Ignore("This sudoku requires other solving methods, e.g reducing the 8 from one column of a block, because in the block below it can only be in that column")
	@Test
	public void solveHard1() {
		Sudoku sudoku = new Sudoku(new Integer[][] { { null, null, 5, 8, null, 2, null, 4, null },
				{ null, 6, null, null, null, null, null, 8, null }, { null, 1, null, 9, null, null, null, 2, null },
				{ 4, null, null, 7, null, null, null, 9, null },
				{ null, null, null, null, null, null, null, null, null },
				{ null, 5, null, 2, null, null, 1, null, null }, { null, 7, null, 5, null, null, 3, null, null },
				{ null, null, null, null, 8, 6, null, null, null }, { null, null, 6, null, 7, null, 9, null, null } });
		SudokuSolver solver = new SudokuSolver();
		System.out.println("Initial: " + sudoku);
		Sudoku solvedSudoku = solver.solve(sudoku);
		System.out.println("Solved: " + solvedSudoku);
		assertTrue(solvedSudoku.isSolved());
	}

	@Ignore("This sudoku requires other solving methods, e.g reducing the 8 from one column of a block, because in the block above it can only be in that column")
	@Test
	public void solveHard2() {
		Sudoku sudoku = new Sudoku(new Integer[][] { { null, null, 5, 9, null, null, null, null, null },
				{ null, null, 1, 6, null, null, null, 4, null }, { null, 3, null, 7, null, null, 6, 9, null },
				{ null, null, null, null, null, null, null, null, null },
				{ 7, null, null, 4, null, null, null, 2, null }, { null, null, 3, null, null, 1, null, 6, null },
				{ null, null, null, null, null, null, 5, null, 9 }, { null, null, 2, null, null, 8, null, 3, null },
				{ null, 5, null, null, null, 4, null, null, 2 } });
		SudokuSolver solver = new SudokuSolver();
		System.out.println("Initial: " + sudoku);
		Sudoku solvedSudoku = solver.solve(sudoku);
		System.out.println("Solved: " + solvedSudoku);
		assertTrue(solvedSudoku.isSolved());
	}

	private List<List<Integer>> toList(int[][] array) {

		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			List<Integer> cell = new ArrayList<>();
			for (int j = 0; j < array[i].length; j++) {
				cell.add(array[i][j]);
			}
			result.add(cell);
		}
		return result;
//        return Stream.of(array).map(cell -> Arrays.stream(cell).boxed().toList()).toList();
	}
}
