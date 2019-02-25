
public enum Levels {

	LevelBasic(new char[][] {
        {'B', 'O' }
	}, 2, "Simple but unbeatable level"),
	
	LevelLong(new char[][] {
	            {'B', 'X', 'X', 'X', 'X'},
	            {' ', 'X', ' ', 'X', ' '},
	            {' ', 'X', ' ', 'X', ' '},
	            {' ', 'X', ' ', 'X', 'X'},
	            {' ', ' ', ' ', ' ', 'X'},
	            {'O', 'X', 'X', 'X', 'X'}
          }, 26, "Level with a long route"),
	
	
	LevelLongSame(new char[][] {
            {'B', 'X', 'X', 'X', 'X'},
            {'X', 'X', ' ', 'X', 'X'},
            {'X', 'X', ' ', 'X', ' '},
            {'X', 'X', ' ', 'X', 'X'},
            {' ', ' ', ' ', ' ', 'X'},
            {'O', 'X', 'X', 'X', 'X'}
      }, 26, "Level with long route, one direction possible"),

	LevelLongAndShort(new char[][] {
            {'B', 'X', 'X', 'X', 'X'},
            {'X', 'X', ' ', 'X', 'X'},
            {'X', 'X', ' ', 'X', ' '},
            {'X', 'X', ' ', 'X', 'X'},
            {' ', 'X', ' ', ' ', 'X'},
            {'O', 'X', 'X', 'X', 'X'}
      }, 11, "Level with long route and one short route");
	
	private final char[][] level;
	private final int expectedNrOfSteps;
	private final String description;
	
	private Levels(final char[][] level, final int expectedNrOfSteps, final String description) {
		this.level = level;
		this.expectedNrOfSteps = expectedNrOfSteps;
		this.description = description;
	}
	
	public char[][] getLevel() {
		return level;
	}
	
	public int getExpectedNrOfSteps() {
		return expectedNrOfSteps;
	}
	
	public String getDescription() {
		return description;
	}
}
