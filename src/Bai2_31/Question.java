package Bai2_31;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Question {
	private StringProperty name;
	private StringProperty text;
	private StringProperty image;
	private ObservableList<Choice> choices;
	private DoubleProperty mark = new SimpleDoubleProperty(1);
	private BooleanProperty selected = new SimpleBooleanProperty(false);

	public Question(String name) {
		this.name = new SimpleStringProperty(name);
		this.text = new SimpleStringProperty("");
		this.image = new SimpleStringProperty("");
		this.choices = FXCollections.observableArrayList();
	}

	public Question(String name, String text, String image, ObservableList<Choice> choices) {
		this.name = new SimpleStringProperty(name);
		this.text = new SimpleStringProperty(text);
		this.image = new SimpleStringProperty(image);
		this.choices = choices;
	}

	public Question(String name, String text, double mark, ObservableList<Choice> choices) {
		this.name = new SimpleStringProperty(name);
		this.text = new SimpleStringProperty(text);
		this.image = new SimpleStringProperty("");
		this.choices = choices;
		this.mark.set(mark);
	}

	public Question(String name, String text, ObservableList<Choice> choices) {
		this.name = new SimpleStringProperty(name);
		this.text = new SimpleStringProperty(text);
		this.image = new SimpleStringProperty("");
		this.choices = choices;
	}

	public void edit(Question question) {
		this.name.set(question.getName());
		this.text.set(question.getText());
		this.image.set(question.getImage());
		this.choices = question.getChoices();
		this.mark.set(question.getMark());
	}

	public ObservableList<Choice> getChoices() {
		return choices;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public String getName() {
		return name.get();
	}

	public String getText() {
		return text.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty name_textProperty() {
		String str = name.get() + ": " + text.get();
		return new SimpleStringProperty(str);
	}

	public String getImage() {
		return image.get();
	}

	public Boolean getSelected() {
		return selected.get();
	}

	public BooleanProperty getSelectedProperty() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected.set(selected);
	}

	public double getMark() {
		return mark.get();
	}

	public void setMark(double mark) {
		this.mark.set(mark);
	}

	public DoubleProperty getMarkProperty() {
		return mark;
	}
}
