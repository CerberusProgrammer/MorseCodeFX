import BinaryTree.BinaryTree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("App.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("MorseCodeFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        binaryTree.insertar(1);
        System.out.println("Raiz: " + binaryTree.getRaiz());
        binaryTree.insertar(2);
        binaryTree.insertar(0);
        binaryTree.insertar(4);
        binaryTree.insertar(9);
        binaryTree.insertar(10);
        binaryTree.insertar(5);

        System.out.println(binaryTree.recorridoInOrden().toString());

        launch();
    }
}
