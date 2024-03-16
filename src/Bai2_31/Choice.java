package Bai2_31;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Choice {
	private StringProperty text;
	private double grade;
	private StringProperty image;
	private int stt;
	
	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public Choice() {
		this.text = new SimpleStringProperty("");
		this.image = new SimpleStringProperty("");
		this.grade = 0.0;
	}

	public Choice(String text) {
		this.text = new SimpleStringProperty(text);
		this.image = new SimpleStringProperty("");
		this.grade = 0.0;
	}

	public Choice(String text, double grade) {
		this.text = new SimpleStringProperty(text);
		this.image = new SimpleStringProperty("");
		this.grade = grade;
	}

	public Choice(String text, String image, double grade) {
		this.text = new SimpleStringProperty(text);
		this.image = new SimpleStringProperty(image);
		this.grade = grade;
	}

	public void setText(String text) {
		this.text = new SimpleStringProperty(text);
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getText() {
		return text.get();
	}

	public double get_double_Grade() {
		return grade;
	}

	public String getImage() {
		return image.get();
	}
}