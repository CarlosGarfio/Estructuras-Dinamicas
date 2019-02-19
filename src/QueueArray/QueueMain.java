package QueueArray;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import Queue.Queue;
import java.lang.reflect.Array;
import java.util.Iterator;

public class QueueMain<T extends Comparable<T>> implements Queue<T>, Iterable<T> {

    private T[] queue;
    private int size;
    private int front = -1;
    private int back = 0;
    private int count = 0;
    private Class<T> type = null;

    public QueueMain(Class<T> clazz, int size) {
        this.type = clazz;
        this.size = size;
        this.queue = (T[]) Array.newInstance(clazz, size);
    }

    @Override
    public boolean EnQueue(T value) throws IsFullException {
        try {
            IsFull();
            count++;
            queue[back++ % size] = value;
            return true;
        } catch (IsFullException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    @Override
    public T DeQueue() throws IsEmptyException {
        try {
            IsEmpty();
            count--;
            return queue[++front % size];
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean RemoveAll() throws IsEmptyException {
        front = -1;
        back = 0;
        count = 0;
        return true;
    }

    @Override
    public void IsFull() throws IsFullException {
        if (count == size) {
            throw new IsFullException("Full queue");
        }
    }

    @Override
    public T Front() throws IsEmptyException {
        return queue[(front + 1) % size];
    }

    @Override
    public T Last() throws IsEmptyException {
        return queue[(back - 1) % size];
    }

    @Override
    public void IsEmpty() throws IsEmptyException {
        if (count == 0) {
            throw new IsEmptyException("Empty queue.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
        int _count=count;
        int _front=front;
            @Override
            public boolean hasNext() {
                return (_count-- != 0);
            }

            @Override
            public T next() {
                return queue[++_front % size];
            }
        };
    }
}
