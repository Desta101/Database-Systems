package view;

import java.util.ArrayList;

import classes.Basketball;
import classes.Competition.GameType;
import classes.Game;
import classes.Game.DrawException;
import classes.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import listeners.ViewListenable;

public class BasketballView extends AbstractGameView implements ViewListenable {

	private GridPane gpBasketBall;
	private Label lblPlayer1;
	private Label lblPlayer2;
	private Label firstRound;
	private Label secondRound;
	private Label thirdRound;
	private Label fourRound;

	private TextField tfPlayer1ScoreFirstHalf;
	private TextField tfPlayer1ScoreSecondHalf;
	private TextField tfPlayer1ScoreThirdHalf;
	private TextField tfPlayer1ScoreFourHalf;

	private TextField tfPlayer2ScoreFirstHalf;
	private TextField tfPlayer2ScoreSecondHalf;
	private TextField tfPlayer2ScoreThirdHalf;
	private TextField tfPlayer2ScoreFourHalf;

	ArrayList<TextField> allTextFields;

	private ArrayList<Label> player1Labels;
	private ArrayList<Label> player2Labels;

	private Button btnEnterResult;

	public BasketballView(Stage stage) {

		stage.setTitle("Basketball championship");
		allTextFields = new ArrayList<>();
		player1Labels = new ArrayList<>(4);
		player2Labels = new ArrayList<>(4);
		gpBasketBall = new GridPane();
		gpBasketBall.setPadding(new Insets(10));
		gpBasketBall.setHgap(10);
		gpBasketBall.setVgap(10);
		gpBasketBall.setAlignment(Pos.CENTER);

		for (int i = 0; i < 4; i++) {
			player1Labels.add(new Label());
			gpBasketBall.add(player1Labels.get(i), 0, i * 2 + 1);
			player2Labels.add(new Label());
			gpBasketBall.add(player2Labels.get(i), 3, i * 2 + 1);
			Label label = new Label("VS");
			gpBasketBall.add(label, 2, i * 2 + 1);
			GridPane.setHalignment(label, HPos.CENTER);
		}

		tfPlayer1ScoreFirstHalf = new TextField();
		tfPlayer1ScoreSecondHalf = new TextField();
		tfPlayer1ScoreThirdHalf = new TextField();
		tfPlayer1ScoreFourHalf = new TextField();

		tfPlayer2ScoreFirstHalf = new TextField();
		tfPlayer2ScoreSecondHalf = new TextField();
		tfPlayer2ScoreThirdHalf = new TextField();
		tfPlayer2ScoreFourHalf = new TextField();

		allTextFields.add(tfPlayer1ScoreFirstHalf);
		allTextFields.add(tfPlayer1ScoreFourHalf);
		allTextFields.add(tfPlayer1ScoreThirdHalf);
		allTextFields.add(tfPlayer1ScoreFourHalf);

		allTextFields.add(tfPlayer2ScoreFirstHalf);
		allTextFields.add(tfPlayer2ScoreSecondHalf);
		allTextFields.add(tfPlayer2ScoreThirdHalf);
		allTextFields.add(tfPlayer2ScoreFourHalf);

		Label firstRound = new Label("first round");
		Label secondRound = new Label("secound round");
		Label thirdRound = new Label("third round");
		Label fourRound = new Label("four round");

		btnEnterResult = new Button("Enter result");

		gpBasketBall.add(tfPlayer1ScoreFirstHalf, 1, 1);
		gpBasketBall.add(tfPlayer2ScoreFirstHalf, 4, 1);
		gpBasketBall.add(firstRound, 2, 0);
		GridPane.setHalignment(firstRound, HPos.CENTER);

		gpBasketBall.add(tfPlayer1ScoreSecondHalf, 1, 3);
		gpBasketBall.add(tfPlayer2ScoreSecondHalf, 4, 3);
		gpBasketBall.add(secondRound, 2, 2);
		GridPane.setHalignment(secondRound, HPos.CENTER);

		gpBasketBall.add(tfPlayer1ScoreThirdHalf, 1, 5);
		gpBasketBall.add(tfPlayer2ScoreThirdHalf, 4, 5);
		gpBasketBall.add(thirdRound, 2, 4);
		GridPane.setHalignment(thirdRound, HPos.CENTER);

		gpBasketBall.add(tfPlayer1ScoreFourHalf, 1, 7);
		gpBasketBall.add(tfPlayer2ScoreFourHalf, 4, 7);
		gpBasketBall.add(fourRound, 2, 6);
		GridPane.setHalignment(fourRound, HPos.CENTER);

		gpBasketBall.add(btnEnterResult, 2, 8);

		for (TextField textField : allTextFields) {
			textField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (isAllFieldsFilled()) {
						btnEnterResult.setDisable(false);
					} else {
						btnEnterResult.setDisable(true);
					}
				}
			});
		}

		btnEnterResult.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				int player1FirestRoundScore = 0;
				int Player1ScoreSecondHalf = 0;
				int Player1ScoreThirdHalf = 0;
				int Player1ScoreFourHalf = 0;

				int Player2ScoreFirstHalf = 0;
				int Player2ScoreSecondHalf = 0;
				int Player2ScoreThirdHalf = 0;
				int Player2ScoreFourHalf = 0;

				Player player;
				try {
					Basketball.checkInput(tfPlayer1ScoreFirstHalf.getText(), tfPlayer1ScoreSecondHalf.getText(),
							tfPlayer1ScoreThirdHalf.getText(), tfPlayer1ScoreFourHalf.getText(),
							tfPlayer2ScoreFirstHalf.getText(), tfPlayer2ScoreSecondHalf.getText(),
							tfPlayer2ScoreThirdHalf.getText(), tfPlayer2ScoreFourHalf.getText());

					player1FirestRoundScore = Integer.parseInt(tfPlayer1ScoreFirstHalf.getText());
					Player1ScoreSecondHalf = Integer.parseInt(tfPlayer1ScoreSecondHalf.getText());
					Player1ScoreThirdHalf = Integer.parseInt(tfPlayer1ScoreThirdHalf.getText());
					Player1ScoreFourHalf = Integer.parseInt(tfPlayer1ScoreFourHalf.getText());

					Player2ScoreFirstHalf = Integer.parseInt(tfPlayer2ScoreFirstHalf.getText());
					Player2ScoreSecondHalf = Integer.parseInt(tfPlayer2ScoreSecondHalf.getText());
					Player2ScoreThirdHalf = Integer.parseInt(tfPlayer2ScoreThirdHalf.getText());
					Player2ScoreFourHalf = Integer.parseInt(tfPlayer2ScoreFourHalf.getText());

					player = startBasketballGame(player1FirestRoundScore, Player1ScoreSecondHalf, Player1ScoreThirdHalf,
							Player1ScoreFourHalf, Player2ScoreFirstHalf, Player2ScoreSecondHalf, Player2ScoreThirdHalf,
							Player2ScoreFourHalf);

					stage.hide();
					onEndGame(getButtonIndexFromModel(), getCurrentLevel() + 1, player);

					if (getCurrentLevel() < 2) {
						showInfoDialog("Winner", "The winner is: " + player.getName());
					}
				} catch (Exception e1) {
					showWarnDialog("Error", e1.getMessage());
				}
			}

		});
		gpBasketBall.setPadding(new Insets(50));


		mainScene = new Scene(gpBasketBall);
	}

	private boolean isAllFieldsFilled() {
		for (int i = 0; i < allTextFields.size(); i++) {
			if (allTextFields.get(i).getText().replace(" ", "").isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void addPlayerToModel(Player player) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Player startBasketballGame(int player1FirestRoundScore, int player1SecendRoundScore,
			int player1theredRoundScore, int player1fourRoundScore, int player2FirestRoundScore,
			int player2SecendRoundScore, int player2theredRoundScore, int player2fourRoundScore) throws Exception {
		return allListeners.get(0).startBasketballGame(player1FirestRoundScore, player1SecendRoundScore,
				player1theredRoundScore, player1fourRoundScore, player2FirestRoundScore, player2SecendRoundScore,
				player2theredRoundScore, player2fourRoundScore);
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
			for (int i = 0; i < player1Labels.size(); i++) {
				String playerName1 = player1.getName();
				String playerName2 = player2.getName();
				player1Labels.get(i).setText(playerName1);
				player2Labels.get(i).setText(playerName2);
			}
		}
	}

	@Override
	public void resetUi() {
		tfPlayer1ScoreFirstHalf.clear();
		tfPlayer1ScoreSecondHalf.clear();
		tfPlayer1ScoreThirdHalf.clear();
		tfPlayer1ScoreFourHalf.clear();

		tfPlayer2ScoreFirstHalf.clear();
		tfPlayer2ScoreSecondHalf.clear();
		tfPlayer2ScoreThirdHalf.clear();
		tfPlayer2ScoreFourHalf.clear();
		btnEnterResult.setDisable(true);
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
