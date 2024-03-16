package Bai7;

import java.io.IOException;

import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;

import Bai2_31.Choice;
import Bai2_31.tree;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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

public class ControllerThi implements Initializable {

	private int countdownTime; // Thời gian đếm ngược (số giây)
	@FXML
	private Label countdownLabel;
	@FXML
	VBox questionVBox;
	@FXML
	AnchorPane quesBoxes;
	@FXML
	ScrollPane scrPane;
	@FXML
	FlowPane flowPane;

	@FXML
	AnchorPane anchor;
	@FXML
	Button finish;

	Instant end;

	double mm;

	long duration;
	public static Timeline timeline;

	ObservableList<Integer> count = FXCollections.observableArrayList();
	ObservableList<SelectedChoices> slchoice = FXCollections.observableArrayList();

	SelectedChoices sltChoice = new SelectedChoices();

	ObservableList<SelectedChoices> SelectedChoiceList;
	BooleanProperty bulean = new SimpleBooleanProperty(true);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tree.checkScene = 72;

		mm = tree.quiz_selected.getlistQues().size();

		// Nếu có time limit, startCountdown
		if (!tree.quiz_selected.getTimeLimit().get(0).equals("-1")) {
			countdownLabel.setText(formatTime(countdownTime));
			startCountdown();
		}

		scrPane.setFitToWidth(true);
		scrPane.setContent(questionVBox);

		for (int i = 0; i < tree.quiz_selected.getlistQues().size(); i++) {
			count.add(0);
		}

		displayQuestion();
		
		tree.quiz_selected.setQuizPaused(slchoice);
		tree.quizResult.setSelectedChoice(slchoice);

		// lấy time1 tiếp
		Instant bro = Instant.now();
		long noww = bro.toEpochMilli() / 1000;
		tree.quiz_selected.setTime1(Long.toString(noww));

		// Nếu ko có time limit
		if (tree.quiz_selected.getTimeLimit().get(0).equals("-1")) {
			countdownLabel.setVisible(false);

			// Nếu ấn ra ngoài rồi quay lại thi
			if (!tree.quiz_selected.getTime().equals("")) {
				duration = Long.parseLong(tree.quiz_selected.getTime1()) - Long.parseLong(tree.quiz_selected.getTime());
				tree.quiz_selected.setDuration(tree.quiz_selected.getDuration() + (int) duration);
			}

		}

	}

	public void startCountdown() { // phải có time limit mới chạy
		// Nếu không ấn ra ngoài
		if (tree.quiz_selected.getTime().equals("")) {
			countdownTime = tree.getTimeLimit(tree.quiz_selected.getTimeLimit());
		} else { // Nếu ấn ra ngoài rồi quay lại thi
			duration = Long.parseLong(tree.quiz_selected.getTime()) - Long.parseLong(tree.quiz_selected.getTime1());
			tree.quiz_selected.setDuration(tree.quiz_selected.getDuration() + (int) duration);
			countdownTime = tree.getTimeLimit(tree.quiz_selected.getTimeLimit()) - tree.quiz_selected.getDuration(); // thời gian đếm ngược còn lại

		}
		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE); // Cho phép chạy vô hạn
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
			if (tree.checkScene != 72) {
				timeline.stop();
				tree.quiz_selected.setQuizPaused(slchoice);
				tree.quizResult.setSelectedChoice(slchoice);
			}
			countdownTime--; // Giảm giá trị đếm ngược

			if (countdownTime >= 0) {
				countdownLabel.setText(formatTime(countdownTime));
			} else {
				timeline.stop(); // Dừng đếm ngược khi hết thời gian

				tree.checkScene = 73;

				// end = Instant.now();
				finish.fire();

			}
		}));
		timeline.play();
	}

	public String formatTime(int timeInSeconds) {
		int hours = timeInSeconds / 3600;
		int minutes = (timeInSeconds % 3600) / 60;
		int seconds = timeInSeconds % 60;

		return String.format("Time left: %02d:%02d:%02d", hours, minutes, seconds);
	}

	@SuppressWarnings("static-access")
	public void finishAttempt(ActionEvent event) throws IOException {
		tree.quiz_selected.setQuizPaused(slchoice);
		tree.quizResult.setSelectedChoice(slchoice);
		if (tree.checkScene == 72) {
			Alert xacNhanNop = new Alert(AlertType.CONFIRMATION);
			xacNhanNop.setTitle("CONFIRMATION!");
			xacNhanNop.setHeaderText("Are you sure to finish the attempt?");
			xacNhanNop.showAndWait();
			if (xacNhanNop.getResult() == ButtonType.OK) {

				end = Instant.now();
				
				//Nếu có time limit
				if (!tree.quiz_selected.getTimeLimit().get(0).equals("-1")) {
					int timeTaken = tree.getTimeLimit(tree.quiz_selected.getTimeLimit()) - countdownTime - 1;
					tree.quizResult.setTimeTaken(timeTaken);
				} else { //Nếu không có time limit
					long durationInSeconds = end.toEpochMilli() / 1000 - tree.quizResult.getTimeStartt()
							- tree.quiz_selected.getDuration();
					tree.quizResult.setTimeTaken((int) durationInSeconds);
				}

				tree.checkScene = 73;

				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
				Parent root = loader.load();

				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(getClass().getResource("/Bai7/ReviewScene.fxml"));
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
		} else {
			end = Instant.now();

			if (!tree.quiz_selected.getTimeLimit().get(0).equals("-1")) {
				int timeTaken = tree.getTimeLimit(tree.quiz_selected.getTimeLimit()) - countdownTime - 1;
				tree.quizResult.setTimeTaken(timeTaken);
			} else {
				long durationInSeconds = end.toEpochMilli() / 1000 - tree.quizResult.getTimeStartt()
						- tree.quiz_selected.getDuration();
				tree.quizResult.setTimeTaken((int) durationInSeconds);
			}

			tree.checkScene = 73;

			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
			Parent root = loader.load();

			FXMLLoader loader1 = new FXMLLoader();
			loader1.setLocation(getClass().getResource("/Bai7/ReviewScene.fxml"));
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
	public void displayQuestion() {
		for (int i = 0; i < tree.quiz_selected.getlistQues().size(); i++) {
			HBox quizBox = new HBox();
			VBox quesInfo = new VBox();
			VBox quesBox = new VBox();
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
			// colorPane.setStyle("-fx-background-color: lightgray;");
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
			lb2.setText("Not yet answered");

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
			quesInfo.setStyle("-fx-background-color: lightgray; -fx-border-color: gray;");
			quesInfo.setPadding(new Insets(10));
			quesInfo.setSpacing(10);

			quesVInfo.getChildren().add(quesInfo);
			blankPane.setMinHeight(50);
			quesVInfo.getChildren().add(blankPane);

			quizBox.getChildren().add(quesInfo);

			questionVBox.getChildren().add(quizBox);

			Label quesName = new Label();
			quesName.setWrapText(true);
			quesName.setText(tree.quiz_selected.getlistQues().get(i).getText());
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

			ObservableList<Boolean> yourchoice = FXCollections.observableArrayList();

			ObservableList<RadioButton> radioBtn = FXCollections.observableArrayList();

			RadioBox_Count CHOICE = new RadioBox_Count(radioBtn, 0);

			ObservableList<Boolean> urchoice = FXCollections.observableArrayList();

			for (int k = 0; k <= 50; k++) {
				urchoice.add(false);

			}

			SelectedChoices sltChoice = new SelectedChoices(urchoice);
			sltChoice.setCheckDaDien(0);

			slchoice.add(sltChoice);

			for (Choice choice : tree.quiz_selected.getlistQues().get(i).getChoices()) {

				RadioButton choice1 = new RadioButton();
				choice1.setText(choice.getText());
				choice1.setWrapText(true);
				
				quesBox.getChildren().add(choice1);
				
				if (!choice.getImage().equals("")) {
					Image img = new Image(choice.getImage());
					ImageView imgView = new ImageView(img);
					imgView.setFitHeight(100);
					imgView.setFitWidth(100);
					quesBox.getChildren().add(imgView);
				}

				int index1 = tree.quiz_selected.getlistQues().get(i).getChoices().indexOf(choice);

				if (tree.quiz_selected.getQuizPaused().get(i).getYourChoice().get(index1)) {
					sltChoice.getYourChoice().set(index1, true);
					choice1.setSelected(true);
					colorPane.setStyle("-fx-background-color: darkgray;");
					lb2.setText("Answered");
					CHOICE.setCount(CHOICE.getCount() + 1);
				}

				CHOICE.addRadioBtn(choice1);
				choice1.selectedProperty().addListener((observable, oldValue, newValue) -> {
					int index = CHOICE.getRadioBtn().indexOf(choice1);
					if (newValue) {
						sltChoice.getYourChoice().set(index, true);

						CHOICE.setCount(CHOICE.getCount() + 1);
						bulean.set(true);
						if (CHOICE.getCount() > 0) {
							sltChoice.setCheckDaDien(1);
							colorPane.setStyle("-fx-background-color: darkgray;");

							lb2.setText("Answered");
						} else {
							sltChoice.setCheckDaDien(0);

							colorPane.setStyle("-fx-background-color: whitesmoke;");

							lb2.setText("Not yet answered");
						}
					} else {
						sltChoice.getYourChoice().set(index, false);

						sltChoice.setCheckDaDien(0);
						CHOICE.setCount(CHOICE.getCount() - 1);
						bulean.set(false);
						if (CHOICE.getCount() > 0) {
							colorPane.setStyle("-fx-background-color: darkgray;");
							sltChoice.setCheckDaDien(1);
							lb2.setText("Answered");
						} else {
							colorPane.setStyle("-fx-background-color: whitesmoke;");
							sltChoice.setCheckDaDien(0);
							lb2.setText("Not yet answered");
						}
					}
					yourchoice.add(bulean.get());
				});
			}

			quesBox.setPadding(new Insets(10));
			quesBox.setSpacing(10);
			quesBox.setStyle("-fx-background-color: #e7f3f6;");

			quizBox.getChildren().add(quesBox);
			HBox.setHgrow(quesBox, Priority.ALWAYS);
			VBox.setVgrow(quizBox, Priority.ALWAYS);
			questionVBox.setSpacing(5);

			flag.setOnAction(event -> {
				if (colorPane.getStyle().equals("-fx-background-color: red;")) {
					if (CHOICE.getCount() > 0) {
						colorPane.setStyle("-fx-background-color: darkgray;");
					} else {
						colorPane.setStyle("-fx-background-color: whitesmoke;");
					}
				} else {
					colorPane.setStyle("-fx-background-color: red;");
				}
			});

			quesNumber.setOnAction(event -> {
				scrPane.setVvalue((Double.parseDouble(quesNumber.getText()) - 1) / (mm)
						+ (Double.parseDouble(quesNumber.getText()) - 1) / (mm * mm));
			});

			flowPane.getChildren().add(naviBox);
		}

	}

}
