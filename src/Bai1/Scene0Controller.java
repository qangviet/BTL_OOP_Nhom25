package Bai1;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;

import Bai2_31.QuestionController;
import Bai2_31.tree;
import Bai51.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Scene0Controller implements Initializable {
	@FXML
	private ImageView setting;
	@FXML
	private ImageView tamgiac;

	@FXML
	private ImageView imageView;
	@FXML
	private AnchorPane anchorPane0;
	@FXML
	public AnchorPane anchorPane1;
	@FXML
	private VBox quizBox;
	@FXML
	private HBox breadCrumb;
	@FXML
	private Button turnOnEdit;
	public static Quiz quiz1;
	public static Button Question;
	Hyperlink QBank;
	public static Button Cate;
	public static Button Import;
	public static Button Export;

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (tree.checkScene != 0) {
			setting.setVisible(false);
			tamgiac.setVisible(false);
			turnOnEdit.setVisible(false);
		}

		// Create HyperLinks

		Insets margin = new Insets(2, 0, 0, 0);
		Hyperlink Home = new Hyperlink();
		Home.setText("Home");
		Home.setStyle("-fx-font-size: 16px;");

		Label gachCheo1 = new Label();
		gachCheo1.setText("/");
		gachCheo1.setStyle("-fx-font-size: 16px;");
		HBox.setMargin(gachCheo1, margin);

		Hyperlink myCourses = new Hyperlink();
		myCourses.setText("My Courses");
		myCourses.setStyle("-fx-font-size: 16px;");

		Label gachCheo2 = new Label();
		gachCheo2.setText("/");
		gachCheo2.setStyle("-fx-font-size: 16px;");
		HBox.setMargin(gachCheo2, margin);

		Hyperlink THICK = new Hyperlink();
		THICK.setText("THI CUỐI KỲ");
		THICK.setStyle("-fx-font-size: 16px;");
		THICK.setOnAction(event -> {
			try {

				// lấy thời gian ấn ra khỏi thi
				if (tree.checkScene == 72) {
					Instant bro = Instant.now();
					long noww = bro.toEpochMilli() / 1000;
					tree.quiz_selected.setTime(Long.toString(noww));
				}

				tree.checkScene = 0;

				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
			} catch (Exception e) {
				// TODO: handle exception
			}
		});

		Label gachCheo3 = new Label();
		gachCheo3.setText("/");
		gachCheo3.setStyle("-fx-font-size: 16px;");
		HBox.setMargin(gachCheo3, margin);

		QBank = new Hyperlink();
		QBank.setText("Question bank");
		QBank.setStyle("-fx-font-size: 16px;");
		QBank.setOnAction(event -> {

		});

		Label gachCheo4 = new Label();
		gachCheo4.setText("/");
		gachCheo4.setStyle("-fx-font-size: 16px;");
		HBox.setMargin(gachCheo4, margin);

		Hyperlink Questions = new Hyperlink();
		Questions.setText("Questions");
		Questions.setStyle("-fx-font-size: 16px;");
		Questions.setOnAction(event -> {
			try {

				tree.checkScene = 31;

				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
				Parent root = loader.load();

				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(getClass().getResource("/Bai2_31/QuestionsScene.fxml"));
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
			} catch (Exception e) {
				// TODO: handle exception
			}
		});

		Label gachCheo5 = new Label();
		gachCheo5.setText("/");
		gachCheo5.setStyle("-fx-font-size: 16px;");
		HBox.setMargin(gachCheo5, margin);

		Hyperlink addQues = new Hyperlink();
		addQues.setText("Adding a Multiple choice question");
		addQues.setStyle("-fx-font-size: 16px;");

		Hyperlink editQues = new Hyperlink();
		editQues.setText("Editing a Multiple choice question");
		editQues.setStyle("-fx-font-size: 16px;");

		Hyperlink Category = new Hyperlink();
		Category.setText("Category");
		Category.setStyle("-fx-font-size: 16px;");

		Hyperlink Import = new Hyperlink();
		Import.setText("Import");
		Import.setStyle("-fx-font-size: 16px;");

		Hyperlink Export = new Hyperlink();
		Export.setText("Export");
		Export.setStyle("-fx-font-size: 16px;");

		Hyperlink editQuiz = new Hyperlink();
		editQuiz.setText("Edit quiz");
		editQuiz.setStyle("-fx-font-size: 16px;");

		Hyperlink Preview = new Hyperlink();
		Preview.setText("Preview");
		Preview.setStyle("-fx-font-size: 16px;");

		// Navigation

		// Starting

		// Home / My courses / THI CUỐI KỲ

		breadCrumb.getChildren().add(Home);

		breadCrumb.getChildren().add(gachCheo1);

		breadCrumb.getChildren().add(myCourses);

		breadCrumb.getChildren().add(gachCheo2);

		breadCrumb.getChildren().add(THICK);

		// On each scene

		// Question bank

		if (tree.checkScene == 12) {
			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(QBank);
		}

		// Question bank / Questions
		if (tree.checkScene == 31) {
			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(QBank);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Questions);
		}

		// Question bank / Questions / Adding a Multiple choice question
		if (tree.checkScene == 321) {
			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(QBank);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Questions);

			breadCrumb.getChildren().add(gachCheo5);
			breadCrumb.getChildren().add(addQues);
		}

		// Question bank / Questions / Editing a Multiple choice question
		if (tree.checkScene == 322) {
			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(QBank);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Questions);

			breadCrumb.getChildren().add(gachCheo5);
			breadCrumb.getChildren().add(editQues);
		}

		// Question bank / Category
		if (tree.checkScene == 33) {
			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(QBank);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Category);
		}

		// Question bank / Import
		if (tree.checkScene == 34) {
			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(QBank);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Import);
		}

		// Question bank / Export
		if (tree.checkScene == 35) {
			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(QBank);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Export);
		}

		// "quizName"
		if (tree.checkScene == 61) {
			Hyperlink quizName = new Hyperlink();
			quizName.setText(tree.quiz_selected.getName());
			quizName.setStyle("-fx-font-size: 16px;");
			quizName.setOnAction(event -> {
				try {

					tree.checkScene = 61;

					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
					Parent root = loader.load();

					FXMLLoader loader1 = new FXMLLoader();
					loader1.setLocation(getClass().getResource("/Bai6/Scene6_1.fxml"));
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
				} catch (Exception e) {
					// TODO: handle exception
				}
			});

			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(quizName);
		}

		// "quizName" / Edit quiz
		if (tree.checkScene == 62) {
			Hyperlink quizName = new Hyperlink();
			quizName.setText(tree.quiz_selected.getName());
			quizName.setStyle("-fx-font-size: 16px;");
			quizName.setOnAction(event -> {
				try {

					tree.checkScene = 61;

					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
					Parent root = loader.load();

					FXMLLoader loader1 = new FXMLLoader();
					loader1.setLocation(getClass().getResource("/Bai6/Scene6_1.fxml"));
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
				} catch (Exception e) {
					// TODO: handle exception
				}
			});

			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(quizName);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(editQuiz);
		}

		// "quizName" / Preview
		if (tree.checkScene == 71) {
			Hyperlink quizName = new Hyperlink();
			quizName.setText(tree.quiz_selected.getName());
			quizName.setStyle("-fx-font-size: 16px;");
			quizName.setOnAction(event -> {
				try {

					tree.checkScene = 61;

					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
					Parent root = loader.load();

					FXMLLoader loader1 = new FXMLLoader();
					loader1.setLocation(getClass().getResource("/Bai6/Scene6_1.fxml"));
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
				} catch (Exception e) {
					// TODO: handle exception
				}
			});

			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(quizName);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Preview);
		}

		if (tree.checkScene == 72) {

			// ControllerThi.timeline.stop();
			Hyperlink quizName = new Hyperlink();
			quizName.setText(tree.quiz_selected.getName());
			quizName.setStyle("-fx-font-size: 16px;");
			quizName.setOnAction(event -> {
				try {

					tree.checkScene = 61;

					// lấy thời gian ấn ra khỏi thi
					Instant bro = Instant.now();
					long noww = bro.toEpochMilli() / 1000;
					tree.quiz_selected.setTime(Long.toString(noww));

					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
					Parent root = loader.load();

					FXMLLoader loader1 = new FXMLLoader();
					loader1.setLocation(getClass().getResource("/Bai6/Scene6_1.fxml"));
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
				} catch (Exception e) {
					// TODO: handle exception
				}
			});

			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(quizName);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Preview);
		}

		if (tree.checkScene == 73) {
			Hyperlink quizName = new Hyperlink();
			quizName.setText(tree.quiz_selected.getName());
			quizName.setStyle("-fx-font-size: 16px;");
			quizName.setOnAction(event -> {
				try {

					tree.checkScene = 61;

					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
					Parent root = loader.load();

					FXMLLoader loader1 = new FXMLLoader();
					loader1.setLocation(getClass().getResource("/Bai6/Scene6_1.fxml"));
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
				} catch (Exception e) {
					// TODO: handle exception
				}
			});

			breadCrumb.getChildren().add(gachCheo3);
			breadCrumb.getChildren().add(quizName);

			breadCrumb.getChildren().add(gachCheo4);
			breadCrumb.getChildren().add(Preview);
		}

		tree.checkTabb.textProperty().addListener((observable, oldValue, newValue) -> {
			if (tree.checkTabb.getText().equals("1")) {

				int index = breadCrumb.getChildren().indexOf(Questions);

				if (index >= 0) {
					while (breadCrumb.getChildren().size() > index) {
						int lastNode1 = breadCrumb.getChildren().size() - 1;
						breadCrumb.getChildren().remove(lastNode1);
					}
					breadCrumb.getChildren().add(Category);
				} else {
					int lastNode = breadCrumb.getChildren().size() - 1;
					breadCrumb.getChildren().remove(lastNode);
					breadCrumb.getChildren().add(Category);
				}

			}
			if (tree.checkTabb.getText().equals("2")) {

				int index = breadCrumb.getChildren().indexOf(Questions);

				if (index >= 0) {
					while (breadCrumb.getChildren().size() > index) {
						int lastNode1 = breadCrumb.getChildren().size() - 1;
						breadCrumb.getChildren().remove(lastNode1);
					}
					breadCrumb.getChildren().add(Import);
				} else {
					int lastNode = breadCrumb.getChildren().size() - 1;
					breadCrumb.getChildren().remove(lastNode);
					breadCrumb.getChildren().add(Import);
				}

			}
			if (tree.checkTabb.getText().equals("3")) {

				int index = breadCrumb.getChildren().indexOf(Questions);

				if (index >= 0) {
					while (breadCrumb.getChildren().size() > index) {
						int lastNode1 = breadCrumb.getChildren().size() - 1;
						breadCrumb.getChildren().remove(lastNode1);
					}
					breadCrumb.getChildren().add(Export);
				} else {
					int lastNode = breadCrumb.getChildren().size() - 1;
					breadCrumb.getChildren().remove(lastNode);
					breadCrumb.getChildren().add(Export);
				}

			}
			if (tree.checkTabb.getText().equals("4")) {

				int index = breadCrumb.getChildren().indexOf(Questions);

				if (index >= 0) {
					while (breadCrumb.getChildren().size() > index) {
						int lastNode1 = breadCrumb.getChildren().size() - 1;
						breadCrumb.getChildren().remove(lastNode1);
					}
					breadCrumb.getChildren().add(Questions);
				} else {
					int lastNode = breadCrumb.getChildren().size() - 1;
					breadCrumb.getChildren().remove(lastNode);
					breadCrumb.getChildren().add(Questions);
				}

			}

		});

	}

	@SuppressWarnings("static-access")
	public void goView12(MouseEvent event) throws IOException {
		int index = breadCrumb.getChildren().indexOf(QBank);
		if (index >= 0) {
			breadCrumb.getChildren().remove(index);
			breadCrumb.getChildren().remove(index - 1);
		}
		Insets margin = new Insets(2, 0, 0, 0);

		Label gachCheo4 = new Label();
		gachCheo4.setText("/");
		gachCheo4.setStyle("-fx-font-size: 16px;");
		HBox.setMargin(gachCheo4, margin);
		breadCrumb.getChildren().add(gachCheo4);
		breadCrumb.getChildren().add(QBank);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Popup popup = new Popup();
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/Bai1/Scene12.fxml"));
		Parent root2 = loader2.load();
		popup.getContent().add(root2);
		root2.setStyle("-fx-background-color: white;"); // Đặt màu nền trắng cho Popup
		popup.show(stage);
		Scene currentScene = (Scene) ((Node) event.getSource()).getScene();
		currentScene.getRoot().setDisable(true);
		popup.setAutoHide(true);
		popup.setOnAutoHide(e2 -> {
			currentScene.getRoot().setDisable(false);
			int index1 = breadCrumb.getChildren().indexOf(QBank);
			if (index1 >= 0) {
				breadCrumb.getChildren().remove(index1);
				breadCrumb.getChildren().remove(index1 - 1);
			}
		});

		Question = new Button();
		Question.setPrefSize(0, 0);
		Question.setStyle("-fx-background-color: steelblue ;");
		Question.setOnAction(e -> {
			popup.hide();
			setting.setDisable(false);
			tamgiac.setDisable(false);
			tree.checkScene = 31;

			try {
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
				Parent root;
				root = loader.load();
				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(getClass().getResource("/Bai2_31/QuestionsScene.fxml"));
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		// tree.checkScene = 12;
		Cate = new Button();
		Cate.setPrefSize(0, 0);
		Cate.setStyle("-fx-background-color:  white;");
		Cate.setOnAction(e -> {
			popup.hide();
			setting.setDisable(false);
			tamgiac.setDisable(false);

			try {
				tree.checkScene = 33;

				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
				Parent root = loader.load();

				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(getClass().getResource("/Bai2_31/QuestionsScene.fxml"));
				Parent root1 = loader1.load();

				QuestionController workspaceController = loader1.getController();
				workspaceController.changeCategoryTab();

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

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		Import = new Button();
		Import.setPrefSize(0, 0);
		Import.setStyle("-fx-background-color:  white;");
		Import.setOnAction(e -> {
			popup.hide();
			setting.setDisable(false);
			tamgiac.setDisable(false);
			try {
				tree.checkScene = 34;

				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
				Parent root = loader.load();

				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(getClass().getResource("/Bai2_31/QuestionsScene.fxml"));
				Parent root1 = loader1.load();

				QuestionController workspaceController = loader1.getController();
				workspaceController.changeImportTab();

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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Export = new Button();
		Export.setPrefSize(0, 0);
		Export.setStyle("-fx-background-color:  white;");
		Export.setOnAction(e -> {
			popup.hide();
			setting.setDisable(false);
			tamgiac.setDisable(false);
			try {
				tree.checkScene = 35;

				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
				Parent root = loader.load();

				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(getClass().getResource("/Bai2_31/QuestionsScene.fxml"));
				Parent root1 = loader1.load();

				QuestionController workspaceController = loader1.getController();
				workspaceController.changeExportTab();

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

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	public void turnonedit(ActionEvent e) throws IOException {

		// lấy thời gian ấn ra khỏi thi
		if (tree.checkScene == 72) {
			Instant bro = Instant.now();
			long noww = bro.toEpochMilli() / 1000;
			tree.quiz_selected.setTime(Long.toString(noww));
		}

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/Bai51/Scene51.fxml"));
		Scene scene = new Scene(root, tree.stageWidth, tree.stageHeight);
		stage.setScene(scene);
		stage.show();

	}

}
