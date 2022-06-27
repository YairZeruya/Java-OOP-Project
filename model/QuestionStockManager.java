package id206914392_id315043117.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.crypto.Data;

import id206914392_id315043117.listeners.QSMEventListener;


public class QuestionStockManager implements Serializable, Cloneable {
	static LocalDate date = LocalDate.now();
	private QuestionStockManager test;
	private static final String FILE_EXAM_NAME = "Exam " + date;
	private static final String FILE_SOLUTION_NAME = "Solution " + date;
	private static final String QUESTIONS_STOCK = "Question Stock";
	private ArrayList<Question> questions;
	private ObjectOutputStream o;
	private ArrayList<QSMEventListener> listeners;

	public QuestionStockManager() {
		questions = new ArrayList<Question>();
		listeners = new ArrayList<QSMEventListener>();
	}
	
	public void registerListener(QSMEventListener listener) {
		listeners.add(listener);
	}

	public void createBinnaryFile() throws FileNotFoundException, IOException {
		this.o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public void hardCoded() throws FileNotFoundException, IOException, ClassNotFoundException {
		AmericanQuestion americanQ1 = new AmericanQuestion("which color exists in the france flag?");
		AmericanAnswer americanAQ11 = new AmericanAnswer("blue", true);
		AmericanAnswer americanAQ12 = new AmericanAnswer("orange", false);
		AmericanAnswer americanAQ13 = new AmericanAnswer("yellow", false);
		AmericanAnswer americanAQ14 = new AmericanAnswer("red", true);
		AmericanAnswer americanAQ15 = new AmericanAnswer("gereen", false);
		AmericanAnswer americanAQ16 = new AmericanAnswer("gray", false);
		AmericanAnswer americanAQ17 = new AmericanAnswer("purple", false);
		AmericanAnswer americanAQ18 = new AmericanAnswer("white", true);
		AmericanAnswer americanAQ19 = new AmericanAnswer("black", false);
		AmericanAnswer americanAQ110 = new AmericanAnswer("brown", false);
		addAnswerToAmericanQ(americanQ1, americanAQ11);
		addAnswerToAmericanQ(americanQ1, americanAQ12);
		addAnswerToAmericanQ(americanQ1, americanAQ13);
		addAnswerToAmericanQ(americanQ1, americanAQ14);
		addAnswerToAmericanQ(americanQ1, americanAQ15);
		addAnswerToAmericanQ(americanQ1, americanAQ16);
		addAnswerToAmericanQ(americanQ1, americanAQ17);
		addAnswerToAmericanQ(americanQ1, americanAQ18);
		addAnswerToAmericanQ(americanQ1, americanAQ19);
		addAnswerToAmericanQ(americanQ1, americanAQ110);
		OpenQuestion openQ2 = new OpenQuestion("what is the capital city of Israel?", "Jerusalem");
		OpenQuestion openQ3 = new OpenQuestion("what is the biggest country in the world?", "russia");
		OpenQuestion openQ4 = new OpenQuestion("How long is an Olympic swimming pool (in meters)? ", "50 meters");
		AmericanQuestion americanQ5 = new AmericanQuestion("How many languages are written from right to left??");
		AmericanAnswer americanAQ51 = new AmericanAnswer("7", false);
		AmericanAnswer americanAQ52 = new AmericanAnswer("8", false);
		AmericanAnswer americanAQ53 = new AmericanAnswer("9", false);
		AmericanAnswer americanAQ54 = new AmericanAnswer("12", true);
		AmericanAnswer americanAQ55 = new AmericanAnswer("4", false);
		AmericanAnswer americanAQ56 = new AmericanAnswer("15", false);
		AmericanAnswer americanAQ57 = new AmericanAnswer("17", false);
		AmericanAnswer americanAQ58 = new AmericanAnswer("22", false);
		AmericanAnswer americanAQ59 = new AmericanAnswer("27", false);
		AmericanAnswer americanAQ510 = new AmericanAnswer("38", false);
		addAnswerToAmericanQ(americanQ5, americanAQ51);
		addAnswerToAmericanQ(americanQ5, americanAQ52);
		addAnswerToAmericanQ(americanQ5, americanAQ53);
		addAnswerToAmericanQ(americanQ5, americanAQ54);
		addAnswerToAmericanQ(americanQ5, americanAQ55);
		addAnswerToAmericanQ(americanQ5, americanAQ56);
		addAnswerToAmericanQ(americanQ5, americanAQ57);
		addAnswerToAmericanQ(americanQ5, americanAQ58);
		addAnswerToAmericanQ(americanQ5, americanAQ59);
		addAnswerToAmericanQ(americanQ5, americanAQ510);
		addQuestion(americanQ1);
		addQuestion(openQ2);
		addQuestion(openQ3);
		addQuestion(openQ4);
		addQuestion(americanQ5);
		createBinnaryFile();
		SaveBinarryFile(this, o);
	}

	private void SaveBinarryFile(QuestionStockManager qsm, ObjectOutputStream o)
			throws FileNotFoundException, IOException {
		o.writeObject(qsm.getQuestions());
		o.close();
	}

	public void testSort(int numOfQuestions) {
		int n = numOfQuestions;
		int i, IndMax;
		for (; n > 1; n--) {
			for (IndMax = 0, i = 1; i < n; i++) {
				if (howManyLetterInAnswer(questions.get(IndMax)) < howManyLetterInAnswer(questions.get(i))) {
					IndMax = i;
				}
			}
			swap(questions, n - 1, IndMax);
		}
	}

	public int howManyLetterInAnswer(Question question) {
		if (question instanceof OpenQuestion) {
			String answer = ((OpenQuestion) question).getAnswer();
			if (answer == null) {
				return 0;
			}
			return answer.length();
		}
		int answers = ((AmericanQuestion) question).getAnswers().getValues().size();
		int sum = 0;
		for (int i = 0; i < answers; i++) {
			sum += ((AmericanQuestion) question).getAnswers().getValues().get(i).getText().length();
		}
		return sum;
	}

	public void swap(ArrayList<Question> a, int i, int j) {
		Question tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}

	public void randomQuestions(int numOfQuestionsToRand, QuestionStockManager qstock)
			throws ClassNotFoundException, IOException {
		Random rnd = new Random();
		int QuestionNumberChoosen = 0;
		int i = 0;
		while (i < numOfQuestionsToRand) {
			QuestionNumberChoosen = rnd.nextInt(numOfQuestionsToRand);
			Question q = qstock.getQuestions().get(QuestionNumberChoosen);
			if (q instanceof OpenQuestion) {
				OpenQuestion qCopy = (OpenQuestion) q;
				OpenQuestion questionChoosen = new OpenQuestion(qCopy.getText(), qCopy.getAnswer());

				if (this.addQuestion(questionChoosen)) {
					i++;
				}

			} else {
				AmericanQuestion aq = new AmericanQuestion(q.getText());
				aq = chooseManualAmericanAnswers(aq);
				randomAnswers((AmericanQuestion) q, howManyAnswersIsExists((AmericanQuestion) q), aq);
				if (this.addQuestion(aq)) {
					i++;
					whatIsTheTrueAnswer(aq);
				}

			}
		}

	}

	public int howManyTrueAnswers(AmericanQuestion americanQuestion) {
		int count = 0;
		for (int i = 0; i < howManyAnswersIsExists(americanQuestion); i++) {
			if (americanQuestion.getAnswers().getValues().get(i).getIfTrue()) {
				count++;
			}
		}
		return count;
	}

	public void randomAnswers(AmericanQuestion americanQuestion, int numOfAnswers, AmericanQuestion aq) {
		Random rnd = new Random();
		int AnswerNumberChoosen = 0;
		int i = 0;
		boolean thereIsTrueAnswer = false;
		int howManyAnswersToRand;
		if(howManyTrueAnswers(americanQuestion) <=0) {
			howManyAnswersToRand = howManyAnswersIsExists(americanQuestion) - howManyTrueAnswers(americanQuestion);
		}
		else {
		 howManyAnswersToRand = howManyAnswersIsExists(americanQuestion) - howManyTrueAnswers(americanQuestion) + 1;
		}
		if (numOfAnswers > 4) {
			if (howManyAnswersToRand > 4) {
				numOfAnswers = 4;
			} else {
				numOfAnswers = howManyAnswersToRand;
			}

		} else {
			numOfAnswers = howManyAnswersToRand;
		}
		while (i < numOfAnswers) {
			if(americanQuestion.getAnswers().getValues().size() == 0) {
				break;
			}
			AnswerNumberChoosen = rnd.nextInt(americanQuestion.getAnswers().getValues().size());
			if (americanQuestion.getAnswers().getValues().get(AnswerNumberChoosen) != null) {
				AmericanAnswer answer = new AmericanAnswer(null, false);
				answer.setText(americanQuestion.getAnswers().getValues().get(AnswerNumberChoosen).getText());
				answer.setIfTrue(americanQuestion.getAnswers().getValues().get(AnswerNumberChoosen).getIfTrue());
				if (thereIsTrueAnswer == false || thereIsTrueAnswer == true && answer.getIfTrue() == false) {
					if (!(aq.ifAnswerExists(answer))) {
						addAnswerToAmericanQ(aq, answer);
						if (answer.getIfTrue()) {
							thereIsTrueAnswer = true;
						}
						i++;
					}
					
					
				}
			}
		}
	}

	public void whatIsTheTrueAnswer(AmericanQuestion americanQuestion) {
		int HowManyTrue = 0;
		Set<AmericanAnswer> americanAnswer = americanQuestion.getAnswers();
		for (int i = 2; i < americanQuestion.getAnswers().getValues().size(); i++) {
			if (americanAnswer.getValues().get(i) != null && americanAnswer.getValues().get(i).IfTrue()) {
				HowManyTrue++;
			}
		}
		if (HowManyTrue == 0) {
			americanAnswer.getValues().get(1).setIfTrue(true);
		}
		if (HowManyTrue > 1) {
			americanAnswer.getValues().get(0).setIfTrue(true);
			for (int i = 2; i < americanAnswer.getValues().size(); i++) {
				if (americanAnswer.getValues().get(i) != null) {
					americanAnswer.getValues().get(i).setIfTrue(false);
				}
			}
		}
	}

	public AmericanQuestion chooseManualAmericanAnswers(AmericanQuestion americanQuestion) {
		americanQuestion.answersIntoArray(new AmericanAnswer("more than one answer is true", false));
		americanQuestion.answersIntoArray(new AmericanAnswer("there is no true answer", false));

		return americanQuestion;

	}

	public boolean addAnswerToAmericanQ(AmericanQuestion americanQuestion, AmericanAnswer americanAnswer) {
		if (!(americanQuestion.ifAnswerExists(americanAnswer))) {
			americanQuestion.answersIntoArray(americanAnswer);
			return true;
		}

		return false;
	}

	public Question getQuestionBySerialNumber(int serialNumber) throws ClassNotFoundException, IOException {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getSerialNumber() == serialNumber) {
				return questions.get(i);
			}
		}
		return null;
	}

	public int howManyAnswersIsExists(AmericanQuestion americanQuestion) {
		return americanQuestion.getAnswers().getValues().size();
	}

	public int howManyAnswersIsExists2(int serialNumber) throws ClassNotFoundException, IOException {
		AmericanQuestion americanQuestion = (AmericanQuestion) getQuestionBySerialNumber(serialNumber);
		return americanQuestion.getAnswers().getValues().size();
	}

	public int howManyQuestionsIsExists() {
		return questions.size();
	}

	public boolean addQuestion(Question q) throws ClassNotFoundException, IOException {
		for (int i = 0; i < questions.size(); i++) {
			if ((this.questions.get(i).equals(q))) {
				return false;
			}

		}
		questions.add(q);
		return true;
	}

	public boolean addOpenQuestion(String text, String answer) throws IOException, ClassNotFoundException {
		OpenQuestion openQuestion = new OpenQuestion(text, answer);
		if (this.addQuestion(openQuestion)) {
			o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
			o.writeObject(questions);
			return true;
		} 
		
		return false;
	}

	public void addAmericanQuestion(String questionText, ArrayList<String> answerTexts, ArrayList<Boolean> answerIfTrue)
			throws ClassNotFoundException, IOException {
		AmericanQuestion americanQuestion = new AmericanQuestion(questionText);
		for (int i = 0; i < answerTexts.size(); i++) {
			AmericanAnswer americanAnswer = new AmericanAnswer(answerTexts.get(i), answerIfTrue.get(i));
			addAnswerToAmericanQ(americanQuestion, americanAnswer);

		}
		this.addQuestion(americanQuestion);
		this.o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
		o.writeObject(questions);
	}



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i) != null) {
				sb.append(i + 1).append(")");
				sb.append(questions.get(i));
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void changeQuestionText(int serialNumber, String newText) throws ClassNotFoundException, IOException {
		QuestionStockManager qsm = new QuestionStockManager();
		qsm.readQuestionsFile();
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
		Question QtoChange = qsm.getQuestionBySerialNumber(serialNumber);
		QtoChange.setText(newText);
		o.writeObject(qsm.getQuestions());
		o.close();
	}

	public void changeOpenQuestion(int serialNumber, String newAnswer) throws ClassNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
		Question QtoChange = getQuestionBySerialNumber(serialNumber);
		((OpenQuestion) QtoChange).setAnswer(newAnswer);
		o.writeObject(getQuestions());
		o.close();
	}

	public void changeAmericanQuestionText(int serialNumber, String answerTextToChange, boolean truthAnswerTochange,
			int numOfAnswerToChange) throws ClassNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
		AmericanQuestion americanQuestion = (AmericanQuestion) getQuestionBySerialNumber(serialNumber);
		AmericanAnswer ans = ((AmericanQuestion) americanQuestion).getAnswers().getValues()
				.get(numOfAnswerToChange - 1);
		boolean ifBooleanAnswerChanged = ans.setIfTrue(truthAnswerTochange);
		boolean ifTextAnswerChanged = ans.setText(answerTextToChange);
		o.writeObject(getQuestions());
		o.close();
	}

	public void deleteOpenQuestion(int serialNumberToDeleteAnswer) throws ClassNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
		OpenQuestion openQuestion = (OpenQuestion) getQuestionBySerialNumber(serialNumberToDeleteAnswer);
		openQuestion.setAnswer(null);
		o.writeObject(getQuestions());
		o.close();
	}

	public void deleteAmericanAnswer(int serialNumberToDeleteAnswer, int answerToDelete) 
			throws ClassNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(QUESTIONS_STOCK));
		AmericanQuestion QToChange = (AmericanQuestion) getQuestionBySerialNumber(serialNumberToDeleteAnswer);
		QToChange.getAnswers().getValues().remove(answerToDelete - 1);
		o.writeObject(getQuestions());
		o.close();
	}

	public String manualTest(ArrayList<Integer> questionsNumbers, ArrayList<Integer> answerSerialNumbers)
			throws ClassNotFoundException, IOException , IndexOutOfBoundsException{
		test  = new QuestionStockManager();
		int j = 0;
		for (int i = 0; i < questionsNumbers.size(); i++) {
			if (getQuestionBySerialNumber(questionsNumbers.get(i)) instanceof OpenQuestion) {
				OpenQuestion op = (OpenQuestion) getQuestionBySerialNumber(questionsNumbers.get(i));
				OpenQuestion openQuestion = new OpenQuestion(op.getText(), op.getAnswer());
				test.getQuestions().add(openQuestion);
			} else {
				AmericanQuestion aq = (AmericanQuestion) getQuestionBySerialNumber(questionsNumbers.get(i));
				AmericanQuestion americanQuestion = new AmericanQuestion(aq.getText());
				test.chooseManualAmericanAnswers(americanQuestion);
				while (answerSerialNumbers.get(j) != -1) {
					AmericanAnswer americanAnswer = new AmericanAnswer(
							aq.getAnswers().getValues().get(answerSerialNumbers.get(j) - 1).getText(),
							aq.getAnswers().getValues().get(answerSerialNumbers.get(j) - 1).getIfTrue());
					addAnswerToAmericanQ(americanQuestion, americanAnswer);
					j++;
				}
				j++;

				test.getQuestions().add(americanQuestion);
				test.whatIsTheTrueAnswer(americanQuestion);

			}

		}
		test.testSort(test.getQuestions().size());
		test.saveTestFile();
		test.saveAnswersFile();
		return test.toString();

	}

	public String automaticTest(int questionsInTheAutomaticTest, QuestionStockManager q)
			throws ClassNotFoundException, IOException {
		test  = new QuestionStockManager();
		test.randomQuestions(questionsInTheAutomaticTest, q);
		test.testSort(questionsInTheAutomaticTest);
		test.saveTestFile();
		test.saveAnswersFile();
		return test.toString();
	}

	public void saveTestFile() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(FILE_EXAM_NAME);
		for (int i = 0; i < questions.size(); i++) {
			pw.println("Question " + (i + 1) + ") " + this.getQuestions().get(i).getText());
		}
		pw.close();
	}

	public void saveAnswersFile() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(FILE_SOLUTION_NAME);
		for (int i = 0; i < questions.size(); i++) {
			if (this.getQuestions().get(i) instanceof OpenQuestion) {
				OpenQuestion openQuestion = (OpenQuestion) this.getQuestions().get(i);
				pw.println("Answer for question " + (i + 1) + ") " + openQuestion.getAnswer());
			} else {

				AmericanQuestion americanQuestion = (AmericanQuestion) this.getQuestions().get(i);
				pw.println("answer for question " + (i + 1) + ") ");
				for (int j = 0; j < americanQuestion.getAnswers().getValues().size(); j++) {
					pw.println(americanQuestion.getAnswers().getValues().get(j));
				}
			}

			pw.println();
		}

		pw.close();
	}

	public static int serialNumber() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(QUESTIONS_STOCK));
		ArrayList<Question> arr = new ArrayList<Question>();
		arr = (ArrayList<Question>) i.readObject();
		return arr.size() + 1;
	}

	public String printQuestionsFile() throws IOException, ClassNotFoundException {
		readQuestionsFile();
		return (this.toString());

	}

	public void readQuestionsFile() throws IOException, ClassNotFoundException {
		ObjectInputStream t = new ObjectInputStream(new FileInputStream(QUESTIONS_STOCK));
		ArrayList<Question> questionsInFile = new ArrayList<Question>();
		questionsInFile = (ArrayList<Question>) t.readObject();
		t.close();
		this.setQuestions(questionsInFile);
	}

	public boolean copyTest(String date) throws CloneNotSupportedException, FileNotFoundException {
		File exam = new File("Exam " + date);
		File solution = new File("Solution " + date);
		test  = new QuestionStockManager();
		if(test.getQuestions() != null) {
			QuestionStockManager copyLastTest = test.clone();
		}
		if (!(exam.exists())) {
			return false;
		}
		File copyExam = new File("Copy" + exam);
		File copySolution = new File("Copy" + solution);
		copyTestFile(exam, copyExam);
		copyTestFile(solution, copySolution);
		return true;

	}

	public void copyTestFile(File file, File copy) throws FileNotFoundException {
		Scanner myReader = new Scanner(file);
		PrintWriter pw = new PrintWriter(copy);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			pw.println(data);
		}
		myReader.close();
		pw.close();

	}


	public static boolean ifQuestionStockFileExists() throws IOException {
		Path p = Paths.get("Question Stock");
		boolean exists = Files.exists(p);
		return exists;
	}
	
	  public QuestionStockManager clone() throws CloneNotSupportedException
	    {
	        return (QuestionStockManager) super.clone();
	    }
	  
	public int openserial(int i) {
		Question question = questions.get(i);
		if (question instanceof OpenQuestion) {
			return question.getSerialNumber();
		}
		return -1;
	}
	
	public int americanSerial(int i) {
		Question question = questions.get(i);
		if (question instanceof AmericanQuestion) {
			return question.getSerialNumber();
		}
		return -1;
	}

}
