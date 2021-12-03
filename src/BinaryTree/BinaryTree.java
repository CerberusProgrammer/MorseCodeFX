package BinaryTree;

import java.util.ArrayList;

public class BinaryTree<E> {

    private Node<E> root;
    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    public Node<E> insertRoot(E node) {
        if (!estaVacia())
            return null;

        root = crearNodo(node, null, null, null);
        size = 1;

        return root;
    }

    public Node<E> insertLeft(Node<E> node, E data) {
        Node<E> temp = check(node);

        if (temp.getLeft() != null)
            return null;

        Node<E> left = crearNodo(data, temp, null, null);
        temp.setLeft(left);
        size++;

        return left;
    }

    public Node<E> insertRight(Node<E> node, E data) {
        Node<E> temp = check(node);

        if (temp.getRight() != null)
            return null;

        Node<E> right = crearNodo(data, temp, null, null);
        temp.setRight(right);
        size++;

        return right;
    }

    public Node<E> insert(E data) {
        return insert(data, getRoot());
    }

    /*
     * compareTo()
     * return x < 0  = El que llama es primero
     * return x == 0 = Equivalentes
     * return x > 0  = El parametro es primero
     */
    private Node<E> insert(E data, Node<E> node) {
        if (node == null)
            return insertRoot(data);
        else if (((Comparable<E>) data).compareTo(node.getData()) < 0) {
            if (getLeft(node) == null)
                return insertLeft(check(node), data);

            return insert(data, getLeft(node));
        } else {
            if (getRight(node) == null)
                return insertRight(check(node), data);

            return insert(data, getRight(node));
        }
    }

    private Node<E> crearNodo(E data, Node<E> root, Node<E> left, Node<E> right) {
        return new Node<E>(data, root, left, right);
    }

    public boolean estaVacia() {
        return getSize() == 0;
    }

    public Iterable<Node<E>> getChildren(Node<E> node) {
        ArrayList<Node<E>> nodes = new ArrayList<>(2);

        if (getLeft(node) != null)
            nodes.add(getLeft(node));

        if (getRight(node) != null)
            nodes.add(getRight(node));

        return nodes;
    }

    public ArrayList<Node<E>> preOrden() {
        ArrayList<Node<E>> node = new ArrayList<>();

        if (!estaVacia())
            preOrden(getRoot(), node);

        return node;
    }

    private void preOrden(Node<E> root, ArrayList<Node<E>> data) {
        data.add(root);

        for (Node<E> elemento : getChildren(root))
            preOrden(elemento, data);
    }

    public ArrayList<Node<E>> inOrden() {
        ArrayList<Node<E>> node = new ArrayList<>();

        if (!estaVacia())
            inOrden(getRoot(), node);

        return node;
    }

    private void inOrden(Node<E> root, ArrayList<Node<E>> data) {
        if (getLeft(root) != null)
            inOrden(getLeft(root), data);

        data.add(root);

        if (getRight(root) != null)
            inOrden(getRight(root), data);
    }

    public ArrayList<Node<E>> posOrden() {
        ArrayList<Node<E>> node = new ArrayList<>();

        if (!estaVacia())
            posOrden(getRoot(), node);

        return node;
    }

    private void posOrden(Node<E> root, ArrayList<Node<E>> data) {
        for (Node<E> node : getChildren(root))
            posOrden(node, data);

        data.add(root);
    }

    public int getSize() {
        return size;
    }

    public Node<E> getRoot() {
        return root;
    }

    private Node<E> check(Node<E> node) {
        if (node == null)
            return null;

        return node.getRoot() == node ? null : node;
    }

    public Node<E> getRoot(Node<E> node) {
        return check(node).getRoot();
    }

    public Node<E> getLeft(Node<E> node) {
        return check(node).getLeft();
    }

    public Node<E> getRight(Node<E> node) {
        return check(node).getRight();
    }

    public boolean isRoot(Node<E> node) {
        return node == getRoot();
    }
}
