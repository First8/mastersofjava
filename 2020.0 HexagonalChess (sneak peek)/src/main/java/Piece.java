package moj;

public abstract class Piece {
	private char currentX;
	private int currentY;

	public Piece(char x, int y) {
		this.currentX = x;
		this.currentY = y;
	}

	public char getCurrentX() {
		return currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public abstract boolean isValidMove(char x, int y);

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + getCurrentX() + getCurrentY() + "]";
	}
}
