package treeAVL;

import Excepciones.IsEmptyException;

public class Main {

    public static void main(String[] args) throws IsEmptyException {
        TreeAVL<Integer> tree = new TreeAVL<>(5);
        tree.add(2);
        tree.add(15);
        tree.add(3);
        tree.add(10);
        tree.add(8);
        tree.add(4);
        tree.remove(3);
        tree.remove(2);
        tree.remove(4);
        System.out.println(tree.beetwen(2, 8));
        System.out.println(tree);
    }

}
