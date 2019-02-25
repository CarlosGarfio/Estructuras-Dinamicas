package treeB;

import Excepciones.IsEmptyException;
import Node.Node;
import tree.Tree;

public class TreeB<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public TreeB (T value){
        this.root=new Node<>(value);
    }
    
    public TreeB (Node<T> node){
        this.root= node;
    }
    @Override
    public boolean add(T value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void isEmpty() throws IsEmptyException {
        if(root == null) throw new IsEmptyException("Esmpty tree.");
    }

    @Override
    public T depthFirstSearch(T value) throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posOrder() throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preOrder() throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
