package Bai2_31;

import java.io.IOException;

import java.net.URL;
//import java.util.Locale.Category;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

public class QuestionController implements Initializable {

	@FXML
	private AnchorPane anchor;
	@FXML
	private AnchorPane anchorPane1;
	@FXML
	private AnchorPane anchorPane2;
	@FXML
	private AnchorPane anchorPane3;

	@FXML
	public TabPane tabPane;
	@FXML
	public Tab categoryTab;
	@FXML
	Tab importTab;
	@FXML
	Tab exportTab;
	@FXML
	VBox mainLayout;
	@FXML
	ScrollPane scrollPane;
	@FXML
	public ComboBox<Category> comboBox_category;
	@FXML
	CheckBox checkBox_show_sub;
	public static Question question_selected_edit;
	public static Category category_selected_edit;
	public static int pos_when_edit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 31;

		createComboBox_Category(comboBox_category);
		tabPane.setId("myTabPane");

	}

	public void createComboBox_Category(ComboBox<Category> choice_cate) {
		choice_cate.setPromptText("Choose a category");
		choice_cate.setOnShown(e -> {
			TreeItem<Category> root = new TreeItem<>(tree.defaultCategory);
			if (checkBox_show_sub.isSelected())
				tree.defaultCategory.setShowSub(true);
			else
				tree.defaultCategory.setShowSub(false);
			add_tree_item(tree.defaultCategory, root);
			Popup popup = new Popup();
			TreeView<Category> treeview = new TreeView<>();
			treeview.setRoot(root);
			treeview.setOnMouseClicked(event -> {
				TreeItem<Category> selectedItem = treeview.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					choice_cate.setValue(selectedItem.getValue());
					popup.hide();
					choice_cate.hide();
					displayQuestion(selectedItem.getValue());
				}
			});
			popup.getContent().add(treeview);
			popup.show(choice_cate, choice_cate.getLayoutX(), choice_cate.getLayoutY() + choice_cate.getHeight() + 85);
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

	@SuppressWarnings("deprecation")
	public void displayQuestion(Category category) {
		ObservableList<Question> data;
		if (category.getShowSub())
			data = category.getQuestions_also_subCategory(category);
		else
			data = category.getQuestions();
		TableView<Question> table_Questions = new TableView<>(data);

		// Create column Question
		TableColumn<Question, String> column_1 = new TableColumn<>();

		Label lb1 = new Label("Question");
		Label lb2 = new Label("Question name / ID number");
		lb2.getStyleClass().add("small-label");
		VBox nameQuestions_Col = new VBox(lb1, lb2);

		column_1.setGraphic(nameQuestions_Col);
		column_1.setCellValueFactory(cellData -> cellData.getValue().name_textProperty());

		// Create column Action
		TableColumn<Question, Void> column_2 = new TableColumn<>("Action");

		column_2.setCellFactory(new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {

			@SuppressWarnings("static-access")
			@Override
			public TableCell<Question, Void> call(TableColumn<Question, Void> arg0) {
				
				TableCell<Question, Void> cell = new TableCell<>() {
					final Button button = new Button("Edit");
					{
						button.setOnAction(e -> {
							tree.chk_create_edit = true;
							question_selected_edit = getTableView().getItems().get(getIndex());
							category_selected_edit = find_Category(category, question_selected_edit);
							try {

								tree.checkScene = 322;

								Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
								Parent root = loader.load();

								FXMLLoader loader1 = new FXMLLoader();
								loader1.setLocation(getClass().getResource("/Bai32/Scene3_2.fxml"));
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

								V0.getChildren().addAll(p1, p2);

								V0.setVgrow(p2, Priority.ALWAYS);

								Scene scene = new Scene(V0, tree.stageWidth, tree.stageHeight);
								primaryStage.setScene(scene);

							} catch (Exception e2) {

							}
						});

						button.setPickOnBounds(true);
						button.setCursor(Cursor.HAND);
					}

					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(button);
							button.setStyle("-fx-text-fill: blue; -fx-background-color: transparent");
						}
					}
				};
				return cell;
			}
		});
		table_Questions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table_Questions.getColumns().add(column_1);
		table_Questions.getColumns().add(column_2);
		column_1.setPrefWidth(1250);
		column_1.setResizable(false);
		column_2.setStyle("-fx-alignment: center");
		column_2.setPrefWidth(100);
		column_2.setResizable(false);
		table_Questions.getStyleClass().add("table-cell");

		scrollPane.setContent(table_Questions);
		scrollPane.setFitToWidth(true);
	}

	public void add_tree_item(Category category, TreeItem<Category> root) {
		ObservableList<Category> sub_cate = category.getSub_Category();
		if (sub_cate.size() == 0)
			return;
		root.setExpanded(true);
		for (Category cate : sub_cate) {
			if (checkBox_show_sub.isSelected())
				cate.setShowSub(true);
			else
				cate.setShowSub(false);
			TreeItem<Category> branch = new TreeItem<Category>(cate);
			root.getChildren().add(branch);
			add_tree_item(cate, branch);
		}
	}

	public void show_sub(ActionEvent e) {
		if (comboBox_category.getValue() != null) {
			comboBox_category.setValue(null);
			scrollPane.setContent(null);
		}
	}

	public Category find_Category(Category c, Question q) {
		if (c.getQuestions().contains(q)) {
			return c;
		} else if (c.getSub_Category().size() == 0)
			return null;
		Category res = null;
		for (Category cate : c.getSub_Category()) {
			res = find_Category(cate, q);
			if (res != null)
				return res;
		}
		return res;
	}

	public void goQuess(Event event) throws IOException {

		tree.checkTabb.setText("4");

	}

	@SuppressWarnings("static-access")
	public void goCategoryy(Event event) throws IOException {

		tree.checkTabb.setText("1");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bai33/scene33.fxml"));
		Parent root = (Parent) loader.load();
		anchorPane1.getChildren().setAll(root);
		anchorPane1.setBottomAnchor(root, (double) 0);
		anchorPane1.setLeftAnchor(root, (double) 0);
		anchorPane1.setTopAnchor(root, (double) 0);
		anchorPane1.setRightAnchor(root, (double) 0);
	}

	@SuppressWarnings("static-access")
	public void goImportt(Event event) throws IOException {

		tree.checkTabb.setText("2");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bai34_4/View34.fxml"));
		Parent root = (Parent) loader.load();
		anchorPane2.getChildren().setAll(root);
		anchorPane2.setBottomAnchor(root, (double) 0);
		anchorPane2.setLeftAnchor(root, (double) 0);
		anchorPane2.setTopAnchor(root, (double) 0);
		anchorPane2.setRightAnchor(root, (double) 0);
	}

	public void goExportt(Event event) throws IOException {

		tree.checkTabb.setText("3");

	}

	@SuppressWarnings("static-access")
	public void goGUI32(ActionEvent event) throws IOException {

		tree.chk_create_edit = false;

		tree.checkScene = 321;

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
		Parent root = loader.load();

		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("/Bai32/Scene3_2.fxml"));
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

		V0.getChildren().addAll(p1, p2);

		Scene scene = new Scene(V0, tree.stageWidth, tree.stageHeight);
		primaryStage.setScene(scene);
	}

	public int changeCategoryTab() {
		tabPane.getSelectionModel().select(categoryTab);
		return 1;
	}

	public int changeImportTab() {
		tabPane.getSelectionModel().select(importTab);
		return 1;
	}

	public int changeExportTab() {
		tabPane.getSelectionModel().select(exportTab);
		return 1;
	}
}
