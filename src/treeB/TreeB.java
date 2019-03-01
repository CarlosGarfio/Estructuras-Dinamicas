package treeB;

import Excepciones.IsEmptyException;
import Node.Node;
import tree.Tree;

public class TreeB<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public TreeB(T value) {
        this.root = new Node<>(value);
        this.root.setCont(0L);
        this.root.setLevel(0L);
    }

    public TreeB(Node<T> node) {
        this.root = node;
        this.root.setCont(0L);
        this.root.setLevel(0L);
    }

    @Override
    public boolean add(T value) {
        if (value == null) {
            return false;
        } else {
            if (root.getValue() == null) {
                root.setValue(value);
                return true;
            } else {
                if (add(root, value, root.getLevel()) != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private Node<T> add(Node<T> root, T value, long lvl) {
        if (root == null) {
            root = new Node<>(value);
            root.setLevel(lvl);
            root.setCont(0);
            return root;
        } else {
            switch (root.getValue().compareTo(value)) {
                case 0:
                    root.setCont(root.getCont() + 1);
                    break;
                case 1:
                    root.setBack(add(root.getBack(), value, ++lvl));
                    break;
                default:
                    root.setNext(add(root.getNext(), value, ++lvl));
                    break;
            }
        }
        return root;
    }

    @Override
    public void balance() throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void beetwen(T start, T end) throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T bigger() throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int height() throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inOrder() throws IsEmptyException {
        System.out.println("\nIn-Order:");
        inOrder(root);
    }

    private void inOrder(Node<T> root) {
        if (root != null) {
            inOrder(root.getBack());
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
            inOrder(root.getNext());
        }
    }

    @Override
    public void isEmpty() throws IsEmptyException {
        if (root == null) {
            throw new IsEmptyException("Esmpty tree.");
        }
    }

    @Override
    public T depthFirstSearch(T value) throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posOrder() throws IsEmptyException {
        System.out.println("\nPos-Order:");
        posOrder(root);
    }

    private void posOrder(Node<T> root) {
        if (root != null) {
            posOrder(root.getBack());
            posOrder(root.getNext());
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
        }
    }

    @Override
    public void preOrder() throws IsEmptyException {
        System.out.println("\nPre-Order:");
        preOrder(root);
    }

    private void preOrder(Node<T> root) {
        if (root != null) {
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
            preOrder(root.getBack());
            preOrder(root.getNext());
        }
    }

    @Override
    public boolean remove(T value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T search(T value) throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T smaller() throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int width() throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
