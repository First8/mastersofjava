/**
 * Interface specification for the Slide puzzle Masters of Java case.
 * 
 * @author biermaro
 */
public interface SlidePuzzle {

	enum Swap {
		UP, DOWN, RIGHT, LEFT
	}

	/**
	 * 
	 * @param puzzle A two dimensional array that represents the slide puzzle
	 * @return A array with swap operations necessary to solve the puzzle.
	 */
	Swap[] solvePuzzle(Integer[][] puzzle);
}
