package classes;

public class Player {

	private String name;
	private int quarterFinalsScore;
	private int SemiFinalssScore;
	private int finalsScore;
	private int wins;
	

	public Player(String name) {
		this.name = name;
		quarterFinalsScore = 0;
		SemiFinalssScore = 0;
		finalsScore = 0;
		wins = 0;
	}


	public int getWins() {
		return wins;
	}


	public void setWins(int wins) {
		this.wins = wins;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getQuarterFinalsScore() {
		return quarterFinalsScore;
	}


	public void setQuarterFinalsScore(int quarterFinalsScore) {
		this.quarterFinalsScore = quarterFinalsScore;
	}


	public int getSemiFinalssScore() {
		return SemiFinalssScore;
	}


	public void setSemiFinalssScore(int semiFinalssScore) {
		SemiFinalssScore = semiFinalssScore;
	}


	public int getFinalsScore() {
		return finalsScore;
	}


	public void setFinalsScore(int finalsScore) {
		this.finalsScore = finalsScore;
	}

	
	
	
	
}
