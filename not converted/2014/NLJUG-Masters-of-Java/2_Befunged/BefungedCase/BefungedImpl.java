import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main implementation class of Duke's Befunge interpreter.
 *
 * @author Jaap Coomans
 */
public class BefungedImpl {
	private List<ProgramListener> listeners = new ArrayList<ProgramListener>();

	/**
	 * Creates a new instance with an {@link InfiniteLoopDetector} attached to it.
	 */
	public BefungedImpl() {
		this.addProgramListener(new InfiniteLoopDetector());
	}

	/**
	 * Evaluates and executes the given Befunge program.
	 *
	 * @param program
	 *            The program to execute. Newline characters are interpreted as the break between two rows in the
	 *            playfield.
	 * @return The resulting output of the program as a String.
	 * @throws IOException
	 * @throws InfiniteLoopException
	 *             When the program gets stuck in an infinite loop.
	 */
	public String eval(String program) throws IOException {
		ExecutionContext context = new ExecutionContext(program);
		this.execute(context);
		return context.getOutput();
	}

	/**
	 * Executes the commands of the program loaded in the given context, until either the program is terminated or an
	 * exception has occurred.
	 *
	 * @param context
	 *            The context in which the program and it's current state are loaded.
	 */
	private void execute(ExecutionContext context) {
		while (context.isActive()) {
			this.executeCommand(context);
			context.getProgramCounter().move();
		}
	}

	/**
	 * Executes the next command of the program represented in the context.
	 *
	 * @param context
	 *            The context to execute the next command of.
	 */
	private void executeCommand(ExecutionContext context) {
		try {
			char rawCommand = context.getCurrentCommand();
			Command command = Command.createCommand(rawCommand, context.getProgramCounter().isStringMode());

			command.execute(context);
		} catch (RuntimeException rte) {
			context.terminate();
			String message = "Failed while executing command at " + context.getProgramCounter();
			throw new RuntimeException(message, rte);
		}

		this.fireAfterStep(context);
	}

	/**
	 * Fires an {@link ProgramListener#afterStep(ProgramState)} event to each listener bound to this instance.
	 *
	 * @param context
	 *            The context of the program.
	 */
	private void fireAfterStep(ExecutionContext context) {
		for (ProgramListener listener : this.listeners) {
			listener.afterStep(new ProgramState(context.getProgramCounter(), context.getStack()));
		}
	}

	public void addProgramListener(ProgramListener listener) {
		this.listeners.add(listener);
	}
}
