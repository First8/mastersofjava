import java.io.IOException;

public class TestCase {
	private String name;
	private String program;
	private String expectedOutput;
	private boolean isInfiniteLoop;

	public TestCase(String name, String program, String expectedOutput) {
		this(name, program, expectedOutput, false);
	}

	public TestCase(String name, String program, boolean isInfiniteLoop) {
		this(name, program, "", isInfiniteLoop);
	}

	private TestCase(String name, String program, String expectedOutput, boolean isInfiniteLoop) {
		this.name = name;
		this.program = program;
		this.expectedOutput = expectedOutput;
		this.isInfiniteLoop = isInfiniteLoop;
	}

	public boolean execute(BefungedImpl instance) throws IOException {
		boolean result = false;

		if (this.isInfiniteLoop) {
			System.out.println("Expected output : InfiniteLoopException");
		} else {
			System.out.println("Expected output : [" + this.expectedOutput + "]");
		}

		try {

			String actualOutput = instance.eval(this.program);
			System.out.println("Actual output   : [" + actualOutput + "]");

			result = this.expectedOutput.equals(actualOutput);
		} catch (InfiniteLoopDetector.InfiniteLoopException ie) {
			if (this.isInfiniteLoop) {
				System.out.println("Correctly detected infinite loop!");
				result = true;
			} else {
				throw ie;
			}
		}
		return result;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.program;
	}
}
