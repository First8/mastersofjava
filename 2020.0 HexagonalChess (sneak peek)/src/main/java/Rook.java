package moj;

public class Rook extends Piece {
    public Rook(char x, int y) {
        super(x, y);
    }

    public boolean isValidMove(char x, int y) {
        boolean result = false;
        if (x == getCurrentX() && y == getCurrentY()) {
            result = false;// is not a move
        }
        if (x == getCurrentX() && y != getCurrentY()) {
            result = true;
        }
        if (x != getCurrentX() && y == getCurrentY()) {
            result = true;
        }
        return result;
    }
}
