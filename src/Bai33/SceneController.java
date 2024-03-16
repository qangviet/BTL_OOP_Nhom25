package Bai33;

import java.net.URL;

import java.util.ResourceBundle;

import Bai2_31.Category;
import Bai2_31.tree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;

public class SceneController implements Initializable {

	@FXML
	private ComboBox<Category> choice_Category;
	@FXML
	private AnchorPane anchorpane1;

	@FXML
	private TextField nameTextField;
	@FXML
	private TextArea categoryInfoTextArea;
	@FXML
	private TextField idTextField;
	@FXML
	private Button addCategoryButton;
	static Category selected;
	private Category check = new Category("");
	static String name, id;
	static Category newCategory;

	public TreeView<Category> treeview = new TreeView<>();

	// private Category defaultCategory1;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 33;

		createComboBox_Category(choice_Category);

		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.trim().isEmpty()) {
				addCategoryButton.setDisable(true);
			} else {
				addCategoryButton.setDisable(false);
			}
		});
	}

	public void createComboBox_Category(ComboBox<Category> choice_Category) {
		choice_Category.setPromptText("Choose a category");
		choice_Category.setOnShown(e -> {
			TreeItem<Category> root = new TreeItem<Category>(tree.defaultCategory);
			tree.defaultCategory.setShowSub(false);
			tree.add_tree_item(tree.defaultCategory, root);
			Popup popup = new Popup();
			TreeView<Category> treeview = new TreeView<Category>();
			treeview.setRoot(root);
			treeview.setOnMouseClicked(event -> {
				TreeItem<Category> selectedItem = treeview.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					choice_Category.setValue(selectedItem.getValue());
					popup.hide();
					choice_Category.hide();
				}
			});
			popup.getContent().add(treeview);
			Bounds boundsInScreen = choice_Category.localToScreen(choice_Category.getBoundsInLocal());
			popup.show(choice_Category, boundsInScreen.getMinX(), boundsInScreen.getMaxY());
			popup.setAutoHide(true);
			popup.setOnAutoHide(event -> {
				if (popup.isShowing() && !popup.getScene().getRoot().getBoundsInParent()
						.contains(((MouseEvent) event).getX(), ((MouseEvent) event).getY())) {
					popup.hide();
				}
			});
		});
	}

	public void addCategory(ActionEvent event) {

		selected = choice_Category.getValue();

		name = nameTextField.getText();
		id = idTextField.getText();

		newCategory = new Category(id, name, selected);

		if (name.isEmpty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("WARNING!");
			alert.setHeaderText(null);
			alert.setContentText("Category name is blank!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("SUCCESS!");
			alert.setHeaderText(null);
			alert.setContentText("You have successfully added a new category!");
			alert.showAndWait();

		}

	}

}
