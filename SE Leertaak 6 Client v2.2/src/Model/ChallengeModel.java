package Model;

public class ChallengeModel {

	private String challenger;
	private String gameType;
	private String challengeNumber;
	private boolean accepted;

	public ChallengeModel(String challenger, String challengeNumber, String gameType){
		this.challenger = challenger;
		this.gameType = gameType;
		this.challengeNumber = challengeNumber;
		accepted = false;
	}

	public String getChallenger() {
		return challenger;
	}

	public void setChallenger(String challenger) {
		this.challenger = challenger;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getChallengeNumber() {
		return challengeNumber;
	}

	public void setChallengeNumber(String challengeNumber) {
		this.challengeNumber = challengeNumber;
	}
	
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
