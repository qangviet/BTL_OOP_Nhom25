package Bai6;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ControllerScene6_3b implements Initializable {
	@FXML
	ComboBox<Category> comboBox_category;
	@FXML
	ComboBox<Integer> comboBox_randomques;
	@FXML
	Pagination pagination_page;
	@FXML
	CheckBox checkBox_show_sub;
	private ObservableList<Question> list_questions;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 62;

		createComboBox_Category(comboBox_category);
	}

	public void createComboBox_randomques(ObservableList<Question> lq) {

		ObservableList<Integer> ob = FXCollections
				.observableArrayList(IntStream.range(1, lq.size() + 1).boxed().collect(Collectors.toList()));
		comboBox_randomques.setItems(ob);

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
					Category category_selected = comboBox_category.getValue();
					if (checkBox_show_sub.isSelected()) {
						list_questions = category_selected.getQuestions_also_subCategory(category_selected);
					} else
						list_questions = category_selected.getQuestions();
					createComboBox_randomques(list_questions);
					pagination_page.setPageCount((list_questions.size() + 9) / 10);
					if (list_questions.size() > 0) {
						pagination_page.setPageCount((list_questions.size() + 9) / 10);
						pagination_page.setPageFactory(pageIndex -> createListViewPage(pageIndex, 10, list_questions));
					} else {
						pagination_page.setPageCount(1);
						pagination_page.setPageFactory(param -> null);
					}
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

	private ListView<Question> createListViewPage(int pageIndex, int itemsPerPage, ObservableList<Question> data) {
		int fromIndex = pageIndex * itemsPerPage;
		int toIndex = Math.min(fromIndex + itemsPerPage, data.size());
		ListView<Question> listView = new ListView<>(
				FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
		listView.setCellFactory(param -> new ListCell<>() {
			@Override
			protected void updateItem(Question item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null) {
					setGraphic(null);
				} else {
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("Column6_2.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					HBox hb = (HBox) root.lookup("#column_listview");
					Label lb = (Label) root.lookup("#label_infoQues");
					lb.setText(item.name_textProperty().get());
					setGraphic(hb);
				}
			}
		});
		listView.setFocusTraversable(false);
		listView.setSelectionModel(new NoSelectionModel<Question>());
		return listView;
	}

	@SuppressWarnings("static-access")
	public void button_addRandomQues(ActionEvent e) throws IOException {

		if (list_questions != null && comboBox_randomques.getValue() != null) {
			Collections.shuffle(list_questions);
			int numbers = comboBox_randomques.getValue();
			ObservableList<Question> random_list = FXCollections
					.observableArrayList(list_questions.subList(0, numbers));
			tree.quiz_selected.addlistQues(random_list);
		}

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
			pagination_page.setPageCount(1);
			pagination_page.setPageFactory(param -> null);
		}
	}
}
