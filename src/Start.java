import BinaryTree.BinaryTree;

public class Start {

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
    }
}
