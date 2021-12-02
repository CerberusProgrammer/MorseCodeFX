package BinaryTree;

public class Node<E> {

    public E data;
    public Node<E> root;
    public Node<E> left;
    public Node<E> right;

    public Node() {
        this.data = null;
        this.root = null;
        this.left = null;
        this.right = null;
    }

    public Node(E node, Node<E> root, Node<E> left, Node<E> right) {
        this.data = node;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
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