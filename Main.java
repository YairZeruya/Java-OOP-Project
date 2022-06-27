package id206914392_id315043117;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import id206914392_id315043117.controller.Controller;
import id206914392_id315043117.interfaces.AbstractClassViewer;
import id206914392_id315043117.model.QuestionStockManager;
import id206914392_id315043117.view.View;
import javafx.application.Application;
//to support in different kinds of courses we need to do functions like hard-coded that contains questions of this type of course.
//we need to add to question class also another attribute "type".
//and to do a function that classify the question to the course it belong to, with using the  attribute "type".
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		QuestionStockManager model = new QuestionStockManager();
		AbstractClassViewer view = new View(stage);
		Controller controller = new Controller(model, view);
	}
}


