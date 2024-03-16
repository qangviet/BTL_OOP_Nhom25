package Bai6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Bai2_31.Category;
import Bai2_31.Question;
import Bai2_31.tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerScene6_3a implements Initializable {

	@FXML
	ComboBox<Category> comboBox_category;
	@FXML
	ScrollPane scrollPane;
	@FXML
	VBox vbox_mainlayout;
	@FXML
	CheckBox checkBox_show_sub;

	public static ObservableList<Question> list_quesSelected;
	ObservableList<Question> listques;
	CheckBox selectAll = new CheckBox("All");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 62;

		// TODO Auto-generated method stub
		createComboBox_Category(comboBox_category);
		list_quesSelected = FXCollections.observableArrayList();
	}

	public void createComboBox_Category(ComboBox<Category> choice_cate) {
		choice_cate.setPromptText("Choose a category");
		choice_cate.setOnShown(e -> {
			TreeItem<Category> root = new TreeItem<>(tree.defaultCategory);
			// TODO
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
			TreeItem<Category> branch = new TreeItem<>(cate);
			root.getChildren().add(branch);
			add_tree_item(cate, branch);
		}
	}

	@SuppressWarnings("deprecation")
	public void displayQuestion(Category category) {
		if (category.getShowSub())
			listques = category.getQuestions_also_subCategory(category);
		else
			listques = category.getQuestions();
		TableView<Question> table_Questions = new TableView<>(listques);
		TableColumn<Question, Boolean> checkBox_column = new TableColumn<>();
		selectAll.setFocusTraversable(false);
		checkBox_column.setGraphic(selectAll);

		checkBox_column.setCellFactory(new Callback<TableColumn<Question, Boolean>, TableCell<Question, Boolean>>() {

			@Override
			public TableCell<Question, Boolean> call(TableColumn<Question, Boolean> arg0) {
				return new TableCell<>() {
					@Override
					protected void updateItem(Boolean item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							Parent root = null;
							try {
								root = FXMLLoader.load(getClass().getResource("/Bai6/Column6_2.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							HBox hb = (HBox) root.lookup("#column3");
							CheckBox cb = (CheckBox) root.lookup("#checkBox");
							cb.selectedProperty().bindBidirectional(this.getTableRow().getItem().getSelectedProperty());
							setGraphic(hb);
//		                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		TableColumn<Question, String> text_column = new TableColumn<>("Question");
		text_column.setCellValueFactory(celldata -> celldata.getValue().name_textProperty());
		text_column.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setGraphic(null);
				} else {
					setText(item);
					this.setStyle("-fx-font-size: 13");
					setAlignment(Pos.CENTER_LEFT);
				}
			}
		});
		table_Questions.getColumns().add(checkBox_column);
		table_Questions.getColumns().add(text_column);

		table_Questions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		table_Questions.setEditable(true);

		selectAll.selectedProperty().addListener((observable, oldValue, newValue) -> {

			for (Question item : table_Questions.getItems()) {
				item.setSelected(newValue);
			}
		});
		table_Questions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		checkBox_column.setMinWidth(100);
		checkBox_column.setMaxWidth(120);

		table_Questions.setSelectionModel(null);
		table_Questions.setFocusTraversable(true);

		scrollPane.setContent(table_Questions);
	}

	@SuppressWarnings("static-access")
	public void button_add_selected_questions(ActionEvent e) throws IOException {
		if (listques != null) {
			for (Question q : listques) {
				if (q.getSelected()) {
					list_quesSelected.add(q);
					q.setSelected(false);
				}
			}
		}
		tree.quiz_selected.addlistQues(list_quesSelected);

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

	public void show_sub(ActionEvent e) {
		if (comboBox_category.getValue() != null) {
			comboBox_category.setValue(null);
			scrollPane.setContent(null);
		}
	}
}
