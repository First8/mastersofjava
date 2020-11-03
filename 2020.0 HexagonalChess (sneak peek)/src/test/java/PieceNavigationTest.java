import org.junit.Assert;
import org.junit.Test;

import moj.ChessBoards;
import moj.ChessBoards.Board;
import moj.Piece;
import moj.Rook;

public class PieceNavigationTest {
	@Test
	public void testNavigatingRook() {
		ChessBoards boards = new ChessBoards(); 
		Board board = boards.createNormalBoard(); 
		
		for (Rook rook: board.getRookList()) {
			// check vertical
			for (int index = 1; index < 8;index++) {
				int y = (rook.getCurrentY() + index) % 8;
				Assert.assertTrue(rook.isValidMove(rook.getCurrentX(),y));
			}
			// check horizontal
			for (char row = 'a'; row <= 'g';row++) {
				if (rook.getCurrentX()==row) {
					continue;
				}
				Assert.assertTrue(rook.isValidMove(row,rook.getCurrentY()));
			}
			// check no move
			Assert.assertFalse(rook.isValidMove(rook.getCurrentX(), rook.getCurrentY()));

			// check a diagonal
			int nextColumn = (rook.getCurrentY() + 1) % 8;
			char nextRow = nextRow(rook.getCurrentX());
			Assert.assertFalse(rook.isValidMove(nextRow, nextColumn));// diagonal is invalid
		}
	}

	private char nextRow(char ch) {
		ch++;
		if (ch=='h') {
			ch = 'a';
		}
		return ch;
	}
}
