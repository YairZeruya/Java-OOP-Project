package id206914392_id315043117.listeners;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public interface UIEventListener {
	void changeOpenAnswer(GridPane grid, Stage stage);
	void changeAmericanAnswer(GridPane grid, Stage stage);
	void readFile();
	void deleteAnswerOpen(GridPane grid, Stage stage);
	void deleteAnswerAmerican(GridPane grid, Stage stage);
	void manualTest(GridPane grid,Stage stage);
	void addQuestion(GridPane grid,Stage stage);
	void changeQuestion(GridPane grid, Stage stage);
	void automaticTest(GridPane grid, Stage stage);
	void copyTest(GridPane grid, Stage stage);
	void showQuestions();
}
