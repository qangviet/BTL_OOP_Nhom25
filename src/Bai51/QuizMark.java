package Bai51;

public class QuizMark {

	double mark;
	double totalmark;
	double grade;
	public QuizMark(double mark, double totalmark, double grade) {
		this.mark = mark;
		this.totalmark = totalmark;
		this.grade = grade;
	}

	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public double getTotalmark() {
		return totalmark;
	}
	public void setTotalmark(double totalmark) {
		this.totalmark = totalmark;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}

}
