package Model;

public class MatchModel {

	private String playerToMove;
	private String gameType;
	private String opponent;
	
	public MatchModel(String playerToMove, String gameType,  String opponent) {
		this.playerToMove = playerToMove;
		this.gameType = gameType;
		this.opponent = opponent;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getPlayerToMove() {
		return playerToMove;
	}

	public void setPlayerToMove(String playerToMove) {
		this.playerToMove = playerToMove;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
}
