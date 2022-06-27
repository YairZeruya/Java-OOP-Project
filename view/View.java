package id206914392_id315043117.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import id206914392_id315043117.interfaces.AbstractClassViewer;
import id206914392_id315043117.interfaces.MenuInterface;
import id206914392_id315043117.listeners.UIEventListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View implements AbstractClassViewer, Serializable, MenuInterface {
	private ArrayList<UIEventListener> allListeners = new ArrayList<UIEventListener>();

	@Override
	public void registerListener(UIEventListener listener) {
		allListeners.add(listener);
	}

	public View(Stage stage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		grid.setPadding(new Insets(50, 50, 50, 50));
		Scene scene = new Scene(grid, 450, 400);
		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.show();
		

		Text scenetitle = new Text("Please Select an option:");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label case1 = new Label("1 - Show all questions and anwers added");
		grid.add(case1, 0, 1);

		Label case2 = new Label("2 - Add question and answer");
		grid.add(case2, 0, 2);

		Label case3 = new Label("3 - Change Question text");
		grid.add(case3, 0, 3);

		Label case4 = new Label("4 - Change Answer text");
		grid.add(case4, 0, 4);

		Label case5 = new Label("5 - Delete answer to question");
		grid.add(case5, 0, 5);

		Label case6 = new Label("6 - Create a manual test");
		grid.add(case6, 0, 6);

		Label case7 = new Label("7 - Create an automatic test");
		grid.add(case7, 0, 7);

		Label case8 = new Label("8 - Copy a test");
		grid.add(case8, 0, 8);

		Label case9 = new Label("9 - EXIT");
		grid.add(case9, 0, 9);

		TextField userTextField = new TextField();
		grid.add(userTextField, 0, 11);

		Button bEnter = new Button("enter");
		grid.add(bEnter, 2, 11);

		bEnter.setOnAction(e -> {
			try {
				for (UIEventListener l : allListeners)
					l.readFile();
				int select = Integer.parseInt(userTextField.getText());

				if (select < 1 || select > 9) {
					exp("number should be between 1-9");
					userTextField.setText("");
				}
				switch (select) {
				case 1:
					printQuestionStock();
					break;
				case 2:
					addQuestion();
					break;
				case 3:
					changeQuestionText();
					break;
				case 4:
					changeAnswerText();
					break;
				case 5:
					deleteAnswer();
					break;
				case 6:
					createManualTest();
					break;
				case 7:
					createAutomaticTest();
					break;
				case 8:
					copyTest();
					break;
				case 9:
					stage.close();
					break;
				}

			} catch (NumberFormatException ex) {
				exp("not an integer");
				userTextField.setText("");
			}

		});

	}

	@Override
	public void printQuestionStock() {
		for (UIEventListener l : allListeners)
			l.showQuestions();
	}
	
	@Override
	public void addQuestion() {
		GridPane grid = new GridPane();
		Stage stage = new Stage();
		stage.setTitle("add Question");
		stage.setScene(new Scene(grid, 450,400));
		for (UIEventListener l : allListeners)
			l.addQuestion(grid, stage);
		stage.show();		
}


	@Override
	public void changeQuestionText() {
		Stage stage = new Stage();
		stage.setTitle("change Question Text");
		GridPane grid = new GridPane();
		for (UIEventListener l : allListeners)
			l.changeQuestion(grid, stage);
		stage.setScene(new Scene(grid, 450, 400));
		stage.show();
	}

	@Override
	public void changeAnswerText() {
		GridPane grid = new GridPane();
		Stage stage = new Stage();
		for (UIEventListener l : allListeners) {
			l.changeOpenAnswer(grid, stage); 
			l.changeAmericanAnswer(grid, stage);
		}
		stage.setTitle("Change Answer Text");
		stage.setScene(new Scene(grid, 450, 400));
		stage.show();
	}

	@Override
	public void deleteAnswer() {
		Stage stage = new Stage();
		stage.setTitle("Delete Question Text");
		GridPane grid = new GridPane();
		for (UIEventListener l : allListeners) {
			l.deleteAnswerOpen(grid,stage); 
			l.deleteAnswerAmerican(grid,stage);
		}
		stage.setScene(new Scene(grid, 450, 400));
		stage.show();
	}
	

	@Override
	public void createManualTest() {
		Stage stage = new Stage();
		stage.setTitle("Create Manual Test");
		GridPane grid = new GridPane();
		for (UIEventListener l : allListeners)
			l.manualTest(grid,stage);
		stage.setScene(new Scene(grid, 450, 400));
		stage.show();

	}

		@Override
		public void createAutomaticTest() {
			Stage stage = new Stage();
			stage.setTitle("Create Automatic Test");
			GridPane grid = new GridPane();
			
			for (UIEventListener l : allListeners)
				l.automaticTest(grid, stage);
			stage.setScene(new Scene(grid, 450, 400));
			stage.show();
		}

	@Override
	public void copyTest() {
		Stage stage = new Stage();
		stage.setTitle("Copy Test");
		GridPane grid = new GridPane();
		for (UIEventListener l : allListeners) 
			l.copyTest(grid, stage);
		stage.setScene(new Scene(grid, 450, 400));
		stage.show();
	}

	public static void exp(String s) {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Input not valid");
		errorAlert.setContentText(s);
		errorAlert.showAndWait();
	}
	

}


