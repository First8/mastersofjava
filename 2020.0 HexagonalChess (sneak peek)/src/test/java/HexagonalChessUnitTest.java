


import org.junit.Assert;
import org.junit.Test;

import moj.ChessBoards;
import moj.ChessBoards.Board;

public class HexagonalChessUnitTest {
    @Test
    public void testNormalChessBoard() {
        ChessBoards boards = new ChessBoards();
        Board board = boards.createNormalBoard();
        board.print();
        int squareBoardLength = 8;
        int expectedNrOfFields = squareBoardLength * squareBoardLength;
        Assert.assertEquals(expectedNrOfFields, board.countFields());// a chess board has 64 fields.

        for (int index = 0; index < squareBoardLength; index++) {
            Assert.assertEquals(squareBoardLength, board.getAmountOfRankingsInColumn(index));
        }
        // the normal chess board has a strict Black/White color pattern
        Assert.assertEquals("BWBWBWBW", board.createHorizontalBoardLine(0));
        Assert.assertEquals("WBWBWBWB", board.createHorizontalBoardLine(1));
    }
    @Test
    public void testCountFieldsAndFieldColours() {
        ChessBoards boards = new ChessBoards();
        Board board = boards.createHexagonalBoard();
        board.print();// print the board
        Assert.assertEquals(91, board.countFields());// a hexagonal chess board has 91 fields.

        // the hexagonal chess board has a strict symmetric color pattern (Black-Gray-White on endings)
        Assert.assertEquals("BGWBGW", board.createHorizontalBoardLine(0));
        Assert.assertEquals("WBGWBGWBGWB", board.createHorizontalBoardLine(5));
        Assert.assertEquals("BGWBGW", board.createHorizontalBoardLine(10));
    }
    @Test
    public void testRankingsAndHexagonalFields() {
        ChessBoards boards = new ChessBoards();
        Board board = boards.createHexagonalBoard();

        Assert.assertEquals(91, board.countFields());//a hexagonal chess board has 91 fields.

        // requirements for hexagon fields in a column ranking

        // the first 6 rankings have 11 hexagon fields in a row
        for (int index = 0; index < 6; index++) {
            Assert.assertEquals(11, board.getAmountOfRankingsInColumn(index));
        }
        // the next 5 asserts ensure that each column ranking has an descending expected number of hexagonal fields.
        Assert.assertEquals(9, board.getAmountOfRankingsInColumn(6));
        Assert.assertEquals(7, board.getAmountOfRankingsInColumn(7));
        Assert.assertEquals(5, board.getAmountOfRankingsInColumn(8));
        Assert.assertEquals(3, board.getAmountOfRankingsInColumn(9));
        Assert.assertEquals(1, board.getAmountOfRankingsInColumn(10));
    }

}
