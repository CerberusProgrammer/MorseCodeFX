package BinaryTree;

import java.util.ArrayList;

public class BinaryTree<E> {

    private Node<E> root;
    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    /**
     * Metodo para insertar un nodo raiz al arbol binario.
     *
     * @param node Nodo que sera insertado en el nodo raiz.
     * @return Retorna el nuevo valor del nodo raiz.
     */
    public Node<E> insertRoot(E node) {
        if (!estaVacia())
            return null;

        root = new Node<>(node, null, null, null);
        size = 1;

        return root;
    }

    /**
     * Metodo con la funcion de hacer una insercion de lado izquierdo de un nodo.
     *
     * @param node Nodo que sera revisado por check() y segun su valor se usara para crear un nuevo nodo.
     * @param data Informacion que contendra el nuevo nodo izquierdo.
     * @return Retorna un valor de tipo Nodo.
     */
    public Node<E> insertLeft(Node<E> node, E data) {
        Node<E> temp = check(node);

        if (temp.getLeft() != null)
            return null;

        Node<E> left = new Node<E>(data, temp, null, null);
        temp.setLeft(left);
        size++;

        return left;
    }

    /**
     * Metodo con la funcion de hacer una insercion de lado derecho de un nodo.
     *
     * @param node Nodo que sera revisado por check() y segun su valor se usara para crear un nuevo nodo.
     * @param data Informacion que contendra el nuevo nodo derecho.
     * @return Retorna un valor de tipo Nodo.
     */
    public Node<E> insertRight(Node<E> node, E data) {
        Node<E> temp = check(node);

        if (temp.getRight() != null)
            return null;

        Node<E> right = new Node<E>(data, temp, null, null);
        temp.setRight(right);
        size++;

        return right;
    }

    /**
     * Metodo con la funcion de insertar un nodo de una forma accesible.
     * Llamando a otro metodo con los datos
     * necesarios para el funcionamiento de la insercion de un nodo.
     *
     * @param data Informacion que contendra el nuevo nodo.
     * @return Retorna el nodo creado.
     */
    public Node<E> insert(E data) {
        return insert(data, getRoot());
    }

    /**
     * Metodo para insertar un nodo.
     * El metodo es recursivo para verificar nodo por nodo desde su raiz donde debe ser insertado, en caso de ser un
     * nodo nulo, este pasa al metodo insertRoot() para ingresarse en el nodo raiz. En el caso de que no sea el valor
     * este pasara a ser un dato Comparable, con ello se revisa con el metodo compareTo() si el valor de la informacion
     * es mayor o menos al nodo enviado.
     *
     * A continuacion se puede apreciar como funciona el metodo y a que valor se asignaria cada cosa segun el valor
     * devuelto por el metodo.
     * compareTo()
     * return x < 0  = El que llama es primero
     * return x == 0 = Equivalentes
     * return x > 0  = El parametro es primero
     *
     * @param data Informacion que contendra el nuevo nodo.
     * @param node Nodo raiz que sera iterado.
     * @return Retorna el nodo creado.
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

    /**
     * Metodo para verificar si se encuentra vacio el arbol binario.
     *
     * @return Valor booleano
     */
    public boolean estaVacia() {
        return getSize() == 0;
    }

    /**
     * Metodo que obtiene los hijos de un nodo.
     * Se crea un ArrayList con una capacidad limitada de 2, ya que solo un nodo puede contener 2 hijos.
     * Posteriormente se observa si no son valores nodos los hijos del nodo brindado, en caso de que no sea asi,
     * se agregara al ArrayList y se retornara como un valor Iterable para un uso correcto.
     *
     * @param node Nodo donde se obtendran sus valores hijos
     * @return Retorna un iterable de nodos.
     */
    public Iterable<Node<E>> getChildren(Node<E> node) {
        ArrayList<Node<E>> nodes = new ArrayList<>(2);

        if (getLeft(node) != null)
            nodes.add(getLeft(node));

        if (getRight(node) != null)
            nodes.add(getRight(node));

        return nodes;
    }

    /**
     * Metodo accesible para obtener el orden preOrden de un arbol binario.
     *
     * @return Retorna un ArrayList con los nodos obtenidos en la busqueda.
     */
    public ArrayList<Node<E>> preOrden() {
        ArrayList<Node<E>> node = new ArrayList<>();

        if (!estaVacia())
            preOrden(getRoot(), node);

        return node;
    }

    /**
     * Metodo recursivo que agrega los valores de los nodos en forma de preOrden.
     * Primero se agrega al ArrayList el nodo raiz, posteriormente se llama a todos los hijos del nodo raiz con el
     * metodo getChildren(), al retornarnos este metodo un objeto Iterable, podemos iterar nodo por nodo y despues
     * agregaremos los hijos del nodo, ya que la busqueda preOrden comienza por la raiz, y sus hojas de izquierda
     * a derecha.
     *
     * @param root Nodo raiz que permitira obtener sus hijos.
     * @param data ArrayList que agregara cada uno de los nodos
     */
    private void preOrden(Node<E> root, ArrayList<Node<E>> data) {
        data.add(root);

        for (Node<E> node : getChildren(root))
            preOrden(node, data);
    }

    /**
     * Metodo accesible para obtener el orden inOrden de un arbol binario.
     *
     * @return Retorna un ArrayList con los nodos obtenidos en la busqueda.
     */
    public ArrayList<Node<E>> inOrden() {
        ArrayList<Node<E>> node = new ArrayList<>();

        if (!estaVacia())
            inOrden(getRoot(), node);

        return node;
    }

    /**
     * Metodo recursivo que agrega los valores de los nodos en forma de inOrden.
     * Primero se verifica si contendra informacion en el nodo izquierdo, si es asi se manda a llamar de vuelta la
     * funcion y esta se detendra hasta encontrar que un nodo no contenga un nodo izquierdo, que en cuyo caso se
     * agregaran los nodos hasta llegar al nodo raiz y volver ahora por el nodo derecho.
     *
     * @param root Nodo raiz que permitira obtener sus hijos.
     * @param data ArrayList que agregara cada uno de los nodos
     */
    private void inOrden(Node<E> root, ArrayList<Node<E>> data) {
        if (getLeft(root) != null)
            inOrden(getLeft(root), data);

        data.add(root);

        if (getRight(root) != null)
            inOrden(getRight(root), data);
    }

    /**
     * Metodo accesible para obtener el orden posOrden de un arbol binario.
     *
     * @return Retorna un ArrayList con los nodos obtenidos en la busqueda.
     */
    public ArrayList<Node<E>> posOrden() {
        ArrayList<Node<E>> node = new ArrayList<>();

        if (!estaVacia())
            posOrden(getRoot(), node);

        return node;
    }

    /**
     * Metodo recursivo que agrega los valores de los nodos en forma de posOrden.
     * El metodo recorre primero los hijos del nodo raiz por medio de un forEach, teniendo de valores los nodos y
     * provenientes de los hijos del nodo raiz obtenido del metodo getChildren(), que nos retorna un iterable,
     * posteriormente itera de forma recursiva el metodo que va obteniendo cada nodo primero de forma izquierda,
     * despues derecha para finalmente agregar los nodos raiz.
     *
     * @param root Nodo raiz que permitira obtener sus hijos.
     * @param data ArrayList que agregara cada uno de los nodos
     */
    private void posOrden(Node<E> root, ArrayList<Node<E>> data) {
        for (Node<E> node : getChildren(root))
            posOrden(node, data);

        data.add(root);
    }

    /**
     * Metodo para obtener el tamaño del arbol binario
     *
     * @return Retorna valor de tipo entero
     */
    public int getSize() {
        return size;
    }

    /**
     * Metodo para obtener el nodo raiz del arbol binario.
     *
     * @return Retorna un objeto de tipo Nodo.
     * @see Node
     */
    public Node<E> getRoot() {
        return root;
    }

    /**
     * Metodo para verificar que un nodo se encuentra vacio.
     * El metodo veridica si el nodo enviado es nulo o en cuyo caso, su nodo padre es igual al nodo enviado,
     * en caso de que si pasara a ser nulo para evitar errores de nodos agregados y repetidos.
     *
     * @param node Nodo que sera verificado.
     * @return Retorna el nodo en caso de ser correcto respecto a las especificaciones, en caso contrario sera nulo.
     */
    private Node<E> check(Node<E> node) {
        if (node == null)
            return null;

        return node.getRoot() == node ? null : node;
    }

    /**
     * Metodo para obtener el nodo Raiz de un nodo.
     *
     * @param node Nodo que se desea saber su nodo padre.
     * @return Retorna un objeto de tipo nodo.
     * @see Node
     */
    public Node<E> getRoot(Node<E> node) {
        return check(node).getRoot();
    }

    /**
     * Metodo para obtener el nodo izquierdo de un nodo raiz.
     *
     * @param node Nodo al que se desea saber su nodo izquierdo.
     * @return Retorna un objeto de tipo nodo.
     * @see Node
     */
    public Node<E> getLeft(Node<E> node) {
        return check(node).getLeft();
    }

    /**
     * Metodo para obtener el nodo derecho de un nodo raiz.
     *
     * @param node Nodo al que se desea saber su nodo derecho.
     * @return Retorna un objeto de tipo nodo.
     * @see Node
     */
    public Node<E> getRight(Node<E> node) {
        return check(node).getRight();
    }

    /**
     * Metodo para verificar si un nodo es raiz.
     *
     * @param node Nodo al que se desea saber si es raiz.
     * @return Retorna un valor de tipo booleano.
     */
    public boolean isRoot(Node<E> node) {
        return node == getRoot();
    }
}
