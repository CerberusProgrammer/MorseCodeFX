package BinaryTree;

import java.util.ArrayList;

public class BinaryTree<E> {

    private Node<E> root;
    private int size;

    public BinaryTree() {
        this.size = 0;
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

    public Node<E> insertar(E dato) {
        return insertar(dato, getRoot());
    }

    private Node<E> insertar(E dato, Node<E> nodo) {
        if (nodo == null)
            return insertarRaiz(dato);
        else if (((Comparable<E>) dato).compareTo(nodo.getData()) < 0) {
            if (getLeft(nodo) == null)
                return insertarHijoIzquierdo(check(nodo), dato);

            return insertar(dato, getLeft(nodo));
        } else {
            if (getRight(nodo) == null)
                return insertarHijoDerecho(check(nodo), dato);

            return insertar(dato, getRight(nodo));
        }
    }

    private Node<E> crearNodo(E elemento, Node<E> padre,
                              Node<E> hijoIzquierdo,
                              Node<E> hijoDerecho) {
        return new Node<E>(elemento, padre, hijoIzquierdo, hijoDerecho);
    }

    public boolean estaVacia() {
        return getSize() == 0;
    }


    public Iterable<Node<E>> obtenerHijos(Node<E> elemento) {
        ArrayList<Node<E>> hijos = new ArrayList<>(2);

        if (getLeft(elemento) != null)
            hijos.add(getLeft(elemento));

        if (getRight(elemento) != null)
            hijos.add(getRight(elemento));

        return hijos;
    }


    public ArrayList<Node<E>> recorridoPreOrden() {
        ArrayList<Node<E>> elementos = new ArrayList<>();

        if (!estaVacia())
            recorridoPreOrden(getRoot(), elementos);

        return elementos;
    }

    private void recorridoPreOrden(Node<E> raiz, ArrayList<Node<E>> elementos) {
        elementos.add(raiz);

        for (Node<E> elemento : obtenerHijos(raiz))
            recorridoPreOrden(elemento, elementos);
    }

    public ArrayList<Node<E>> recorridoInOrden() {
        ArrayList<Node<E>> elementos = new ArrayList<>();

        if (!estaVacia())
            recorridoInOrden(getRoot(), elementos);

        return elementos;
    }

    private void recorridoInOrden(Node<E> raiz, ArrayList<Node<E>> elementos) {
        if (getLeft(raiz) != null)
            recorridoInOrden(getLeft(raiz), elementos);

        elementos.add(raiz);

        if (getRight(raiz) != null)
            recorridoInOrden(getRight(raiz), elementos);
    }


    public ArrayList<Node<E>> recorridoPosOrden() {
        ArrayList<Node<E>> elementos = new ArrayList<>();

        if (!estaVacia())
            recorridoPosOrden(getRoot(), elementos);

        return elementos;
    }

    private void recorridoPosOrden(Node<E> raiz, ArrayList<Node<E>> elementos) {
        for (Node<E> elemento : obtenerHijos(raiz))
            recorridoPosOrden(elemento, elementos);

        elementos.add(raiz);
    }


}
