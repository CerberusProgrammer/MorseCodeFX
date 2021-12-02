import BinaryTree.BinaryTree;
import BinaryTree.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class App implements Initializable {

    @FXML
    public static Pane paneTree;
    @FXML
    public static TextArea textAreaNormal;

    final int separation = 50;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void importMorseCode(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo de texto plano");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );

        File file = fileChooser.showOpenDialog(new Stage());

        Decoder.importWords(file);
    }

    @FXML
    void insertMorseCode(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Insert.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void displayBinaryTree() {
        int x = (int) paneTree.getPrefWidth() / 2;
        int y = 40;

        draw(x, y, x, y, Decoder.binaryTree.getRoot());
    }

    public void draw(int x1, int y1, int x, int y, Node node) {
        Line line = new Line(x1, y1 + 5, x, y);
        paneTree.getChildren().add(line);

        Circle circle = new Circle(x, y, 20, Paint.valueOf("black"));
        paneTree.getChildren().add(circle);

        Text text = new Text(x - 3, y + 3, String.valueOf(node));
        text.setFill(Color.WHITE);
        paneTree.getChildren().add(text);

        if (node.left != null)
            draw(x, y, x - (separation * 2), y + separation, node.left);
        if (node.right != null)
            draw(x, y, x + (separation * 2), y + separation, node.right);
    }
}
