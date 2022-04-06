package model;

import java.util.ArrayList;
import java.util.Vector;

import classes.Soccer;
import controller.Controller;
import classes.Competition;
import classes.Basketball;
import classes.Player;
import listeners.ModelListenable;
import view.AbstractView;

public class CompetitionModel implements ModelListenable {

	private Soccer soccer;
	public Competition competition;
	private Basketball basketball;
	private ArrayList<ModelListenable> listeners;
	private Vector<ModelListenable> allListeners = new Vector<>();

	public CompetitionModel() {
		competition = new Competition();
		listeners = new ArrayList<>();
	}
	
	public void registerModelListeners() {
		competition.registerListeners(allListeners);
	}

	@Override
	public void addingNewPlayer() {
		// TODO Auto-generated method stub
	}

	public void addPlayer(Player player) throws Exception {
		competition.addPlayer(player);
		
		for (ModelListenable l : listeners) {
			l.addingNewPlayer();
		}
	}
	
	public void deletePlayer(Player player) throws Exception {
		competition.deletePlayer(player);
		
		for (ModelListenable l : listeners) {
			l.deleteingPlayer();
		}
	}
	public void infoPlayer(Player player) throws Exception {
		competition.infoPlayer(player);
		
		for (ModelListenable l : listeners) {
			l.infoPlayers();
		}
		
	}
	

	
	public ArrayList<Player> getQuaterFinalsArry() {
		return competition.getQuaterFinalsArry();
	}

	public Player startBasketballGame(int player1FirestRoundScore, int player1SecendRoundScore,
			int player1theredRoundScore, int player1fourRoundScore, int player2FirestRoundScore,
			int player2SecendRoundScore, int player2theredRoundScore, int player2fourRoundScore) throws Exception {
		return basketball.basketballGame(player1FirestRoundScore, player1SecendRoundScore, player1theredRoundScore,
				player1fourRoundScore, player2FirestRoundScore, player2SecendRoundScore, player2theredRoundScore,
				player2fourRoundScore);
	}

	@Override
	public void levelComplited(int level) {
		// TODO Auto-generated method stub
	}

	public void registerListener(ModelListenable listener) {
		allListeners.add(listener);
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
