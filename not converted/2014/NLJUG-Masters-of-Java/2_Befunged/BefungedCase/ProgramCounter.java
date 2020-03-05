import java.util.Objects;

/**
 * Represents the current state of the Program Counter (PC) in a Befunge program that is being executed.
 *
 * @author Jaap Coomans
 */
public class ProgramCounter {
	private int row = 0;
	private int column = 0;
	private Direction direction = Direction.RIGHT;
	private boolean stringMode = false;

	private int rows;
	private int cols;

	public ProgramCounter(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	/**
	 * Copy constructor
	 *
	 * @param original
	 */
	public ProgramCounter(ProgramCounter original) {
		this(original.rows, original.cols);

		this.row = original.row;
		this.column = original.column;
		this.direction = original.direction;
		this.stringMode = original.stringMode;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isStringMode() {
		return this.stringMode;
	}

	public void toggleStringMode() {
		this.stringMode = !this.stringMode;
	}

	public void move() {
		switch (this.direction) {
		case UP:
			this.row = (this.row - 1 + this.rows) % this.rows;
			break;
		case DOWN:
			this.row = (this.row + 1) % this.rows;
			break;
		case LEFT:
			this.column = (this.column - 1 + this.cols) % this.cols;
			break;
		case RIGHT:
			this.column = (this.column + 1) % this.cols;
			break;
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append("(");

		if (this.column < 10) {
			result.append("0");
		}
		result.append(this.column);

		result.append(",");

		if (this.row < 10) {
			result.append("0");
		}
		result.append(this.row);

		result.append(") [");
		result.append(this.direction);
		result.append("]");

		if (this.stringMode) {
			result.append(" {Mode:String}");
		} else {
			result.append(" {Mode:Normal}");
		}

		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ProgramCounter)) {
			return false;
		}
		ProgramCounter other = (ProgramCounter) obj;
		if (other.row != this.row) {
			return false;
		}
		if (other.column != this.column) {
			return false;
		}
		if (other.direction != this.direction) {
			return false;
		}
		if (other.stringMode != this.stringMode) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.row, this.column, this.direction, this.stringMode);
	}
}
