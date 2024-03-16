package Bai32;

import java.text.DecimalFormat;

public class Grade {
	private double value;

	Grade(Double value) {
		this.value = value;
	}

	Grade(String s) {
		this.value = 0.0;
	}

	@Override
	public String toString() {

		DecimalFormat df = new DecimalFormat("##.####");

		return Double.parseDouble(df.format(value)) + " %";
	}

	public double get_Grade() {

		return this.value;
	}
}
