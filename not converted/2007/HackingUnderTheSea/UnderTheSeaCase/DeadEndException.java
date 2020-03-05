import java.awt.Point;

/**
 * Thrown if the flow hits a dead end.
 */
public class DeadEndException extends RuntimeException {

	public DeadEndException(Point p) {
		super("Ran into a deadend at "+p);
	}
	
}
