import java.util.List;

public class CrosswordValidator {

	/**
	 * 
	 * @return true if the clues are consistent with eachother, false otherwise
	 */
	public static boolean validate(List<Clue> clues) {

		char[][] board = findSize(clues);
		try {

			for (Clue c : clues) {
				fillOut(board, c);
			}

			return true;
		} catch (IllegalArgumentException e) {
			print(board);
			return false;
		}

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

	private static void fillOut(char[][] board, Clue c) {
		for (int i = 0; i < c.answer.length(); i++) {

			if (c.horizontal) {
				if (c.x!=0 && board[c.x-1][c.y]!=0) {
					throw new IllegalArgumentException("adjacent to another word");
				}
				if (board[c.x+c.answer.length()][c.y]!=0) {
					throw new IllegalArgumentException("adjacent to another word");
				}
				
				if (board[c.x + i][c.y] == 0 || board[c.x+i][c.y]==c.answer.charAt(i)) {
					board[c.x + i][c.y] = c.answer.charAt(i);
				} else {
					throw new IllegalArgumentException("wrong clue  for " + c.answer);
				}
			}
			if (!c.horizontal) {
				
				if (c.y!=0 && board[c.x][c.y-1]!=0) {
					throw new IllegalArgumentException("adjacent to another word");
				}
				if (board[c.x][c.y+c.answer.length()]!=0) {
					throw new IllegalArgumentException("adjacent to another word");
				}

				
				if (board[c.x][c.y + i] == 0 || board[c.x][c.y+i]==c.answer.charAt(i)) {
					board[c.x][c.y + i] = c.answer.charAt(i);
				} else {
					throw new IllegalArgumentException("wrong clue  for " + c.answer);
				}
			}
		}
	}

	private static char[][] findSize(List<Clue> clues) {
		int maxX = 0;
		int maxY = 0;
		int maxLength = 0;
		for (Clue c : clues) {
			if (c.x > maxX)
				maxX = c.x;
			if (c.y > maxY)
				maxY = c.y;
			if (c.answer.length() > maxLength )
				maxLength = c.answer.length();
		}

		return new char[maxX + maxLength +1][maxY + maxLength +1];
	}

}
