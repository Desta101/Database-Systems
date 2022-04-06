package view;

import java.util.ArrayList;

import classes.Competition.GameType;
import classes.Game.DrawException;
import classes.Game.InvalidInputException;
import classes.Game;
import classes.Player;
import classes.Tennis;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import listeners.ViewListenable;

public class TennisView extends AbstractGameView implements ViewListenable {

	private GridPane gpTennis;
	private Label lblEnterName;
	private Label lblEnterNameFourRoundWinner;
	private Label lblEnterNameFifthRound;

	private ComboBox<String> cbFirstRoundWinner;
	private ComboBox<String> cbSecondRoundWinner;

	private ComboBox<String> cbThirdRoundWinner;
	private ComboBox<String> cbFourRoundWinner;
	private ComboBox<String> cbFifthRoundWinner;
	private ArrayList<Label> startGameLabels;

	private Button btnEnterResult;
	private Button btnEnterfifthRound;

	public TennisView(Stage stage) {
		stage.setTitle("Tennis championship");

		gpTennis = new GridPane();
		gpTennis.setPadding(new Insets(10));
		gpTennis.setHgap(10);
		gpTennis.setVgap(10);
		gpTennis.setAlignment(Pos.CENTER);

		cbFirstRoundWinner = new ComboBox<String>();
		cbSecondRoundWinner = new ComboBox<String>();
		cbThirdRoundWinner = new ComboBox<String>();
		btnEnterResult = new Button("Enter result");

		cbFourRoundWinner = new ComboBox<String>();	

		lblEnterNameFourRoundWinner = new Label("Choose four round winner:");

		cbFifthRoundWinner = new ComboBox<String>();

		btnEnterfifthRound = new Button("Enter result");

		lblEnterNameFifthRound = new Label("Choose fifth round winner:");

		startGameLabels = new ArrayList<>();

		///////////////////////////////////////////////////////////// ����� 1,2,3
		String[] numbersStrings = new String[] { "first", "second", "third" };
		for (int i = 0; i < 3; i++) {
			Label label = new Label("Choose " + numbersStrings[i] + " round winner:");
			startGameLabels.add(label);
			gpTennis.add(label, 0, i);
		}
		gpTennis.add(cbFirstRoundWinner, 1, 0);
		gpTennis.add(cbSecondRoundWinner, 1, 1);
		gpTennis.add(cbThirdRoundWinner, 1, 2);
		gpTennis.add(btnEnterResult, 1, 3);

		///////////////////////////////////////////////////////////// ����� 4

		gpTennis.add(lblEnterNameFourRoundWinner, 0, 4);
		gpTennis.add(cbFourRoundWinner, 1, 4);
		
		////////////////////////////////////////////////////////////// ����� 5

		gpTennis.add(lblEnterNameFifthRound, 0, 5);
		gpTennis.add(cbFifthRoundWinner, 1, 5);
		gpTennis.add(btnEnterfifthRound, 1, 6);
		
		gpTennis.setPadding(new Insets(50));

		mainScene = new Scene(gpTennis);

		btnEnterResult.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				String FirstRoundWinner = cbFirstRoundWinner.getValue();
				String SecondRoundWinner = cbSecondRoundWinner.getValue();
				String ThirdRoundWinner = cbThirdRoundWinner.getValue();
				try {
					Tennis.checkInput(FirstRoundWinner, SecondRoundWinner, ThirdRoundWinner);

					Player player = startTennisGame(FirstRoundWinner, SecondRoundWinner, ThirdRoundWinner);

					stage.hide();
					onEndGame(getButtonIndexFromModel(), getCurrentLevel() + 1, player);

					if (getCurrentLevel() < 2) {
						showInfoDialog("Winner", "The winner is: " + player.getName());
					}
				} catch (DrawException e) {
					showInfoDialog("Next match", "Winner cannot be determind, start fourth round.");
					disabelStartGame(true);
					disabelFourthAndFifthRound(false);

				} catch (InvalidInputException e) {
					showWarnDialog("Error", e.getMessage());
					
				}
			}
		});
		
		btnEnterfifthRound.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				String FourRoundWinner = cbFourRoundWinner.getValue();
				String FifthRoundWinner = cbFifthRoundWinner.getValue();
				try {
					Tennis.checkInput(FourRoundWinner, FifthRoundWinner);

					Player player = startTennisGame(FourRoundWinner, FifthRoundWinner);

					stage.hide();
					onEndGame(getButtonIndexFromModel(), getCurrentLevel() + 1, player);

					if (getCurrentLevel() < 2) {
						showInfoDialog("Winner", "The winner is: " + player.getName());
					}
				} catch (DrawException e) {
					e.printStackTrace();
					
				} catch (InvalidInputException e) {
					showWarnDialog("Error", e.getMessage());
				}
			}
		});
	}

	private void disabelStartGame(boolean bool) {
		cbFirstRoundWinner.setDisable(bool);
		cbSecondRoundWinner.setDisable(bool);
		cbThirdRoundWinner.setDisable(bool);
		btnEnterResult.setDisable(bool);
		for (Label label : startGameLabels) {
			label.setDisable(bool);
		}
	}

	private void disabelFourthAndFifthRound(boolean bool) {
		lblEnterNameFourRoundWinner.setDisable(bool);
		cbFourRoundWinner.setDisable(bool);
		lblEnterNameFifthRound.setDisable(bool);
		cbFifthRoundWinner.setDisable(bool);
		btnEnterfifthRound.setDisable(bool);
	}

	@Override
	public void addPlayerToModel(Player player) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameTypeSelected(GameType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public Player getPlayerFromModel(int level, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePlayerNames() {
		if (player1 != null && player2 != null) {
			String playerName1 = player1.getName();
			String playerName2 = player2.getName();
			cbFirstRoundWinner.getItems().addAll(playerName1, playerName2);
			cbSecondRoundWinner.getItems().addAll(playerName1, playerName2);
			cbThirdRoundWinner.getItems().addAll(playerName1, playerName2);
			cbFourRoundWinner.getItems().addAll(playerName1, playerName2);
			cbFifthRoundWinner.getItems().addAll(playerName1, playerName2);
		}
	}

	@Override
	public void resetUi() {
		disabelStartGame(false);
		disabelFourthAndFifthRound(true);
		
		cbFirstRoundWinner.getItems().clear();
		cbSecondRoundWinner.getItems().clear();
		cbThirdRoundWinner.getItems().clear();
		cbFourRoundWinner.getItems().clear();
		cbFifthRoundWinner.getItems().clear();
	}

	@Override
	public Player startBasketballGame(int player1FirestRoundScore, int player1SecendRoundScore,
			int player1theredRoundScore, int player1fourRoundScore, int player2FirestRoundScore,
			int player2SecendRoundScore, int player2theredRoundScore, int player2fourRoundScore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStartGame(int buttonIndex, int level, int player1_index, int player2_index) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEndGame(int buttonIndex, int level, Player player) {
		allListeners.get(0).onEndGame(buttonIndex, level, player);
	}

	@Override
	public void onEndLevel(int level) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onEndCompetition(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getButtonIndexFromModel() {
		return allListeners.get(0).getButtonIndexFromModel();
	}

	@Override
	public int getCurrentLevel() {
		return allListeners.get(0).getCurrentLevel();
	}

	@Override
	public Player onSoccerEndHalf(int... playersScores) throws DrawException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub

	}

	@Override
	public Player startTennisGame(String... playersNames) throws DrawException {
		return allListeners.get(0).startTennisGame(playersNames);
	}

	@Override
	public GameType getGameTypeFromModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePlayerToModel(Player player) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void infoPlayerToModel(Player player) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
