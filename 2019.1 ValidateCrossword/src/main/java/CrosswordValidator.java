import java.util.List;

public class CrosswordValidator {

	/**
	 * 
	 * @return true if the clues are consistent with each other, false otherwise
	 */
	public static boolean validate(List<Clue> clues) {
		return false;
	}

	private static void print(char[][] board) {
		int w = board.length;
		int h = board[0].length;

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				System.out.print(board[x][y]);
			}
			System.out.println();
		}

	}

}
