package queueSimpleLinkedList;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import List.LinkedList;
import Queue.Queue;
import java.util.Iterator;

public class QueueSimpleLinkedList<T extends Comparable<T>> implements Queue<T>, Iterable<T> {

    private LinkedList<T> queue = new LinkedList<>();
    private int capacity;
    private int front = -1;
    private int back = 0;
    private int count;

    public QueueSimpleLinkedList(int capacity) {
        for (int i = 0; i < capacity; i++) {
            queue.Add((T)null);
        }
        
        this.capacity = capacity;
        this.count=0;
    }

    @Override
    public boolean EnQueue(T value) throws IsFullException {
        try {
            IsFull();
            count++;
            queue.GetElementAt(back++ % capacity).setValue(value);
            return true;
        } catch (IsFullException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public T DeQueue() throws IsEmptyException {
        try {
            IsFull();
            count--;
            return queue.GetElementAt(++front % capacity).getValue();
            
        } catch (IsFullException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean RemoveAll() throws IsEmptyException {
        for(int i = 0; i< capacity;i++){
            queue.GetElementAt(i).setValue(null);
        }
        this.front=-1;
        this.back=0;
        this.count=0;
        return true;
    }

    @Override
    public void IsFull() throws IsFullException {
        if (count == capacity) {
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
            return queue.GetElementAt((front+1) % capacity).getValue();
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public T Last() throws IsEmptyException {
        try {
            IsEmpty();
            return queue.GetElementAt((back+1)% capacity).getValue();
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }

}
