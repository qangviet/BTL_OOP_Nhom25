package Bai32;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Bai2_31.Category;
import Bai2_31.Choice;
import Bai2_31.Grade;
import Bai2_31.Question;
import Bai2_31.QuestionController;
import Bai2_31.tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerScene3_2 implements Initializable {
	int numbersOfChoices = 0;
	@FXML
	Label label_title;
	@FXML
	ImageView view_Image;
	@FXML
	MediaView view_Media;
	@FXML
	HBox view_Video;
	@FXML
	private Button addMedia;
	@FXML
	private Button more_3_choices;
	@FXML
	ComboBox<Category> comboBox_category;
	@FXML
	TextField textField_nameQuestion;
	@FXML
	TextArea textArea_textQuestion;
	@FXML
	TextField textField_mark;
	@FXML
	VBox vbox_warp_gp;
	@FXML
	ComboBox<String> comboBox_choose;
	@FXML
	AnchorPane anchorPane;

	private ObservableList<TextArea> textArea_Choices;
	private ObservableList<ComboBox<Grade>> grade_Choices;
	private ObservableList<String> image_Choices;

	private Category category_selected;
	private Category category_selected_previous;

	private Question question_previous;
//	private int chk_pos = -1;
	private String image_question = "";
	private boolean chk_category_selected = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 321;

		createComboBox_Category(comboBox_category);
		textArea_Choices = FXCollections.observableArrayList();
		grade_Choices = FXCollections.observableArrayList();
		image_Choices = FXCollections.observableArrayList();
		if (tree.chk_create_edit) {
			try {
				loadQuestion(QuestionController.category_selected_edit, QuestionController.question_selected_edit);
			} catch (IOException e) {
				e.printStackTrace();
			}
			numbersOfChoices = QuestionController.question_selected_edit.getChoices().size();
		} else
			for (int i = 1; i <= 2; i++) {
				try {
					createChoice();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		comboBox_choose.getItems().addAll("Image", "Video", "GIF");
		comboBox_choose.setPromptText("None");
	}

	public void createChoice() throws IOException {
		numbersOfChoices++;
		image_Choices.add("");
		Parent root = FXMLLoader.load(getClass().getResource("gridpane.fxml"));
		// Đổi tên Choice
		Label name = (Label) root.lookup("#label_choice_name");
		name.setText("Choice " + numbersOfChoices);
		@SuppressWarnings("unchecked")
		// Tạo grade
		ComboBox<Grade> grade = (ComboBox<Grade>) root.lookup("#grade");
		createGrade(grade);
		grade_Choices.add(grade);
		ImageView imageview_choice = (ImageView) root.lookup("#view_choice");

		// Set on Action image_button
		Button image_button = (Button) root.lookup("#add_image_choice");
		image_button.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Chọn ảnh");
			FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Hình ảnh", "*.png", "*.jpg",
					"*.jpeg");
			fileChooser.getExtensionFilters().add(imageFilter);

			Stage stage = (Stage) addMedia.getScene().getWindow();
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				String imagePath = selectedFile.getAbsolutePath();
				image_Choices.set(grade_Choices.indexOf(grade), imagePath);
				Image image = new Image(imagePath);
				imageview_choice.setImage(image);
			}
		});
		TextArea textarea = (TextArea) root.lookup("#textArea_Choice");
		textArea_Choices.add(textarea);
		GridPane choice_GridPane = (GridPane) root.lookup("#choice_GridPane");
		vbox_warp_gp.getChildren().add(vbox_warp_gp.getChildren().indexOf(more_3_choices), choice_GridPane);
	}

	public void createComboBox_Category(ComboBox<Category> choice_cate) {
		choice_cate.setPromptText("Choose a category");
		choice_cate.setOnShown(e -> {
			TreeItem<Category> root = new TreeItem<Category>(tree.defaultCategory);
			tree.defaultCategory.setShowSub(false);
			tree.add_tree_item(tree.defaultCategory, root);
			Popup popup = new Popup();
			TreeView<Category> treeview = new TreeView<Category>();
			treeview.setRoot(root);
			treeview.setOnMouseClicked(event -> {
				TreeItem<Category> selectedItem = treeview.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					choice_cate.setValue(selectedItem.getValue());
					popup.hide();
					choice_cate.hide();
				}
			});
			popup.getContent().add(treeview);
			Bounds boundsInScreen = choice_cate.localToScreen(choice_cate.getBoundsInLocal());
			popup.show(choice_cate, boundsInScreen.getMinX(), boundsInScreen.getMaxY());
			popup.setAutoHide(true);
			popup.setOnAutoHide(event -> {
				if (popup.isShowing() && !popup.getScene().getRoot().getBoundsInParent()
						.contains(((MouseEvent) event).getX(), ((MouseEvent) event).getY())) {
					popup.hide();
				}
			});
		});
	}

	public void chooseMedia(ActionEvent event) throws IOException {

		if (comboBox_choose.getValue() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!");
			alert.setHeaderText("Choose media before adding!");
			alert.showAndWait();
			return;
		}
		if (comboBox_choose.getValue().equals(new String("Image"))) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose image");
			FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Hình ảnh", "*.png", "*.jpg",
					"*.jpeg");
			fileChooser.getExtensionFilters().add(imageFilter);

			Stage stage = (Stage) addMedia.getScene().getWindow();
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				String imagePath = selectedFile.getAbsolutePath();
				image_question = imagePath;
				Image image = new Image(imagePath);
				view_Image.setImage(image);
				view_Image.setVisible(true);
				view_Video.setVisible(false);
			}
		} else if (comboBox_choose.getValue().equals(new String("Video"))) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose video");
			FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter("Video", "*.mp4", "*.avi",
					"*.mov", "*.flv", "*.mpg");
			fileChooser.getExtensionFilters().add(videoFilter);
			Stage stage = (Stage) addMedia.getScene().getWindow();
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				String videoPath = selectedFile.getAbsolutePath();
				videoPath = "file:///" + videoPath.replace("\\", "/");
				image_question = videoPath;

				Media media = new Media(videoPath);
				MediaPlayer mp = new MediaPlayer(media);
				view_Media.setMediaPlayer(mp);
				view_Video.setVisible(true);
				view_Image.setVisible(false);
				mp.play();
			}
		} else if (comboBox_choose.getValue().equals(new String("GIF"))) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose GIF");
			FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("GIF", "*.gif");
			fileChooser.getExtensionFilters().add(imageFilter);
			Stage stage = (Stage) addMedia.getScene().getWindow();
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				String gifPath = selectedFile.getAbsolutePath();
				image_question = gifPath;
				Image gif_image = new Image(gifPath);
				view_Image.setImage(gif_image);
				view_Image.setVisible(true);
				view_Video.setVisible(false);
			}
		}
	}

	public void loadQuestion(Category category, Question question) throws IOException {
		label_title.setText("Editing a Multiple choice question");
		comboBox_category.setValue(category);
		ObservableList<Choice> choices = question.getChoices();

		for (int i = 1; i <= choices.size(); i++) {
			Parent root = FXMLLoader.load(getClass().getResource("gridpane.fxml"));
			// Đổi tên Choice
			Label name = (Label) root.lookup("#label_choice_name");
			name.setText("Choice " + i);
			@SuppressWarnings("unchecked")
			// Tạo grade
			ComboBox<Grade> grade = (ComboBox<Grade>) root.lookup("#grade");
			createGrade(grade);
			grade.setValue(new Grade(choices.get(i - 1).get_double_Grade()));
			grade_Choices.add(grade);
			ImageView imageview_choice = (ImageView) root.lookup("#view_choice");
			if (!choices.get(i - 1).getImage().isEmpty()) {
				String image_path_choice = choices.get(i - 1).getImage();
				Image image_choice = new Image(image_path_choice);
				imageview_choice.setImage(image_choice);
				image_Choices.add(image_path_choice);
			} else
				image_Choices.add("");

			// Set on Action image_button
			Button image_button = (Button) root.lookup("#add_image_choice");
			image_button.setOnAction(e -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose image");
				FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Hình ảnh", "*.png", "*.jpg",
						"*.jpeg");
				fileChooser.getExtensionFilters().add(imageFilter);

				Stage stage = (Stage) addMedia.getScene().getWindow();
				File selectedFile = fileChooser.showOpenDialog(stage);
				if (selectedFile != null) {
					String imagePath = selectedFile.getAbsolutePath();
					image_Choices.set(grade_Choices.indexOf(grade), imagePath);
					Image image = new Image(imagePath);
					imageview_choice.setImage(image);
				}
			});
			TextArea textarea = (TextArea) root.lookup("#textArea_Choice");
			textarea.setText(choices.get(i - 1).getText());
			textArea_Choices.add(textarea);
			GridPane choice_GridPane = (GridPane) root.lookup("#choice_GridPane");
			vbox_warp_gp.getChildren().add(vbox_warp_gp.getChildren().indexOf(more_3_choices), choice_GridPane);
		}
		chk_category_selected = true;
		textField_nameQuestion.setText(question.getName());
		textArea_textQuestion.setText(question.getText());
		image_question = question.getImage();

		if (!image_question.isBlank()) {
//			File file = new File(image_question);
			String file_name = image_question;
			if (file_name.endsWith(".jpg") || file_name.endsWith(".png") || file_name.endsWith(".jpeg")) {
				Image image = new Image(image_question);
				view_Image.setImage(image);
				view_Image.setVisible(true);
				view_Video.setVisible(false);
				comboBox_choose.setValue("Image");
			} else if (file_name.endsWith(".mp4") || file_name.endsWith(".avi") || file_name.endsWith(".mov")
					|| file_name.endsWith(".mpg")) {
				Media media = new Media(image_question);
				MediaPlayer mp = new MediaPlayer(media);
				view_Media.setMediaPlayer(mp);
				mp.play();
				comboBox_choose.setValue("Video");
				view_Video.setVisible(true);
				view_Image.setVisible(false);
			} else if (file_name.endsWith(".gif")) {
				Image image = new Image(image_question);
				view_Image.setImage(image);
				view_Image.setVisible(true);
				view_Video.setVisible(false);
				comboBox_choose.setValue("GIF");
			}

		}
		question_previous = question;
		category_selected_previous = category;
	}

	@SuppressWarnings("static-access")
	public void button_saveChanges(ActionEvent event) {
				
		if (comboBox_category.getValue() == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("You have to choose a category!");
			alert.showAndWait();
			return;
		}
		category_selected = comboBox_category.getValue();
		if (textField_nameQuestion.getText().isBlank()) // kiểm tra chuỗi có kí tự hay trống
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Question name is blank!");
			alert.showAndWait();
			return;
		}
		if (textArea_textQuestion.getText().isBlank()) // kiểm tra chuỗi có kí tự hay trống
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Question text is blank!");
			alert.showAndWait();
			return;
		}
		double mark_value = 0;
		try {
			String mark_string = textField_mark.getText();
			mark_value = Double.parseDouble(mark_string);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Invalid mark value!");
			alert.setContentText("The value of mark must be a number!");
			alert.showAndWait();
			return;
		}
		String textQuestion = textArea_textQuestion.getText();
		String nameQuestion = textField_nameQuestion.getText();

		double sum_grade = 0.0;
		ObservableList<Choice> choices = FXCollections.observableArrayList();
		for (int i = 1; i <= numbersOfChoices; i++) {
			TextArea textArea = textArea_Choices.get(i - 1);
			ComboBox<Grade> grade = grade_Choices.get(i - 1);
			if (!textArea.getText().isBlank() || !image_Choices.get(i - 1).isBlank()) {
				String textChoice = textArea.getText();
				String image_path_choice = image_Choices.get(i - 1);
				Double grade_value = grade.getValue().get_Grade();
				Choice choice = new Choice(textChoice, image_path_choice, grade_value);
				sum_grade += grade_value;
				choices.add(choice);
			}
		}
		
		if (choices.size() < 2) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText("There must be at least 2 choices!");
			alert.show();
			return;
		}
		
		if (Math.round(sum_grade) != 100) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Invalid grade choice!");
			alert.setContentText("Sum of grades must be 100%!");
			alert.showAndWait();
			return;
		}
		Question question = new Question(nameQuestion, textQuestion, image_question, choices);
		question.setMark(mark_value);
		if (!tree.chk_create_edit)
			category_selected.addQuestion(question);
		if (tree.chk_create_edit) {
			question_previous.edit(question);
			if (category_selected_previous == null) {
				category_selected.addQuestion(question_previous);
			} else if (category_selected != category_selected_previous) {
				category_selected.addQuestion(question);
				ObservableList<Question> list_question = category_selected_previous.getQuestions();
				list_question.remove(question_previous);
			}
		}

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("SUCCESS");
		alert.setHeaderText("You have successfully saved your question!");
		alert.setContentText("Exit!");
		alert.showAndWait();
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
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void button_cancel(ActionEvent event) {
		if (tree.chk_create_edit) {
			if (!chk_category_selected) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION!");
				alert.setHeaderText("This question will not be saved!");
				alert.setContentText("You want to remove this question? ");
				ButtonType button_yes = new ButtonType("Yes");
				ButtonType button_no = new ButtonType("No");
				alert.getButtonTypes().setAll(button_yes, button_no);
				alert.showAndWait().ifPresent(response -> {
					if (response == button_yes) {
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
							e.printStackTrace();
						}
					} else if (response == button_no) {
						alert.close();
					}
				});

				return;
			}

			if (question_previous.getName().isBlank()) // kiểm tra chuỗi có kí tự hay trống
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("ERROR!");
				alert.setHeaderText("Question name is blank!");
				alert.showAndWait();
				return;
			}
			if (question_previous.getText().isBlank()) // kiểm tra chuỗi có kí tự hay trống
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("ERROR!");
				alert.setHeaderText("Question text is blank!");
				alert.showAndWait();
				return;
			}
		}

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
			e.printStackTrace();
		}
	}

	public void button_blanks_3_choices(ActionEvent event) throws IOException {
		for (int i = 1; i <= 3; i++) {
			createChoice();
		}
	}

	public void button_saveChanges_continueEdit(ActionEvent event) {
		if (comboBox_category.getValue() == null) {
			category_selected = null;
		} else {
			category_selected = comboBox_category.getValue();
			chk_category_selected = true;
		}
		double mark_value = 0;
		try {
			String mark_string = textField_mark.getText();
			mark_value = Double.parseDouble(mark_string);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Invalid mark value!");
			alert.setContentText("The value of mark must be a number!");
			alert.showAndWait();
		}

		String nameQuestion = textField_nameQuestion.getText();
		String textQuestion = textArea_textQuestion.getText();
		ObservableList<Choice> choices = FXCollections.observableArrayList();
		double sum_grade = 0.0;
		for (int i = 1; i <= numbersOfChoices; i++) {
			TextArea textArea = textArea_Choices.get(i - 1);
			ComboBox<Grade> grade = grade_Choices.get(i - 1);
			if (!textArea.getText().isBlank() || !image_Choices.get(i - 1).isBlank()) {
				String textChoice = textArea.getText();
				String image_path_choice = image_Choices.get(i - 1);
				Double grade_value = grade.getValue().get_Grade();
				Choice choice = new Choice(textChoice, image_path_choice, grade_value);
				sum_grade += grade_value;
				choices.add(choice);
			}
		}
		
		if (choices.size() < 2) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText("There must be at least 2 choices!");
			alert.show();
			return;
		}
		
		if (Math.round(sum_grade) != 100) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Invalid grade choice!");
			alert.setContentText("Sum of grades must be 100%!");
			alert.showAndWait();
			return;
		}
		Question question = new Question(nameQuestion, textQuestion, image_question, choices);
		question.setMark(mark_value);

		if (!tree.chk_create_edit) {
			label_title.setText("Editing a Multiple choice question");
			tree.chk_create_edit = true;
			if (chk_category_selected)
				category_selected.addQuestion(question);

			question_previous = question;

		} else {
			question_previous.edit(question);
			if (chk_category_selected) {
				if (category_selected_previous == null) {
					category_selected.addQuestion(question);
				} else if (category_selected != category_selected_previous) {
					category_selected.addQuestion(question);
					ObservableList<Question> list_question = category_selected_previous.getQuestions();
					list_question.remove(question_previous);
				}
			}
		}
		category_selected_previous = category_selected;

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("SUCCESS");
		alert.setHeaderText("You have successfully saved your question!");
		alert.setContentText("Continue editing");
		alert.showAndWait();
	}

	public void playMedia(MouseEvent e) {
		MediaPlayer mp = view_Media.getMediaPlayer();
		mp.play();
	}

	public void pauseMedia(MouseEvent e) {
		MediaPlayer mp = view_Media.getMediaPlayer();
		mp.pause();
	}

	public void againMedia(MouseEvent e) {
		MediaPlayer mp = view_Media.getMediaPlayer();
		mp.seek(Duration.ZERO);
		mp.play();
	}

	public void createGrade(ComboBox<Grade> grade) {
		ObservableList<Grade> list_grade = FXCollections.observableArrayList();
		Grade none = new Grade(0.0);
		list_grade.add(none);
		grade.setValue(none);
		for (int i = 100; i >= -100; i = i - 10) {
			if (i != 0)
				list_grade.add(new Grade((double) i));
			if (i == 90 || i == -80) {
				list_grade.add(new Grade((double) Math.abs(i) / i * 80.3333));
			}
			if (i == 80 || i == -70) {
				list_grade.add(new Grade((double) i / Math.abs(i) * 75));
			}
			if (i == 70 || i == -60) {
				list_grade.add(new Grade(i / Math.abs(i) * 66.6667));
			}
			if (i == 40 || i == -30) {
				list_grade.add(new Grade(i / Math.abs(i) * 33.3333));
			}
			if (i == 30 || i == -20) {
				list_grade.add(new Grade((double) i / Math.abs(i) * 25));
			}
			if (i == 20) {
				for (int j = 6; j <= 9; j++)
					list_grade.add(new Grade((double) 100 / j));
			}
			if (i == -10) {
				for (int j = 9; j >= 6; j--)
					list_grade.add(new Grade((double) -100 / j));
			}
			if (i == 10) {
				list_grade.add(new Grade((double) 5));
			}
			if (i == 0) {
				list_grade.add(new Grade((double) -5));
			}
		}
		grade.setItems(list_grade);
	}

}
