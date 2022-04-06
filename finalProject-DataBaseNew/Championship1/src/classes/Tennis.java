package classes;

import classes.Game.DrawException;
import classes.Game.InvalidInputException;

public class Tennis extends Game {

	int firstPlayerScore;
	int secondePlayerScore;
	String fourRoundWinner;
	String fifthRoundWinner;

	public Tennis() {
		firstPlayerScore = 0;
		secondePlayerScore = 0;
	}

	public Player tennisStartGame(String... playersNames) throws DrawException {	
		//בודק כמה נצחונות כל שחקן קיבל
		for (String playerName: playersNames) {
			if(players.get(0).getName().equals(playerName)) {
				firstPlayerScore = firstPlayerScore + 1;
			}
			else {
				secondePlayerScore = secondePlayerScore + 1;
			}
		}
		Player winner = getWinner();
		String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+winner.getName()+"'");
		sql.executeQuery("update scores set Number_of_wins_in_tennis = Number_of_wins_in_tennis + 1, Total_wins = Total_wins +1 where Player_id = "+ playerId+"");
		return winner;
	}
	
	public static void checkInput(String... input_integers) throws InvalidInputException {
		for(String input : input_integers) {
			if(input == null || input.isEmpty()) {
				throw new InvalidInputException("Input can't be empty");
			}
		}
	}

	@Override
	public Player getWinner() throws DrawException {
		if (firstPlayerScore - secondePlayerScore == 3) {
			return players.get(0);
		}
		if (secondePlayerScore - firstPlayerScore == 3) {
			return players.get(1);
		}
		if (secondePlayerScore + firstPlayerScore == 5) {
			if (firstPlayerScore > secondePlayerScore) {
				return players.get(0);
			} else {
				return players.get(1);
			}
		}
		throw new DrawException();
	}
}
