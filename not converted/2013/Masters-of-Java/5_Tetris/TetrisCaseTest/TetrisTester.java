import nl.moj.model.Tester;

public abstract class TetrisTester implements Tester.Testable {
	
	public static void main(String[] args) throws Throwable {
		
		int pass = 0;
		
		TetrisTester clientTester = new TetrisClientTester();
		for (int nr = 0; nr < clientTester.getTestCount(); nr++) {
			if (clientTester.performTest(nr)) {
				pass++;
			}
		}
		
		TetrisTester serverTester = new TetrisServerTester();
		for (int nr = 0; nr < serverTester.getTestCount(); nr++) {
			if (serverTester.performTest(nr)) {
				pass++;
			}
		}
		
		if (pass != clientTester.getTestCount() + serverTester.getTestCount()) {
			System.out.println("FAIL");
		}
		else {
			System.out.println("PASS");
		}
		
	}

    protected abstract TetrisTestCase[] getTestCases();

    private TetrisTestCase getTestCase(int nr) {
        return getTestCases()[nr];
    }

    public int getTestCount() {
        return getTestCases().length;
    }

    public String getTestName(int nr) {
        return getTestCase(nr).getName();
    }

    public String getTestDescription(int nr) {
        return getTestCase(nr).getDescription();
    }

    public boolean performTest(int nr) throws Throwable {

        //
        // Assume the worst
        //
        boolean result = false;

        //
        // Create a new Instance for each test.
        //
        try {
            TetrisImpl tetris = new TetrisImpl();

            //
            // Perform the specified test.
            //
            TetrisTestCase testCase = getTestCase(nr);
            Game game = testCase.getGame();
            Block block = testCase.getBlock();
            int rows = testCase.getRows();
            int maxNumOfCollapsingRows = tetris.getMaxNumOfCollapsingRows(game, block);

            result = maxNumOfCollapsingRows == rows;

            //
            // Give some feedback.
            //
            System.out.println("You got " + maxNumOfCollapsingRows + " rows. It should be " + rows + ".");
        } catch (Exception ex) {

            //
            // Catch the exception, so that other tests may
            // still be executed. Do print the stacktrace.
            //
            ex.printStackTrace();

            return false;
        }

        return result;
    }

}
