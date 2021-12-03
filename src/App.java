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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class App implements Initializable {

    @FXML
    public Pane paneTree;
    @FXML
    public TextArea textAreaNormal;

    final int separation = 50;

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

            toBinaryTree();
            displayBinaryTree();

            Node node = binaryTree.getRoot();
            node = node.right;
            node = node.right;

            Node node1 = binaryTree.getRoot(); // T
            node1 = node1.right; // W
            node1 = node1.right; // Z


            // Z > W = Z esta a la derecha del padre
            if (((String)node1.getData()).compareTo((String)node1.root.getData()) > 0) {
                System.out.println("X");
            } else
                System.out.println("y");

            System.out.println(node.root);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        int x = (int) (paneTree.getPrefWidth() / 2.33);
        int y = 40;

        draw(x, y, x, y, binaryTree.getRoot());
        //drawBinaryTree();
    }

    public void drawBinaryTree() {
        Node node = binaryTree.getRoot();

        Boolean b = true;

        int x = (int) (paneTree.getPrefWidth() / 2.33);
        int y = 40;

        node = node.right;
        System.out.println(binaryTree.obtenerHijos(node));
        // derecha
        while (node != null) {
            Line line = new Line(x, y, x + 40, y + 40);
            paneTree.getChildren().add(line);

            Circle circle = new Circle(x, y, 20, Paint.valueOf("black"));
            paneTree.getChildren().add(circle);

            Text text = new Text(x - 3, y + 3, String.valueOf(node).substring(0, 1));
            text.setFill(Color.WHITE);
            paneTree.getChildren().add(text);

            if (node.right == null)
                node = node.left;
            else
                node = node.right;

            x += 40;
            x += 40;
        }
    }

    public void draw(int x1, int y1, int x, int y, Node node) {
        Line line = new Line(x1, y1 + 5, x, y);
        paneTree.getChildren().add(line);

        Circle circle = new Circle(x, y, 20, Paint.valueOf("black"));
        paneTree.getChildren().add(circle);

        Text text = new Text(x - 3, y + 3, String.valueOf(node).substring(0, 1));
        text.setFill(Color.WHITE);
        paneTree.getChildren().add(text);


        if (node.left != null)
            draw(x, y, x - 40, y + 70, node.left);
        if (node.right != null)
            draw(x, y, x + 40, y + 70, node.right);
    }

    public void toBinaryTree() {
        for (String s : arrayList) {
            binaryTree.insertar(s);
        }
    }
}

/*
* if (node != null && node.root != null)
            if (((String)node.getData()).compareTo((String)node.root.getData()) > 0) {
                //Es derecha
                x += 5;
            } else {
                //Es izquierda
                x -= 100;
            }
*
*
*
* */
