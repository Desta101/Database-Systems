package classes;

import java.util.ArrayList;

import classes.Game.InvalidInputException;

public abstract class Game extends Helper{

	ArrayList<Player> players;
	int playerScore1;
	int playerScore2;
	int buttonIndex;
	int level;

	public Game() {
		players = new ArrayList<>(2);
		this.playerScore1 = 0;
		this.playerScore2 = 0;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void addPlayer1(Player player) {
		players.add(0, player);
	}

	public void addPlayer2(Player player) {
		players.add(1, player);
	}

	public abstract Player getWinner() throws Exception;

	public int getButtonIndex() {
		return buttonIndex;
	}

	public void setButtonIndex(int buttonIndex) {
		this.buttonIndex = buttonIndex;
	}

	public static void checkInput(String... input_integers) throws InvalidInputException {
		for(String input : input_integers) {
			if(input.isEmpty() || input == null) {
				throw new InvalidInputException("Input can't be empty");
			}
			if(!isNumeric(input)) {
				throw new InvalidInputException("Scores can contain only numbers");
			}
		}
	}

	
	private static boolean isNumeric(String input) {
	    if (input == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(input);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}	
	
	public static class DrawException extends Exception {
		public DrawException() {}
	}
	public static class InvalidInputException extends Exception {
		public InvalidInputException(String message) {
			super(message);
		}
	}
}