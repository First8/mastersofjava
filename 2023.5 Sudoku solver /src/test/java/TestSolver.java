import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Non-competition test that tests the Solver itself. 
 */
public class TestSolver {
    @Test
    public void testSudoku1() {
        Sudoku sudoku = new Sudoku(new Integer[][]{
                {5, 3, null, null, 7, null, null, null, null},
                {6, null, null, 1, 9, 5, null, null, null},
                {null, 9, 8, null, null, null, null, 6, null},
                {8, null, null, null, 6, null, null, null, 3},
                {4, null, null, 8, null, 3, null, null, 1},
                {7, null, null, null, 2, null, null, null, 6},
                {null, 6, null, null, null, null, 2, 8, null},
                {null, null, null, 4, 1, 9, null, null, 5},
                {null, null, null, null, 8, null, null, 7, 9}});

        List<List<Integer>> expectedFirstColumn = toList(new Integer[][]{{5}, {6}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {8}, {4}, {7}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}});
        assertEquals(expectedFirstColumn, sudoku.getColumn(1));

        List<List<Integer>> expectedNinthColumn = toList(new Integer[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {3}, {1}, {6}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {5}, {9}});
        assertEquals(expectedNinthColumn, sudoku.getColumn(9));

        List<List<Integer>> expectedSixthBlock = toList(new Integer[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {3}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {1}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {6}});
        assertEquals(expectedSixthBlock, sudoku.getBlock(6));

        assertFalse(sudoku.isSolved());
        System.out.println(sudoku);
    }

    private List<List<Integer>> toList(Integer[][] array){
        return asList(array).stream().map(Arrays::asList).toList();
    }

    @Test
    public void testSolved() {
        Sudoku sudoku = new Sudoku(new Integer[][]{
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}});
        assertTrue(sudoku.isSolved());
    }

    @Test
    public void testNotSolvedBecauseWrong() {
        Sudoku sudoku = new Sudoku(new Integer[][]{
                {1, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}});
        assertFalse(sudoku.isSolved());
    }

    @Test
    public void testNotSolvedBecauseNotFinished() {
        Sudoku sudoku = new Sudoku(new Integer[][]{
                {null, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}});
        assertFalse(sudoku.isSolved());
    }
}
