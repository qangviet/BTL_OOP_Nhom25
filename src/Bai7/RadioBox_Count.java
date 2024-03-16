package Bai7;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;

public class RadioBox_Count {
	private ObservableList<RadioButton> radioBtn;
	private IntegerProperty count;

	public RadioBox_Count(ObservableList<RadioButton> radioBtn, int count) {
		this.radioBtn = FXCollections.observableArrayList();
		this.radioBtn = radioBtn;
		this.count = new SimpleIntegerProperty(count);
	}

	public ObservableList<RadioButton> getRadioBtn() {
		return radioBtn;
	}

	public void setRadioBtn(ObservableList<RadioButton> radioBtn) {
		this.radioBtn = radioBtn;
	}

	public int getCount() {
		return count.get();
	}

	public void setCount(int count) {
		this.count = new SimpleIntegerProperty(count);
	}

	public void addRadioBtn(RadioButton radiobutton) {
		radioBtn.add(radiobutton);
	}
}
