package Bai51;

import java.util.Collections;
import java.util.List;

import Bai2_31.Question;
import Bai7.SelectedChoices;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Quiz {

	private StringProperty name;
	private StringProperty description;
	private ObservableList<Integer> timeOpen = FXCollections.observableArrayList();
	private ObservableList<Integer> timeClose = FXCollections.observableArrayList();
	private ObservableList<String> timeLimit = FXCollections.observableArrayList();
	private ObservableList<Question> listques = FXCollections.observableArrayList();
	
	private List<Question> shuffled_listques = Collections.emptyList();
	
	private IntegerProperty displayDesciption = new SimpleIntegerProperty(0);
	private IntegerProperty check_shuffle = new SimpleIntegerProperty(0);
	private DoubleProperty grade;
	private ObservableList<QuizMark> marks = FXCollections.observableArrayList();
	private ObservableList<Double> quizResultt = FXCollections.observableArrayList();
	private DoubleProperty lastGrade = new SimpleDoubleProperty(-1);
	IntegerProperty duration = new SimpleIntegerProperty(0);
	public String quizPath;
	public int sttQuiz = 0;

	public int getSttQuiz() {
		return sttQuiz;
	}

	public void setSttQuiz(int sttQuiz) {
		this.sttQuiz = sttQuiz;
	}

	ObservableList<SelectedChoices> quizPaused = FXCollections.observableArrayList();
	int timePaused = -1;

	String time = "";
	String time1 = "";

	public String getQuizPath() {
		return quizPath;
	}

	public void setQuizPath(String quizPath) {
		this.quizPath = quizPath;
	}

	public void setDuration(int duration) {
		this.duration.setValue(duration);
	}

	public int getDuration() {
		return duration.get();
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Quiz() {
	}

	public void setQuizPaused(ObservableList<SelectedChoices> quizPaused) {
		this.quizPaused = quizPaused;
	}

	public ObservableList<SelectedChoices> getQuizPaused() {
		return this.quizPaused;
	}

	public void setTimePaused(int x) {
		timePaused = x;
	}

	public int getTimePaused() {
		return timePaused;
	}

	public Quiz(String name, String description, ObservableList<Integer> timeOpen, ObservableList<Integer> timeClose,
			ObservableList<String> timeLimit, double grade, ObservableList<Double> quizResultt) {
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);

		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.timeLimit = timeLimit;
		this.grade = new SimpleDoubleProperty(grade);
		this.quizResultt = quizResultt;
	}

	public Quiz(String name, String description) {
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
	}

	public Quiz(String name, String description, ObservableList<Question> q) {
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.listques = q;
	}

	public ObservableList<QuizMark> getMarks() {
		return marks;
	}

	public void setMarks(ObservableList<QuizMark> marks) {
		this.marks = marks;
	}

	public ObservableList<Question> getlistQues() {
		return listques;
	}

	public void setshuffled_listQues(List<Question> ques) {
		shuffled_listques = ques;
	}
	
	public List<Question> getshuffled_listQues() {
		return shuffled_listques;
	}

	public void setlistQues(ObservableList<Question> ques) {
		listques = ques;
	}

	public void setdisplayDesciption(int display) {
		this.displayDesciption = new SimpleIntegerProperty(display);
	}

	public int getdisplayDesciption() {
		return displayDesciption.get();
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}

	public ObservableList<Integer> getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(ObservableList<Integer> timeOpen) {
		this.timeOpen = timeOpen;
	}

	public ObservableList<Integer> getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(ObservableList<Integer> timeClose) {
		this.timeClose = timeClose;
	}

	public ObservableList<String> getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(ObservableList<String> timeLimit) {
		this.timeLimit = timeLimit;
	}

	public void setQuiz(String name, String description, ObservableList<Integer> timeOpen,
			ObservableList<Integer> timeClose, ObservableList<String> timeLimit) {
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.timeLimit = timeLimit;
	}

	public IntegerProperty getSize_listQues() {
		return new SimpleIntegerProperty(this.listques.size());
	}

	public void addlistQues(ObservableList<Question> qss) {
		this.listques.addAll(qss);
	}

	public void setCheck_shuffle(int x) {
		check_shuffle.set(x);
	}

	public int getCheck_shuffle() {
		return this.check_shuffle.get();
	}

	public void setGrade(double grade) {
		this.grade = new SimpleDoubleProperty(grade);
	}

	public double getGrade() {
		return this.grade.get();
	}

	public void setQuizResultt(ObservableList<Double> quizResultt) {
		this.quizResultt = quizResultt;
	}

	public ObservableList<Double> getQuizResultt() {
		return this.quizResultt;
	}

	public void setLastGrade(double lastGrade) {
		this.lastGrade = new SimpleDoubleProperty(lastGrade);
	}

	public double getLastGrade() {
		return this.lastGrade.get();
	}

}