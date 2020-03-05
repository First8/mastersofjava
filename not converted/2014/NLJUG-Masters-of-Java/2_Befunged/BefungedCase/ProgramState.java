import java.util.Objects;
import java.util.Stack;

/**
 * Represents the state of a particular Befunge program at a certain point during its execution.
 *
 * @author Jaap Coomans
 */
public class ProgramState {
	private ProgramCounter programCounter;
	private Stack<Integer> stack;

	/**
	 * Creates a new instance that makes defensive copies of the PC and the stack.
	 *
	 * @param programCounter
	 *            The program counter.
	 * @param stack
	 *            The current stack.
	 */
	@SuppressWarnings("unchecked")
	public ProgramState(ProgramCounter programCounter, Stack<Integer> stack) {
		this.programCounter = new ProgramCounter(programCounter);
		this.stack = (Stack<Integer>) stack.clone();
	}

	public ProgramCounter getProgramCounter() {
		return this.programCounter;
	}

	public Stack<Integer> getStack() {
		return this.stack;
	}

	@Override
	public boolean equals(Object obj) {
		ProgramState other = (ProgramState) obj;
		if (!Objects.equals(this.programCounter, other.programCounter)) {
			return false;
		}
		if (!this.stack.equals(other.stack)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.programCounter, this.stack);
	}

	@Override
	public String toString() {
		return this.programCounter.toString() + " " + this.stack.toString();
	}
}
