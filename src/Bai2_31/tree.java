package Bai2_31;

import java.awt.Toolkit;

import Bai51.Quiz;
import Bai7.QuizResult;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;

public class tree {
	public static Category defaultCategory = Data.defaultCategory();

	public static Quiz quiz_selected;

	public static int checkScene = 0;
	
	public static int checkDangThi = -1;

	public static IntegerProperty checkTab = new SimpleIntegerProperty(1);

	public static TextField checkTabb = new TextField("0");

	public final static int stageHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
	public final static int stageWidth = Toolkit.getDefaultToolkit().getScreenSize().width - 100;

	// public static ObservableList<Question> quesList =
	// FXCollections.observableArrayList();
	public static Question ques1 = addChoices();

	public static QuizResult quizResult = new QuizResult();
	// public static QuizResult quizResult1 = quizResult;

	public static Question addChoices() {
		ObservableList<Choice> choices = FXCollections.observableArrayList();
		Choice choice1 = new Choice("A", "", 100);
		Choice choice2 = new Choice("B", "", 0);
		Choice choice3 = new Choice("B", "", 0);
		Choice choice4 = new Choice("B", "", 0);
		Choice choice5 = new Choice("B", "", 0);

		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		choices.add(choice4);
		choices.add(choice5);

		Question question = new Question("TEST", "", choices);
		return question;
	}

	public static boolean chk_create_edit = false;

	public static void setChangeTab(int x) {
		checkTab.setValue(x);
	}

	public static int getChangeTab() {
		return checkTab.get();
	}

	public static void add_tree_item(Category category, TreeItem<Category> root) {
		ObservableList<Category> sub_cate = category.getSub_Category();
		if (sub_cate.size() == 0)
			return;
		root.setExpanded(true);
		for (Category cate : sub_cate) {
			cate.setShowSub(false);
			TreeItem<Category> branch = new TreeItem<>(cate);
			root.getChildren().add(branch);
			add_tree_item(cate, branch);
		}
	}

	public static int getTimeLimit(ObservableList<String> timeLimit) {
		if (timeLimit.get(0) == "")
			return 0;
		if (timeLimit.get(1) == "hours") {
			return Integer.parseInt(timeLimit.get(0)) * 3600;
		} else if (timeLimit.get(1) == "minutes") {
			return Integer.parseInt(timeLimit.get(0)) * 60;
		} else {
			return Integer.parseInt(timeLimit.get(0));
		}
	}
}
