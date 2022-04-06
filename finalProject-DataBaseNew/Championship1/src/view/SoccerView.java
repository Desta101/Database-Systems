package view;

import java.util.ArrayList;
import java.util.Arrays;

import classes.Competition.GameType;
import classes.Game.DrawException;
import classes.Game.InvalidInputException;
import classes.Competition;
import classes.Game;
import classes.Player;
import classes.Soccer;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import listeners.ViewListenable;

public class SoccerView extends AbstractGameView implements ViewListenable {

	private Label lblPlayer1FirstHalf;
	private Label lblPlayer1SecondHalf;

	private Label lblPlayer2FirstHalf;
	private Label lblPlayer2SecondHalf;

	private Label lblPlayer1ThirdHalf;
	private Label lblPlayer2ThirdHalf;
	private Label lblPlayer1Pendels;
	private Label lblPlayer2Pendels;

	private ArrayList<Label> lblVsFirstSecondHalf;
	private Label lblVsThirdHalf;
	private Label lblVsPendels;

	private Button btnEnterResultFirstSecondHalf;
	private Button btnEnterResultThirdHalf;
	private Button btnEnterResultPendels;

	private TextField txtGoulsPlayer1FirstHalf;
	private TextField txtGoulsPlayer1SecoundHalf;
	private TextField txtGoulsPlayer1ThirdHalf;
	private TextField txtGoulsPlayer2FirstHalf;
	private TextField txtGoulsPlayer2SecoundHalf;
	private TextField txtGoulsPlayer2ThirdHalf;
	private TextField txtGoulsPlayer1Pendels;
	private TextField txtGoulsPlayer2Pendels;
	private ArrayList<Label> player1Labels;
	private ArrayList<Label> player2Labels;
	private ArrayList<Label> titles;
	private String[] halfNames;
	private GridPane gpSoccer;

	public SoccerView(Stage stage) {
		gpSoccer = new GridPane();
		titles = new ArrayList<>();
		lblVsFirstSecondHalf = new ArrayList<>();
		gpSoccer.setPadding(new Insets(10));
		gpSoccer.setHgap(10);
		gpSoccer.setVgap(10);
		gpSoccer.setAlignment(Pos.CENTER);

		player1Labels = new ArrayList<>(4);
		player2Labels = new ArrayList<>(4);
		halfNames = new String[] { "First half", "Second half", "Third half", "Pendels" };

		for (int i = 0; i < 4; i++) {
			player1Labels.add(new Label());
			player2Labels.add(new Label());
			Label lblHalfs = new Label(halfNames[i]);
			lblHalfs.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
			GridPane.setHalignment(lblHalfs, HPos.CENTER);
			titles.add(lblHalfs);

			if (i == 0) {
				gpSoccer.add(lblHalfs, 2, 0);
			}
			if (i == 1) {
				gpSoccer.add(lblHalfs, 2, 2);
			}
			if (i == 2) {
				gpSoccer.add(lblHalfs, 2, 5);
			}
			if (i == 3) {
				gpSoccer.add(lblHalfs, 2, 8);
			}

			if (i < 2) {
				Label label = new Label("VS");
				gpSoccer.add(label, 2, i * 2 + 1);
				GridPane.setHalignment(label, HPos.CENTER);
				lblVsFirstSecondHalf.add(label);
			}
		}

		lblPlayer1FirstHalf = new Label();

		lblPlayer1SecondHalf = new Label();
		lblPlayer2FirstHalf = new Label();
		lblPlayer2SecondHalf = new Label();
		lblPlayer1ThirdHalf = new Label();
		lblPlayer2ThirdHalf = new Label();
		lblPlayer1Pendels = new Label();
		lblPlayer2Pendels = new Label();

		lblVsThirdHalf = new Label("VS");
		gpSoccer.add(lblVsThirdHalf, 2, 6);
		GridPane.setHalignment(lblVsThirdHalf, HPos.CENTER);

		lblVsPendels = new Label("VS");
		gpSoccer.add(lblVsPendels, 2, 9);
		GridPane.setHalignment(lblVsPendels, HPos.CENTER);

		btnEnterResultFirstSecondHalf = new Button("Enter result");
		GridPane.setHalignment(btnEnterResultFirstSecondHalf, HPos.CENTER);
		btnEnterResultThirdHalf = new Button("Enter result");
		GridPane.setHalignment(btnEnterResultThirdHalf, HPos.CENTER);
		btnEnterResultPendels = new Button("Enter result");
		GridPane.setHalignment(btnEnterResultPendels, HPos.CENTER);

		txtGoulsPlayer1FirstHalf = new TextField();
		txtGoulsPlayer1SecoundHalf = new TextField();
		txtGoulsPlayer1ThirdHalf = new TextField();
		txtGoulsPlayer1Pendels = new TextField();

		txtGoulsPlayer2FirstHalf = new TextField();
		txtGoulsPlayer2SecoundHalf = new TextField();
		txtGoulsPlayer2ThirdHalf = new TextField();
		txtGoulsPlayer2Pendels = new TextField();

///////////////////////////////////////////////////////////////////////////////////////����� ������	 
		gpSoccer.add(lblPlayer1FirstHalf, 0, 1);
		gpSoccer.add(txtGoulsPlayer1FirstHalf, 1, 1);
		gpSoccer.add(lblPlayer2FirstHalf, 3, 1);
		gpSoccer.add(txtGoulsPlayer2FirstHalf, 4, 1);

///////////////////////////////////////////////////////////////////////////////////////����� �����	
		gpSoccer.add(lblPlayer1SecondHalf, 0, 3);
		gpSoccer.add(txtGoulsPlayer1SecoundHalf, 1, 3);
		gpSoccer.add(lblPlayer2SecondHalf, 3, 3);
		gpSoccer.add(txtGoulsPlayer2SecoundHalf, 4, 3);
		gpSoccer.add(btnEnterResultFirstSecondHalf, 2, 4);//// ����� ������ ����� +������

///////////////////////////////////////////////////////////////////////////////////////����� ������	
		gpSoccer.add(lblPlayer1ThirdHalf, 0, 6);
		gpSoccer.add(txtGoulsPlayer1ThirdHalf, 1, 6);
		gpSoccer.add(lblPlayer2ThirdHalf, 3, 6);
		gpSoccer.add(txtGoulsPlayer2ThirdHalf, 4, 6);
		gpSoccer.add(btnEnterResultThirdHalf, 2, 7);

///////////////////////////////////////////////////////////////////////////////////////������		
		gpSoccer.add(lblPlayer1Pendels, 0, 9);
		gpSoccer.add(txtGoulsPlayer1Pendels, 1, 9);
		gpSoccer.add(lblPlayer2Pendels, 3, 9);
		gpSoccer.add(txtGoulsPlayer2Pendels, 4, 9);
		gpSoccer.add(btnEnterResultPendels, 2, 10);

		disabelThirdHalf(true);
		disabelPendels(true);

		btnEnterResultFirstSecondHalf.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String player1FirstHalfInput = txtGoulsPlayer1FirstHalf.getText();
				String player1SecondHalfInput = txtGoulsPlayer1SecoundHalf.getText();
				String player2FirstHalfInput = txtGoulsPlayer2FirstHalf.getText();
				String player2SecondHalfInput = txtGoulsPlayer2SecoundHalf.getText();

				try {
					Soccer.checkInput(player1FirstHalfInput, player1SecondHalfInput, player2FirstHalfInput,
							player2SecondHalfInput);

					Player player = onSoccerEndHalf(Integer.parseInt(player1FirstHalfInput), Integer.parseInt(player1SecondHalfInput),
							Integer.parseInt(player2FirstHalfInput), Integer.parseInt(player2SecondHalfInput));
					
					stage.hide();
					onEndGame(getButtonIndexFromModel(), getCurrentLevel() + 1, player);
					
					if (getCurrentLevel() < 2) {
						showInfoDialog("Winner", "The winner is: " + player.getName());
					}
				} catch (DrawException e) {
					showInfoDialog("Draw", "The result is draw, start third half.");
					disabelFirstSecondHalf(true);
					disabelThirdHalf(false);

				} catch (InvalidInputException e) {
					showWarnDialog("Error", e.getMessage());
				}
			}
		});

		btnEnterResultThirdHalf.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String player1ThirdHalfInput = txtGoulsPlayer1ThirdHalf.getText();
				String player2ThirdHalfInput = txtGoulsPlayer2ThirdHalf.getText();

				try {
					Soccer.checkInput(player1ThirdHalfInput, player2ThirdHalfInput);

					Player player = onSoccerEndHalf(Integer.parseInt(player1ThirdHalfInput), Integer.parseInt(player2ThirdHalfInput));
					
					stage.hide();
					onEndGame(getButtonIndexFromModel(), getCurrentLevel() + 1, player);
					
					if (getCurrentLevel() < 2) {
						showInfoDialog("Winner", "The winner is: " + player.getName());
					}
				} catch (DrawException e) {
					showInfoDialog("Draw", "The result is draw, start forth half.");
					disabelThirdHalf(true);
					disabelPendels(false);

				} catch (InvalidInputException e) {
					showWarnDialog("Error", e.getMessage());
				}
			}
		});

		btnEnterResultPendels.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String player1Pendels = txtGoulsPlayer1Pendels.getText();
				String player2Pendels = txtGoulsPlayer2Pendels.getText();

				try {
					Soccer.checkInput(player1Pendels, player2Pendels);

					Player player = onSoccerEndHalf(Integer.parseInt(player1Pendels), Integer.parseInt(player2Pendels));
					
					stage.hide();
					onEndGame(getButtonIndexFromModel(), getCurrentLevel() + 1, player);
					
					if (getCurrentLevel() < 2) {
						showInfoDialog("Winner", "The winner is: " + player.getName());
					}
				} catch (DrawException e) {
					showWarnDialog("Draw", "In pendels can't be draw, change scores.");

				} catch (InvalidInputException e) {
					showWarnDialog("Error", e.getMessage());
				}
			}
		});
		
		gpSoccer.setPadding(new Insets(50));

		mainScene = new Scene(gpSoccer);
	}

	public void disabelFirstSecondHalf(boolean bool) {
		lblPlayer1FirstHalf.setDisable(bool);
		lblPlayer2FirstHalf.setDisable(bool);
		lblPlayer1SecondHalf.setDisable(bool);
		lblPlayer2SecondHalf.setDisable(bool);
		lblVsFirstSecondHalf.get(0).setDisable(bool);
		lblVsFirstSecondHalf.get(1).setDisable(bool);
		titles.get(0).setDisable(bool);
		titles.get(1).setDisable(bool);
		txtGoulsPlayer1FirstHalf.setDisable(bool);
		txtGoulsPlayer2FirstHalf.setDisable(bool);
		txtGoulsPlayer1SecoundHalf.setDisable(bool);
		txtGoulsPlayer2SecoundHalf.setDisable(bool);
		btnEnterResultFirstSecondHalf.setDisable(bool);
	}

	public void disabelThirdHalf(boolean bool) {
		lblPlayer1ThirdHalf.setDisable(bool);
		txtGoulsPlayer1ThirdHalf.setDisable(bool);
		lblPlayer2ThirdHalf.setDisable(bool);
		txtGoulsPlayer2ThirdHalf.setDisable(bool);
		btnEnterResultThirdHalf.setDisable(bool);
		lblVsThirdHalf.setDisable(bool);
		titles.get(2).setDisable(bool);
	}

	public void disabelPendels(boolean bool) {
		lblPlayer1Pendels.setDisable(bool);
		txtGoulsPlayer1Pendels.setDisable(bool);
		lblPlayer2Pendels.setDisable(bool);
		txtGoulsPlayer2Pendels.setDisable(bool);
		btnEnterResultPendels.setDisable(bool);
		lblVsPendels.setDisable(bool);
		titles.get(3).setDisable(bool);
	}

	@Override
	public void updatePlayerNames() {
		if (player1 != null) {
			String playerName1 = player1.getName();
			lblPlayer1FirstHalf.setText(playerName1);
			lblPlayer1SecondHalf.setText(playerName1);
			lblPlayer1ThirdHalf.setText(playerName1);
			lblPlayer1Pendels.setText(playerName1);

		}
		if (player2 != null) {
			String playerName2 = player2.getName();
			lblPlayer2FirstHalf.setText(playerName2);
			lblPlayer2SecondHalf.setText(playerName2);
			lblPlayer2ThirdHalf.setText(playerName2);
			lblPlayer2Pendels.setText(playerName2);
		}
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
	public void resetUi() {
		disabelFirstSecondHalf(false);
		disabelPendels(true);
		disabelThirdHalf(true);

		txtGoulsPlayer1FirstHalf.clear();
		txtGoulsPlayer1SecoundHalf.clear();
		txtGoulsPlayer1ThirdHalf.clear();
		txtGoulsPlayer1Pendels.clear();

		txtGoulsPlayer2FirstHalf.clear();
		txtGoulsPlayer2SecoundHalf.clear();
		txtGoulsPlayer2ThirdHalf.clear();
		txtGoulsPlayer2Pendels.clear();
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
		return allListeners.get(0).onSoccerEndHalf(playersScores);
	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player startTennisGame(String... playersNames) throws DrawException {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public void infoPlayerToModel(Player player) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
