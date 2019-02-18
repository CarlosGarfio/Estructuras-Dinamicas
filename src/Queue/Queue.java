package Queue;

import Excepciones.*;

public interface Queue<T> {

    boolean EnQueue(T value) throws IsFullException;

    T DeQueue() throws IsEmptyException;

    boolean RemoveAll() throws IsEmptyException;

    void IsFull() throws IsFullException;

    void IsEmpty()throws IsEmptyException;
    
    T Front() throws IsEmptyException;

    T Last() throws IsEmptyException;

}
