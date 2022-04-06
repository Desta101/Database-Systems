package classes;

public class Basketball extends Game {

	public Player basketballGame(int player1FirestRoundScore, int player1SecendRoundScore, int player1theredRoundScore,
			int player1fourRoundScore, int player2FirestRoundScore, int player2SecendRoundScore,
			int player2theredRoundScore, int player2fourRoundScore) throws Exception {

		playerScore1 = player1FirestRoundScore + player1SecendRoundScore + player1theredRoundScore
				+ player1fourRoundScore;
		playerScore2 = player2FirestRoundScore + player2SecendRoundScore + player2theredRoundScore
				+ player2fourRoundScore;
		Player winner = getWinner();
		String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+winner.getName()+"'");
		sql.executeQuery("update scores set Number_of_wins_in_basketball = Number_of_wins_in_basketball + 1, Total_wins = Total_wins +1 where Player_id = "+ playerId+"");
		
		return winner;	
	}

	@Override
	public Player getWinner() throws Exception {
		if(playerScore1 > playerScore2) {
			return players.get(0);	
			}
		else if(playerScore1 < playerScore2) {
			return players.get(1);
		}
		throw new Exception("In basketball game can't be draw, change scores.");
	}
	
}
