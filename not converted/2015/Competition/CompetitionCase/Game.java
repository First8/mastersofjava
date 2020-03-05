public class Game {

	private int hometeam;
	private int awayteam;
	
	private int homescore = -1;
	private int awayscore = -1;
	
	public Game(int hometeam, int awayteam) {
		this.hometeam = hometeam;
		this.awayteam = awayteam;
	}

	public int getHometeam() {
		return hometeam;
	}

	public int getAwayteam() {
		return awayteam;
	}
	
	public void setScore(int home, int away) {
		this.homescore = home;
		this.awayscore = away;
	}
	
	public int[] getScore() {
		int[] score = new int[2];
		
		if (homescore < 0) {
			return null;
		}
		
		score[0] = this.homescore;
		score[1] = this.awayscore;
		return score;
	}
	
}
