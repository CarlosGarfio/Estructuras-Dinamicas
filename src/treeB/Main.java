package treeB;

import Excepciones.IsEmptyException;

public class Main {

    public static void main(String[] args) throws IsEmptyException {
        TreeB<Double> btree= new TreeB<>(6d);
        btree.add(1d);
        btree.add(8d);
        btree.add(-5d);
        btree.add(1d);
        btree.add(7d);
        btree.add(6d);

        btree.preOrder();
        btree.inOrder();
        btree.posOrder();
        
    }
}
