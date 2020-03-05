import java.util.Arrays;

public class GameSituationTestCase extends AbstractTestCase {
	private final Card[] table;
	private final Player player1;
	private final Player player2;
	private final String expectedResult;

	protected GameSituationTestCase(String name, Card[] table, Player player1, Player player2, String expectedResult) {
		super(name);
		this.table = table;
		this.player1 = player1;
		this.player2 = player2;
		this.expectedResult = expectedResult;
	}

	@Override
	public String getDescription() {
		StringBuilder description = new StringBuilder();

		description.append("Table: " + Arrays.toString(this.table)).append("\n");
		description.append(this.player1).append("\n");
		description.append(this.player2).append("\n");
		if (this.expectedResult == null) {
			description.append("Verwachting: gelijkspel\n");
		} else {
			description.append("Verwachting: ").append(this.expectedResult).append(" wint\n");
		}

		return description.toString();
	}

	@Override
	public boolean execute(HandsUpImpl implementation) {
		String actualResult = implementation.determineWinner(this.table, this.player1, this.player2);

		if (actualResult == null) {
			if (this.expectedResult != null) {
				System.out.printf("Voorspeld: gelijkspel. Verwacht: %s wint.%n", this.expectedResult);
				return false;
			}
		} else if (!actualResult.equals(this.expectedResult)) {
			if (this.expectedResult == null) {
				System.out.printf("Voorspeld: %s wint. Verwacht: gelijkspel.%n", actualResult);
			} else {
				System.out.printf("Voorspeld: %s wint. Verwacht: %s wint.%n", actualResult, this.expectedResult);
			}
			return false;
		}
		return true;
	}
}
