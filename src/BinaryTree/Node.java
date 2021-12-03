package BinaryTree;

public class Node<E> {

    public E data;
    public Node<E> root;
    public Node<E> left;
    public Node<E> right;

    /**
     * @param node
     * @param root
     * @param left
     * @param right
     */
    public Node(E node, Node<E> root, Node<E> left, Node<E> right) {
        this.data = node;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    /**
     * @return
     */
    public E getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * @return
     */
    public Node<E> getRoot() {
        return root;
    }

    /**
     * @param root
     */
    public void setRoot(Node<E> root) {
        this.root = root;
    }

    /**
     * @return
     */
    public Node<E> getLeft() {
        return left;
    }

    /**
     * @param left
     */
    public void setLeft(Node<E> left) {
        this.left = left;
    }

    /**
     * @return
     */
    public Node<E> getRight() {
        return right;
    }

    /**
     * @param right
     */
    public void setRight(Node<E> right) {
        this.right = right;
    }

    /**
     * @return
     */
    public String toString() {
        return getData().toString();
    }
}