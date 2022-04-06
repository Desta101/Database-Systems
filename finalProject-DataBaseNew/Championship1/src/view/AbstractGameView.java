package view;

import classes.Player;
import javafx.scene.Scene;

public abstract class AbstractGameView extends AbstractView {
	protected Scene mainScene;
	protected Player player1;
	protected Player player2;

	public Scene getScene() {
		return mainScene;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public abstract void updatePlayerNames();

	public abstract void resetUi();

}
