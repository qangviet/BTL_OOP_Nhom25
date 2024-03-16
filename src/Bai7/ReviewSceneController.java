package Bai7;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import Bai2_31.Choice;
import Bai2_31.Question;
import Bai2_31.tree;
import Bai51.QuizMark;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ReviewSceneController implements Initializable {
	@FXML
	HBox quizReview;
	@FXML
	FlowPane flowPane;
	@FXML
	VBox questionVBox;
	@FXML
	ScrollPane scrPane;
	double mm;

	double grade = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tree.checkScene = 73;

		mm = tree.quiz_selected.getlistQues().size();

		reviewQues();
				
		double gr = (grade / tree.quiz_selected.getlistQues().size()) * tree.quiz_selected.getGrade();
		double percent = (grade / tree.quiz_selected.getlistQues().size() * 100);

		DecimalFormat decimalFormat1 = new DecimalFormat("0.00");
		DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
		DecimalFormat decimalFormat3 = new DecimalFormat("0.00");
		DecimalFormat decimalFormat4 = new DecimalFormat("0.00");

		ListView<String> leftList = new ListView<>();
		ListView<String> rightList = new ListView<>();

		ObservableList<String> items = FXCollections.observableArrayList("Started on", "State", "Completed on",
				"Time taken", "Marks", "Grade");

		leftList.setItems(items);

		leftList.setStyle("-fx-alignment: center-right; -fx-text-fill: red;");

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a");
		String endTimee = now.format(formatter);

		ObservableList<String> itemss = FXCollections.observableArrayList(tree.quizResult.getTimeStart(), "Finished",
				endTimee, formatTime(tree.quizResult.getTimeTaken()),
				decimalFormat1.format(grade) + "/" + tree.quiz_selected.getlistQues().size() + ".00",
				decimalFormat2.format(gr) + " out of " + decimalFormat4.format(tree.quiz_selected.getGrade()) + "("
						+ decimalFormat3.format(percent) + "%)");

		rightList.setItems(itemss);
		HBox.setHgrow(rightList, Priority.ALWAYS);
		quizReview.getChildren().add(leftList);
		quizReview.getChildren().add(rightList);
		quizReview.setMinHeight(150);

		while (tree.quiz_selected.getQuizPaused().size() != 0) {
			tree.quiz_selected.getQuizPaused().remove(0);
		}

		for (int o = 0; o < tree.quiz_selected.getlistQues().size(); o++) {
			ObservableList<Boolean> urchoice = FXCollections.observableArrayList();
			for (int k = 0; k <= 50; k++) {
				urchoice.add(false);

			}

			SelectedChoices sltChoice = new SelectedChoices(urchoice);
			sltChoice.setCheckDaDien(0);

			tree.quiz_selected.getQuizPaused().add(sltChoice);

		}
		
		for (Question q : tree.quiz_selected.getlistQues()) {
			FXCollections.sort(q.getChoices(), (choice1, choice2) -> choice1.getStt() - choice2.getStt());
		}

		tree.quiz_selected.setDuration(0);

		tree.quiz_selected.setTime("");
		tree.quiz_selected.setTime1("");
		
		tree.checkDangThi = -1; 

	}

	public String formatTime(int timeInSeconds) {
		int hours = timeInSeconds / 3600;
		int minutes = (timeInSeconds % 3600) / 60;
		int seconds = timeInSeconds % 60;

		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	@SuppressWarnings("static-access")
	public void reviewQues() {
		for (int i = 0; i < tree.quiz_selected.getlistQues().size(); i++) {
			HBox quizBox = new HBox();
			VBox quesInfo = new VBox();
			VBox quesBox = new VBox();
			VBox quesVBox = new VBox();
			Pane informans = new Pane();
			VBox quesVInfo = new VBox();
			Pane blankPane = new Pane();

			flowPane.setVgap(8);
			flowPane.setHgap(8);

			VBox naviBox = new VBox();
			naviBox.setPrefHeight(50);
			naviBox.setPrefWidth(45);
			Button quesNumber = new Button();
			quesNumber.setText((i + 1) + "");
			quesNumber.setTextAlignment(TextAlignment.CENTER);
			quesNumber.setTextFill(Color.BLACK);
			quesNumber.setPrefWidth(40);
			quesNumber.setStyle("-fx-background-color: whitesmoke;");

			AnchorPane colorPane = new AnchorPane();
			colorPane.setStyle("-fx-background-color: darkgray;");
			naviBox.getChildren().add(quesNumber);
			naviBox.getChildren().add(colorPane);
			VBox.setVgrow(colorPane, Priority.ALWAYS);
			naviBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 2px;");

			quizBox.setPadding(new Insets(10));
			quizBox.setSpacing(10);

			Label lb1 = new Label();
			lb1.setText("Question " + (i + 1));
			lb1.setTextFill(javafx.scene.paint.Color.RED);

			Label lb2 = new Label();
			if (tree.quizResult.selectedChoice.get(i).getCheckDaDien() == 1) {
				lb2.setText("Answered");
			} else {
				lb2.setText("Not yet answered");
			}

			Label lb3 = new Label();
			lb3.setText("Marked out of 1.00");

			Hyperlink flag = new Hyperlink();
			flag.setText("Flag question");

			quesInfo.getChildren().add(lb1);
			quesInfo.getChildren().add(lb2);
			quesInfo.getChildren().add(lb3);
			quesInfo.getChildren().add(flag);
			quesInfo.setPrefWidth(140);
			quesInfo.setMinWidth(140);
			quesInfo.setMaxHeight(50);
			quesInfo.setStyle("-fx-background-color: lightgray; -fx-border-color: gray;");
			quesInfo.setPadding(new Insets(10));
			quesInfo.setSpacing(10);

			quesVInfo.getChildren().add(quesInfo);
			blankPane.setMinHeight(50);
			quesVInfo.getChildren().add(blankPane);

			quizBox.getChildren().add(quesVInfo);

			questionVBox.getChildren().add(quizBox);

			Label quesName = new Label();
			quesName.setText(tree.quiz_selected.getlistQues().get(i).getName() + ": "
					+ tree.quiz_selected.getlistQues().get(i).getText());
			quesName.setWrapText(true);
			quesBox.getChildren().add(quesName);

			if (!tree.quiz_selected.getlistQues().get(i).getImage().equals("")) {

				int dot = tree.quiz_selected.getlistQues().get(i).getImage().lastIndexOf(".");
				String fileType = tree.quiz_selected.getlistQues().get(i).getImage().substring(dot + 1);

				if (fileType.equals("mp4") || fileType.equals("mov") || fileType.equals("avi") || fileType.equals("flv")
						|| fileType.equals("mpg")) {
					HBox VideoBox = new HBox();
					VBox buttonBox = new VBox();

					Media media = new Media(tree.quiz_selected.getlistQues().get(i).getImage());
					MediaPlayer mp = new MediaPlayer(media);
					MediaView view_Media = new MediaView();
					view_Media.setMediaPlayer(mp);
					view_Media.setFitHeight(200);
					view_Media.setFitWidth(200);

					Image play = new Image("/Bai32/play.png");
					ImageView playView = new ImageView(play);
					playView.setFitHeight(30);
					playView.setFitWidth(30);
					playView.setCursor(Cursor.HAND);
					playView.setPickOnBounds(true);
					playView.setOnMouseClicked(event -> {
						mp.play();
					});
					Pane playPane = new Pane();
					playPane.getChildren().add(playView);
					playPane.setPrefHeight(30);
					playPane.setPrefWidth(30);
					playPane.setStyle("-fx-border-color: gray;");

					Image pause = new Image("/Bai32/pause.png");
					ImageView pauseView = new ImageView(pause);
					pauseView.setFitHeight(30);
					pauseView.setFitWidth(30);
					pauseView.setCursor(Cursor.HAND);
					pauseView.setPickOnBounds(true);
					pauseView.setOnMouseClicked(event -> {
						mp.pause();
					});
					Pane pausePane = new Pane();
					pausePane.getChildren().add(pauseView);
					pausePane.setPrefHeight(30);
					pausePane.setPrefWidth(30);
					pausePane.setStyle("-fx-border-color: gray;");

					Image reload = new Image("/Bai32/reload.png");
					ImageView reloadView = new ImageView(reload);
					reloadView.setFitHeight(30);
					reloadView.setFitWidth(30);
					reloadView.setCursor(Cursor.HAND);
					reloadView.setPickOnBounds(true);
					reloadView.setOnMouseClicked(event -> {
						mp.seek(Duration.ZERO);
						mp.play();
					});
					Pane reloadPane = new Pane();
					reloadPane.getChildren().add(reloadView);
					reloadPane.setPrefHeight(30);
					reloadPane.setPrefWidth(30);
					reloadPane.setStyle("-fx-border-color: gray;");

					buttonBox.getChildren().addAll(playPane, pausePane, reloadPane);
					buttonBox.setSpacing(4);
					VideoBox.getChildren().addAll(view_Media, buttonBox);
					VideoBox.setHgrow(view_Media, Priority.ALWAYS);
					VideoBox.setSpacing(5);
					quesBox.getChildren().add(VideoBox);
				} else {
					Image img = new Image(tree.quiz_selected.getlistQues().get(i).getImage());
					ImageView imgView = new ImageView(img);
					imgView.setPreserveRatio(true);
					imgView.setFitHeight(100);
					imgView.setFitWidth(100);
					quesBox.getChildren().add(imgView);
				}
			}

			double s = 0;

			String s1 = "";

			int checkSoDA = 1;

			for (Choice choice : tree.quiz_selected.getlistQues().get(i).getChoices()) {

				if (choice.get_double_Grade() > 0) {
					s1 = s1 + choice.getText() + " ";
				}

				RadioButton choice1 = new RadioButton();
				choice1.setText(choice.getText());
				quesBox.getChildren().add(choice1);

				int index = tree.quiz_selected.getlistQues().get(i).getChoices().indexOf(choice);

				if (!tree.quiz_selected.getlistQues().get(i).getChoices().get(index).getImage().equals("")) {
					Image img = new Image(choice.getImage());
					ImageView imgView = new ImageView(img);
					imgView.setPreserveRatio(true);
					imgView.setFitHeight(100);
					imgView.setFitWidth(100);
					quesBox.getChildren().add(imgView);
				}

				if (tree.quizResult.getSelectedChoice().get(i).getYourChoice().get(index)) {
					if (choice.get_double_Grade() == 0.0)
						checkSoDA = 0;
					choice1.setSelected(true);
					s = s + choice.get_double_Grade() / 100.0;
				}
				choice1.setDisable(true);
			}
			if (checkSoDA == 1)
				grade = grade + s;

			quesBox.setPadding(new Insets(10));
			quesBox.setSpacing(10);
			quesBox.setStyle("-fx-background-color: #e7f3f6;");
			// quesBox.setMinHeight(50);
			// quesBox.setVgrow(quesName, null);
			quesVBox.getChildren().add(quesBox);
			quizBox.getChildren().add(quesVBox);
			if (s == 1 && checkSoDA == 1) {

				Label lban = new Label();
				lban.setText("Your answer is correct");
				lban.setWrapText(true);
				lban.setTextFill(Color.BROWN);
				informans.getChildren().add(lban);

			} else {
				Label lban = new Label();
				lban.setText("The correct answer is: " + s1);
				lban.setWrapText(true);
				lban.setTextFill(Color.BROWN);
				informans.getChildren().add(lban);
			}

			informans.setStyle("-fx-background-color: #fcefdc;");
			informans.setMinHeight(40);

			quesVBox.setSpacing(10);
			// quesVBox.setPadding(new Insets(5));
			quesVBox.getChildren().add(informans);

			VBox.setVgrow(quesBox, Priority.ALWAYS);
			HBox.setHgrow(quesVBox, Priority.ALWAYS);
			VBox.setVgrow(quizBox, Priority.ALWAYS);
			scrPane.setFitToWidth(true);

			quesNumber.setOnAction(event -> {
				scrPane.setVvalue((Double.parseDouble(quesNumber.getText()) - 1) / (mm)
						+ (Double.parseDouble(quesNumber.getText()) - 1) / (mm * mm));
			});

			flowPane.getChildren().add(naviBox);
		}

		QuizMark qr = new QuizMark(grade, tree.quiz_selected.getlistQues().size(), tree.quiz_selected.getGrade());

		tree.quiz_selected.getMarks().add(qr);

		double gr = (grade / tree.quiz_selected.getlistQues().size()) * tree.quiz_selected.getGrade();
		tree.quiz_selected.setLastGrade(gr);
	}

	@SuppressWarnings("static-access")
	public void finishReview(ActionEvent e) throws IOException {

		tree.checkScene = 0;

		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
		Parent root = loader.load();

		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("/Bai1/Scene01.fxml"));
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
