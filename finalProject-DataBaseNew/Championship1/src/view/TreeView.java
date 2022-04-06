package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TreeView extends GridPane {
	private int countTextField = 1;
	private int countButton = 1;
	private int rowCountSecoundViewEighthFinals = 0;
	private int rowCountSecoundViewQuarterfinals = 1;
	private int rowCountSecoundViewSemifinals = 3;

	private int rowCountSecoundViewEightFinalsBoutton = 1;
	private int rowCountSecoundViewQuarterfinalsBoutton = 3;

	public TreeView() {
		setPadding(new Insets(10));
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
	}

	public TextField addTextFieldComp() {
		TextField textField = new TextField();
		if (countTextField <= 8) {
			add(textField, 0, rowCountSecoundViewEighthFinals);
			rowCountSecoundViewEighthFinals = rowCountSecoundViewEighthFinals + 2;
		}
		if (countTextField <= 12 && countTextField > 8) {
			add(textField, 2, rowCountSecoundViewQuarterfinals);
			rowCountSecoundViewQuarterfinals = rowCountSecoundViewQuarterfinals + 4;
		}
		if (countTextField <= 14 && countTextField > 12) {
			add(textField, 4, rowCountSecoundViewSemifinals);
			rowCountSecoundViewSemifinals = rowCountSecoundViewSemifinals + 8;
		}
		if (countTextField > 14) {
			add(textField, 6, 7);
		}
		countTextField++;
		return textField;
	}


	public Button addButtonComp(String text) {
		Button button = new Button(text);
		if (countButton <= 4) {
			add(button, 1, rowCountSecoundViewEightFinalsBoutton);
			rowCountSecoundViewEightFinalsBoutton = rowCountSecoundViewEightFinalsBoutton + 4;
		}
		if (countButton <= 6 && countButton > 4) {
			add(button, 3, rowCountSecoundViewQuarterfinalsBoutton);
			rowCountSecoundViewQuarterfinalsBoutton = rowCountSecoundViewQuarterfinalsBoutton + 8;
		}
		if (countButton > 6) {
			add(button, 5, 7);
		}
		countButton++;
		return button;
	}
	
}
