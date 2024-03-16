package Bai2_31;

import java.text.DecimalFormat;

public class Grade {
	private double value;

	public Grade(Double value) {
		this.value = value;
	}

	public Grade(String s) {
		this.value = 0.0;
	}

	@Override
	public String toString() {
		if (this.value == 0.0)
			return "NONE";
		else {
			DecimalFormat df = new DecimalFormat("##.####");

			return Double.parseDouble(df.format(value)) + " %";
		}
	}

	public Double get_Grade() {
		return this.value;
	}
}
