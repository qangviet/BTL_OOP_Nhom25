package Bai7;

import com.aspose.pdf.CryptoAlgorithm;
import com.aspose.pdf.Document;
import com.aspose.pdf.facades.DocumentPrivilege;

import Bai2_31.Question;
import Bai2_31.tree;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PasswordController {

	@FXML
	TextField user_password;
	@FXML
	TextField owner_password;

	ObservableList<Question> listquestion = tree.quiz_selected.getlistQues();

	public void create_password(ActionEvent event) {

		String userPassword = user_password.getText();
		String ownerPassword = owner_password.getText();

		try {

			DocumentPrivilege privilege = DocumentPrivilege.getForbidAll();

			Document document = new Document(tree.quiz_selected.getQuizPath());

			document.encrypt(userPassword, ownerPassword, privilege, CryptoAlgorithm.RC4x128, false);

			document.save(tree.quiz_selected.getQuizPath());
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("SUCCESS!");
			alert.setHeaderText("You have successfully created a PDF file");

			alert.showAndWait();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void Cancel(ActionEvent event) {

		try {

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
