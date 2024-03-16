package Bai7;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelectedChoices {
	ObservableList<Boolean> yourChoice;

	IntegerProperty checkDaDien;

	public SelectedChoices() {}

	public SelectedChoices(ObservableList<Boolean> urChoice) {
		yourChoice = FXCollections.observableArrayList();
		yourChoice = urChoice;
	}

	public ObservableList<Boolean> getYourChoice() {
		return yourChoice;
	}

	public int getCheckDaDien() {
		return checkDaDien.get();
	}

	public void setCheckDaDien(int checkDaDien) {
		this.checkDaDien = new SimpleIntegerProperty(checkDaDien);
	}

}
