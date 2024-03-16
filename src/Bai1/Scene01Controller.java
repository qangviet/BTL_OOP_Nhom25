package Bai1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Bai2_31.tree;
import Bai51.Quiz;
import Bai51.listquiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scene01Controller implements Initializable {

	public static Quiz quiz1;
	@FXML
	private VBox quizBox;

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (listquiz.listQuiz.size() > 0) {
			for (Quiz k : listquiz.listQuiz) {
				quiz1 = k;
				
				int q = listquiz.listQuiz.indexOf(k);
				k.setSttQuiz(q);
				
				Hyperlink hyperlink = new Hyperlink(k.getName());
				hyperlink.setStyle("-fx-font-size: 16px;");

				HBox newQuiz = new HBox();
				newQuiz.setPrefHeight(50);
				newQuiz.setPrefWidth(342);

				ImageView imgView = new ImageView();
				imgView.setFitWidth(22);
				imgView.setFitHeight(25);

				Image img = new Image("/check-mark.png");
				imgView.setImage(img);

				newQuiz.getChildren().addAll(imgView, hyperlink);

				quizBox.getChildren().add(newQuiz);

				hyperlink.setOnAction(event -> {
					
					if (tree.checkDangThi == k.getSttQuiz() || tree.checkDangThi == -1) {
					
					try {
						
						tree.quiz_selected = k;

						tree.checkScene = 61;

						Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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

					} catch (IOException e) {
						e.printStackTrace();
					}
					} else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("WARNING!");
						alert.setHeaderText("Unsubmitted quiz must be finished: " + listquiz.listQuiz.get(tree.checkDangThi).getName());
						alert.show();
					}
				});
			}
		}
	}

}
