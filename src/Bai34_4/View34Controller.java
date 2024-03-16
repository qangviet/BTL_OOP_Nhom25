package Bai34_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Bai2_31.Choice;
import Bai2_31.Data;
import Bai2_31.Question;
import Bai2_31.tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class View34Controller implements Initializable {

	@FXML
	TextArea textArea;
	String filePath = "";
	String listDA;

//-----------------------------------------------------------------------------------------------------------------------------------

	public void chooseFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");

		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter("Text Files", "*.txt"),
				new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.gif")
				);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		File selectedFile = fileChooser.showOpenDialog(primaryStage);

		if (selectedFile != null) {
			filePath = selectedFile.getAbsolutePath();
			textArea.setText(filePath);
		}
		primaryStage.show();
	}

//-----------------------------------------------------------------------------------------------------------------------------------

	public void setOnDragOver(DragEvent event) {

		if (event.getGestureSource() != textArea && event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.COPY);
			textArea.setStyle("-fx-border-color: green; -fx-border-width: 2;");
		} else {
			textArea.setStyle("-fx-border-color: red; -fx-border-width: 2;");
		}
		event.consume();
	}

	public void setOnDragDroped(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		boolean success = false;

		if (dragboard.hasFiles()) {
			File file = dragboard.getFiles().get(0);
			filePath = file.getAbsolutePath();
			success = true;
		}

		textArea.setStyle("-fx-border-color: transparent;");
		event.setDropCompleted(success);
		event.consume();
	}

	public void setOnDragExited(DragEvent event) {
		textArea.setStyle("-fx-border-color: transparent;");
		event.consume();
		textArea.setText(filePath);
	}

//--------------------------------------------------------------------------------------------------------------

	public void importFile(ActionEvent e) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMATION!");

		int dotIndex = filePath.lastIndexOf(".");
		String fileExtension = filePath.substring(dotIndex + 1);
		if (filePath.equals("")) {
			alert.setHeaderText("No file imported!");
			alert.show();
		} else if (fileExtension.equals("txt")) {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			boolean isAikenFormat = true;
			int count = 0, kount = 0, check = 0;

			while ((line = reader.readLine()) != null) {

				while (line.trim().isEmpty()) {
					count++;
					line = reader.readLine();
				}

				if ((line = reader.readLine()) != null) {
					check = 1;
					kount = 0;
					count++;

					listDA = "";

					while (!line.startsWith("ANSWER:")) {

						count++;
						kount++;
						if (!line.startsWith("A. ") && !line.startsWith("B. ") && !line.startsWith("C. ")
								&& !line.startsWith("D. ") && !line.startsWith("E. ") && !line.startsWith("F. ")
								&& !line.startsWith("G. ") && !line.startsWith("H. ") && !line.startsWith("I. ")
								&& !line.startsWith("J. ") && !line.startsWith("K. ") && !line.startsWith("L. ")
								&& !line.startsWith("M. ") && !line.startsWith("N. ") && !line.startsWith("O. ")
								&& !line.startsWith("P. ") && !line.startsWith("Q. ") && !line.startsWith("R. ")
								&& !line.startsWith("S. ") && !line.startsWith("T. ") && !line.startsWith("U. ")
								&& !line.startsWith("V. ") && !line.startsWith("W. ") && !line.startsWith("X. ")
								&& !line.startsWith("Y. ") && !line.startsWith("Z. ")) {
							isAikenFormat = false;
							break;
						} else {
							if (line.length() < 4) {
								isAikenFormat = false;
								break;
							} else {
								if (line.charAt(3) == ' ') {
									isAikenFormat = false;
									break;
								}
							}

						}
						listDA = listDA + line.charAt(0);

						line = reader.readLine();

					}
				}

				if (kount < 2) {
					isAikenFormat = false;
				}
				if (line != null && isAikenFormat) {
					count++;

					String ans = line;

					if (ans.startsWith("ANSWER: ") && kount >= 2) {
						if (ans.length() == 9 && listDA.contains(ans.substring(8, 9))) {
						} else {
							isAikenFormat = false;
						}
					} else {
						isAikenFormat = false;
					}
				}

				if (isAikenFormat) {

					line = reader.readLine();
					if (line != null) {
						count++;
						if (!line.trim().isEmpty()) {
							isAikenFormat = false;
							break;
						}
					}
				} else {
					break;
				}
			}
			reader.close();

			if (check == 0) {
				alert.setHeaderText("Empty file!");
				alert.show();
			} else if (!isAikenFormat) {
				alert.setHeaderText("Error at line: " + count);
				alert.show();
			} else {
				BufferedReader reader1 = new BufferedReader(new FileReader(filePath));
				String line1;
				int ccc = 0;

				// Choice choice;
				String name;
				String text;

				while ((line1 = reader1.readLine()) != null) {
					while (line1.trim().isEmpty() && line1 != null) {
						line1 = reader1.readLine();
					}
					if (line1 != null) {
						ccc++;
						text = line1;
						if (line1.length() < 6) {
							name = line1;
						} else {
							name = line1.substring(0, 5);
						}

						ObservableList<Choice> choices = FXCollections.observableArrayList();
						int cout = 0;
						while ((line1 = reader1.readLine()) != null && !line1.startsWith("ANSWER:")) {
							Choice choice = new Choice();
							choice.setText(line1);
							choices.add(cout, choice);
							cout++;
						}

						String dapAn = line1.substring(8, 9);
						for (Choice choice1 : choices) {
							if (choice1.getText().startsWith(dapAn)) {
								choice1.setGrade(100.0);
							} else {
								choice1.setGrade(0.0);
							}
						}
						Question question = new Question(name, text, "", choices);
						Data.read_file.addQuestion(question);
						line1 = reader1.readLine();
					}
				}

				alert.setHeaderText("Success: " + ccc);

				alert.show();
			}

		} else {
			alert.setHeaderText("Wrong format!");
			alert.show();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 34;

	}
}