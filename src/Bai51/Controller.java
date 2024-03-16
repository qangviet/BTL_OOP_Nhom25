package Bai51;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import Bai2_31.tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements Initializable {
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField timelimitTextField;
	@FXML
	private TextArea descriptionTextArea;
	@FXML
	private CheckBox checkDescription;
	@FXML
	private CheckBox checkTimeLimit;
	@FXML
	private DatePicker dateOpen;
	@FXML
	private DatePicker dateClose;

	@FXML
	private Spinner<Integer> hourOpen;
	@FXML
	private Spinner<Integer> hourClose;
	@FXML
	private Spinner<Integer> minuteOpen;
	@FXML
	private Spinner<Integer> minuteClose;
	@FXML
	private Spinner<String> timelimitSpinner;

	private ListView<String> timeListView;
	private Quiz quiz = new Quiz();

	private int checkLim = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		timeListView = new ListView<>();

		hourOpen.setEditable(true);
		minuteOpen.setEditable(true);
		hourClose.setEditable(true);
		minuteClose.setEditable(true);

		// gán giá trị cho spinner hour, minute
		SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
		hourOpen.setValueFactory(valueFactory1);

		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
		hourClose.setValueFactory(valueFactory2);

		SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
		minuteOpen.setValueFactory(valueFactory3);

		SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
		minuteClose.setValueFactory(valueFactory4);

	}

	public void enabletimelimit(ActionEvent e) {
		if (checkTimeLimit.isSelected()) {
			timelimitTextField.setDisable(false);
			timelimitSpinner.setDisable(false);

			// tạo liststring cho timelimitspinner

			ObservableList<String> items = FXCollections.observableArrayList("hours", "minutes", "seconds");
			timeListView.setItems(items);

			// tạo các giá trị cho timelimitSpinner

			SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(
					timeListView.getItems());
			timelimitSpinner.setValueFactory(valueFactory);

			timelimitTextField.setText("1");

			checkLim = 1;

		}
		if (!checkTimeLimit.isSelected()) {
			timelimitTextField.setDisable(true);
			timelimitSpinner.setDisable(true);

			ObservableList<String> items = FXCollections.observableArrayList("");
			timeListView.setItems(items);

			// tạo các giá trị cho timelimitSpinner

			SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(
					timeListView.getItems());
			timelimitSpinner.setValueFactory(valueFactory);
			timelimitTextField.setText("");
			checkLim = 0;
		}

	}

	@SuppressWarnings("static-access")
	public void Create(ActionEvent event) throws IOException {
		// lấy dữ liệu cho lớp quiz

		// thuộc tính Name:
		String name = nameTextField.getText();

		// thuộc tính description
		String description = descriptionTextArea.getText();

		// thuộc tính timeOpen:
		ObservableList<Integer> timeOpen = FXCollections.observableArrayList();

		LocalDate date1 = dateOpen.getValue(); // lấy giá trị từ datepicker
		if (date1 != null) {
			timeOpen.addAll(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth());
		} else
			timeOpen.addAll(2003, 1, 1);

		if (hourOpen != null) {
			timeOpen.add(hourOpen.getValue());
		} else {
			timeOpen.add(0);
		}
		if (minuteOpen != null) {
			timeOpen.add(minuteOpen.getValue());
		} else {
			timeOpen.add(0);
		}

		// thuộc tính timeClose:
		ObservableList<Integer> timeClose = FXCollections.observableArrayList();

		LocalDate date2 = dateClose.getValue(); // lấy giá trị từ datepicker
		if (date1 != null) {
			timeClose.addAll(date2.getYear(), date2.getMonthValue(), date2.getDayOfMonth());
		} else
			timeClose.addAll(2103, 1, 1);

		if (hourClose != null) {
			timeClose.add(hourClose.getValue());
		} else {
			timeClose.add(0);
		}
		if (minuteClose != null) {
			timeClose.add(minuteClose.getValue());
		} else {
			timeClose.add(0);
		}

		LocalDateTime quizOpen = LocalDateTime.of(timeOpen.get(0), timeOpen.get(1), timeOpen.get(2), timeOpen.get(3),
				timeOpen.get(4), 0);

		LocalDateTime quizClose = LocalDateTime.of(timeClose.get(0), timeClose.get(1), timeClose.get(2),
				timeClose.get(3), timeClose.get(4), 0);

		// thuộc tính timeLimit:

		ObservableList<String> timeLimit = FXCollections.observableArrayList();

		if (checkLim == 1) {
			timeLimit.add(timelimitTextField.getText());
			timeLimit.add(timelimitSpinner.getValue().toString());
		} else {
			timeLimit.add("-1");
			timeLimit.add("");
		}

		if (name.isBlank()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!");
			alert.setHeaderText("Quiz name is blank!");
			alert.show();
		} else if (timelimitTextField.getText().isBlank() && checkLim == 1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!");
			alert.setHeaderText("Time limit is not set!");
			alert.show();
		} else if (quizOpen.isAfter(quizClose)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!");
			alert.setHeaderText("Invalid opening and closing time!");
			alert.show();
		} else if (checkLim == 1) {
			try {
				int number = Integer.parseInt(timelimitTextField.getText());
				if (number < 0) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("WARNING!");
					alert.setHeaderText("Time limit value must be a positive number!");
					alert.show();
				} else {
					quiz.setQuiz(name, description, timeOpen, timeClose, timeLimit);
					// quiz.setdisplayDesciption(0);
					listquiz.listQuiz.add(quiz);

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
				}

			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING!");
				alert.setHeaderText("Invalid time limit!");
				alert.show();
			}
		} else {
			quiz.setQuiz(name, description, timeOpen, timeClose, timeLimit);
			// quiz.setdisplayDesciption(0);
			listquiz.listQuiz.add(quiz);

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
		}
	}

	@SuppressWarnings("static-access")
	public void Cancel(ActionEvent e) throws IOException {

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

	public void DisplayDes(ActionEvent e) {
		if (checkDescription.isSelected()) {
			quiz.setdisplayDesciption(1);
		} else {
			quiz.setdisplayDesciption(0);
		}
	}

}
