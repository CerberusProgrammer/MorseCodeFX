import BinaryTree.BinaryTree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Decoder {

    public static ArrayList<String> arrayList = new ArrayList<>();
    public static BinaryTree<String> binaryTree = new BinaryTree<String>();

    public static void importWords(File file) throws IOException {
        String s1;

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((s1 = bufferedReader.readLine()) != null)
            arrayList.add(s1);

        toBinaryTree();
        App app = new App();
        app.displayBinaryTree();
    }

    public static void toBinaryTree() {
        for (String s: arrayList) {
            binaryTree.insertar(s);
        }
    }
}
