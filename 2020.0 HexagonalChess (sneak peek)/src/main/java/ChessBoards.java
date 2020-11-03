package moj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ChessBoards {

    public Board createHexagonalBoard() {
        final char[] colors = new char[]{'W', 'B', 'G'};
        final char[] RANK_NAMES = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l'};
        final int maxWidth = 11;
        Board board = new Board(maxWidth);
        //TODO: implement the chessboard grid with hexagonal fields

        return board;
    }

    public Board createNormalBoard() {
        int squareBoardLength = 8; // square: length = width
        Board board = new Board(squareBoardLength);
        char rowName = 'a';
        int col = 0;
        final char[] colors = new char[]{'W', 'B'};
        for (int rowNr = 0; rowNr < squareBoardLength; rowNr++, rowName++) {
            for (col = 0; col < squareBoardLength; col++) {
                int colorIndex = (((int) rowName) + col) % colors.length;
                BoardField boardField = new BoardField();
                boardField.fieldColor = colors[colorIndex];
                boardField.key = "" + rowName + "" + (col + 1);
                board.grid[rowNr][col] = boardField;
            }
        }
        return board;
    }
    public class Board {
        private BoardField[][] grid;
        private int dimension;

        public List<Rook> getRookList() {
            return rookList;
        }

        private List<Rook> rookList = new ArrayList<>();

        public Board(int dimension) {
            grid = new BoardField[dimension][dimension];
            this.dimension = dimension;
            rookList.add(new Rook('a', 1));
        }

        public int getDimension() {
            return dimension;
        }

        public void print() {
            System.out.println("chessboard with grid length " + grid.length);
            for (int row = 0; row < grid.length; row++) {
                System.out.print(row + ". \t");
                for (int col = 0; col < grid.length; col++) {
                    if (grid[row][col] != null) {
                        System.out.print(grid[row][col].display());
                    }
                }
                System.out.println();
            }
        }

        public String createHorizontalBoardLine(int col) {
            return Arrays.stream(grid[col]).filter(Objects::nonNull).
                    map(boardField -> Character.toString(boardField.fieldColor)).
                    collect(Collectors.joining());
        }

        public String createBoardDisplay() {
            return Arrays.stream(grid).map(row ->
                    Arrays.stream(row).filter(Objects::nonNull).map(boardField -> Character.toString(boardField.fieldColor))
                            .collect(Collectors.joining())).collect(Collectors.joining("\n"));
        }

        public long countFields() {
            return Arrays.stream(grid).mapToLong(row -> Arrays.stream(row).filter(Objects::nonNull).count()).sum();
        }

        public long getAmountOfRankingsInColumn(int col) {
            return Arrays.stream(grid).map(row -> row[col]).filter(Objects::nonNull).count();
        }

        public boolean checkColor(char rank, int y, char color) {
            int x = (rank - 'a');
            return grid[x][y].fieldColor == color;
        }
    }



    private class BoardField {
        char row;
        int col;
        private char fieldColor;
        private String key;

        public String display() {
            return key + this.fieldColor + " ";
        }
    }

}
