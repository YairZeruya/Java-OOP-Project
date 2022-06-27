package id206914392_id315043117.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public abstract class Question  implements Serializable {
	private static final String QUESTIONS_STOCK = "Question Stock";
	protected static int id = 1;
	protected int serialNumber;
	protected String text;

	public Question(String text) throws FileNotFoundException, ClassNotFoundException, IOException {
		if((QuestionStockManager.ifQuestionStockFileExists())) {
			this.serialNumber = QuestionStockManager.serialNumber();
		}
		else {
			this.serialNumber = id++;
		}
		this.text = text;
	}
	

	public int getSerialNumber() {
		return serialNumber;
	}

	public String getText() {
		return text;
	}

	public boolean setText(String text) {
		this.text = text;
		return true;
	}

	public boolean equals(Question q1) {
		if (this.getText().equals((q1.getText()))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Question: " + "\n" + "serialNumber: " + serialNumber + "\n" + "Question Text: " + text + "\n";
	}

}
