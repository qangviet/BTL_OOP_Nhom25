package Bai6;

import java.io.IOException;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import Bai2_31.tree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerScene6_1 implements Initializable {

	@FXML
	Label label_nameQuis;
	@FXML
	Label label_timelimit;
	@FXML
	Label descLabel;
	@FXML
	AnchorPane anchor;
	@FXML
	GridPane previousAtps;
	@FXML
	Label gradeLabel;
	Label grade;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 61;

		label_nameQuis.textProperty().bind(tree.quiz_selected.getNameProperty());

		// Nếu có time limit
		if (!tree.quiz_selected.getTimeLimit().get(0).equals("-1")) {
			label_timelimit.setText("Time limit: " + tree.quiz_selected.getTimeLimit().get(0) + " "
					+ tree.quiz_selected.getTimeLimit().get(1));
			// Nếu không có time limit
		} else {
			label_timelimit.setText("Time limit: No time limit");
		}

		// Nếu hiển thị description
		if (tree.quiz_selected.getdisplayDesciption() == 1) {
			descLabel.setText("Description: " + tree.quiz_selected.getDescription());
			descLabel.setWrapText(true);
		}

		// Hiển thị các lần thi trước
		for (int i = 0; i < tree.quiz_selected.getMarks().size(); i++) {

			DecimalFormat decimalFormat1 = new DecimalFormat("0.00");
			DecimalFormat decimalFormat2 = new DecimalFormat("0.00");

			Label name = new Label("Attempt " + (i + 1));
			name.setStyle("-fx-font-size: 14px;");

			grade = new Label("Grade: " + decimalFormat1.format(tree.quiz_selected.getMarks().get(i).getMark()) + "/"
					+ decimalFormat2.format(tree.quiz_selected.getMarks().get(i).getTotalmark()));
			grade.setStyle("-fx-font-size: 14px;");

			previousAtps.add(name, 0, i + 1);
			GridPane.setHalignment(name, HPos.CENTER);
			previousAtps.add(grade, 1, i + 1);
			GridPane.setHalignment(grade, HPos.CENTER);
			previousAtps.setPadding(new Insets(10));

			DecimalFormat decimalFormat3 = new DecimalFormat("0.00");
			DecimalFormat decimalFormat4 = new DecimalFormat("0.00");

			gradeLabel.setText(
					"Grade: " + decimalFormat3.format(tree.quiz_selected.getLastGrade()) + "/" + decimalFormat4.format(
							tree.quiz_selected.getMarks().get(tree.quiz_selected.getMarks().size() - 1).getGrade()));

		}

		// Nếu ấn ra ngoài rồi quay lại thi
		if (!tree.quiz_selected.getTime().equals("")) {
			Label name = new Label("Preview");
			name.setStyle("-fx-font-size: 14px;");
			grade = new Label("Never submitted");
			grade.setStyle("-fx-font-size: 14px;");

			previousAtps.add(name, 0, tree.quiz_selected.getMarks().size() + 1);
			GridPane.setHalignment(name, HPos.CENTER);
			previousAtps.add(grade, 1, tree.quiz_selected.getMarks().size() + 1);
			GridPane.setHalignment(grade, HPos.CENTER);
			previousAtps.setPadding(new Insets(10));
		}

	}

	@SuppressWarnings("static-access")
	public void button_setting(MouseEvent e) throws IOException {

		if (!tree.quiz_selected.getTime().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!");
			alert.setHeaderText("Action can't be done due to unsubmitted attempt!");
			alert.show();
		} else {

			tree.checkScene = 62;

			Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
			Parent root = loader.load();

			FXMLLoader loader1 = new FXMLLoader();
			loader1.setLocation(getClass().getResource("/Bai6/Scene6_2.fxml"));
			Parent root1 = loader1.load();

			VBox V0 = new VBox();

			AnchorPane p1 = new AnchorPane();
			p1.getChildren().setAll(root);
			p1.setTopAnchor(root, 0.0);
			p1.setBottomAnchor(root, 0.0);
			p1.setLeftAnchor(root, 0.0);
			p1.setRightAnchor(root, 0.0);

			AnchorPane p2 = new AnchorPane();
			p2.getChildren().setAll(root1);
			p2.setTopAnchor(root1, 0.0);
			p2.setBottomAnchor(root1, 20.0);
			p2.setLeftAnchor(root1, 20.0);
			p2.setRightAnchor(root1, 20.0);

			VBox.setVgrow(p2, Priority.ALWAYS);

			V0.getChildren().addAll(p1, p2);

			Scene scene = new Scene(V0, tree.stageWidth, tree.stageHeight);
			primaryStage.setScene(scene);
		}
	}

	@SuppressWarnings("static-access")
	public void previewQuizNow(ActionEvent e) throws IOException {

		LocalDateTime now = LocalDateTime.now();

		LocalDateTime quizOpen = LocalDateTime.of(tree.quiz_selected.getTimeOpen().get(0),
				tree.quiz_selected.getTimeOpen().get(1), tree.quiz_selected.getTimeOpen().get(2),
				tree.quiz_selected.getTimeOpen().get(3), tree.quiz_selected.getTimeOpen().get(4), 0);

		LocalDateTime quizClose = LocalDateTime.of(tree.quiz_selected.getTimeClose().get(0),
				tree.quiz_selected.getTimeClose().get(1), tree.quiz_selected.getTimeClose().get(2),
				tree.quiz_selected.getTimeClose().get(3), tree.quiz_selected.getTimeClose().get(4), 0);

		if (now.isBefore(quizOpen)) {
			Alert alertt = new Alert(Alert.AlertType.WARNING);
			alertt.setTitle("WARNING!");
			alertt.setHeaderText("This quiz is not open yet!");
			alertt.show();
		} else if (now.isAfter(quizClose)) {
			Alert alertt = new Alert(Alert.AlertType.WARNING);
			alertt.setTitle("WARNING!");
			alertt.setHeaderText("This quiz is closed!");
			alertt.show();
		} else {

			if (tree.quiz_selected.getlistQues().isEmpty()) {
				Alert alertt = new Alert(Alert.AlertType.WARNING);
				alertt.setTitle("WARNING!");
				alertt.setHeaderText("You must add questions to this quiz first!");
				alertt.show();
			} else {

				tree.checkScene = 71;

				Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
				Parent root = loader.load();

				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(getClass().getResource("/Bai7/Scene71.fxml"));
				Parent root1 = loader1.load();

				VBox V0 = new VBox();

				AnchorPane p1 = new AnchorPane();
				p1.getChildren().setAll(root);
				p1.setTopAnchor(root, 0.0);
				p1.setBottomAnchor(root, 0.0);
				p1.setLeftAnchor(root, 0.0);
				p1.setRightAnchor(root, 0.0);

				AnchorPane p2 = new AnchorPane();
				p2.getChildren().setAll(root1);
				p2.setTopAnchor(root1, 0.0);
				p2.setBottomAnchor(root1, 20.0);
				p2.setLeftAnchor(root1, 20.0);
				p2.setRightAnchor(root1, 20.0);

				VBox.setVgrow(p2, Priority.ALWAYS);

				V0.getChildren().addAll(p1, p2);

				Scene scene = new Scene(V0, tree.stageWidth, tree.stageHeight);
				primaryStage.setScene(scene);
			}
		}
	}
}
