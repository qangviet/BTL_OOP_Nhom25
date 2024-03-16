package Bai7;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.aspose.pdf.Color;
import com.aspose.pdf.Document;
import com.aspose.pdf.FontRepository;
import com.aspose.pdf.HorizontalAlignment;
import com.aspose.pdf.Matrix;
import com.aspose.pdf.Page;
import com.aspose.pdf.Rectangle;
import com.aspose.pdf.TextFragment;
import com.aspose.pdf.TextFragmentAbsorber;
import com.aspose.pdf.TextFragmentCollection;
import com.aspose.pdf.XImage;
import com.aspose.pdf.drawing.Graph;
import com.aspose.pdf.operators.ConcatenateMatrix;
import com.aspose.pdf.operators.Do;
import com.aspose.pdf.operators.GRestore;
import com.aspose.pdf.operators.GSave;

import Bai2_31.Choice;
import Bai2_31.Question;
import Bai2_31.tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller71 implements Initializable {

	@FXML
	Button create_password;
	@FXML
	Label lb1;
	@FXML
	AnchorPane anchor;

	ObservableList<Question> listquestion = FXCollections.observableArrayList(tree.quiz_selected.getlistQues());

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tree.checkScene = 71;

		// Nếu ko ấn ra ngoài
		if (tree.quiz_selected.getTime().equals("")) {
			// Nếu có time limit
			if (!tree.quiz_selected.getTimeLimit().get(0).equals("-1")) {
				lb1.setText("Your attempt will have a time limit of " + tree.quiz_selected.getTimeLimit().get(0) + " "
						+ tree.quiz_selected.getTimeLimit().get(1)
						+ ". When you start, the timer will begin to count down and cannot be paused. You must finish your attempt before it expires. Are you sure you wish to start now?");
				lb1.setWrapText(true);
				// Nếu ko có time limit
			} else {
				lb1.setText("Your attempt will have no time limit. Are you sure you wish to start now?");
				lb1.setWrapText(true);
			}
			// Nếu đã ấn ra ngoài rồi quay lại thi
		} else {
			lb1.setText("Your attempt was paused. Are you sure you wish to continue?");
			lb1.setWrapText(true);
		}

	}

	public void export(ActionEvent event) throws Exception {

		if (tree.checkDangThi != -1) {
			
			Alert alt = new Alert(AlertType.WARNING);
			alt.setTitle("WARNING!");
			alt.setHeaderText("You must finish this attempt first!");
			alt.show();
			
		} else {

			Stage primaryStage = new Stage();

			FileChooser fileChooser = new FileChooser();

			// Đặt tên cho file mặc định khi lưu
			fileChooser.setInitialFileName(tree.quiz_selected.getName());

			// Chọn đường dẫn mặc định khi lưu
			File initialDirectory = new File("C:");
			fileChooser.setInitialDirectory(initialDirectory);

			// Hiển thị hộp thoại Lưu file
			File file = fileChooser.showSaveDialog(primaryStage);
			tree.quiz_selected.setQuizPath(file.getAbsolutePath() + ".pdf");

			// Export
			String id_image_ques = new String("image_question");
			String id_image_choice = new String("image_choice");
			String Quizname = tree.quiz_selected.getName();
			HashMap<String, Integer> image_ques = new HashMap<>();

			HashMap<String, ArrayList<Integer>> image_choice = new HashMap<>();

			Document document = new Document();

			Page page = document.getPages().add();

			// Shuffle list câu hỏi
			FXCollections.shuffle(listquestion);

			TextFragment name = new TextFragment(Quizname);
			name.getTextState().setFontSize(20);
			name.getTextState().setForegroundColor(Color.getBlack());
			name.getTextState().setFont(FontRepository.findFont("Arial"));
			name.setHorizontalAlignment(HorizontalAlignment.Center);
			page.getParagraphs().add(name);
			TextFragment tab = new TextFragment("");
			tab.getTextState().setFontSize(30);
			page.getParagraphs().add(tab);

			for (Question q : listquestion) {

				TextFragment textFragment = new TextFragment(q.getText());
				textFragment.getTextState().setFontSize(12);
				textFragment.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
				page.getParagraphs().add(textFragment);

				if (!q.getImage().isBlank()) {

					if (!q.getImage().endsWith(".mp4") && !q.getImage().endsWith(".mov")
							&& !q.getImage().endsWith(".avi") && !q.getImage().endsWith(".mpg")
							&& !q.getImage().endsWith(".gif")) {

						Graph graph = new Graph(500, 120);
						page.getParagraphs().add(graph);
						String id = id_image_ques + listquestion.indexOf(q);

						TextFragment tf = new TextFragment("    " + id);
						tf.getTextState().setFontSize(5);
						page.getParagraphs().add(tf);

						image_ques.put(id, listquestion.indexOf(q));

						page.getParagraphs().add(new Graph(0, 15));
					}
				}

				ObservableList<Choice> listchoice = q.getChoices();

				TextFragment textFragment2 = new TextFragment("");

				for (int i = 0; i < q.getChoices().size(); i++) {
					Choice c = q.getChoices().get(i);
					TextFragment textFragment1;
					if (!c.getText().isBlank()) {
						textFragment1 = new TextFragment("   " + c.getText());
					} else {
						textFragment1 = new TextFragment("   " + (char) (i + 65) + ".");
					}
					textFragment1.getTextState().setLineSpacing(2);
					textFragment1.getTextState().setFontSize(12);
					textFragment1.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
					page.getParagraphs().add(textFragment1);

					if (!c.getImage().isBlank()) {

						if (!c.getImage().endsWith(".mp4") && !c.getImage().endsWith(".mov")
								&& !c.getImage().endsWith(".avi") && !c.getImage().endsWith(".mpg")) {

							Graph graph = new Graph(500, 80);
							page.getParagraphs().add(graph);
							String id = id_image_choice + listquestion.indexOf(q) + i;

							TextFragment tf = new TextFragment("   " + id);
							tf.getTextState().setFontSize(5);
							page.getParagraphs().add(tf);

							ArrayList<Integer> al = new ArrayList<>();
							al.add(listquestion.indexOf(q));
							al.add(i);

							image_choice.put(id, al);

							page.getParagraphs().add(new Graph(0, 10));

						}
					}

				}
				page.getParagraphs().add(textFragment2);

			}
			document.save(tree.quiz_selected.getQuizPath());

			Document pdfDocument = new Document(tree.quiz_selected.getQuizPath());

			for (String str : image_ques.keySet()) {
				// Create TextAbsorber object to find all instances of the input search phrase
				TextFragmentAbsorber textFragmentAbsorber = new TextFragmentAbsorber(str);

				// Accept the absorber for all the pages
				pdfDocument.getPages().accept(textFragmentAbsorber);

				// Get the extracted text fragments into collection
				TextFragmentCollection textFragmentCollection = textFragmentAbsorber.getTextFragments();

				for (TextFragment textFragment : textFragmentCollection) {
					// Set coordinates
					Question ques = listquestion.get(image_ques.get(str));

					String i_path = ques.getImage();

					Image image = new Image(i_path);
					double i_ratio = image.getWidth() / image.getHeight();

					int x = (int) textFragment.getBaselinePosition().getXIndent();

					int y = (int) textFragment.getBaselinePosition().getYIndent() - 5;

					int x_upper = x + (int) (125.0 * i_ratio);

					if (x_upper < 150)
						x_upper = 150;

					int y_upper = y + 125;

					add_image_toPage(x, y, x_upper, y_upper, textFragment.getPage(), i_path);

				}
			}
			for (String str : image_choice.keySet()) {
				// Create TextAbsorber object to find all instances of the input search phrase
				TextFragmentAbsorber textFragmentAbsorber = new TextFragmentAbsorber(str);

				// Accept the absorber for all the pages
				pdfDocument.getPages().accept(textFragmentAbsorber);

				// Get the extracted text fragments into collection
				TextFragmentCollection textFragmentCollection = textFragmentAbsorber.getTextFragments();
				for (TextFragment textFragment : textFragmentCollection) {
					Question ques = listquestion.get(image_choice.get(str).get(0));

					Choice choice = ques.getChoices().get(image_choice.get(str).get(1));

					String i_path = choice.getImage();

					Image image = new Image(i_path);

					double i_ratio = image.getWidth() / image.getHeight();

					int x = (int) textFragment.getBaselinePosition().getXIndent();

					int y = (int) textFragment.getBaselinePosition().getYIndent() - 5;

					int x_upper = x + (int) (90 * i_ratio);

					if (x_upper < 150)
						x_upper = 150;

					int y_upper = y + 90;

					add_image_toPage(x, y, x_upper, y_upper, textFragment.getPage(), i_path);
				}

			}
			pdfDocument.save(tree.quiz_selected.getQuizPath());

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION!");
			alert.setHeaderText("Do you want to create a password for the PDF file?");
			ButtonType buttonTypeYes = new ButtonType("YES");
			ButtonType buttonTypeNo = new ButtonType("NO");
			alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

			alert.showAndWait().ifPresent(response -> {
				if (response == buttonTypeYes) {
					try {

						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/Bai7/password.fxml"));
						Parent root = loader.load();
						Scene scene = new Scene(root, 500, 300);
						Stage stage = new Stage();
						stage.setScene(scene);

						stage.show();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (response == buttonTypeNo) {
					Alert alertt = new Alert(Alert.AlertType.INFORMATION);
					alertt.setTitle("SUCCESS!");
					alertt.setHeaderText("You have successfully created a PDF file");
					alertt.showAndWait();
				}
			});
		}
	}

	public void add_image_toPage(int x, int y, int x_upper, int y_upper, Page p, String imagePath) throws IOException {
		int lowerLeftX = x;
		int lowerLeftY = y;
		int upperRightX = x_upper;
		int upperRightY = y_upper;

		FileInputStream imageStream = new FileInputStream(new java.io.File(imagePath));

		p.getResources().getImages().add(imageStream);

		p.getContents().add(new GSave());

		Rectangle rectangle = new Rectangle(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
		Matrix matrix = new Matrix(new double[] { rectangle.getURX() - rectangle.getLLX(), 0, 0,
				rectangle.getURY() - rectangle.getLLY(), rectangle.getLLX(), rectangle.getLLY() });

		p.getContents().add(new ConcatenateMatrix(matrix));
		XImage ximage = p.getResources().getImages().get_Item(p.getResources().getImages().size());

		p.getContents().add(new Do(ximage.getName()));

		p.getContents().add(new GRestore());
		imageStream.close();
	}

	@SuppressWarnings("static-access")
	public void cancelButton(ActionEvent event) throws IOException {

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

	}

	@SuppressWarnings("static-access")
	public void startAttemptButton(ActionEvent event) throws IOException {

		// Ko ấn ra ngoài
		if (tree.quiz_selected.getTime().equals("")) {
			// Nếu shuffle
			if (tree.quiz_selected.getCheck_shuffle() == 1) {
				for (Question element : tree.quiz_selected.getlistQues()) {
					Collections.shuffle(element.getChoices());
				}
			}

			// Lấy timeStart để hiển thị ở listView trong ReviewScene
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a");
			tree.quizResult.setTimeStart(now.format(formatter));

			// Lấy time1 ban đầu
			Instant bro = Instant.now();
			long noww = bro.toEpochMilli() / 1000;
			tree.quiz_selected.setTime1(Long.toString(noww));

			// Thòi gian bắt đầu thi để tính timeTaken
			tree.quizResult.setTimeStartt((int) noww);
		}

		tree.checkDangThi = tree.quiz_selected.getSttQuiz();

		tree.checkScene = 72;

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Bai1/Scene0.fxml"));
		Parent root = loader.load();

		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("/Bai7/SceneThi.fxml"));
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

}
