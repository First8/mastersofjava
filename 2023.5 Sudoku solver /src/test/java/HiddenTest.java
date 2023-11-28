import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HiddenTest {

    // This test requires you implemented reduction for singles and pairs, or singles and single occurrences
    @Test
    public void solve() {
        Sudoku sudoku = new Sudoku(new Integer[][]{
                {5, 3, null, null, 7, null, null, null, null},
                {6, null, null, 1, 9, 5, null, null, null},
                {null, 9, 8, null, null, null, null, 6, null},
                {8, null, null, null, 6, null, null, null, 3},
                {4, null, null, 8, null, 3, null, null, 1},
                {7, null, null, null, 2, null, null, null, 6},
                {null, 6, null, null, null, null, 2, 8, null},
                {null, null, null, 4, 1, 9, null, null, 5},
                {null, null, null, null, 8, null, null, 7, 9}
        });
        SudokuSolver solver = new SudokuSolver();
        System.out.println("Initial: " + sudoku);
        Sudoku solvedSudoku = solver.solve(sudoku);
        System.out.println("Solved: " + solvedSudoku);
        assertTrue(solvedSudoku.isSolved());
    }
}
