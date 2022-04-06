package main;

import controller.Controller;
import data.StaticData;
import javafx.application.Application;
import javafx.stage.Stage;
import model.CompetitionModel;
import view.ClassView;


public class Starter extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		StaticData.setup();
		
		CompetitionModel theModel = new CompetitionModel();
		ClassView theView = new ClassView(primaryStage);
		Controller controller = new Controller(theView, theModel);
	}
	

}
