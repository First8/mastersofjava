public class Solution extends RecursiveAntHillImpl {

	@Override
	public int calculate(final int row, final int col) {

		if (col < 0 || col > row) {
			return 0;
		}

		if (row == 0) {
			return 1;
		}

		return calculate(row - 1, col - 1) + calculate(row - 1, col);
	}

	public static void main(String[] args) throws Throwable {
		RecursiveAntHillTester tester = new RecursiveAntHillTester();
		tester.setSolution(new Solution());
		for (int index = 0; index < tester.getTestCountIncludeHidden(); index++) {
			System.out.println(index + " - " + tester.performTest(index));
		}
	}
}
