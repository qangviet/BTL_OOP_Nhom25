package Bai1;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Scene12Controller implements Initializable {

	@FXML
	private AnchorPane anchorPane;

	public void goQuestions(ActionEvent event) throws IOException {
		
		Scene0Controller.Question.fire();
		
	}

	public void goCategory(ActionEvent event) throws IOException {
		
		Scene0Controller.Cate.fire();
		
	}

	public void goImport(ActionEvent event) throws IOException {
	
		Scene0Controller.Import.fire();
		
	}

	public void goExport(ActionEvent event) throws IOException {
		
		Scene0Controller.Export.fire();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
