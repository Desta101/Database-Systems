package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class FormView extends GridPane {
	
	private int rowCount = 0;
	
	public FormView() {
		setPadding(new Insets(10));
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
	}

	public TextField addField(String label_string) {
		return addField(label_string, null);
	}

	public TextField addField(String label_string, Double font_size) {
		Label label = new Label(label_string);
		if (font_size != null) {
			label.setFont(Font.font("Cambria", font_size));
		}
		TextField textField = new TextField();
		add(label, 0,rowCount);
		add(textField, 2, rowCount);
		rowCount ++;
		return textField;
	}
	
	public ComboBox<String> addComboBox(String text, String... options) {
		Label label = new Label(text);
		ComboBox<String> comboBox = new ComboBox<>();
		add(label, 0, rowCount);
		add(comboBox, 2, rowCount);
		comboBox.getItems().addAll(options);
		rowCount ++;
		return comboBox;
	}
	
	public RadioButton addRadioButton(String label_string) {
		return addRadioButton(label_string, null);
	}
	
	public RadioButton addRadioButton(String text, Double fontSize) {
		RadioButton radioButton = new RadioButton (text);
		if(fontSize != null) {
			radioButton.setFont(Font.font("Cambria", fontSize));
		}
//		add(radioButton, 2, rowCount +1);
//		rowCount = rowCount + 2;
		return radioButton;
	}
	
	public ComboBox<String> addComboBox(String text, int defult, String... options) {
		ComboBox<String> combobox = addComboBox(text, options);
		combobox.getSelectionModel().select(defult);
		return combobox;
	}
	
	public Button addButton(String text) {
		Button button = new Button(text);
		add(button, 2, rowCount +1);
		rowCount = rowCount + 2;
		return button;
	}
	
}
