package PriorityQueue;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import QueueArray.QueueMain;

public class PriorityQueue<T> {

    static QueueMain<Double>[] a = new QueueMain[5];

    enum priority {
        alta, regular, media, baja, muyBaja
    };

    public static void Print(){
        for (QueueMain<Double> queueMain : a) {
            try {
                queueMain.IsEmpty();
                for (Double double1 : queueMain) {
                    System.out.println(double1);
                }
            } catch (IsEmptyException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public static void Insert(int priority, double dato) {
        try {
            a[priority].EnQueue(dato);
        } catch (IsFullException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void Init() {
        for (int i = 0; i < a.length; i++) {
            a[i] = new QueueMain<>(Double.class, 4);
        }
    }

    public static void main(String[] args) {
        Init();
        Insert(priority.alta.ordinal(), 1d);
        Insert(priority.alta.ordinal(), 2d);
        Insert(priority.alta.ordinal(), 3d);
        Insert(priority.alta.ordinal(), 4d);
        
        Print();

    }
}
