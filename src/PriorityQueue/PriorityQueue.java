package PriorityQueue;

import Excepciones.IsEmptyException;
import List.DoubleLinkedList;
import java.util.ArrayList;

public class PriorityQueue<T> {

    ArrayList<DoubleLinkedList> queue;
    
    enum priority {
        Muy_alta, Alta, Media, Baja, Muy_baja
    };

    public PriorityQueue() {
        
        int size= priority.values().length;
        
        queue=new ArrayList<>(size);
        
        for (int i = 0; i < size; i++) {
            queue.add(new DoubleLinkedList());
        }
    }

    public void Print() {
        System.out.println("-----Prioridad-----");

        for (int i = 0; i < queue.size(); i++) {
            try {
                queue.get(i).IsEmpty();
                System.out.print("Prioridad " + priority.values()[i] + ": ");
                for (Object d : queue.get(i)) {
                    System.out.print("\t" + d);
                }
                System.out.println("");
            } catch (IsEmptyException e) {
                System.out.println("Prioridad " + priority.values()[i] + " vacia.");
            }
        }
        System.out.println("-------------------");

    }

    public void Remove(int prioridad) {
        if (prioridad >= 0 && prioridad < queue.size()) {
            System.out.println("Limpiando la prioridad: " + priority.values()[prioridad]);
            try {
                queue.get(prioridad).IsEmpty();
                System.out.println(queue.get(prioridad).RemoveFirst());
            } catch (IsEmptyException e) {
                System.out.println("La prioridad " + priority.values()[prioridad] + " esta vacia.");
            }
        }
    }
     
    public void Insert(int priority, double dato) {
        queue.get(priority).Add(dato);
    }

    public void Random() {
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 5);
            double random = (double) (Math.random() * 20) + 1;
            Insert(priority.values()[x].ordinal(), random);
        }
    }
}
