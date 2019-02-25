import nl.moj.model.Tester;

public class BloxorzTester implements Tester.Testable{

	private Levels[] bloxorzLevels = Levels.values();
			
	@Override
	public int getTestCount() {
		return bloxorzLevels.length;
	}

	@Override
	public String getTestName(int nr) {
		return bloxorzLevels[0].name();
	}

	@Override
	public String getTestDescription(int nr) {
		return bloxorzLevels[0].getDescription();
	}

	@Override
	public boolean performTest(int nr) throws Throwable {
		final int expectedNrOfSteps = bloxorzLevels[0].getExpectedNrOfSteps();
		
		final Board board = Board.fromCharArray(bloxorzLevels[0].getLevel());
		
		final GameEngine gameEngine = new GameEngineImpl();
		gameEngine.loadGame(board);
	
		int shortestPath = gameEngine.calculate();
		final int actualNrOfSteps = shortestPath;
		
		System.out.println("shortest path: " + shortestPath+ " - expected nr of steps: " + expectedNrOfSteps); 
		
		return expectedNrOfSteps == actualNrOfSteps && expectedNrOfSteps == shortestPath;
	}
}
