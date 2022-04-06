package view;

import java.util.Vector;

import classes.Helper;
import classes.Player;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import listeners.ViewListenable;

public abstract class AbstractView extends Helper{
	
	protected Vector<ViewListenable> allListeners = new Vector<ViewListenable>();

	public void registerListener(ViewListenable listener) {
		allListeners.add(listener);
	};

	public void registerListeners(Vector<ViewListenable> listeners) {
		allListeners.addAll(listeners);
	};


	public void showWarnDialog(String title, String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public void showInfoDialog(String titel, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titel);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
}
