package view;

import java.util.ArrayList;
import java.util.Arrays;

import classes.Competition.GameType;
import classes.Game.DrawException;
import classes.Competition;
import classes.Player;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import listeners.ViewListenable;
import data.StaticData;

public class ClassView extends AbstractView implements ViewListenable {

	private Stage primaryStage;
	private GameWindow gameStage;

	public GameWindow getGameStage() {
		return gameStage;
	}

	private FormView screen;
	private TreeView secoundScreen;
	private StackPane stBourd;
	private TextField tfParticipantName;
	private Button btnAddPlayer;
	private Button btnAddPlayerFromDB;
	private Button btnDelPlayerFromDB;
	private Button btnInfoPlayerFromDB;
	
	private Button btnStartChampionship;
	private Color btnColorRed;
	private Color btnColorGreen;
	private Color btnColorBlue;

	private ImageView bgrImageView;
	private Group bgrImageGroup;
	private Image bgrImageTennis;
	private Image bgrImageSoccer;
	private Image bgrImageBasketball;

	private DropShadow dShadow;
	private BorderPane brRoot;
	private BorderPane brTitle;
	private VBox vbPersonsLabel;
	private Label title;
	private VBox vbPlayers;
	private VBox vbGames;
	private RadioButton tennis;
	private RadioButton basketball;
	private RadioButton soccer;
	private HBox hbTitel;
	private int playersCount;

	private AbstractGameView currentGameView;
	private SoccerView soccerView;
	private BasketballView basketballView;
	private TennisView tennisView;
	private Button btnBack;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private ArrayList<TextField> quarterfinals = new ArrayList<>(8);
	private ArrayList<TextField> semifinals = new ArrayList<>(4);
	private ArrayList<TextField> finals = new ArrayList<>(2);
	private TextField winnerTextField;

	private ArrayList<Button> btnQuarterfinals = new ArrayList<>(4);
	private ArrayList<Button> btnSemifinals = new ArrayList<>(2);
	private Button finalsButton;

	public ClassView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		playersCount = 0;
		primaryStage.setTitle("Championship");
		
		gameStage = new GameWindow();
		gameStage.initOwner(primaryStage);
		gameStage.initModality(Modality.WINDOW_MODAL);

		soccerView = new SoccerView(gameStage);
		basketballView = new BasketballView(gameStage);
		tennisView = new TennisView(gameStage);

		vbPlayers = new VBox();
		vbPlayers.setSpacing(10);
		vbPlayers.setPadding(new Insets(10));
		vbPlayers.setAlignment(Pos.CENTER_LEFT);

		vbGames = new VBox();
		vbGames.setSpacing(10);
		vbGames.setPadding(new Insets(10));
		vbGames.setAlignment(Pos.CENTER_LEFT);
		vbPersonsLabel = new VBox();

		hbTitel = new HBox();
		hbTitel.setAlignment(Pos.CENTER);

		screen = new FormView();
		secoundScreen = new TreeView();
		////////////////////////////////////////////////////////////// soccer photo

		bgrImageView = new ImageView();
		bgrImageView.setOpacity(0.2);
		bgrImageTennis = new Image(StaticData.getImageFullname("bgr_tennis.jpg"));
		bgrImageSoccer = new Image(StaticData.getImageFullname("bgr_soccer.jpg"));
		bgrImageBasketball = new Image(StaticData.getImageFullname("bgr_basketball.jpg"));

		Label title = new Label();
		title.setText("Championship");
		title.setFont(Font.font("Cambria", 55));

		hbTitel.getChildren().addAll(title);

		tennis = screen.addRadioButton("tennis", 20.0);
		basketball = screen.addRadioButton("basketball", 20.0);
		soccer = screen.addRadioButton("soccer", 20.0);
		vbGames.getChildren().addAll(tennis, basketball, soccer);

		tfParticipantName = screen.addField("Participant name", 20.0);
		btnAddPlayer = screen.addButton("Add Paricipant");
		btnAddPlayerFromDB = screen.addButton("Add Random Participant From DB");
		btnDelPlayerFromDB = screen.addButton("Delete Participant From DB");
		btnInfoPlayerFromDB = screen.addButton("Get Data about Participant From DB");
		btnStartChampionship = screen.addButton("Start Championship");
		btnStartChampionship.setDisable(true);
		btnColorBlue = Color.rgb(26, 255, 255);
		btnColorRed = Color.rgb(255, 0, 0);
		btnColorGreen = Color.rgb(0, 255, 0);
		btnStartChampionship
				.setStyle(String.format("-fx-background-color: #%s;", btnColorRed.toString().replace("0x", "")));
		btnStartChampionship.setStyle(btnStartChampionship.getStyle() + "-fx-opacity: 1.0;");

		dShadow = new DropShadow(10, Color.PURPLE);
		dShadow.setOffsetX(3);
		dShadow.setOffsetY(3);
		title.setEffect(dShadow);

		tennis.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				soccer.setSelected(false);
				basketball.setSelected(false);
				onGameTypeSelected(Competition.GameType.Tennis);
				bgrImageView.setImage(bgrImageTennis);

				currentGameView = tennisView;
				gameStage.setTitle("Tennis");
				gameStage.setScene(tennisView.getScene());
			}
		});

		basketball.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				soccer.setSelected(false);
				tennis.setSelected(false);
				onGameTypeSelected(Competition.GameType.Basketball);

				bgrImageView.setImage(bgrImageBasketball);
				currentGameView = basketballView;
				gameStage.setTitle("Basketball");
				gameStage.setScene(basketballView.getScene());
			}
		});

		soccer.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				tennis.setSelected(false);
				basketball.setSelected(false);
				onGameTypeSelected(Competition.GameType.Soccer);
				bgrImageView.setImage(bgrImageSoccer);

				currentGameView = soccerView;
				gameStage.setTitle("Soccer");
				gameStage.setScene(soccerView.getScene());
			}
		});
		btnAddPlayerFromDB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
//				String name;
//				name = tfParticipantName.getText();
				
				String name = sql.executeQueryAndGetResult("select name from players order by rand() limit 1");
				if(!fromDB) {
					
				    int a,b,c,typeG;
					String id = sql.executeQueryAndGetResult("select Player_id from players where name = '" + name +  "'");
					a=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_soccer from scores where Player_id = '" +  id +  "'"));
					b=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_tennis from scores where Player_id = '" +  id +  "'"));
					c=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_basketball from scores where Player_id = '" +  id +  "'"));
					typeG=resultTopGame(a,b,c);
					String game=sql.executeQueryAndGetResult("select namegame from typegmae where idtypegmae = '" + typeG +  "'");
					
					
					if(typeG==3) {
						sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+  id+"'");
						sql.executeQuery("UPDATE  games  set   numofplay ='"+c+"'  where  Player_id = '" +  id +  "'");
						
					}
					else if(typeG==2) {
						sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+  id+"'");
						sql.executeQuery("UPDATE  games  set   numofplay ='"+b+"'  where  Player_id = '" +  id +  "'");
						
						
						}
					
					else {
						sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+  id+"'");
						sql.executeQuery("UPDATE  games  set   numofplay ='"+a+"'  where  Player_id = '" + id +  "'");	
					
					}
				}
				

				
				Player player = new Player(name);
				try {
					fromDB = true;
					addPlayerToModel(player);
				} catch (Exception e1) {
					addObjectToModelFailed(e1.getMessage());
					return;
				} finally {
					fromDB = false;
					}
				quarterfinals.get(playersCount).setText(player.getName());
																
																
				playersCount++;

				if (playersCount == 8) {
					playersCount = 0;
					btnAddPlayer.setDisable(true);
					btnAddPlayerFromDB.setDisable(true);
					btnInfoPlayerFromDB.setDisable(true);
					btnStartChampionship.setStyle(
							String.format("-fx-background-color: #%s;", btnColorGreen.toString().replace("0x", "")));
					btnStartChampionship.setDisable(false);
					showInfoDialog("championship", "all players has been added, click on Start Championship");
				}
			}
		});
		
		btnDelPlayerFromDB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				
				
				String name;
				name = tfParticipantName.getText();
				Player player = new Player(name);
				/*
				if(!fromDB) {
					String temp = sql.executeQueryAndGetResult("select Name from players where Name = '"+ player.getName()+"'");
					if(temp != "") {
						addObjectToModelFailed("One User -" + player.getName() + " Successfully Deleted form data base.");
						String playerId = sql.executeQueryAndGetResult("select Player_id from players where Name = '"+player.getName()+"'");
						sql.executeQuery("DELETE FROM scores WHERE  Player_id = '" + playerId +  "'");
						sql.executeQuery("DELETE from players WHERE Player_id = '" + playerId + "'");
						

					}
				}
				*/
				check_PlayerName(player);
				
				try {
					fromDB = true;
					deletePlayerToModel(player);
				} catch (Exception e1) {
					addObjectToModelFailed(e1.getMessage());
					return;
				} finally {
					fromDB = false;
					}
				//quarterfinals.get(playersCount).setText(player.getName());
																
																
													
				//////////////////
				///
				///

				if (playersCount == 8) {
					playersCount = 0;
					btnAddPlayer.setDisable(true);
					btnAddPlayerFromDB.setDisable(true);
					btnDelPlayerFromDB.setDisable(true);
					btnInfoPlayerFromDB.setDisable(true);
					btnStartChampionship.setStyle(
							String.format("-fx-background-color: #%s;", btnColorGreen.toString().replace("0x", "")));
					btnStartChampionship.setDisable(false);
					showInfoDialog("championship", "all players has been added, click on Start Championship");
				}
			}
		});
			
		
		
		btnInfoPlayerFromDB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				
				
				String name;
				name = tfParticipantName.getText();
				Player player = new Player(name);

				check_PlayerName(player);
				
				try {
					fromDB = true;
					infoPlayerToModel(player);
					
				} catch (Exception e1) {
					addObjectToModelFailed(e1.getMessage());
					return;
				} finally {
					fromDB = false;
					}
			
																
																
				if (playersCount == 8) {
					playersCount = 0;
					btnAddPlayer.setDisable(true);
					btnAddPlayerFromDB.setDisable(true);
					btnDelPlayerFromDB.setDisable(true);
					btnInfoPlayerFromDB.setDisable(true);
					btnStartChampionship.setStyle(
							String.format("-fx-background-color: #%s;", btnColorGreen.toString().replace("0x", "")));
					btnStartChampionship.setDisable(false);
					showInfoDialog("championship", "all players has been added, click on Start Championship");
				}
			}
		});
		
		
		
		
		btnAddPlayer.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				String name;
				name = tfParticipantName.getText();
				Player player = new Player(name);
				try {
					addPlayerToModel(player);
				} catch (Exception e1) {
					addObjectToModelFailed(e1.getMessage());
					return;
				}
				quarterfinals.get(playersCount).setText(name);
																
																
				playersCount++;

				if (playersCount == 8) {
					playersCount = 0;
					btnAddPlayer.setDisable(true);
					btnAddPlayerFromDB.setDisable(true);
					btnDelPlayerFromDB.setDisable(true);
					btnInfoPlayerFromDB.setDisable(true);
					btnStartChampionship.setStyle(
							String.format("-fx-background-color: #%s;", btnColorGreen.toString().replace("0x", "")));
					btnStartChampionship.setDisable(false);
					showInfoDialog("championship", "all players has been added, click on Start Championship");
				}
			}
		});

		btnStartChampionship.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				try {
					checkGameTypeSelected();
				} catch (Exception e1) {
					showWarnDialog("Alert", e1.getMessage());
					return;
				}
				brRoot.setVisible(false);
				btnAddPlayer.setDisable(true);
				btnAddPlayerFromDB.setDisable(true);
				btnDelPlayerFromDB.setDisable(true);
				btnInfoPlayerFromDB.setDisable(true);
				secoundScreen.setVisible(true);
				treeVisible(true);
				btnBack.setVisible(true);

				switch (getGameTypeFromModel()) {
				case Soccer:
					showInfoDialog("championship",
							"Lets start the soccer championship!\n\n"
							+ "To start a game click on: 'play a game'.\n"
							+ "Enter number of gouls for each player untill you get the winner of the match.");
					
				
					//sql.executeQuery("UPDATE games SET numofplay = numofplay + 1 WHERE idgames = '" + 1 +  "'");
					
					break;

				case Basketball:
					showInfoDialog("championship",
							"Lets start the basketball championship!\n\n"
									+ "To start a game click on: 'play a game'.\n"
									+ "Enter number of points for each player untill you get the winner of the match.");
					//sql.executeQuery("UPDATE games SET numofplay = numofplay + 1 WHERE idgames = '" + 2 +  "'");
					break;

				case Tennis:
					showInfoDialog("championship",
							"Lets start the tennis championship!\n\n"
									+ "To start a game click on: 'play a game'.\n"
									+"Choose winner from each roud untill you get the winner of the match.");
					//sql.executeQuery("UPDATE games SET numofplay = numofplay + 1 WHERE idgames = '" + 3 +  "'");
					break;
				}
			}
		});

		////////////////////////////////////////////////////////////////////////// second
		////////////////////////////////////////////////////////////////////////// view
		btnBack = new Button("Back");
		btnBack.setEffect(dShadow);
		btnBack.setVisible(false);

		btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				resetMainUI();
				resetData();
				btnBack.setVisible(false);
				
				for (int i = 1; i < quarterfinals.size(); i++) {
					if (quarterfinals.get(i) == null) {
						
						//add player to data base	
						if(!fromDB) {
						
						    int a,b,c,typeG;
							String name = sql.executeQueryAndGetResult("select name from players where Player_id = '" + i +  "'");
							a=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_soccer from scores where Player_id = '" + name +  "'"));
							b=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_tennis from scores where Player_id = '" + name +  "'"));
							c=Integer.parseInt(sql.executeQueryAndGetResult("select Number_of_wins_in_basketball from scores where Player_id = '" + name +  "'"));
							typeG=resultTopGame(a,b,c);
							String game=sql.executeQueryAndGetResult("select namegame from typegmae where idtypegmae = '" + typeG +  "'");
							
							
							if(typeG==3) {
								sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+ i+"'");
								sql.executeQuery("UPDATE  games  set   numofplay ='"+c+"'  where  Player_id = '" + i +  "'");
								
							}
							else if(typeG==2) {
								sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+ i+"'");
								sql.executeQuery("UPDATE  games  set   numofplay ='"+b+"'  where  Player_id = '" + i +  "'");
								
								
								}
							
							else {
								sql.executeQuery("update games set type ='"+game+"' where Player_id = '"+ i+"'");
								sql.executeQuery("UPDATE  games  set   numofplay ='"+a+"'  where  Player_id = '" + i +  "'");	
							
							}
						}
						
		
						
						
						
						
					}
					
					
					
				}
		
					
			}

		});

		setupTreeTextField(quarterfinals, 8);
		setupTreeTextField(semifinals, 4);
		setupTreeTextField(finals, 2);

		winnerTextField = secoundScreen.addTextFieldComp();
		winnerTextField.setEffect(dShadow);
		winnerTextField.setDisable(true);
		winnerTextField.setStyle("-fx-opacity: 1.0;");

		setupTreeButtons(btnQuarterfinals, 4);
		setupTreeButtons(btnSemifinals, 2);
		finalsButton = setupTreeButton(0, 1, finalsButton);

		stBourd = new StackPane();

		brRoot = new BorderPane();
		brRoot.setLeft(vbPersonsLabel);
		brRoot.setCenter(screen);
		brRoot.setRight(vbGames);
		// brRoot.setTop(hbTitel);

		brTitle = new BorderPane();
		brTitle.setTop(hbTitel);

		secoundScreen.setVisible(true);
		brRoot.setVisible(true);
		treeVisible(false);
		setSemiFinalsDisable(true);
		setFinalsDisable(true);
		setWinnerDisable(true);

		bgrImageView.setPreserveRatio(true);
		bgrImageView.fitWidthProperty().bind(primaryStage.widthProperty());
//		bgrImageView.fitHeightProperty().bind(primaryStage.heightProperty());

//		Line line = new Line();
//
//		// Setting the properties to a line
//		Bounds rootBounds = brRoot.getBoundsInParent();
//		Bounds boundsInScene = quarterfinals.get(0).getBoundsInParent();
//
//		double button_x = boundsInScene.getWidth();
//		double button_y = boundsInScene.getHeight();
//		
//		System.out.println(secoundScreen.localToScene(0.0, 0.0));
//		System.out.println(quarterfinals.get(0).localToScene(0.0, 0.0));
////		System.out.println(secoundScreen.localToScene(secoundScreen.getBoundsInLocal()));
//		
//		line.setStartX(button_x);
//		line.setStartY(button_y);
//		line.setEndX(button_x + 200);
//		line.setEndY(button_y);
//
//		// Creating a Group
//		Pane lines = new Pane(line);
////		lines.resize(stBourd.getWidth(), stBourd.getHeight());
////		StackPane.setAlignment(lines, Pos.TOP_LEFT);

		secoundScreen.add(btnBack, 33, 20);
		btnBack.setFont(Font.font("Cambria", 20));
		btnBack.setStyle(String.format("-fx-background-color: #%s;", btnColorBlue.toString().replace("0x", "")));
		stBourd.getChildren().addAll(bgrImageView, /*lines,*/ brTitle, secoundScreen, brRoot);

		primaryStage.setScene(new Scene(stBourd, 500, 500));
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public void jumpToNextScreen() {
		// select game type
		onGameTypeSelected(GameType.Soccer);
		bgrImageView.setImage(bgrImageSoccer);
		currentGameView = soccerView;
		gameStage.setTitle("soccer");
		gameStage.setScene(soccerView.getScene());

		// add players to model
		// add players to view

		ArrayList<String> names = new ArrayList<>();
		names.addAll(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));

		for (int i = 0; i < 8; i++) {
			Player player = new Player(names.get(i));
			try {
				addPlayerToModel(player);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			quarterfinals.get(i).setText(names.get(i));// ����� �� ���
		} // �� // �������

		btnStartChampionship.setDisable(false);

		// click on start competition
		brRoot.setVisible(false);
		secoundScreen.setVisible(true);
		treeVisible(true);
		btnBack.setVisible(true);
	}

	public void registerViewListeners() {
		soccerView.registerListeners(allListeners);
		basketballView.registerListeners(allListeners);
		tennisView.registerListeners(allListeners);
	}

	private void treeVisible(boolean visability) {
		for (TextField textField : semifinals) {
			textField.setVisible(visability);
		}
		for (Button button : btnQuarterfinals) {
			button.setVisible(visability);
		}

		for (TextField textField : finals) {
			textField.setVisible(visability);
		}

		for (Button button : btnSemifinals) {
			button.setVisible(visability);
		}

		for (TextField textField : semifinals) {
			textField.setVisible(visability);
		}
		finalsButton.setVisible(visability);
		winnerTextField.setVisible(visability);
	}

	private void setupTreeButtons(ArrayList<Button> finalsList, int size) {
		for (int i = 0; i < size; i++) {
			Button button = secoundScreen.addButtonComp("play a game");
			finalsList.add(button);

			setupTreeButton(i, size, button);
		}
	}

	private Button setupTreeButton(int index, int size, Button button) {
		if (button == null) {
			button = secoundScreen.addButtonComp("play a game");
		}

		button.setEffect(dShadow);
		final int countI = index;

		button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				int level;

				if (size == 4) {
					level = 0;
				} else if (size == 2) {
					level = 1;
				} else if (size == 1) {
					level = 2;
				} else {
					level = 0; // �� ���� �����
				}

				int player1_index = countI * 2;
				int player2_index = player1_index + 1;

				currentGameView.resetUi();
				currentGameView.setPlayer1(getPlayerFromModel(level, player1_index));
				currentGameView.setPlayer2(getPlayerFromModel(level, player2_index));
				currentGameView.updatePlayerNames();

				try {
					onStartGame(countI, level, player1_index, player2_index);
				} catch (Exception e) {
					showWarnDialog("Error", e.getMessage());
				}

				gameStage.show();
			}
		});
		return button;
	}

	public void setSemiFinalsDisable(boolean bool) {
		for (Button button : btnSemifinals) {
			button.setDisable(bool);
			if (bool) {
				button.setEffect(null);
			} else {
				button.setEffect(dShadow);
			}
		}

		for (TextField textField : semifinals) {
			if (bool) {
				textField.setEffect(null);
			} else {
				textField.setEffect(dShadow);
			}
		}
	}

	public void setFinalsDisable(boolean bool) {

		finalsButton.setDisable(bool);
		if (bool) {
			finalsButton.setEffect(null);
		} else {
			finalsButton.setEffect(dShadow);
		}

		for (TextField textField : finals) {
			if (bool) {
				textField.setEffect(null);
			} else {
				textField.setEffect(dShadow);
			}
		}
	}

	public void setWinnerDisable(boolean bool) {
		if (bool) {
			winnerTextField.setEffect(null);
		} else {
			winnerTextField.setEffect(dShadow);
		}
	}
	public void check_PlayerName(Player givenPlayer) {
		if(!fromDB) {
			String temp = sql.executeQueryAndGetResult("select Name from players where Name = '"+ givenPlayer.getName()+"'");
			if(temp != "") {
				addObjectToModelFailed("One User - " + givenPlayer.getName() + ".");
			}
			else if(givenPlayer.getName().isEmpty()) {
				addObjectToModelFailed("Name can't be empty.");
			}
			else{
				addObjectToModelFailed("There is NO player with the name " + givenPlayer.getName() + " in the data base.");	
			}
		}	
	}
	
	
	
	private void setupTreeTextField(ArrayList<TextField> finalsList, int size) {
		for (int i = 0; i < size; i++) {
			TextField textField = secoundScreen.addTextFieldComp();
			textField.setEffect(dShadow);
			textField.setDisable(true);
			textField.setStyle("-fx-opacity: 1.0;");
			finalsList.add(textField);
		}
	}

	public void checkGameTypeSelected() throws Exception {
		if (!tennis.isSelected() && !soccer.isSelected() && !basketball.isSelected()) {
			throw new Exception("Must select game type");
		}
	}

	public void addObjectToModelFailed(String message) {
		showWarnDialog("Error", message);
	}

	public void updateNewPlayer() {
		showInfoDialog("add player", "new player has been added");
	}

	@Override
	public void addPlayerToModel(Player player) throws Exception {
		for (ViewListenable l : allListeners)
			l.addPlayerToModel(player);
	}
	public void deletePlayerToModel(Player player) throws Exception {
		for (ViewListenable l : allListeners)
			l.deletePlayerToModel(player);
	}
	
	public void infoPlayerToModel(Player player) throws Exception {
		for (ViewListenable l : allListeners)
			l.infoPlayerToModel(player);
	}
	
	
	
	
	@Override
	public void onGameTypeSelected(GameType type) {
		allListeners.get(0).onGameTypeSelected(type);
	}

	@Override
	public Player getPlayerFromModel(int level, int index) {
		return allListeners.get(0).getPlayerFromModel(level, index);
	}

	@Override
	public void onStartGame(int buttonIndex, int level, int player1_index, int player2_index) throws Exception {
		allListeners.get(0).onStartGame(buttonIndex, level, player1_index, player2_index);
	}

	@Override
	public Player startBasketballGame(int player1FirestRoundScore, int player1SecendRoundScore,
			int player1theredRoundScore, int player1fourRoundScore, int player2FirestRoundScore,
			int player2SecendRoundScore, int player2theredRoundScore, int player2fourRoundScore) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLevelTextField(int buttonIndex, int level, Player player) {
		switch (level) {
		case 1:
			semifinals.get(buttonIndex).setText(player.getName());
			break;
		case 2:
			finals.get(buttonIndex).setText(player.getName());
			break;
		case 3:
			winnerTextField.setText(player.getName());
			;
			break;
		}
	}

	public void resetMainUI() {
		treeVisible(false);
		brRoot.setVisible(true);
		soccer.setSelected(false);
		tennis.setSelected(false);
		basketball.setSelected(false);
		btnAddPlayer.setDisable(false);
		btnAddPlayerFromDB.setDisable(false);
		btnDelPlayerFromDB.setDisable(false);
		btnInfoPlayerFromDB.setDisable(false);
		btnStartChampionship.setDisable(true);

		setSemiFinalsDisable(true);
		setFinalsDisable(true);
		setWinnerDisable(true);
		btnStartChampionship
				.setStyle(String.format("-fx-background-color: #%s;", btnColorRed.toString().replace("0x", "")));
		btnStartChampionship.setStyle(btnStartChampionship.getStyle() + "-fx-opacity: 1.0;");
		bgrImageView.setImage(null);
		tfParticipantName.clear();

		for (int i = 0; i < quarterfinals.size(); i++) {
			quarterfinals.get(i).clear();
			if (i < semifinals.size()) {
				semifinals.get(i).clear();
			}
			if (i < finals.size()) {
				finals.get(i).clear();
			}
		}

	}

	@Override
	public void onEndGame(int buttonIndex, int level, Player player) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Player onSoccerEndHalf(int... playersScores) throws DrawException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetData() {
		allListeners.get(0).resetData();

	}

	@Override
	public Player startTennisGame(String... playersNames) throws DrawException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameType getGameTypeFromModel() {
		return allListeners.get(0).getGameTypeFromModel();
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
		
	

			
}
