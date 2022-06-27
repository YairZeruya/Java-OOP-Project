package id206914392_id315043117.model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OpenQuestion extends Question {
	private String answer;

	public OpenQuestion(String text, String answer) throws FileNotFoundException, ClassNotFoundException, IOException {
		super(text);
		this.answer = answer;
	}

	@Override
	public boolean equals(Question q1) {
		if (q1 instanceof AmericanQuestion) {
			return false;
		}
		return super.equals(q1);
	}

	public boolean setAnswer(String answer) {
		this.answer = answer;
		return true;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "Type ---> " + "Open " + super.toString() + "Answer: " + answer + "\n";
	}
}
