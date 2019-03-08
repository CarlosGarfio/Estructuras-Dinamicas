package priorityQueueDoubleLinkedList;

import List.DoubleLinkedList;
import Excepciones.IsEmptyException;
import Excepciones.IsFullException;

public class PriorityQueue<T extends Comparable<T>> {
    
    DoubleLinkedList<T> queue= new DoubleLinkedList<>();
    
    enum priority {
        Muy_alta, Alta, Media, Baja, Muy_baja
    };

    public  void Print() {
        queue.iterator();
    }

    public void Remove(int prioridad) {
        
    }

    public void Insert(int priority, double dato) {
        
    }

    public static void Init() {
        
    }

    public static void Random() {
        
    }

}
