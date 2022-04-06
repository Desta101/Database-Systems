package controller;

import java.util.ArrayList;
import classes.Basketball;
import classes.Competition;
import classes.Competition.GameType;
import classes.Game.DrawException;
import classes.Helper;
import classes.Player;
import classes.Soccer;
import classes.Tennis;
import listeners.ModelListenable;
import listeners.ViewListenable;
import model.CompetitionModel;
import view.ClassView;

public class Controller extends Helper implements ViewListenable, ModelListenable {

	private ClassView theView;
	private CompetitionModel theModel;

	public Controller(ClassView v, CompetitionModel m) {
		theView = v;
		theModel = m;

		theView.registerListener(this);
		theModel.registerListener(this);

		theView.registerViewListeners(); // SoccerView, BasketBallView, TennisView
		theModel.registerModelListeners(); // Competition

	//	theView.jumpToNextScreen(); 

	}

	@Override
	public void addingNewPlayer() {
		theView.updateNewPlayer();
	}

	@Override
	public void addPlayerToModel(Player player) throws Exception {
		theModel.addPlayer(player);
	}
	public void deletePlayerToModel(Player player) throws Exception {
		theModel.deletePlayer(player);
	}
	public void infoPlayerToModel(Player player) throws Exception {
		// TODO Auto-generated method stub
		theModel.infoPlayer(player);
	}
	
	
	@Override
	public void onGameTypeSelected(GameType type) {
		theModel.competition.setGameType(type);
	}

	@Override
	public Player getPlayerFromModel(int level, int index) {
		return theModel.competition.getPlayer(level, index);
	}

	@Override
	public Player startBasketballGame(int player1FirestRoundScore, int player1SecendRoundScore,
			int player1theredRoundScore, int player1fourRoundScore, int player2FirestRoundScore,
			int player2SecendRoundScore, int player2theredRoundScore, int player2fourRoundScore) throws Exception {

		return ((Basketball) theModel.competition.getCurrentGame()).basketballGame(player1FirestRoundScore,
				player1SecendRoundScore, player1theredRoundScore, player1fourRoundScore, player2FirestRoundScore,
				player2SecendRoundScore, player2theredRoundScore, player2fourRoundScore);
	}

	@Override
	public void onStartGame(int buttonIndex, int level, int player1_index, int player2_index) throws Exception {
		theModel.competition.startGame(buttonIndex, level, player1_index, player2_index);
	}

	@Override
	public void onEndGame(int buttonIndex, int level, Player player) {
		// show winner at next level
		theView.setLevelTextField(buttonIndex, level, player);
		// add player to next list
		theModel.competition.addWinnerToNextLevel(buttonIndex, level, player);
	}

	@Override
	public void onEndLevel(int level) {
		if (level == 1) {
			theView.setSemiFinalsDisable(false);
		}
		if (level == 2) {
			theView.setFinalsDisable(false);
		}
		if (level == 3) {
			theView.setWinnerDisable(false);
			onEndCompetition(theModel.competition.getCompetitionWinner());
		}
	}

	@Override
	public void onEndCompetition(Player player) {
		String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+player.getName()+"'");
		sql.executeQuery("update matches set Total_championship_wins = Total_championship_wins + 1 where Player_id = '"+ playerId+"'");
		theView.showInfoDialog("Winner", "The competition winner is: " + player.getName());

	    int a,b,c,typeG;
		
		a=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_soccer from scores where Player_id = '" + playerId +  "'"));
		b=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_tennis from scores where Player_id = '" + playerId +  "'"));
		c=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_basketball from scores where Player_id = '" + playerId +  "'"));
		typeG=resultTopGame(a,b,c);
		String game=sql.executeQueryAndGetResult("select namegame from typegmae where idtypegmae = '" + typeG +  "'");
				
		if(typeG==3) {
			sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+ playerId+"'");
			sql.executeQuery("UPDATE  games  set   numofplay ='"+c+"'  where  Player_id = '" + playerId +  "'");
			
		}
		else if(typeG==2) {
			sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+ playerId+"'");
			sql.executeQuery("UPDATE  games  set   numofplay ='"+b+"'  where  Player_id = '" + playerId +  "'");
			
			
			}
		
		else {
			sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+ playerId+"'");
			sql.executeQuery("UPDATE  games  set   numofplay ='"+a+"'  where  Player_id = '" + playerId +  "'");	
		
		}
		
		
		
		
	}
	
	public int resultTopGame(int n1, int n2,int n3) {
		
	       if(n1 >= n2) {
	            if(n1 >= n3)
	            	return 1; 
	            else
	            	return 3; 
	        } else {
	            if(n2 >= n3)	        
	            	return 2; 
	            else
	               return 3; 
	        }
	}
		
	

	@Override
	public int getButtonIndexFromModel() {
		return theModel.competition.getCurrentGame().getButtonIndex();
	}

	@Override
	public int getCurrentLevel() {
		return theModel.competition.getCurrentGame().getLevel();
	}

	@Override
	public void levelComplited(int level) {
		onEndLevel(level);
	}

	@Override
	public Player onSoccerEndHalf(int... playersScores) throws DrawException {
		return ((Soccer) theModel.competition.getCurrentGame()).startSoccer(playersScores);
	}

	@Override
	public void resetData() {
		theModel = new CompetitionModel();
		theModel.registerListener(this);
		theModel.registerModelListeners();
	}

	@Override
	public Player startTennisGame(String... playersNames) throws DrawException {
		return ((Tennis) theModel.competition.getCurrentGame()).tennisStartGame(playersNames);
	}

	@Override
	public GameType getGameTypeFromModel() {
		return theModel.competition.getGameType();
	}

	@Override
	public void deleteingPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoPlayers() {
		// TODO Auto-generated method stub
		
	}

	

}
