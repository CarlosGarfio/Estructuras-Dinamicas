package treeB;

import Excepciones.IsEmptyException;

public class Main {

    public static void main(String[] args) throws IsEmptyException {
        TreeB<Integer> btree = new TreeB<>(6);
        btree.fill(btree, 20, 1, 10);
        //btree.remove(8);
        //btree.preOrder();
        //btree.inOrder();
        //btree.posOrder();
        System.out.println(btree);
        System.exit(0);
    }
}
