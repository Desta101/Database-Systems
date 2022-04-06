package classes;

import java.util.Random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javafx.scene.control.Alert;
import listeners.ModelListenable;
import listeners.ViewListenable;

public class Competition extends Helper implements ModelListenable {
	Vector<ModelListenable> allListeners = new Vector<>();
	private ArrayList<Player> quarterfinals = new ArrayList<Player>(Collections.nCopies(8, null));
	private ArrayList<Player> semifinals = new ArrayList<Player>(Collections.nCopies(4, null));
	private ArrayList<Player> finals = new ArrayList<Player>(Collections.nCopies(2, null));
	private Player winner;
	private GameType gameType;
	private Game currentGame;

	public enum GameType {
		Soccer, Basketball, Tennis;
	}

//	public void addWinnerToNextLvl(Player player) {
//		for (int i = 0; i < quarterfinals.size(); i++) {
//			if (quarterfinals.get(i).equals(player)) {
//				for (int j = 0; j < semifinals.size(); j++) {
//					if (semifinals.get(i).equals(player))
//						finals.add(player);
//				}
//				semifinals.add(player);
//			}
//			quarterfinals.add(player);
//		}
//	}

	public ArrayList<Player> getQuaterFinalsArry() {
		return quarterfinals;
	}

	public void addWinnerToNextLevel(int buttonIndex, int level, Player player) {
		player.setWins(player.getWins() + 1);

		if (level == 1) {
			semifinals.set(buttonIndex, player);
		}
		if (level == 2) {
			finals.set(buttonIndex, player);
		}
		if (level == 3) {
			winner = player;
		}
		
		if(isLevelEnd(level)) {
			levelComplited(level);
		}
	}

	public boolean addPlayer(Player player) throws Exception {
		// Player player = new Player(name);
		
		for (int i = 0; i < quarterfinals.size(); i++) {
			player = checkPlayerName(player);
		}

	    
	    
	    
		for (int i = 0; i < quarterfinals.size(); i++) {
			if (quarterfinals.get(i) == null) {
				quarterfinals.set(i, player);
				//add player to data base	
				if(!fromDB) {
					int min = 1,max = 11;
				    int randstate = (int)Math.floor(Math.random()*(max-min+1)+min);
				    //String strState=sql.executeQueryAndGetResult("select statename from state where idstate = '" + randstate +  "'");
				    int a,b,c,typeG;
					sql.executeQuery("INSERT INTO players(Name,idstate) VALUES('"+player.getName()+"','"+randstate+"')");
					String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+player.getName()+"'");
					sql.executeQuery("INSERT INTO scores(Player_id, Number_of_wins_in_soccer, Number_of_wins_in_tennis, Number_of_wins_in_basketball,Total_wins) VALUES('"+playerId+"',0,0,0,0)");
					System.out.println(1);

					sql.executeQuery("INSERT INTO matches(Player_id,Total_championship_wins, idstate) VALUES('"+playerId+"',0,'"+randstate+"')");
					
					a=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_soccer from scores where Player_id = '" + playerId +  "'"));
					b=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_tennis from scores where Player_id = '" + playerId +  "'"));
					c=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_basketball from scores where Player_id = '" + playerId +  "'"));
					
					typeG=resultTopGame(a,b,c);
					String game=sql.executeQueryAndGetResult("select namegame from typegmae where idtypegmae = '" + typeG +  "'");

					if(typeG==3)
						sql.executeQuery("INSERT INTO games(Player_id, type,numofplay) VALUES('"+playerId+"','"+game+"','"+c+"')");
					else if(typeG==2)
						sql.executeQuery("INSERT INTO games(Player_id, type,numofplay) VALUES('"+playerId+"','"+game+"','"+b+"')");
					else 
						sql.executeQuery("INSERT INTO games(Player_id, type,numofplay) VALUES('"+playerId+"','"+game+"','"+a+"')");				
				}
				return true;
			}
		}

		return false;
	}

	/*************************/
	public boolean infoPlayer(Player player)throws Exception {

	      
		//delete player to data base	
		if(fromDB) {
			String temp = sql.executeQueryAndGetResult("select Name from players where Name = '"+ player.getName()+"'");
			if(temp != "") {
				String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+player.getName()+"'");
				String strInfo= sql.ResultPlayerInfo("SELECT * FROM  scores WHERE  Player_id = '" + playerId +  "'");
				String strInfowin=sql.ResultWinPlayerInfo("SELECT * FROM  matches WHERE  Player_id = '" + playerId +  "'");
				String strInfotop=sql.ResultTypeInfo("SELECT * FROM  games WHERE  Player_id = '" + playerId +  "'");
				String idstea = sql.executeQueryAndGetResult("select idstate from players where Name = '"+ player.getName()+"'");
				String cantry = sql.ResultWinPlayerInfoState("select statename from state where idstate = '"+ idstea +"'");
	
				//System.out.println(cantry);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Info player");
				alert.setHeaderText("all data about the player "+player.getName()+" form the data base.");
				alert.setContentText(strInfo + strInfowin +cantry +strInfotop );
				alert.showAndWait();
			}
			return true;
		}
			
	return false;	
}

	
	
	





	public boolean deletePlayer(Player player) throws Exception {
	
		//delete player to data base	
		if(fromDB) {
			String temp = sql.executeQueryAndGetResult("select Name from players where Name = '"+ player.getName()+"'");
			if(temp != "") {
				String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+player.getName()+"'");
				sql.executeQuery("DELETE FROM games WHERE  Player_id = '" + playerId +  "'");
				sql.executeQuery("DELETE FROM matches WHERE  Player_id = '" + playerId +  "'");
				sql.executeQuery("DELETE FROM scores WHERE  Player_id = '" + playerId +  "'");
				sql.executeQuery("DELETE from players WHERE Player_id = '" + playerId + "'");
				throw new Exception("Successfully Deleted form data base.");
				
			}
			return true;
		}
			
	return false;
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
		
	
	
	public Player getPlayer(int level, int index) {
		if (level == 0) {
			return quarterfinals.get(index);
		}
		if (level == 1) {
			return semifinals.get(index);
		}
		return finals.get(index);
	}
	
	
	public Player checkPlayerName(Player givenPlayer) throws Exception {
		for (Player player : quarterfinals) {
			if (player != null) {
				if (player.getName().equals(givenPlayer.getName()) && !fromDB) {
					throw new Exception("There is already a player with the name " + givenPlayer.getName() + ".");
				}
				if (player.getName().equals(givenPlayer.getName()) && fromDB) {
					while(player.getName().equals(givenPlayer.getName())) {
						String tempName = sql.executeQueryAndGetResult("select name from players order by rand() limit 1");
						givenPlayer.setName(tempName);
						//Player finalPlayer = new Player(tempName);
						return givenPlayer;
					}
					//throw new Exception("There is already a player with the name " + name + ".");
				}
				
			}
			if(givenPlayer.getName().isEmpty()) {
				throw new Exception("Name can't be empty.");
			}
		}
		if(!fromDB) {
			String temp = sql.executeQueryAndGetResult("select Name from players where Name = '"+ givenPlayer.getName()+"'");
			if(temp != "") {
				throw new Exception("There is already a player with the name " + givenPlayer.getName() + " in the data base.");

			}
		}
		return givenPlayer;
	}

//	public void checkPlayerName(String name) throws Exception {
//		for (Player player : quarterfinals) {
//			if (player != null) {
//				if (player.getName().equals(name) && !fromDB) {
//					throw new Exception("There is already a player with the name " + name + ".");
//				}
//				if (player.getName().equals(name) && fromDB) {
//					while(player.getName().equals(name)) {
//						String tempName = sql.executeQueryAndGetResult("select name from players order by rand() limit 1");
//					}
//					//throw new Exception("There is already a player with the name " + name + ".");
//				}
//				
//			}
//			if(name.isEmpty()) {
//				throw new Exception("Name can't be empty.");
//			}
//		}
//		if(!fromDB) {
//			String temp = sql.executeQueryAndGetResult("select Name from players where Name = '"+ name+"'");
//			if(temp != "") {
//				throw new Exception("There is already a player with the name " + name + " in the data base.");
//
//			}
//		}
//		
//	}

	public boolean isLevelEnd(int level) {
		ArrayList<Player> listFinals;
		switch (level) {
		case 1:
			listFinals = semifinals;
			break;
		case 2:
			listFinals = finals;
			break;
		case 3:
			return winner != null;
		default:
			listFinals = finals;
			break;
		}
		
		for(Player player : listFinals) {
			if(player == null){
				return false;
			}
		}
		return true;
	}
	
	public void startGame(int buttonIndex, int level, int player1_index, int player2_index) throws Exception {

		switch (gameType) {
		case Soccer:
			currentGame = new Soccer();
			break;
		case Basketball:
			currentGame = new Basketball();
			break;
		case Tennis:
			currentGame = new Tennis();
			break;
		}

		currentGame.setButtonIndex(buttonIndex);
		currentGame.setLevel(level);

		ArrayList<Player> playersList;

		switch (level) {
		case 0:
			playersList = quarterfinals;
			break;
		case 1:
			playersList = semifinals;
			break;
		case 2:
			playersList = finals;
			break;
		default:
			throw new Exception("There is no game for this number. Please pass 0 or 1 0r 2.");
		}

		currentGame.addPlayer1(playersList.get(player1_index));
		currentGame.addPlayer2(playersList.get(player2_index));
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public GameType getGameType() {
		return gameType;
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	@Override
	public void addingNewPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelComplited(int level) {
		allListeners.get(0).levelComplited(level);
	}

	public void registerListener(ModelListenable listener) {
		allListeners.add(listener);
	};

	public void registerListeners(Vector<ModelListenable> listeners) {
		allListeners.addAll(listeners);
	}

	public Player getCompetitionWinner() {
		return winner;
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
