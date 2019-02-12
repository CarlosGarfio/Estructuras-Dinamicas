package stack;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;

public interface Stack<T> {

    long size();

    void push(T value) throws IsFullException;

    T pop() throws IsEmptyException;

    T peek() throws IsEmptyException;

    void isEmpty() throws IsEmptyException;

    void isFull() throws IsFullException;

}
