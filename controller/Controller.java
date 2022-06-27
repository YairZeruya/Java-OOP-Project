package id206914392_id315043117.controller;

import javafx.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import id206914392_id315043117.interfaces.AbstractClassViewer;
import id206914392_id315043117.listeners.QSMEventListener;
import id206914392_id315043117.listeners.UIEventListener;
import id206914392_id315043117.model.AmericanQuestion;
import id206914392_id315043117.model.OpenQuestion;
import id206914392_id315043117.model.QuestionStockManager;
import javafx.event.EventHandler.*;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller implements UIEventListener, QSMEventListener, Serializable {
	QuestionStockManager model;
	AbstractClassViewer view;

	public Controller(QuestionStockManager model, AbstractClassViewer view) {
		this.model = model;
		this.view = view;

		try {
			if (!(QuestionStockManager.ifQuestionStockFileExists())) {

				model.hardCoded();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		model.registerListener(this);
		view.registerListener(this);
	}

	public String showQuestionStock() {
		try {
			return model.printQuestionsFile();
		} catch (ClassNotFoundException | IOException e) {
			return "can't read file";
		}

	}

	public boolean addOpenQuestionToUI(String text, String answer) {
		try {
			return model.addOpenQuestion(text, answer);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addAmericanQuestionToUI(String text, ArrayList<String> answersText, ArrayList<Boolean> answersTruth) {
		try {
			model.addAmericanQuestion(text, answersText, answersTruth);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	public String showManualTest(ArrayList<Integer> questionsSerialNumbers, ArrayList<Integer> answerSerialNumbers) {
		try {
			return model.manualTest(questionsSerialNumbers, answerSerialNumbers);
		} catch (ClassNotFoundException | IndexOutOfBoundsException | IOException e) {
			return "can't read file";
		}

	}

	public boolean changeQuestionText(int serialNumber, String text) {
		try {
			model.changeQuestionText(serialNumber, text);
			return true;
		} catch (ClassNotFoundException | IOException e) {
			return false;
		}

	}

	public boolean changeOpenAnswerText(int serialNumber, String text) {

		try {
			if (model.getQuestionBySerialNumber(serialNumber) instanceof OpenQuestion) {
				model.changeOpenQuestion(serialNumber, text);
				return true;
			}
		} catch (ClassNotFoundException | IOException e) {
			return false;
		}
		return false;

	}

	public boolean changeAmericanAnswerText(String text, boolean ifTrue, int serialNumber, int numOfAnswers) {
		try {
			if (model.getQuestionBySerialNumber(serialNumber) instanceof AmericanQuestion) {
				model.changeAmericanQuestionText(serialNumber, text, ifTrue, numOfAnswers);
			}

		} catch (ClassNotFoundException | IOException | IndexOutOfBoundsException | NumberFormatException e) {
			return false;
		}
		return false;
	}

	public boolean deleteOpenAnswer(int serialNumber) {
		try {
			if (model.getQuestionBySerialNumber(serialNumber) instanceof OpenQuestion) {
				model.deleteOpenQuestion(serialNumber);
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAmericanAnswer(int serialNumber, int answer) {

		try {
			if (model.getQuestionBySerialNumber(serialNumber) instanceof AmericanQuestion) {
				model.deleteAmericanAnswer(serialNumber, answer);
				return true;
			}
		} catch (ClassNotFoundException | IOException | NullPointerException | IndexOutOfBoundsException e) {
			return false;
		}
		return false;

	}

	public boolean createManualTest(ArrayList<Integer> questionsSerialNumbers, ArrayList<Integer> answerSerialNumbers) {
		try {
			model.manualTest(questionsSerialNumbers, answerSerialNumbers);
			return true;
		} catch (NumberFormatException | NullPointerException | ClassNotFoundException | IndexOutOfBoundsException
				| IOException e) {
			return false;
		}
	}

	public boolean createAutomatic(int questionsInTheTest) {
		try {
			model.automaticTest(questionsInTheTest, model);
			return true;
		} catch (ClassNotFoundException | IndexOutOfBoundsException | IOException e) {
			return false;
		}
	}

	public String showAutomaticTest(int numOfQuestions) {
		try {
			return model.automaticTest(numOfQuestions, model);
		} catch (ClassNotFoundException | IOException e) {
			return "file not found";
		}

	}

	public boolean copyTest(String date) {
		try {
			return model.copyTest(date);
		} catch (FileNotFoundException | CloneNotSupportedException e) {
			return false;
		}
	}

	@Override
	public void readFile() {
		try {
			model.readQuestionsFile();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	// 1
	@Override
	public void showQuestions() {
		ScrollPane scroll = new ScrollPane();
		scroll.setPadding(new Insets(15, 15, 15, 15));
		Text t = new Text(showQuestionStock());
		Stage stage2 = new Stage();
		stage2.setTitle("Questions stock");
		scroll.setContent(t);
		stage2.setScene(new Scene(scroll, 450, 400));
		stage2.show();
	}

	// 2
	@Override
	public void addQuestion(GridPane grid, Stage stage) {

		Button bOpen = new Button("Add open question");
		Button bAmerican = new Button("Add american question");
		bAmerican.setTranslateX(10);
		HBox hbox = new HBox(bOpen, bAmerican);
		hbox.setTranslateX(100);
		hbox.setTranslateY(10);
		grid.add(hbox, 450, 200);

		Label addOpen = new Label("add your question text");
		TextField openQuestionText = new TextField();
		Label addOpenA = new Label("add your answer text");
		TextField OpenQuestionAnswer = new TextField();
		Button bOpenEnter = new Button("Enter");
		VBox v1box = new VBox(addOpen, openQuestionText, addOpenA, OpenQuestionAnswer, bOpenEnter);

		Label addAmerican = new Label("Add your question text");
		TextField americanQuestionText = new TextField();
		ArrayList<String> answerText = new ArrayList();
		ArrayList<Boolean> answerTruth = new ArrayList();
		Label answerTextToAdd = new Label("Add your answer text");
		TextField answerTextT = new TextField();
		ToggleGroup radioGroup = new ToggleGroup();
		RadioButton radioButton1 = new RadioButton("True");
		radioButton1.setSelected(true);
		RadioButton radioButton2 = new RadioButton("False");
		radioButton2.setSelected(true);
		radioButton1.setToggleGroup(radioGroup);
		radioButton2.setToggleGroup(radioGroup);

		HBox hbox2 = new HBox(radioButton1, radioButton2);
		Label max10Answer = new Label("You can enter up to 10 answers");
		Button enterAnswer = new Button("Enter answer");
		Label finish = new Label("Press enter when you finish entering all the answers");
		Button bAmericanEnter = new Button("Enter");
		VBox v2box = new VBox(addAmerican, americanQuestionText, answerTextToAdd, answerTextT, hbox2, max10Answer,
				enterAnswer, finish, bAmericanEnter);

		v1box.setVisible(false);
		v2box.setVisible(false);

		grid.add(v1box, 450, 400);
		grid.add(v2box, 450, 400);

		bOpen.setOnAction(e2 -> {
			v2box.setVisible(false);
			v1box.setVisible(true);
			v1box.setTranslateX(100);
			v1box.setTranslateY(60);
			bOpenEnter.setOnAction(e3 -> {

				if (addOpenQuestionToUI(openQuestionText.getText(), OpenQuestionAnswer.getText())) {
					sucsses("Added sucssefully");
				} else {
					exp("This question already exists");
				}

				stage.close();
			});
		});

		bAmerican.setOnAction(e -> {
			v1box.setVisible(false);
			v2box.setVisible(true);
			enterAnswer.setOnAction(e1 -> {
				boolean ifTrue = radioButton1.isSelected();
				answerText.add(answerTextT.getText());
				answerTruth.add(ifTrue);
				answerTextT.setText("");
			});

			bAmericanEnter.setOnAction(e2 -> {

				if (answerText.size() <= 10) {
					addAmericanQuestionToUI(americanQuestionText.getText(), answerText, answerTruth);
					sucsses("Added succesfully");
				} else {
					exp("You can add only 10 answers");
				}

				stage.close();

			});

			v2box.setTranslateX(100);
			v2box.setTranslateY(60);

		});

	}

	// 3
	@Override
	public void changeQuestion(GridPane grid, Stage stage) {

		MenuButton Menu = new MenuButton("Select the question you want to change");
		MenuItem subMenuItem;
		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {
			int j = i;
			subMenuItem = new MenuItem(model.getQuestions().get(i).getText());
			Menu.getItems().add(subMenuItem);
			subMenuItem.setOnAction(ee -> {
				Label l2 = new Label("Press your text");

				TextField Text = new TextField();
				Text.setTranslateY(10);
				Button enter = new Button("Enter");
				enter.setTranslateY(20);
				VBox vbox = new VBox(l2, Text, enter);
				vbox.setTranslateX(110);
				vbox.setTranslateY(60);
				grid.add(vbox, 300, 320);

				enter.setOnAction(eee -> {

					changeQuestionText(j + 1, Text.getText());
					sucsses("Question chnaged sucssesfully");
					stage.close();
				});
			});
		}

		VBox vbox = new VBox(Menu);
		vbox.setTranslateX(110);
		vbox.setTranslateY(20);
		grid.add(vbox, 300, 300);
	}

	// 4
	@Override
	public void changeOpenAnswer(GridPane grid, Stage stage) {

		MenuButton menu = new MenuButton("Open Questions");
		MenuItem subMenuItem;
		menu.setTranslateX(120);
		menu.setTranslateY(10);

		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {
			if (model.openserial(i) > -1) {
				int j = i;
				subMenuItem = new MenuItem(model.getQuestions().get(i).getText());
				menu.getItems().add(subMenuItem);
				subMenuItem.setOnAction(ee -> {
					Label l2 = new Label("Press your answer");

					TextField answer = new TextField();

					Button enter = new Button("Enter");

					VBox vbox = new VBox(l2, answer, enter);
					vbox.setTranslateX(120);
					vbox.setTranslateY(50);
					grid.add(vbox, 300, 320);

					enter.setOnAction(eee -> {

						changeOpenAnswerText(j + 1, answer.getText());
						sucsses("Answer chnaged sucssesfully");
						stage.close();
					});
				});
			}
		}

		VBox vbox = new VBox(menu);
		grid.add(vbox, 300, 300);
	}

	// 4
	@Override
	public void changeAmericanAnswer(GridPane grid, Stage stage) {

		MenuButton menu = new MenuButton("American questions");
		MenuItem subMenuItem;
		menu.setTranslateX(120);
		menu.setTranslateY(80);
		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {
			if (model.americanSerial(i) > -1) {
				int n = i;
				subMenuItem = new MenuItem(model.getQuestions().get(i).getText());
				menu.getItems().add(subMenuItem);
				subMenuItem.setOnAction(ee -> {
					MenuButton Menu2 = new MenuButton("Choose the answer you want to change");
					MenuItem subMenu2Item;
					AmericanQuestion q = (AmericanQuestion) model.getQuestions().get(n);
					for (int j = 0; j < model.howManyAnswersIsExists(q); j++) {
						int t = j;
						subMenu2Item = new MenuItem(q.getAnswers().getValues().get(t).getText());
						Menu2.getItems().add(subMenu2Item);
						subMenu2Item.setOnAction(eeeee -> {
							Label l4 = new Label("Add the text of your answer");

							TextField answerText = new TextField();

							ToggleGroup radioGroup = new ToggleGroup();
							RadioButton radioButton1 = new RadioButton("True");
							radioButton1.setSelected(true);
							RadioButton radioButton2 = new RadioButton("False");
							radioButton2.setSelected(true);
							radioButton1.setToggleGroup(radioGroup);
							radioButton2.setToggleGroup(radioGroup);
							HBox hbox = new HBox(radioButton1, radioButton2);

							Button enterAnswer = new Button("Enter");

							VBox vbox = new VBox(l4, answerText, hbox, enterAnswer);
							vbox.setTranslateX(120);
							vbox.setTranslateY(120);
							grid.add(vbox, 300, 430);

							enterAnswer.setOnAction(eeeeee -> {
								boolean ifTrue = radioButton1.isSelected();
								changeAmericanAnswerText(answerText.getText(), ifTrue, n + 1, t + 1);
								sucsses("Answer changed sucssesfully");
								stage.close();

							});

						});

					}
					VBox vbox = new VBox(Menu2);
					vbox.setTranslateX(120);
					vbox.setTranslateY(100);
					grid.add(vbox, 300, 360);
				});
			}
		}
		VBox vbox = new VBox(menu);
		grid.add(vbox, 300, 340);
	}

	// 5
	@Override
	public void deleteAnswerOpen(GridPane grid, Stage stage) {
		MenuButton menu = new MenuButton("Choose the open question you want to delete its answer");
		menu.setTranslateX(48);
		menu.setTranslateY(10);
		MenuItem subMenuItem;
		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {
			if (model.openserial(i) > -1) {
				int j = i;
				subMenuItem = new MenuItem(model.getQuestions().get(i).getText());
				menu.getItems().add(subMenuItem);
				subMenuItem.setOnAction(ee -> {

					deleteOpenAnswer(j + 1);
					sucsses("Deleted succesfully");
					stage.close();
				});
			}
		}
		VBox vbox = new VBox(menu);
		grid.add(vbox, 300, 300);
	}

	// 5
	@Override
	public void deleteAnswerAmerican(GridPane grid, Stage stage) {
		MenuButton menu = new MenuButton("Choose the american question you want to delete its answer");
		MenuItem subMenuItem;
		menu.setTranslateX(40);
		menu.setTranslateY(50);
		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {
			if (model.americanSerial(i) > -1) {
				int n = i;
				subMenuItem = new MenuItem(model.getQuestions().get(i).getText());
				menu.getItems().add(subMenuItem);
				subMenuItem.setOnAction(ee -> {
					MenuButton Menu2 = new MenuButton("Select the answer you want to delete");
					MenuItem subMenu2Item;
					AmericanQuestion q = (AmericanQuestion) model.getQuestions().get(n);
					for (int j = 0; j < model.howManyAnswersIsExists(q); j++) {
						int t = j;
						subMenu2Item = new MenuItem(q.getAnswers().getValues().get(t).getText());
						Menu2.getItems().add(subMenu2Item);
						subMenu2Item.setOnAction(eeeee -> {

							deleteAmericanAnswer(n + 1, t + 1);
							sucsses("Deleted succssesfully");
							stage.close();
						});

					}
					VBox vbox = new VBox(Menu2);
					vbox.setTranslateX(120);
					vbox.setTranslateY(70);
					grid.add(vbox, 300, 360);
				});
			}
		}
		VBox vbox = new VBox(menu);
		grid.add(vbox, 300, 340);
	}

	// 6
	@Override
	public void manualTest(GridPane grid, Stage stage) {
		ArrayList<Integer> questionsSerialNumbers = new ArrayList<Integer>();
		ArrayList<Integer> answerSerialNumbers = new ArrayList<Integer>();
		MenuButton Menu = new MenuButton("Open Questions");
		Menu.setTranslateX(30);
		MenuItem subMenuItem;
		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {
			if (model.openserial(i) > -1) {
				int j = i;
				subMenuItem = new MenuItem(model.getQuestions().get(i).getText());
				Menu.getItems().add(subMenuItem);
				subMenuItem.setOnAction(ee -> {

					questionsSerialNumbers.add(j + 1);
					sucsses("Question added succesfully");
				});
			}
		}

		MenuButton MenuAmrican = new MenuButton("American Questions");
		MenuItem subMenuItemAmrican;

		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {
			if (model.americanSerial(i) > -1) {
				int n = i;
				subMenuItemAmrican = new MenuItem(model.getQuestions().get(i).getText());
				MenuAmrican.getItems().add(subMenuItemAmrican);
				subMenuItemAmrican.setOnAction(ee -> {
					MenuAmrican.setVisible(false);
					Button enter = new Button("Click when you have finished choosing answers to a question");
					MenuButton Menu2 = new MenuButton("Choose the answers to this question");
					enter.setVisible(true);
					Menu2.setVisible(true);
					questionsSerialNumbers.add(n + 1);
					MenuItem subMenu2Item;
					AmericanQuestion q = (AmericanQuestion) model.getQuestions().get(n);
					for (int j = 0; j < model.howManyAnswersIsExists(q); j++) {
						int t = j;
						subMenu2Item = new MenuItem(q.getAnswers().getValues().get(t).getText());
						Menu2.getItems().add(subMenu2Item);
						subMenu2Item.setOnAction(eeeee -> {

							answerSerialNumbers.add(t + 1);
							sucsses("This answer added to the question");

						});

						enter.setOnAction(e4 -> {
							enter.setVisible(false);
							Menu2.setVisible(false);
							MenuAmrican.setVisible(true);
							answerSerialNumbers.add(-1);
							sucsses("Question added succesfully");
						});

						VBox v3box = new VBox(enter);
						v3box.setTranslateX(10);
						v3box.setTranslateY(60);
						grid.add(v3box, 300, 370);
						VBox v1box = new VBox(Menu2);
						v1box.setTranslateX(10);
						v1box.setTranslateY(40);
						grid.add(v1box, 300, 360);
					}

				});

			}
		}

		Button endTest = new Button("I am done, show my test");
		endTest.setOnAction(e -> {
			if (createManualTest(questionsSerialNumbers, answerSerialNumbers)) {
				ScrollPane test = new ScrollPane();
				Stage showTest = new Stage();
				test.setPadding(new Insets(15, 15, 15, 15));
				showTest.setTitle("Your test");
				Text t = new Text(showManualTest(questionsSerialNumbers, answerSerialNumbers));
				test.setContent(t);
				showTest.setScene(new Scene(test, 450, 400));
				showTest.show();
				stage.close();
			} else {
				exp("You chose answers to a question without putting the question in to the test");
				stage.close();
			}
		});

		HBox hbox = new HBox(MenuAmrican, Menu);
		hbox.setTranslateX(80);
		hbox.setTranslateY(10);
		grid.add(hbox, 300, 300);
		VBox v2box = new VBox(endTest);
		grid.add(v2box, 300, 400);
		v2box.setTranslateX(150);
		v2box.setTranslateY(120);
	}

	// 7
	@Override
	public void automaticTest(GridPane grid, Stage stage) {

		MenuButton Menu = new MenuButton("Select the number of questions you want to add");
		MenuItem subMenuItem;
		for (int i = 0; i < model.howManyQuestionsIsExists(); i++) {

			String s = Integer.toString(i + 1);
			subMenuItem = new MenuItem(s);
			Menu.getItems().add(subMenuItem);
			subMenuItem.setOnAction(ee -> {
				int questionInTheTest = Integer.parseInt(s);
				if (createAutomatic(questionInTheTest)) {
					ScrollPane test = new ScrollPane();
					Stage showTest = new Stage();
					test.setPadding(new Insets(15, 15, 15, 15));
					showTest.setTitle("Your test");
					Text t = new Text(showAutomaticTest(questionInTheTest));
					test.setContent(t);
					showTest.setScene(new Scene(test, 450, 400));
					showTest.show();
					stage.close();
				} else {
					stage.close();
				}
			});
		}

		VBox vbox = new VBox(Menu);
		vbox.setTranslateX(110);
		vbox.setTranslateY(20);
		grid.add(vbox, 300, 300);

	}

	// 8
	@Override
	public void copyTest(GridPane grid, Stage stage) {

		Label label = new Label("Please enter the date of the exam you want to copy by this format: YYYY-MM-DD");
		grid.add(label, 300, 270);

		TextField date = new TextField();
		grid.add(date, 300, 290);

		Button enter = new Button("Enter");
		grid.add(enter, 300, 310);

		enter.setOnAction(e -> {
			if (copyTest(date.getText())) {
				sucsses("File has copied succesfully");
				stage.close();
			} else {
				exp("date isn't valid or you don't have an exam from this date");
			}

		});

	}

	public static void exp(String s) {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Error");
		errorAlert.setContentText(s);
		errorAlert.showAndWait();
	}

	public static void sucsses(String s) {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setHeaderText("Succesfully");
		errorAlert.setContentText(s);
		errorAlert.showAndWait();
	}

}
