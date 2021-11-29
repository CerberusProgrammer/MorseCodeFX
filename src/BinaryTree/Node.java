package BinaryTree;

public class Node<E> implements Elemento<E> {

    E data;
    Node<E> padre;
    Node<E> left;
    Node<E> right;

    public Node(E elemento, Node<E> padre, Node<E> hijoIzquierdo, Node<E> right) {
        this.data = elemento;
        this.padre = padre;
        this.left = hijoIzquierdo;
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getPadre() {
        return padre;
    }

    public void setPadre(Node<E> padre) {
        this.padre = padre;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public String toString() {
        return getData().toString();
    }
}