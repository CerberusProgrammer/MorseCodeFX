package BinaryTree;

import java.util.ArrayList;

public class BinaryTree<E> {

    private Node<E> root;
    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    public Node<E> insertarRaiz(E node) {
        if (!estaVacia())
            return null;

        root = crearNodo(node, null, null, null);
        size = 1;

        return root;
    }

    public Node<E> insertarHijoIzquierdo(Node<E> node, E data) {
        Node<E> temporal = check(node);

        if (temporal.getLeft() != null)
            return null;

        Node<E> hijoIzquierdo = crearNodo(data, temporal, null, null);
        temporal.setLeft(hijoIzquierdo);
        size++;

        return hijoIzquierdo;
    }

    public Node<E> insertarHijoDerecho(Node<E> node, E data) {
        Node<E> temporal = check(node);

        if (temporal.getRight() != null)
            return null;

        Node<E> hijoDerecho = crearNodo(data, temporal, null, null);
        temporal.setRight(hijoDerecho);
        size++;

        return hijoDerecho;
    }

    public Node<E> insertar(E data) {
        return insertar(data, getRoot());
    }

    private Node<E> insertar(E data, Node<E> node) {
        if (node == null)
            return insertarRaiz(data);
        else if (((Comparable<E>) data).compareTo(node.getData()) < 0) {
            if (getLeft(node) == null)
                return insertarHijoIzquierdo(check(node), data);

            return insertar(data, getLeft(node));
        } else {
            if (getRight(node) == null)
                return insertarHijoDerecho(check(node), data);

            return insertar(data, getRight(node));
        }
    }

    private Node<E> crearNodo(E data, Node<E> root, Node<E> left, Node<E> right) {
        return new Node<E>(data, root, left, right);
    }

    public boolean estaVacia() {
        return getSize() == 0;
    }

    public Iterable<Node<E>> getChildren(Node<E> node) {
        ArrayList<Node<E>> hijos = new ArrayList<>(2);

        if (getLeft(node) != null)
            hijos.add(getLeft(node));

        if (getRight(node) != null)
            hijos.add(getRight(node));

        return hijos;
    }

    public ArrayList<Node<E>> preOrden() {
        ArrayList<Node<E>> elementos = new ArrayList<>();

        if (!estaVacia())
            preOrden(getRoot(), elementos);

        return elementos;
    }

    private void preOrden(Node<E> root, ArrayList<Node<E>> data) {
        data.add(root);

        for (Node<E> elemento : getChildren(root))
            preOrden(elemento, data);
    }

    public ArrayList<Node<E>> inOrden() {
        ArrayList<Node<E>> elementos = new ArrayList<>();

        if (!estaVacia())
            inOrden(getRoot(), elementos);

        return elementos;
    }

    private void inOrden(Node<E> root, ArrayList<Node<E>> data) {
        if (getLeft(root) != null)
            inOrden(getLeft(root), data);

        data.add(root);

        if (getRight(root) != null)
            inOrden(getRight(root), data);
    }

    public ArrayList<Node<E>> posOrden() {
        ArrayList<Node<E>> elementos = new ArrayList<>();

        if (!estaVacia())
            posOrden(getRoot(), elementos);

        return elementos;
    }

    private void posOrden(Node<E> root, ArrayList<Node<E>> data) {
        for (Node<E> elemento : getChildren(root))
            posOrden(elemento, data);

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
