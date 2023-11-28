
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


/**
 * This class can solve a Sudoku given a good RegionSolver implementation
 */
public class SudokuSolver {
	private RegionReducer regionReducer = new RegionReducer();

	public Sudoku solve(Sudoku sudokuToSolve) {
		if (sudokuToSolve.isSolved()) {
			return sudokuToSolve;
		}
		String initialValue = sudokuToSolve.toString();

		IntStream.rangeClosed(1, 9).forEach(r -> {
			List<List<Integer>> row = sudokuToSolve.getRow(r);
			List<List<Integer>> reducedRow = regionReducer.reduce(copy(row));
			sudokuToSolve.setRow(r,	reducedRow);
		});

		IntStream.rangeClosed(1, 9).forEach(c -> {
			List<List<Integer>> col = sudokuToSolve.getColumn(c);
			List<List<Integer>> reducedCol = regionReducer.reduce(copy(col));
			sudokuToSolve.setColumn(c, reducedCol);
		});

		 System.out.println("Reducing blocks");
			IntStream.rangeClosed(1, 9).forEach(b -> {
				List<List<Integer>> block = sudokuToSolve.getBlock(b);
				List<List<Integer>> reducedBlock = regionReducer.reduce(copy(block));
				sudokuToSolve.setBlock(b, reducedBlock);
			});

		if (initialValue.equals(sudokuToSolve.toString())) {
			throw new RuntimeException("Couldn't solve sudoku any further: " + sudokuToSolve);
		} else {
			return solve(sudokuToSolve);
		}
	}
	
	

	/* make a deep copy of the list of lists so that it is mutable */
    private List<List<Integer>> copy(List<List<Integer>> list) {
    	
    	List<List<Integer>> result = new ArrayList<>();
    	for (int i=0; i<list.size(); i++) {
    		List<Integer> cell = new ArrayList<>();
    	   	for (int j=0; j<list.get(i).size(); j++) {
	   	    	cell.add(list.get(i).get(j));
    	   	}
    	   	result.add(cell);
    	}
    	return result;
//        return Stream.of(array).map(cell -> Arrays.stream(cell).boxed().toList()).toList();
    }
}
