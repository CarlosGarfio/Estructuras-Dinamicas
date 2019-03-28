package tree;

import Excepciones.IsEmptyException;
import Node.AVLNode;

public interface AVLTree<T extends Comparable<T>> {

    boolean add(T value);

    long beetwen(T start, T end) throws IsEmptyException;

    T bigger() throws IsEmptyException;

    long height() throws IsEmptyException;

    void inOrder() throws IsEmptyException;

    void isEmpty() throws IsEmptyException;

    T minor() throws IsEmptyException;

    void posOrder() throws IsEmptyException;

    void preOrder() throws IsEmptyException;

    boolean remove(T value) throws IsEmptyException;

    AVLNode search(T value) throws IsEmptyException;
}
