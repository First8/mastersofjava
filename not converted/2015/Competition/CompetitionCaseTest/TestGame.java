
public class TestGame {

	private TestTeam hometeam;
	private TestTeam awayteam;
	
	public TestGame(TestTeam hometeam, TestTeam awayteam) {
		this.hometeam = hometeam;
		this.awayteam = awayteam;
	}

	public TestTeam getHometeam() {
		return hometeam;
	}

	public TestTeam getAwayteam() {
		return awayteam;
	}
	
}
