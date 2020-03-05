import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Context object voor de uitvoering van een Befunge programma.
 *
 * @author coomanja
 */
public class ExecutionContext {
	/**
	 * Flag that indicates whether execution should continue or should be suspended.
	 */
	private boolean isActive = true;

	/**
	 * The playfield in the form of a two-dimensional array of chars.
	 */
	private char[][] playField;

	/**
	 * The current state of the Program Counter (PC).
	 */
	private ProgramCounter programCounter;

	/**
	 * The current stack.
	 */
	private Stack<Integer> stack = new Stack<Integer>();

	/**
	 * Writer for program output.
	 */
	private StringWriter outputWriter = new StringWriter();

	/**
	 * Creates a new instance for the given program. Initializes the playfield at puts the program counter at (0,0),
	 * heading to {@link Direction#RIGHT}.
	 *
	 * @param program
	 *            The Befunge program to execute.
	 * @throws IOException
	 */
	public ExecutionContext(String program) throws IOException {
		this.playField = createPlayField(program);
		this.programCounter = new ProgramCounter(this.playField.length, this.playField[0].length);
	}

	/**
	 * Creates the playfield based on the String representation of a Befunge program. Newlines are interpreted as the
	 * end of a playfield row. The width of the rows (i.e. the length of the lines) is assumed to be equal.
	 *
	 * @param program
	 *            The String representation of the program.
	 * @return A two-dimensional char array representing the playfield.
	 * @throws IOException
	 */
	private static char[][] createPlayField(String program) throws IOException {
		List<String> lines = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new StringReader(program))) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				lines.add(line);
			}
		}

		int rows = lines.size();
		int columns = lines.get(0).length();
		char[][] grid = new char[rows][columns];

		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			String line = lines.get(rowIndex);

			for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
				grid[rowIndex][columnIndex] = line.charAt(columnIndex);
			}
		}

		return grid;
	}

	/**
	 * Write char output to the program output stream.
	 *
	 * @param output
	 *            The character to write
	 */
	public void writeOutput(char output) {
		this.outputWriter.write(output);
	}

	/**
	 * Write String output to the program output stream.
	 *
	 * @param output
	 *            The String to write
	 */
	public void writeOutput(String output) {
		this.outputWriter.write(output);
	}

	/**
	 * Terminate execution of the program by setting the isActive flag to false.
	 */
	public void terminate() {
		this.isActive = false;
	}

	/**
	 * Get the raw command at the current position of the PC.
	 *
	 * @return The current command.
	 */
	public char getCurrentCommand() {
		return this.playField[this.programCounter.getRow()][this.programCounter.getColumn()];
	}

	/**
	 * Indicates whether the program is actively being executed.
	 *
	 * @return true is the program is being executed, false if it has been suspended.
	 */
	public boolean isActive() {
		return this.isActive;
	}

	public ProgramCounter getProgramCounter() {
		return this.programCounter;
	}

	public Stack<Integer> getStack() {
		return this.stack;
	}

	/**
	 * Get the output that was written by the program.
	 *
	 * @return
	 */
	public String getOutput() {
		return this.outputWriter.toString();
	}
}
