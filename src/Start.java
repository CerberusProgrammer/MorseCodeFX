import BinaryTree.BinaryTree;

public class Start {

    public static void main(String[] args) {
        BinaryTree<String> binaryTree = new BinaryTree<String>();
        binaryTree.insertar("a");
        binaryTree.insertarHijoDerecho(binaryTree.getRaiz(), "s");
        binaryTree.insertar("x");
        binaryTree.insertar("b");
        binaryTree.insertar("c");
        binaryTree.insertar("d");

        System.out.println(binaryTree.recorridoInOrden().toString());
    }
}
