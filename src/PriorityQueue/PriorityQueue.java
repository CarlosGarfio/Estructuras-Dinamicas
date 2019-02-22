package PriorityQueue;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import QueueArray.QueueMain;

public class PriorityQueue<T> {

    static QueueMain<Double>[] a = new QueueMain[5];

    enum priority {
        Muy_alta, Alta, Media, Baja, Muy_baja
    };

    public static void Print() {
        System.out.println("-----Prioridad-----");

        for (int i = 0; i < a.length; i++) {
            try {
                a[i].IsEmpty();
                System.out.print("Prioridad " + priority.values()[i] + ": ");
                for (Double d : a[i]) {
                    System.out.print("\t" + d);
                }
                System.out.println("");
            } catch (IsEmptyException e) {
                System.out.println("Prioridad " + priority.values()[i] + " vacia.");
            }
        }
        System.out.println("-------------------");

    }

    public static void Remove(int prioridad) {
        if (prioridad >= 0 && prioridad < a.length) {
            System.out.println("Limpiando la prioridad: " + priority.values()[prioridad]);
            try {
                a[prioridad].IsEmpty();
                System.out.println(a[prioridad].DeQueue());
            } catch (IsEmptyException e) {
                System.out.println("La prioridad " + priority.values()[prioridad] + " esta vacia.");
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
            a[i] = new QueueMain<>(Double.class, 100);
        }
    }

    public static void Random() {
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 5);
            double random = (double) (Math.random() * 20) + 1;
            Insert(priority.values()[x].ordinal(), random);
        }
    }

    public static void main(String[] args) {
        Init();
        Random();
        Print();
        for (int i = 0; i < 5; i++) {
            Remove(0);
        }
        
        Print();
        //int valorEntero = Math.floor(Math.random()*(N-M+1)+M); NUMERO RANDOM
    }
}
