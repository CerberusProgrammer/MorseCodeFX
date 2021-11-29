package BinaryTree;

import java.util.ArrayList;

public class BinaryTree<E> {

    private Node<E> raiz;
    private int cantidad;

    public BinaryTree() {
        this.cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Elemento<E> getRaiz() {
        return raiz;
    }

    private Node<E> validar(Elemento<E> elemento) {
        if (!(elemento instanceof Node)) {
            return null;
        }
        Node<E> node = (Node<E>) elemento;
        return node.getPadre() == node ? null : node;
    }

    public Elemento<E> obtenerPadre(Elemento<E> elemento) {
        return validar(elemento).getPadre();
    }

    public Elemento<E> obtenerHijoIzquierdo(Elemento<E> elemento) {
        return validar(elemento).getLeft();
    }

    public Elemento<E> obtenerHijoDerecho(Elemento<E> elemento) {
        return validar(elemento).getRight();
    }

    public boolean esRaiz(Elemento<E> elemento) {
        return elemento == getRaiz();
    }

    public Elemento<E> insertarRaiz(E elemento) {
        if (!estaVacia()) {
            return null;
        }
        raiz = crearNodo(elemento, null, null, null);
        cantidad = 1;
        return raiz;
    }

    public Elemento<E> insertarHijoIzquierdo(Elemento<E> elemento, E dato) {
        Node<E> temporal = validar(elemento);
        if (temporal.getLeft() != null) {
            return null;
        }

        Node<E> hijoIzquierdo = crearNodo(dato, temporal, null, null);
        temporal.setLeft(hijoIzquierdo);
        cantidad++;
        return hijoIzquierdo;
    }

    public Elemento<E> insertarHijoDerecho(Elemento<E> elemento, E dato) {
        Node<E> temporal = validar(elemento);
        if (temporal.getRight() != null) {
            return null;
        }

        Node<E> hijoDerecho = crearNodo(dato, temporal, null, null);
        temporal.setRight(hijoDerecho);
        cantidad++;
        return hijoDerecho;
    }

    public Elemento<E> insertar(E dato) {
        return insertar(dato, getRaiz());
    }

    private Elemento<E> insertar(E dato, Elemento<E> nodo) {
        if (nodo == null) {
            return insertarRaiz(dato);
        } else if (((Comparable<E>) dato).compareTo(nodo.getData()) < 0) {
            if (obtenerHijoIzquierdo(nodo) == null) {
                return insertarHijoIzquierdo(validar(nodo), dato);
            }
            return insertar(dato, obtenerHijoIzquierdo(nodo));
        } else {
            if (obtenerHijoDerecho(nodo) == null) {
                return insertarHijoDerecho(validar(nodo), dato);
            }
            return insertar(dato, obtenerHijoDerecho(nodo));
        }
    }


    private Node<E> crearNodo(E elemento, Node<E> padre,
                              Node<E> hijoIzquierdo,
                              Node<E> hijoDerecho) {
        return new Node<E>(elemento, padre, hijoIzquierdo, hijoDerecho);
    }

    public boolean estaVacia() {
        return getCantidad() == 0;
    }


    public Iterable<Elemento<E>> obtenerHijos(Elemento<E> elemento) {
        ArrayList<Elemento<E>> hijos = new ArrayList<Elemento<E>>(2);
        if (obtenerHijoIzquierdo(elemento) != null) {
            hijos.add(obtenerHijoIzquierdo(elemento));
        }
        if (obtenerHijoDerecho(elemento) != null) {
            hijos.add(obtenerHijoDerecho(elemento));
        }
        return hijos;
    }


    public ArrayList<Elemento<E>> recorridoPreOrden() {
        ArrayList<Elemento<E>> elementos = new ArrayList<Elemento<E>>();
        if (!estaVacia()) {
            recorridoPreOrden(getRaiz(), elementos);
        }

        return elementos;
    }

    private void recorridoPreOrden(Elemento<E> raiz, ArrayList<Elemento<E>> elementos) {
        elementos.add(raiz);
        for (Elemento<E> elemento : obtenerHijos(raiz)) {
            recorridoPreOrden(elemento, elementos);
        }
    }

    public ArrayList<Elemento<E>> recorridoInOrden() {
        ArrayList<Elemento<E>> elementos = new ArrayList<Elemento<E>>();
        if (!estaVacia()) {
            recorridoInOrden(getRaiz(), elementos);
        }

        return elementos;
    }

    private void recorridoInOrden(Elemento<E> raiz, ArrayList<Elemento<E>> elementos) {
        if (obtenerHijoIzquierdo(raiz) != null) {
            recorridoInOrden(obtenerHijoIzquierdo(raiz), elementos);
        }
        elementos.add(raiz);
        if (obtenerHijoDerecho(raiz) != null) {
            recorridoInOrden(obtenerHijoDerecho(raiz), elementos);
        }
    }


    public ArrayList<Elemento<E>> recorridoPosOrden() {
        ArrayList<Elemento<E>> elementos = new ArrayList<Elemento<E>>();
        if (!estaVacia()) {
            recorridoPosOrden(getRaiz(), elementos);
        }

        return elementos;
    }

    private void recorridoPosOrden(Elemento<E> raiz, ArrayList<Elemento<E>> elementos) {
        for (Elemento<E> elemento : obtenerHijos(raiz)) {
            recorridoPosOrden(elemento, elementos);
        }

        elementos.add(raiz);
    }


}
