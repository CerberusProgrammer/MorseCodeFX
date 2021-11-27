package BinaryTree;

public class BinaryTree<T> {

    Node<T> root;

    public void insertar(T data) {
        Node<T> node;
        node = new Node<T>();
        node.setData(data);
        node.setLeft(null);
        node.setRight(null);

        if (root == null)
            root = node;
        else {
            Node<T> anterior = null;
            Node<T> reco = root;

            while (reco != null) {
                anterior = reco;
                if (data < reco.getData())
                    reco = reco.setLeft();
                else
                reco = reco.setRight(null);
                ;
            }
            if (data < anterior.info)
                anterior.setLeft(); =node;
            else
            anterior.setRight(null); =node;
        }
    }
}
