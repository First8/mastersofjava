import nl.moj.model.Tester;

import java.util.Set;
import java.util.TreeSet;

public class CompetitionTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"TestCase Games",
			"TestCase Score"
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		return sb.toString();
	}
	
	private static final boolean debug = true;
	
	public boolean performTest(int nr) throws Throwable {
		try {
			switch (nr) {
				case 0: return testGames();
				case 1: return testScore();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean testGames() {
		if (!testGamesRun(createTeams(6))) {
			return false;
		} else if (!testGamesRun(createTeams(7))) {
			return false;
		} else if (!testGamesRun(createTeams(17))) {
			return false;
		}
		return true;
	}
	
	private boolean testScore() {
		if (!createRanking6()) {
			return false;
		} else if (!createRanking7()) {
			return false;	
		}
		return true;
	}
	
	private boolean testGamesRun(Set<String> teams) {
		print("Start testGamesRun with " +  teams.size() + " teams");
		CompetitionImpl instance=new CompetitionImpl(teams.toArray(new String[teams.size()]));
		instance.MakeGames();
		TestCompetition test = new TestCompetition(teams, instance);
		int errors = test.checkCompetition();
		print("Test checkCompetition gave " + errors + " errors.");
		return errors == 0;
	}
	
	private boolean testRankingRun(Set<String> teams, int[][][] scores, String[][] ranking, int winscore) {
		print("Start testRankingRun with " +  teams.size() + " teams");
		CompetitionImpl instance=new CompetitionImpl(teams.toArray(new String[teams.size()]));
		instance.MakeGames();
		TestCompetition test = new TestCompetition(teams, instance);
		//test competition
		int errors = test.checkCompetition();
		print("Test checkCompetition gave " + errors + " errors.");
		if (errors > 0) {
			return false;
		}
		//test ranking
		for (int i=0;i<scores.length;i++) {
			for (int j=0;j<scores[i].length;j++) {
				instance.games[i][j].setScore(scores[i][j][0], scores[i][j][1]);
			}
		}
		errors = test.checkRanking(ranking, winscore);
		print("Test checkRanking gave " + errors + " errors.");
		return errors == 0;
	}
	
	private Set<String> createTeams(int nrteams) {
		if (nrteams <=0) {
			return null;
		}
		Set<String> teams = new TreeSet<String>();
		for (int i=0;i<nrteams;i++) {
			teams.add("team" + i);
		}
		return teams;
	}
	
	private boolean createRanking6() {
		Set<String> teams = createTeams(6);
		/* int[5][3][2] wedstr. 0-1	   2-3    4-5*/
		int[][][] scores = { { {1,3}, {2,1}, {1,6} },
		/*              wedstr. 0-3	   1-5    2-4*/
						     { {1,1}, {3,1}, {2,0} },
		/*              wedstr. 0-5	   3-4    1-2*/
						     { {1,1}, {0,0}, {3,3} },
		/*              wedstr. 0-4	   5-2    3-1*/
						     { {1,0}, {1,0}, {0,0} },
        /*              wedstr. 0-2	   4-1    5-3*/
						     { {0,3}, {0,1}, {1,1} } };
		String[][] ranking = { { "team1", "11" }, 
				 			   { "team2", "10" },
				 			   { "team5", "8" },
				 			   { "team0", "5" },
				 			   { "team3", "4" },
				 			   { "team4", "1" } };
		return testRankingRun(teams, scores, ranking, 3);
	}
	
	private boolean createRanking7() {
		Set<String> teams = createTeams(7);
		/* int[7][3][2] wedstr. 1-2	   3-4    5-6   7*/
		int[][][] scores = { { {1,1}, {2,1}, {2,0} },
		/*              wedstr. 1-4	   2-6    5-7   3*/
						     { {1,1}, {2,1}, {3,3} },
		/*              wedstr. 1-6	   2-7    3-5   4*/
						     { {1,1}, {2,0}, {3,3} },
		/*              wedstr. 6-7	   4-5    2-3   1*/
						     { {1,1}, {2,0}, {1,2} },
        /*              wedstr. 1-7	   6-3    4-2   5*/
						     { {0,1}, {2,1}, {3,1} },
        /*              wedstr. 1-5	   7-3    6-4   2*/
	     					 { {0,1}, {2,3}, {3,4} },
	    /*              wedstr. 1-3	   5-2    7-4   6*/
	     					 { {0,1}, {2,0}, {3,2} } };	     					 
		String[][] ranking = { { "team2", "9" }, 
				 			   { "team4", "8" },
				 			   { "team3", "7" },
				 			   { "team6", "6" },
				 			   { "team1", "5" },
				 			   { "team5", "4" },
				 			   { "team0", "3" } };
		return testRankingRun(teams, scores, ranking, 2);
	}
	
	private void print(String text) {
		if (debug) {
			System.out.println(text);
		}
	}
}
