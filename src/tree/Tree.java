package tree;

import Excepciones.IsEmptyException;

public interface Tree<T> {

    boolean add(T value);

    void balance() throws IsEmptyException;
    
    void beetwen(T start, T end)throws IsEmptyException;

    T bigger() throws IsEmptyException;

    int height() throws IsEmptyException;
    
    void inOrder() throws IsEmptyException;

    void isEmpty() throws IsEmptyException;
    
    T depthFirstSearch(T value) throws IsEmptyException;

    void posOrder() throws IsEmptyException;

    void preOrder() throws IsEmptyException;

    boolean remove(T value);

    T search(T value) throws IsEmptyException;

    T smaller() throws IsEmptyException;
    
    int width() throws IsEmptyException;

}
