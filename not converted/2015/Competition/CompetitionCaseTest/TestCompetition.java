import java.util.Set;
import java.util.TreeMap;

public class TestCompetition {

	private TreeMap<String, TestTeam> teams;
	private CompetitionImpl competition;
	private int gamesPerRound;
	
	public TestCompetition(Set<String> teamNames, CompetitionImpl competition) {
		this.competition = competition;
		teams = new TreeMap<String, TestTeam>();
		int id = 0;
		for (String name: teamNames) {
			this.teams.put(name, new TestTeam(id++, name));
		}
		
		gamesPerRound = new Double(Math.floor(teams.size()/2)).intValue();
	}
	
	public void printCompetition() {
		for (int roundnr = 0;roundnr<competition.games.length;roundnr++) {
			System.out.println("\nprint round " + roundnr);
			for (Game game: competition.games[roundnr]) {
				System.out.println("Game: " + game.getHometeam() + " - " + game.getAwayteam());
			}
		}
		System.out.println("");		
	}
	
	public int checkCompetition() {
		int errors = 0;
		//the correct length of compitition?
		if (competition == null) {
			System.out.println("Competition Instance missing.");
		} else if (competition.games == null) {
			System.out.println("No games created.");
			errors++;
		} else if (competition.teams.size() != teams.size()) {
			System.out.println("Incorrect number of teams.");
			errors++;
		} else if (teams.size()%2 == 0 && competition.games.length != (teams.size() - 1)) {
			System.out.println("Incorrect number of rounds.");
			errors++;
		} else if (teams.size()%2 == 1 && competition.games.length != (teams.size())) {
			System.out.println("Incorrect number of rounds.");
			errors++;
		}
		
		//check for correct rounds
		for (int roundnr = 0;roundnr<competition.games.length;roundnr++) {
			errors = errors + checkRound(competition.games[roundnr], roundnr);
		}
		return errors;
	}
	
	private int checkRound(Game[] round, int roundnr) {
		int errors = 0;
		for (Game game: round) {
			String homeTeam = competition.teams.get(game.getHometeam());
			String awayTeam = competition.teams.get(game.getAwayteam());
			errors = errors + checkGame(homeTeam, awayTeam, roundnr);
		}
		if (gamesPerRound != round.length) {
			System.out.println("Not enough games in round " + roundnr);
			errors++;
		}
		return errors;
	}
	
	private int checkGame(String homeTeam, String awayTeam, int roundnr) {
		int errors = 0;
		if (teams.get(homeTeam) == null) {
			System.out.println("Team " + homeTeam + " doesn't exist");
			errors++;
		} else if (teams.get(awayTeam) == null) {
			System.out.println("Team " + awayTeam + " doesn't exist");
			errors++;
		} else if (!teams.get(homeTeam).addRoundPlaying(roundnr)) {
			System.out.println("Team " + homeTeam + " already plays this round");
			errors++;
		} else if (!teams.get(homeTeam).addTeamPlayedAgainst(awayTeam)) {
			System.out.println("Team " + homeTeam + " already plays this opponent (" + awayTeam + ")");
			errors++;
		} else if (!teams.get(awayTeam).addRoundPlaying(roundnr)) {
			System.out.println("Team " + awayTeam + " already plays this round");
			errors++;
		} else if (!teams.get(awayTeam).addTeamPlayedAgainst(homeTeam)) {
			System.out.println("Team " + awayTeam + " already plays this opponent (" + homeTeam + ")");
			errors++;
		}
		return errors;
	}
	
	public int checkRanking(String[][] ranking, int winscore) {
		String[][] cRanking = competition.getRanking(winscore);
		
		if (!compareStringMatrix(ranking, cRanking)) {
			System.out.println("Ranking incorrect");
			return 1;
		}
		return 0;
	}
	
	public boolean compareStringMatrix(String[][] ranking, String[][] ranking2) {
		if (ranking.length != ranking2.length) {
			return false;
		}
		
		for (int i = 0; i < ranking.length; i++) {
			if (ranking[i].length != ranking2[i].length) {
				return false;
			}
			for (int j = 0; j < ranking[i].length; j++) {
				if (!ranking[i][j].equals(ranking2[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void printRanking(String[][] ranking) {
		for (String[] team : ranking) {
			System.out.println(team[0] + ": " + team[1]);
		}
		System.out.println("");		
	}
	
	public void printTeams() {
		for (int i=0;i<competition.teams.size();i++) {
			System.out.println(i + ": " + competition.teams.get(i));
		}
	}
}
