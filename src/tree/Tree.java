package tree;

import Excepciones.IsEmptyException;
import Node.Node;

public interface Tree<T extends Comparable<T>> {

    boolean add(T value);

    void balance() throws IsEmptyException;
    
    void beetwen(T start, T end)throws IsEmptyException;

    T bigger() throws IsEmptyException;

    T depthFirstSearch(T value) throws IsEmptyException;
    
    int height() throws IsEmptyException;
    
    void inOrder() throws IsEmptyException;

    void isEmpty() throws IsEmptyException;
    
    void lvlUpdate();
    
    void posOrder() throws IsEmptyException;

    void preOrder() throws IsEmptyException;

    boolean remove(T value);

    Node<T> search(T value) throws IsEmptyException;

    T smaller() throws IsEmptyException;
    
    int width() throws IsEmptyException;

}
