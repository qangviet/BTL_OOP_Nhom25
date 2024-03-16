package Bai6;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import Bai2_31.Question;
import Bai2_31.tree;
import Bai7.SelectedChoices;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerScene6_2 implements Initializable {
	@FXML
	Label label_title;
	@FXML
	Label label_numberOfQues;
	@FXML
	Label label_marks;
	@FXML
	CheckBox checkbox_shuffle;
	@FXML
	ScrollPane scrollPane_warpTb;
	@FXML
	Label label_page;
	@FXML
	Button delete_questions;
	@FXML
	TextField maxGrade;
	@FXML
	AnchorPane anchorPane1;

	TableView<Question> table_questions = new TableView<>();
	ObservableList<Question> list_questions = tree.quiz_selected.getlistQues();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 62;
		
		if (tree.quiz_selected.getCheck_shuffle() == 1) {
			checkbox_shuffle.setSelected(true);
		} else {
			checkbox_shuffle.setSelected(false);
		}

		checkbox_shuffle.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				tree.quiz_selected.setCheck_shuffle(1);
			} else {
				tree.quiz_selected.setCheck_shuffle(0);
			}
		});
		
		double maxGradee1 = Double.parseDouble(maxGrade.getText());

		tree.quiz_selected.setGrade(maxGradee1);

		maxGrade.textProperty().addListener((observable, oldValue, newValue) -> {
			double maxGradee = Double.parseDouble(newValue);
			tree.quiz_selected.setGrade(maxGradee);
		});

		label_title.textProperty().bind(Bindings.concat("Editing quiz: ", tree.quiz_selected.getNameProperty()));

		label_numberOfQues.textProperty().bind(Bindings.createStringBinding(
				() -> "Questions: " + list_questions.size() + " | This quiz is open", list_questions));

		DoubleBinding sum_marks = Bindings.createDoubleBinding(() -> {
			double sum = 0.0;
			for (Question q : list_questions) {
				sum += q.getMark();
			}
			return sum;
		}, list_questions);

		label_marks.textProperty().bind(Bindings.concat("Total of marks: ", sum_marks));

		displayQuestion(list_questions);
		list_questions.addListener(new ListChangeListener<Question>() {

			@Override
			public void onChanged(Change<? extends Question> arg0) {
				if (list_questions.isEmpty()) {
					scrollPane_warpTb.setVisible(false);
				}
			}

		});
	}

	@SuppressWarnings({ "deprecation" })
	void displayQuestion(ObservableList<Question> listques) {
		table_questions.setItems(listques);
		table_questions.setId("myTable");
		TableColumn<Question, Integer> column1 = new TableColumn<>();
		column1.setCellFactory(new Callback<TableColumn<Question, Integer>, TableCell<Question, Integer>>() {
			@Override
			public TableCell<Question, Integer> call(TableColumn<Question, Integer> arg0) {
				return new TableCell<>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							Parent root = null;
							try {
								root = FXMLLoader.load(getClass().getResource("/Bai6/Column6_2.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							HBox hb = (HBox) root.lookup("#column1");
							TextField tf = (TextField) root.lookup("#textField_STT");
							tf.setText(this.getTableRow().getIndex() + 1 + "");
							setGraphic(hb);

						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		TableColumn<Question, String> column2 = new TableColumn<>();
		column2.setCellValueFactory(celldata -> celldata.getValue().name_textProperty());
//
		TableColumn<Question, String> column3 = new TableColumn<>();
		column3.setCellFactory(new Callback<TableColumn<Question, String>, TableCell<Question, String>>() {

			@Override
			public TableCell<Question, String> call(TableColumn<Question, String> arg0) {
				return new TableCell<>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							Parent root = null;
							try {
								root = FXMLLoader.load(getClass().getResource("/Bai6/Column6_2.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							HBox hb = (HBox) root.lookup("#column2");
							TextField tf = (TextField) root.lookup("#textField_mark");
							Node delete_ques = root.lookup("#delete_ques");
							Question q = this.getTableRow().getItem();
							delete_ques.setOnMouseClicked(new EventHandler<Event>() {

								@Override
								public void handle(Event arg0) {
									Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
									confirm.setTitle("Confirmation Dialog");
									confirm.setHeaderText("Are you sure you want to delete this question?");
									confirm.setContentText("This action cannot be undone!");
									ButtonType button_yes = new ButtonType("Yes");
									ButtonType button_no = new ButtonType("No");

									confirm.getButtonTypes().setAll(button_yes, button_no);
									confirm.showAndWait().ifPresent(response -> {
										if (response == button_yes) {
											listques.remove(getIndex());
										} else {
											confirm.close();
										}
									});

								}
							});
							tf.setText((int) q.getMark() + ".00");
							setGraphic(hb);

						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		column1.setPrefWidth(130);
		column2.setPrefWidth(800);

		table_questions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table_questions.getColumns().add(column1);
		table_questions.getColumns().add(column2);
		table_questions.getColumns().add(column3);
		table_questions.getStylesheets().add("/Bai6/table_view.css");

		table_questions.setRowFactory(new Callback<TableView<Question>, TableRow<Question>>() {
			@Override
			public TableRow<Question> call(TableView<Question> arg0) {
				final TableRow<Question> row = new TableRow<>();

				row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						int index = row.getIndex();
						if (index >= 0 && index < table_questions.getItems().size()) {
							if (table_questions.getSelectionModel().isSelected(index)) {
								table_questions.getSelectionModel().clearSelection(index);
							} else {
								table_questions.getSelectionModel().select(index);
							}
							event.consume();
						}
					}
				});
				return row;
			}
		});
//		table_questions.setSelectionModel()
		scrollPane_warpTb.setContent(table_questions);
		scrollPane_warpTb.setVisible(true);
	}

	@SuppressWarnings("static-access")
	public void button_from_question_bank(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bai6/Scene6_3a.fxml"));
		Parent root = (Parent) loader.load();

		anchorPane1.getChildren().setAll(root);
		anchorPane1.setBottomAnchor(root, (double) 0);
		anchorPane1.setLeftAnchor(root, (double) 0);
		anchorPane1.setTopAnchor(root, (double) 0);
		anchorPane1.setRightAnchor(root, (double) 0);

	}

	@SuppressWarnings("static-access")
	public void button_save(ActionEvent e) throws IOException {

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
			for (int i = 0; i < q.getChoices().size(); i++) {
				q.getChoices().get(i).setStt(i);
			}
		}
		
		tree.checkScene = 61;

		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

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

	}

	@SuppressWarnings("static-access")
	public void button_random_a_question(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bai6/Scene6_3b.fxml"));
		Parent root = (Parent) loader.load();

		anchorPane1.getChildren().setAll(root);
		anchorPane1.setBottomAnchor(root, (double) 0);
		anchorPane1.setLeftAnchor(root, (double) 0);
		anchorPane1.setTopAnchor(root, (double) 0);
		anchorPane1.setRightAnchor(root, (double) 0);
	}

	public void button_select_multiple(ActionEvent e) {

		table_questions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table_questions.getSelectionModel().selectAll();
		delete_questions.setVisible(true);

	}

	public void button_delete_questions(ActionEvent e) {
		ObservableList<Question> ques_seleted = table_questions.getSelectionModel().getSelectedItems();
		Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
		confirm.setTitle("Confirmation");
		confirm.setHeaderText("Are you sure you want to delete " + ques_seleted.size() + " questions?");
		confirm.setContentText("This action cannot be undone!");
		ButtonType button_yes = new ButtonType("Yes");
		ButtonType button_no = new ButtonType("No");

		confirm.getButtonTypes().setAll(button_yes, button_no);
		confirm.showAndWait().ifPresent(response -> {
			if (response == button_yes) {
				list_questions.removeAll(ques_seleted);
			} else {
				confirm.close();
			}
		});
		table_questions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		table_questions.getSelectionModel().clearSelection();
		delete_questions.setVisible(false);
	}
}
