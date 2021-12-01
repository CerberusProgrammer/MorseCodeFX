import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class App implements Initializable {

    @FXML
    private Pane paneTree;
    @FXML
    private TextArea textAreaNormal;

    private File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double x = paneTree.getPrefWidth() / 2;
        double y = 40;

        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setCenterX(x);
        circle.setCenterY(y);

        Circle circle1 = new Circle();
        circle1.setRadius(20);
        circle1.setCenterX(circle.getCenterX() + 20);
        circle1.setCenterY(circle.getCenterY() + 60);

        Line line = new Line();
        line.setStartX(circle.getCenterX());
        line.setStartY(circle.getCenterY());


        paneTree.getChildren().add(circle);
        paneTree.getChildren().add(circle1);
    }

    @FXML
    void importMorseCode(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo de texto plano");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );

        file = fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    void insertMorseCode(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Insert.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
