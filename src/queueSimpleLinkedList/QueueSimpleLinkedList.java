package queueSimpleLinkedList;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import List.LinkedList;
import Queue.Queue;
import java.util.Iterator;

public class QueueSimpleLinkedList<T extends Comparable<T>> implements Queue<T>, Iterable<T> {

    private LinkedList<T> queue;
    private int size;
    private int front;
    private int back;
    private int count;

    public QueueSimpleLinkedList(int size) {
        queue = new LinkedList<>();
        this.size = size;
        this.count = 0;
        this.front = -1;
        this.back = 0;
        for (int i = 0; i < size; i++) {
            queue.Add((T) null);
        }
    }

    @Override
    public boolean EnQueue(T value) throws IsFullException {
        try {
            IsFull();
            count++;
            queue.GetElementAt(back++ % size).setValue(value);
            return true;
        } catch (IsFullException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public T DeQueue() throws IsEmptyException {
        try {
            IsEmpty();
            count--;
            return queue.GetElementAt(++front % size).getValue();
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean RemoveAll() throws IsEmptyException {
        for (int i = 0; i < size; i++) {
            queue.GetElementAt(i).setValue((T) null);
        }
        this.front = -1;
        this.back = 0;
        this.count = 0;
        return true;
    }

    @Override
    public void IsFull() throws IsFullException {
        if (count == size) {
            throw new IsFullException("Full queue.");
        }
    }

    @Override
    public void IsEmpty() throws IsEmptyException {
        if (count == 0) {
            throw new IsEmptyException("Empty queue.");
        }
    }

    @Override
    public T Front() throws IsEmptyException {
        try {
            IsEmpty();
            return queue.GetElementAt((front + 1) % size).getValue();
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public T Last() throws IsEmptyException {
        try {
            IsEmpty();
            return queue.GetElementAt((back - 1) % size).getValue();
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        //return queue.iterator();

        return new Iterator<T>() {
        int _count=count;
        int _front=front;
            @Override
            public boolean hasNext() {
                return (_count-- != 0);
            }

            @Override
            public T next() {
                return queue.GetElementAt(++_front % size).getValue();
            }
        };
    }

}
