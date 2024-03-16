package Bai1;

import java.io.File;

import Bai2_31.tree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		try {

			tree.checkScene = 0;

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

			V0.getChildren().addAll(p1, p2);

			V0.setVgrow(p2, Priority.ALWAYS);

			Scene scene = new Scene(V0, tree.stageWidth, tree.stageHeight);
			primaryStage.setScene(scene);

			Image icon = new Image("hust.png");
			primaryStage.getIcons().add(icon);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
		// Xóa file .jar trong bin cho đỡ nặng
		String projectFolderDirectory = System.getProperty("user.dir");
		String filePath = projectFolderDirectory + File.separator + "bin" + File.separator + "lib" + File.separator
				+ "aspose-pdf-23.1.jar";
		// System.out.println(filePath);
		File fileToDelete = new File(filePath);
		fileToDelete.delete();

	}
}
