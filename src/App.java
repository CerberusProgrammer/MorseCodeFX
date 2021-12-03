import BinaryTree.BinaryTree;
import BinaryTree.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class App implements Initializable {

    @FXML
    public Pane paneTree;
    @FXML
    public TextArea textAreaNormal;

    public static ArrayList<String> arrayList = new ArrayList<>();
    public static BinaryTree<String> binaryTree = new BinaryTree<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String s1;

            File file = new File("src/codigoMorse.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((s1 = bufferedReader.readLine()) != null)
                arrayList.add(s1);

            fileReader.close();
            bufferedReader.close();

            for (String s : arrayList)
                binaryTree.insertar(s);

            displayBinaryTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void encoder(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Hola mundo...");

        dialog.setTitle("Value Input");
        dialog.setHeaderText("Enter a value:");
        dialog.setContentText("Value:");

        String string = dialog.showAndWait().get();

        ArrayList<String> morseCode = new ArrayList<>();
        String[] strings = string.split("");
        ArrayList<Node<String>> strings1 = binaryTree.recorridoPosOrden();

        for (String s: strings)
            for (int i = 0; i < s.length(); i++)
                for (Node<String> st : strings1)
                    if (s.toUpperCase().substring(i, i + 1).equals(st.getData().substring(0, 1)))
                        morseCode.add(st.getData());

        for (String s: morseCode)
            textAreaNormal.appendText(s + "\n");
    }

    @FXML
    void decoder(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(".... --- .-.. .- -- ..- -. -.. ---");

        dialog.setTitle("Value Input");
        dialog.setHeaderText("Enter a value:");
        dialog.setContentText("Value:");

        String string = dialog.showAndWait().get();

    }

    public void displayBinaryTree() {
        int x = (int) (paneTree.getPrefWidth() / 2.33);
        int y = 40;

        draw(x, y, x, y, binaryTree.getRoot(), 120);
    }

    public void draw(int x1, int y1, int x, int y, Node node, int separator) {
        if (((String) node.getData()).charAt(0) == 'W')
            x += 100;

        Line line = new Line(x1, y1 + 5, x, y);
        paneTree.getChildren().add(line);

        Circle circle = new Circle(x, y, 20, Paint.valueOf("black"));
        paneTree.getChildren().add(circle);

        Text text = new Text(x - 3, y + 3, String.valueOf(node).substring(0, 1));
        text.setFill(Color.WHITE);
        paneTree.getChildren().add(text);

        if (node.left != null)
            draw(x, y, x - separator, y + 70, node.left, separator - 20);
        if (node.right != null)
            draw(x, y, x + separator, y + 70, node.right, separator - 20);
    }
}