import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * {@link ProgramListener} implementation that will throw an {@link InfiniteLoopException} when an infinite loop is
 * detected.
 *
 * @author Jaap Coomans
 *
 */
public class InfiniteLoopDetector implements ProgramListener {
	Map<ProgramCounter, List<ProgramState>> previousSteps = new HashMap<>();

	@Override
	public void afterStep(ProgramState state) {
		this.detectInfiniteLoop(state);
	}

	/**
	 * Detects is the program is in an infinite loop by comparing the current {@link ProgramState} to previous states
	 * the program has been in. There are two situations that can be detected. The first is simply when the program
	 * arrives at the same place in exactly the same state. The second is where the program does arrive at the same
	 * place in the same direction in the same mode, but the stack shows a repetitive pattern.
	 *
	 * @param currentState
	 *            The current state of the program that is being executed.
	 * @throws InfiniteLoopException
	 *             When the program is in an infinite loop.
	 */
	public void detectInfiniteLoop(ProgramState currentState) {
		/*
		 * De ProgramCounter vertegenwoordigt al behoorlijk wat van de totale state en heeft een goede equals en
		 * hashCode implementatie. Deze kunnen we dus gebruiken om de eerste schifting te maken. Door de PC als key te
		 * gebruiken in een map hoeven we niet steeds de hele historie door te lopen op zoek naar stappen op hetzelfde
		 * punt.
		 */
		if (!this.previousSteps.containsKey(currentState.getProgramCounter())) {
			/*
			 * Het programma is nog niet eerder hier geweest, dus het enige wat we hoeven te doen is de huidige staat te
			 * bewaren.
			 */
			List<ProgramState> previousStates = new ArrayList<ProgramState>();
			previousStates.add(currentState);
			this.previousSteps.put(currentState.getProgramCounter(), previousStates);
		} else {
			// TODO: Het programma is blijkbaar eerder op dezelfde plek geweest, voer een verdere analyse uit.

		}
	}

	/**
	 * Check if the two given stacks indicate an infinite loop, given they both originate from the same position in the
	 * playfield.
	 *
	 * @param previousStack
	 *            The state of the stack in a previous step.
	 * @param currentStack
	 *            The current state of the stack.
	 */
	private void checkStack(Stack<Integer> previousStack, Stack<Integer> currentStack) {
		// TODO: Implementeer deze methode
	}

	/**
	 * Returns the last {@code n} items of the {@code original} as a list.
	 *
	 * @param original
	 *            The original list
	 * @param n
	 *            The number of items to retrieve.
	 * @return A list representing the last {@code n} values of the original.
	 */
	private static <T> List<T> tail(List<T> original, int n) {
		List<T> result = new ArrayList<T>(n);
		for (int i = 0; i < n; i++) {
			result.add(original.get(original.size() - n + i));
		}
		return result;
	}

	/**
	 * Determines whether the subject ends with the values in the given tail. In other words: determines if tail is the
	 * tail of the subject.
	 *
	 * @param subject
	 *            The list that is subject to this predicate.
	 * @param tail
	 *            The tail to test for.
	 * @return true if all elements of tail appear at the end of original in the exact same order.
	 */
	private static <T> boolean endsWith(List<T> subject, List<T> tail) {
		if (tail.size() <= subject.size()) {
			List<T> originalTail = tail(subject, tail.size());
			return originalTail.equals(tail);
		} else {
			return false;
		}
	}

	public static class InfiniteLoopException extends RuntimeException {
		private static final long serialVersionUID = -5394663241373123522L;

		public InfiniteLoopException(String message) {
			super(message);
		}
	}
}
