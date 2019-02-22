package stackDoubleLinkedList;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import List.DoubleLinkedList;
import java.util.Iterator;
import java.util.Scanner;
import stack.Stack;

public class StackDoubleLinkedList<T extends Comparable<T>> implements Stack<T>, Iterable<T> {

    private DoubleLinkedList lista = new DoubleLinkedList();
    private long capacity = 0L;

    public StackDoubleLinkedList(long tamanno) {
        this.capacity = tamanno;
    }

    @Override
    public long size() {
        return lista.Length();
    }

    @Override
    public void push(T value) throws IsFullException {
        boolean flag = true;
        try {
            isFull();
        } catch (IsFullException e) {
            System.err.println(e.getMessage());
            flag = resize();
        } finally {
            if (flag) {
                lista.Add(value);
            }
        }
    }

    private boolean resize() {
        Scanner leer = new Scanner(System.in);
        String resp;
        System.out.println("¿Desea incrementar el tamaño del arreglo?");
        resp = leer.next();
        if (resp.toLowerCase().equals("s") || resp.toLowerCase().equals("si")) {
            capacity++;
            return true;
        }
        return false;
    }

    @Override
    public T pop() throws IsEmptyException {
        try {
            isEmpty();
        } catch (IsEmptyException e) {
            throw new IsEmptyException(e.getMessage());
        }
        T tmp = (T) lista.GetLastElement();
        if (lista.Remove(lista.GetLastElement())) {
            return tmp;
        } else {
            return null;
        }
    }

    @Override
    public T peek() throws IsEmptyException {
        try {
            isEmpty();
        } catch (IsEmptyException e) {
            throw new IsEmptyException(e.getMessage());
        }
        return (T) lista.GetLastElement();
    }

    @Override
    public void isEmpty() throws IsEmptyException {
        if (lista.Length() < 0) {
            throw new IsEmptyException("Empty stack.");
        }
    }

    @Override
    public void isFull() throws IsFullException {
        if (lista.Length() == capacity - 1) {
            throw new IsFullException("Full stack.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        
        return lista.iterator();

    }
}
