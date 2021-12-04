package BinaryTree;

public class Node<E> {

    public E data;
    public Node<E> root;
    public Node<E> left;
    public Node<E> right;

    public Node(E node, Node<E> root, Node<E> left, Node<E> right) {
        this.data = node;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    /**
     * Metodo para obtener la informacion de un nodo.
     *
     * @return Retorna el valor del nodo.
     */
    public E getData() {
        return data;
    }

    /**
     * Metodo para modificar la informacion de un nodo.
     *
     * @param data Dato que reemplazara al dato anterior.
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Metodo para obtener la raiz de un nodo.
     *
     * @return Retorna un objeto de tipo nodo.
     */
    public Node<E> getRoot() {
        return root;
    }

    /**
     * Metodo para modificar el valor de un nodo raiz.
     *
     * @param root Nodo que reemplazara al nodo anterior.
     */
    public void setRoot(Node<E> root) {
        this.root = root;
    }

    /**
     * Metodo para obtener el nodo izquierdo de un nodo.
     *
     * @return Retorna un objeto de tipo nodo.
     */
    public Node<E> getLeft() {
        return left;
    }

    /**
     * Metodo para modificar el valor de un nodo izquierdo.
     *
     * @param left Nodo que reemplazara al nodo anterior.
     */
    public void setLeft(Node<E> left) {
        this.left = left;
    }

    /**
     * Metodo para obtener el nodo derecho de un nodo.
     *
     * @return Retorna un objeto de tipo nodo.
     */
    public Node<E> getRight() {
        return right;
    }

    /**
     * Metodo para modificar el valor de un nodo derecho.
     *
     * @param right Nodo que reemplazara al nodo anterior.
     */
    public void setRight(Node<E> right) {
        this.right = right;
    }

    /**
     * Metodo para desplegar la informacion contenida en un nodo.
     *
     * @return Retorna un objeto de tipo String.
     */
    public String toString() {
        return getData().toString();
    }
}