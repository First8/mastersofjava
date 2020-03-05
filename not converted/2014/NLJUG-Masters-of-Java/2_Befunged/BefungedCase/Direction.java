/**
 * Represents the direction the PC is operating in.
 *
 * @author Jaap Coomans
 */
public enum Direction {
	UP("^"), DOWN("v"), LEFT("<"), RIGHT(">");

	private String symbol;

	private Direction(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return this.symbol;
	}
}