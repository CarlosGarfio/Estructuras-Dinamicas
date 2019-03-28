package treeAVL;

import Excepciones.IsEmptyException;

public class Main {
    
    public static void main(String[] args) throws IsEmptyException {
        TreeAVL<Integer> tree = new TreeAVL<>(5);
        tree.add(5);
        tree.add(2);
        tree.add(15);
        tree.add(3);
        tree.add(10);
        tree.add(8);
        tree.add(4);
        tree.add(8);
        tree.add(8);
        tree.add(10);
        tree.add(7);
        tree.add(5);
        tree.add(1);
        tree.add(22);
        tree.add(23);
        tree.add(15);
        tree.remove(10);
        tree.remove(5);
        tree.remove(5);
        tree.remove(5);
//        tree.remove(7);
//        tree.remove(7);
//        tree.remove(7);
//        tree.remove(8);
//        tree.remove(15);
        System.out.println("\nBetween: "+tree.beetwen(-1, 2_000));
        System.out.println("Bigger: "+tree.bigger());
        System.out.println("Minor: "+tree.minor());
        System.out.println(tree);
        tree.inOrder();
        tree.posOrder();
        tree.preOrder();
        System.exit(0);
    }
}
