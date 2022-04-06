package classes;

import classes.Game.DrawException;

public class Soccer extends Game {

	int player1ThirdHalfScore;
	int player2ThirdHalfScore;

	int player1Pendels;
	int player2Pendels;
	int currentHalf = 2;

	public Soccer() {
		player1ThirdHalfScore = 0;
		player2ThirdHalfScore = 0;
		player1Pendels = 0;
		player2Pendels = 0;
	}

	public Player startSoccer(int... playersScores) throws DrawException {
		switch (currentHalf) {
		case 2:
			assert playersScores.length == 4;
			return soccerFirstSecondHalf(playersScores[0], playersScores[1], playersScores[2], playersScores[3]);
		case 3:
			assert playersScores.length == 2;
			return soccerThirdHalf(playersScores[0], playersScores[1]);
		case 4:
		default:
			assert playersScores.length == 2;
			return soccerPendels(playersScores[0], playersScores[1]);
		}
	}

	// מחצית 1+2
	public Player soccerFirstSecondHalf(int player1FirstHlfGoals, int player1SecondeHalfGoals, int player2FirstGoals,
			int player2SeconeHalfGoals) throws DrawException {
		playerScore1 = player1FirstHlfGoals + player1SecondeHalfGoals;
		playerScore2 = player2FirstGoals + player2SeconeHalfGoals;
		Player winner = getWinner();
		String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+winner.getName()+"'");
		sql.executeQuery("update scores set Number_of_wins_in_soccer = Number_of_wins_in_soccer + 1, Total_wins = Total_wins +1 where Player_id = "+ playerId+"");
		
		return winner;
	}

	// מחצית 3
	public Player soccerThirdHalf(int player1ThirdHalfGoals, int player2ThirdHalfGoals) throws DrawException {
		playerScore1 = player1ThirdHalfGoals;
		playerScore2 = player2ThirdHalfGoals;
		Player winner = getWinner();
		String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+winner.getName()+"'");
		sql.executeQuery("update scores set Number_of_wins_in_soccer = Number_of_wins_in_soccer + 1, Total_wins = Total_wins +1 where Player_id = "+ playerId+"");
		
		return winner;
	}

	// פנדלים
	public Player soccerPendels(int player1Pendels, int player2Pendels) throws DrawException {
		playerScore1 = player1Pendels;
		playerScore2 = player2Pendels;
		Player winner = getWinner();
		String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+winner.getName()+"'");
		sql.executeQuery("update scores set Number_of_wins_in_soccer = Number_of_wins_in_soccer + 1, Total_wins = Total_wins +1 where Player_id = "+ playerId+"");
		
		return winner;
	}

	@Override
	public Player getWinner() throws DrawException {
		if (playerScore1 > playerScore2) {
			//sql.executeQuery("update players set Number_of_wins = Number_of_wins + 1 where name = '"+ players.get(0).getName()+"'");
			return players.get(0);
		}
		if (playerScore1 < playerScore2) {
			//sql.executeQuery("update players set Number_of_wins = Number_of_wins + 1 where name = '"+ players.get(1).getName()+"'");
			return players.get(1);
		}
		currentHalf++;
		throw new DrawException();
	}

}
