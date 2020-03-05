package nl.sogeti.test;

import java.util.logging.Logger;

import nl.equens.tester.SudokuSolver;
import nl.equens.tester.SudokuValidator;

public class SudokuSolverTest {

	private static Logger LOGGER = Logger.getLogger(SudokuSolverTest.class
			.getName());

	
	public void testCanSolveFewItemsMissing(){

			Integer[][] seed= { //
			{ 6, 1, 7, 8, 2, 9, 4, 3, 5 }, //
					{ 5, 3, null, 1, 4, 6, 2, 9, 7 }, // 8
					{ null, 9, 2, 5, 7, 3, 1, 8, 6 }, // 4
					{ 3, 8, 5, null, 9, 4, 7, 1, 2 }, // 6
					{ 1, 7, 6, 2, 5, 8, 9, 4, null }, // 3
					{ 2, 4, 9, 7, 3, 1, 5, null, 8 }, // 6
					{ 8, null, 3, 4, 1, 7, 6, 5, 9 }, // 2
					{ 9, 5, 1, 3, 6, 2, null, 7, 4 }, // 8
					{ 7, 6, 4, 9, 8, null, 3, 2, 1 }  // 5
			};

			Integer [][] seedCopy = copy(seed);
			

			SudokuSolver solver = new SudokuSolver(seedCopy);
			
			Integer[][] solution = solver.solve();
			
//			printField(seed);
//			printField(solution);
			
			SudokuValidator validator = new SudokuValidator(solution);
			Assert.isTrue("Solution not valid", validator.isValid());
			Assert.isTrue("Not the solution for the given seed ", validator.matchesSeed(seed));
	}
	


	public void testCansolveHarderSudoku(){
		System.out.println("testCansolveHarderSudoku START");
		Integer[][] seed = { //
				{   7,    3, null, null,    2,    8, null, null, null}, //
				{   8, null, null, null, null, null, null, null,    9}, //
				{null, null,    9, null, null,    6, null, null, null}, //
				{null, null, null, null, null,    7,    8, null, null}, //
				{null, null,    1, null, null, null,    2, null, null}, //
				{null, null,    6,    5, null, null, null, null, null}, //
				{null, null, null,    4, null, null,    9, null, null}, //
				{   6,    9, null, null, null, null, null, null,    4}, //
				{null, null, null,    1,    9, null, null,    5,    6}//
		};
		
		
		
		SudokuSolver solver = new SudokuSolver(copy(seed));
		
		Integer[][] solution = solver.solve();
		
		printField(seed);
		printField(solution);
		
		SudokuValidator validator = new SudokuValidator(solution);
		Assert.isTrue("Solution not valid", validator.isValid());
		Assert.isTrue("Not the solution for the given seed ", validator.matchesSeed(seed));
		
		
		int[][] expectedResult = { //
				{9, 2, 6, 5, 4, 8, 7, 1, 3}, //
				{5, 4, 1, 6, 3, 7, 2, 8, 9}, //
				{3, 7, 8, 2, 9, 1, 4, 5, 6}, //
				{8, 1, 5, 4, 6, 3, 9, 7, 2}, //
				{6, 9, 7, 1, 5, 2, 8, 3, 4}, //
				{4, 3, 2, 8, 7, 9, 5, 6, 1}, //
				{7, 5, 9, 3, 2, 6, 1, 4, 8}, //
				{2, 8, 3, 7, 1, 4, 6, 9, 5}, //
				{1, 6, 4, 9, 8, 5, 3, 2, 7} //
		};
		System.out.println("testCansolveHarderSudoku END");
	}
	

	public void testCansolveSimpleSudoku(){
		Integer[][] seed = { //
				{null, null,    6, null, null, null,    7, null,    3}, //
				{null,    4,    1,    6, null,    7,    2, null, null}, //
				{null, null, null, null, null, null, null,    5, null}, //
				{null, null, null,    4, null, null,    9,    7, null}, //
				{null, null, null,    1,    5,    2, null, null, null}, //
				{null,    3,    2, null, null,    9, null, null, null}, //
				{null,    5, null, null, null, null, null, null, null}, //
				{null, null,    3,    7, null,    4,    6,    9, null}, //
				{   1, null,    4, null, null, null,    3, null, null}//
		};
		
		SudokuSolver solver = new SudokuSolver(copy(seed));
		
		Integer[][] solution = solver.solve();
		
		printField(seed);
		printField(solution);
		
		SudokuValidator validator = new SudokuValidator(solution);
		Assert.isTrue("Solution not valid", validator.isValid());
		Assert.isTrue("Not the solution for the given seed ", validator.matchesSeed(seed));
		
		
		int[][] expectedResult = { //
				{9, 2, 6, 5, 4, 8, 7, 1, 3}, //
				{5, 4, 1, 6, 3, 7, 2, 8, 9}, //
				{3, 7, 8, 2, 9, 1, 4, 5, 6}, //
				{8, 1, 5, 4, 6, 3, 9, 7, 2}, //
				{6, 9, 7, 1, 5, 2, 8, 3, 4}, //
				{4, 3, 2, 8, 7, 9, 5, 6, 1}, //
				{7, 5, 9, 3, 2, 6, 1, 4, 8}, //
				{2, 8, 3, 7, 1, 4, 6, 9, 5}, //
				{1, 6, 4, 9, 8, 5, 3, 2, 7} //
		};
		
	}
	
	
	private void printField(Integer[][] field){
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("    1   2   3   4   5   6   7   8   9  ").append("\n");
		builder.append("  -------------------------------------").append("\n");
		int i = 1;
		for(Integer[] row : field){
			builder.append(i).append(" | ");
			for(Integer value: row){
				builder.append((value==null)?" ":value).append(" | ");
			}
			builder.append("\n");
			builder.append("  -------------------------------------").append("\n");
			i++;
		}
		LOGGER.info(builder.toString());
	}
	
	private Integer[][] copy(Integer[][] seed) {
		Integer[][] copy = new Integer[seed.length][seed[0].length];
		
		for(int row=0; row< seed.length; row++){
			for(int col=0; col< seed[0].length; col++){
				copy[row][col] = seed[row][col];
			}
		}
		
		return copy;
	}
}
