public class MaximumProgramStepsListener implements ProgramListener {
	private static final int MAX_STEPS = 1000;

	private int counter = 0;

	@Override
	public void afterStep(ProgramState state) {
		this.counter++;

		if (this.counter > MAX_STEPS) {
			throw new RuntimeException("Maximum number of program steps (" + MAX_STEPS + ") was exceeded.");
		}
	}
}
