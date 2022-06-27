package id206914392_id315043117.model;

import java.io.Serializable;

public class AmericanAnswer  implements Serializable  {
	private String text;

	@Override
	public String toString() {
		return "Answer: " + text + "   --------> " + ifTrue + "\n";
	}

	private boolean ifTrue;

	public AmericanAnswer(String text, boolean ifTrue) {
		this.text = text;
		this.ifTrue = ifTrue;
	}

	public String getText() {
		return text;
	}

	public boolean setText(String text) {
		this.text = text;
		return true;
	}

	public boolean IfTrue() {
		return ifTrue;
	}

	public boolean setIfTrue(boolean ifTrue) {
		this.ifTrue = ifTrue;
		return true;
	}

	public boolean getIfTrue() {
		return ifTrue;
	}

	public boolean equals(AmericanAnswer americanAnswer) {
		if (americanAnswer.getText().equals(this.getText())) {
			return true;
		}
		return false;
	}

}
