package stackArray;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import stack.Stack;

public class StackArray<T> implements Stack<T>, Iterable<T>, Comparable<T>, Comparator<T> {

    private T[] stack = null;
    private Class<T> type = null;
    private long capacity = 0;
    private int tope = -1;

    public StackArray(Class<T> clazz) {
        this(clazz, 20);
    }

    public StackArray(Class<T> clazz, int capacity) {
        this.stack = stack;
        this.capacity = capacity;
        stack = (T[]) Array.newInstance(clazz, capacity);
    }

    @Override
    public long size() {
        return tope + 1;
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
                stack[++tope] = value;
            }
        }
    }

    private boolean resize() {
        Scanner leer = new Scanner(System.in);
        String resp;
        System.out.println("¿Desea incrementar el tamaño del arreglo?");
        resp = leer.next();
        if (resp.toLowerCase().equals("s") || resp.toLowerCase().equals("si")) {
            stack = Arrays.copyOf(stack, stack.length+1);
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
        return stack[tope--];
    }

    @Override
    public T peek() throws IsEmptyException {
       try {
            isEmpty();
        } catch (IsEmptyException e) {
            throw new IsEmptyException(e.getMessage());
        }
        return stack[tope];
    }

    @Override
    public void isEmpty() throws IsEmptyException {
        if (tope < 0) {
            throw new IsEmptyException("Empty stack.");
        }
    }

    @Override
    public void isFull() throws IsFullException {
        if (tope == capacity - 1) {
            throw new IsFullException("Full stack.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int epot=tope;
            @Override
            public boolean hasNext() {
                return (epot != -1)?true:false;
            }

            @Override
            public T next() {
                return stack[epot--];
            }
        };
    }

    @Override
    public int compareTo(T arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(T arg0, T arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public static void main(String[] args) {
        StackArray<Double> pila = new StackArray<>(Double.class, 3);
        try {
            pila.push(10d);
            pila.push(20d);
            pila.push(30d);
            pila.push(40d);
            for (Double double1 : pila) {
                System.out.print(double1+"\t");
            }
            System.out.println("El proximo en sacar es: "+pila.peek());
            System.out.println("Sacando el valor "+pila.pop());
            
            for (Double double1 : pila) {
                System.out.print(double1+"\t");
            }
        } catch (IsFullException | IsEmptyException e) {
            System.err.println(e.getMessage());
        }

    }

}
