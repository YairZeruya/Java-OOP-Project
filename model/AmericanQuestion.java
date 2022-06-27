package id206914392_id315043117.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class AmericanQuestion extends Question  implements Serializable  {
	private Set <AmericanAnswer> answers;

	public AmericanQuestion(String text) throws FileNotFoundException, ClassNotFoundException, IOException {
		super(text);
		answers = new Set<AmericanAnswer>();
	}

	public Boolean answersIntoArray(AmericanAnswer a) {
		if(answers.getValues().size() > 10) {
			System.out.println("the maximum is 10 answers for question");
			return false;
		}
		answers.add(a);
		return true;
	}
	

	public boolean ifAnswerExists(AmericanAnswer americanAnswer) {
		for (int i = 0; i < answers.getValues().size(); i++) {
			if (this.answers.getValues().get(i) != null && americanAnswer.equals(this.answers.getValues().get(i))) {
				return true;
			}
		}
		return false;
	}



	public Set<AmericanAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<AmericanAnswer> answers) {
		this.answers = answers;
	}

	public boolean ifTestAnswerExists(String americanAnswerText) {
		for (int i = 0; i < answers.getValues().size(); i++) {
			if (this.answers.getValues().get(i) != null && americanAnswerText.equals(this.answers.getValues().get(i).getText())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Question q1) {
		if (q1 instanceof OpenQuestion) {
			return false;
		}
		return super.equals(q1);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Type ---> American ").append(super.toString());
		for (int i = 0; i < answers.getValues().size(); i++) {
			if (answers.getValues().get(i) != null) {
				sb.append("        ").append(i + 1).append(")");
				sb.append(answers.getValues().get(i).toString());
			}

		}
		return sb.toString();
	}

}
