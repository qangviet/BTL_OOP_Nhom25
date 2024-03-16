package Bai7;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuizResult {
	ObservableList<SelectedChoices> selectedChoice;

	StringProperty timeStart;
	StringProperty timeEnd;
	IntegerProperty timeTaken;
	IntegerProperty timeStartt;

	public QuizResult() {
		// selectedChoice = FXCollections.observableArrayList();
	}

	public QuizResult(String timeStart, String timeEnd, int timeTaken, ObservableList<SelectedChoices> selectedChoice) {
		this.timeStart = new SimpleStringProperty(timeStart);
		this.timeEnd = new SimpleStringProperty(timeEnd);
		this.timeTaken = new SimpleIntegerProperty(timeTaken);
		selectedChoice = FXCollections.observableArrayList();
		this.selectedChoice = selectedChoice;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = new SimpleStringProperty(timeStart);
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = new SimpleStringProperty(timeEnd);
	}

	public void setSelectedChoice(ObservableList<SelectedChoices> selectedChoice) {
		this.selectedChoice = selectedChoice;
	}

	public String getTimeStart() {
		return timeStart.get();
	}

	public String getTimeEnd() {
		return timeEnd.get();
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = new SimpleIntegerProperty(timeTaken);
	}

	public int getTimeTaken() {
		return timeTaken.get();
	}

	public void setTimeStartt(int timeStartt) {
		this.timeStartt = new SimpleIntegerProperty(timeStartt);
	}

	public int getTimeStartt() {
		return timeStartt.get();
	}

	public ObservableList<SelectedChoices> getSelectedChoice() {
		return selectedChoice;
	}

}