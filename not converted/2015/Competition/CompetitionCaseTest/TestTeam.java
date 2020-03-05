import java.util.TreeMap;
import java.util.TreeSet;


public class TestTeam {
	
	private int id;
	private String name;
	private TreeSet<Integer> roundsPlayed;
	private TreeSet<String> playedAgainst;
	
	public TestTeam(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		roundsPlayed = new TreeSet<Integer>();
		playedAgainst = new TreeSet<String>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean addRoundPlaying(int round) {
		return roundsPlayed.add(round);
	}
	
	public boolean addTeamPlayedAgainst(String teamName) {
		return playedAgainst.add(teamName);
	}
	
	public String checkTeamInCompetition(int rounds, TreeMap<String, TestTeam> teams) {
		if (roundsPlayed.size() != rounds) {
			return "Team " + getName() + " plays not in all rounds it needs";
		}
		for(TestTeam team: teams.values()) {
			if (playedAgainst.contains(team.getName() == null)) {
				return "Team " + getName() + " doesn't play against all other teams";
			}
		}
		return "";
	}
	
}
