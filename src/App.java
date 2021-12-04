import BinaryTree.BinaryTree;
import BinaryTree.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class App implements Initializable {

    @FXML
    public Pane pane;
    @FXML
    public TextArea textArea;

    public static BinaryTree<String> binaryTree = new BinaryTree<String>();

    /**
     * Metodo que inicializa la clase.
     * El metodo lee el archivo codigoMorse.txt, despues agrega cada una de la informacion contenida en el archivo
     * para posteriormente agregarlo en un ArrayListo, posterior se itera el ArrayList para agregarlo en un arbol
     * binario, una vez terminado el proceso se desplegara el arbol binario de forma visual.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String reader;
            ArrayList<String> arrayList = new ArrayList<>();

            File file = new File("src/codigoMorse.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((reader = bufferedReader.readLine()) != null)
                arrayList.add(reader);

            fileReader.close();
            bufferedReader.close();

            for (String s : arrayList)
                binaryTree.insert(s);

            displayBinaryTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para codificar una entrada de texto.
     * El metodo abre un TextInputDialog en la interfaz, espera a que el usuario brinde la cadena de texto necesaria
     * para posteriormente verificar si la entrada de texto ha sido la requerida, en caso de error se desplegara un
     * aviso de que la entrada es incorrecta.
     *
     * En caso contrario, se crea un ArrayList que almacenara el codigo morse (Que representa el codigo), despues
     * un arreglo de String que dividira la cadena de texto brindada por el usuario en cada espacio que se detecte.
     * Por ultimo un ArrayList de nodos que almacenara los valores del arbol binario, estos valores fueron brindados
     * por el metodo posOrden que se encuentra en la clase BinaryTree.
     * @see BinaryTree
     *
     * Una vez obtenida la informacion necesaria se realizara 3 iteraciones, siendo la primera un forEach que itera
     * cada uno de los String contenidos en el arreglo strings, posteriormente al tener la palabra a iterar, se hara
     * otra iteracion con el tamaño de la palabra, despues se va a iterar el contenido existente en el ArrayList de
     * nodos que almacena la informacion de los nodos de forma posOrden, y se verificara si la primera letra coincide
     * con alguna de las que son iteradas, procedera a agregarse al ArrayList morseCode para almacenar su codificacion.
     *
     * Terminado las iteraciones se agregaran al textArea y mostrarse la codificacion de letra a codigo morse.
     */
    @FXML
    void encoder(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Hola mundo...");

        dialog.setTitle("Encoder Input");
        dialog.setHeaderText("Ingresa una cadena de texto");
        dialog.setContentText("Texto:");

        String string = dialog.showAndWait().get();

        if (string.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Encoder Warning");
            alert.setHeaderText("Ingreso de cadena de texto erronea.");
            alert.showAndWait();

            return;
        }

        ArrayList<String> morseCode = new ArrayList<>();
        String[] strings = string.split(" ");
        ArrayList<Node<String>> strinsgBinary = binaryTree.posOrden();

        for (String s : strings)
            for (int i = 0; i < s.length(); i++)
                for (Node<String> st : strinsgBinary)
                    if (s.toUpperCase().substring(i, i + 1).equals(st.getData().substring(0, 1)))
                        morseCode.add(st.getData());

        textArea.appendText("Codigo morse de la palabra: " + string + "\n");

        for (String s : morseCode)
            textArea.appendText(s + "\n");
    }

    /**
     * Metodo para decodificar una entrada de texto.
     * El metodo abre un TextInputDialog en la interfaz, espera a que el usuario brinde la cadena de texto necesaria
     * para posteriormente verificar si la entrada de texto ha sido la requerida, en caso de error se desplegara un
     * aviso de que la entrada es incorrecta.
     *
     * En caso contrario, se crea un ArrayList que almacenara el codigo morse (Que representara una letra), despues
     * un arreglo de String que dividira la cadena de texto brindada por el usuario en cada espacio que se detecte.
     * Por ultimo un ArrayList de nodos que almacenara los valores del arbol binario, estos valores fueron brindados
     * por el metodo posOrden que se encuentra en la clase BinaryTree.
     *
     * Una vez obtenida la informacion necesaria se realizara 2 iteraciones, siendo la primera un forEach que itera
     * cada uno de los String contenidos en el arreglo strings, posteriormente al tener la palabra a iterar, despues
     * se va a iterar el contenido existente en el ArrayList de nodos que almacena la informacion de los nodos de
     * forma posOrden, y se verificara si el codigo coincide con alguna de las que son iteradas, procedera a
     * agregarse al ArrayList morseCode para almacenar su letra.
     *
     * @see BinaryTree
     */
    @FXML
    void decoder(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(".... --- .-.. .-");

        dialog.setTitle("Decoder Input");
        dialog.setHeaderText("Ingresa un codigo morse con espacios");
        dialog.setContentText("Codigo:");

        String string = dialog.showAndWait().get();

        if (!string.contains("-") && !string.contains(".")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Decoder Warning");
            alert.setHeaderText("Ingreso de codigo morse erronea.");
            alert.showAndWait();

            return;
        }

        ArrayList<String> morseCode = new ArrayList<>();
        String[] strings = string.split(" ");
        ArrayList<Node<String>> stringsBinary = binaryTree.posOrden();

        for (String s : strings)
            for (Node<String> st : stringsBinary)
                if (s.equals(st.getData().substring(2)))
                    morseCode.add(st.getData().substring(0, 1));

       textArea.appendText("Codigo ingresado: " + string + "\n");
       textArea.appendText("Palabra decodificada: ");

        for (String s : morseCode)
            textArea.appendText(s);

        textArea.appendText("\n");
    }

    /**
     * Metodo que desplegara el arbol binario de forma accesible.
     * El metodo toma la medida del pane y brinda los puntos iniciales donde se dibujara ademas de brindar el nodo
     * raiz y por ultimo el separador de nodo a nodo.
     */
    public void displayBinaryTree() {
        int x = (int) (pane.getPrefWidth() / 2);
        int y = 40;

        draw(x, y, x, y, binaryTree.getRoot(), 120);
    }

    /**
     * Metodo recursivo que agrega los elementos visuales para observar un arbol binario.
     * En casos inciales y cuestion de estetica, a la hora de registrar los primeros nodos que sean coincidentes con
     * "W" o "E", seran desplazados 100 pixeles mas a la derecha o izquierda dependiendo del caso, por simple cuestion
     * estetica que se encuentren desplazados respecto a los demas y exista espacio suficiente.
     *
     * Posteriormente se crean la linea que representa una conexion de nodo a nodo, y luego un circulo que representa
     * al nodo por si mismo, para posteriormente ingresarles el texto que es lo que contiene el nodo, despues se
     * agregan al pane.
     *
     * De forma recursiva se ira iterando este metodo hasta que ambos representen ser valores nulos, ya sea que exista
     * un nodo que no tenga nodo izquierdo ni nodo derecho se detendra.
     *
     * @param x1 Punto del eje X inicial que representa la conexion de la linea de nodo a nodo.
     * @param y1 Punto del eje Y inicial que representa la conexion de la linea de nodo a nodo.
     * @param x Punto del eje X que representa la posicion del nodo visual y el punto X final para la linea.
     * @param y Punto del eje Y que representa la posicion del nodo visual y el punto Y final para la linea.
     * @param node Nodo que se usara para obtener su informacion y verificar los nodos faltantes del arbol binario.
     * @param separator Representa el separador de nodo a nodo de forma visual.
     */
    public void draw(int x1, int y1, int x, int y, Node node, int separator) {
        if (((String) node.getData()).charAt(0) == 'W')
            x += 100;
        else if (((String) node.getData()).charAt(0) == 'E')
            x -= 100;

        Line line = new Line(x1, y1 + 5, x, y);

        Circle circle = new Circle(x, y, 20, Paint.valueOf("black"));
        circle.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0,0,0);");
        circle.setStrokeWidth(3);
        circle.setStroke(Color.DIMGRAY);

        Tooltip.install(circle, new Tooltip((String) node.getData()));

        Text text = new Text(x - 3, y + 3, String.valueOf(node).substring(0, 1));
        text.setFill(Color.WHITE);

        pane.getChildren().add(line);
        pane.getChildren().add(circle);
        pane.getChildren().add(text);

        if (node.left != null)
            draw(x, y, x - separator, y + 70, node.left, separator - 20);
        if (node.right != null)
            draw(x, y, x + separator, y + 70, node.right, separator - 20);
    }
}