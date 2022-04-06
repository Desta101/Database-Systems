package listeners;

import java.util.ArrayList;

import classes.Competition.GameType;
import classes.Game.DrawException;
import classes.Player;

public interface ViewListenable {

	void addPlayerToModel(Player player) throws Exception;

	Player startTennisGame(String... playersNames) throws DrawException;

	Player startBasketballGame(int player1FirestRoundScore, int player1SecendRoundScore, int player1theredRoundScore,
			int player1fourRoundScore, int player2FirestRoundScore, int player2SecendRoundScore,
			int player2theredRoundScore, int player2fourRoundScore) throws Exception;

	void onGameTypeSelected(GameType type);

	Player getPlayerFromModel(int level, int index);

	void onStartGame(int buttonIndex, int level, int player1_index, int player2_index) throws Exception;

	void onEndGame(int buttonIndex, int level, Player player);
	Player onSoccerEndHalf(int... playersScores) throws DrawException;

	void onEndLevel(int level);

	void onEndCompetition(Player player);
	int getButtonIndexFromModel();
	int getCurrentLevel();
	void resetData();
	GameType  getGameTypeFromModel();

	void deletePlayerToModel(Player player)throws Exception;

	void infoPlayerToModel(Player player) throws Exception;
	
	
}
