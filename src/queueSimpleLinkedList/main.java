package queueSimpleLinkedList;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;

public class main {

    public static void main(String[] args) {
        QueueSimpleLinkedList<Double> queue = new QueueSimpleLinkedList<>(5);
        try {
            queue.EnQueue(10d);
            queue.EnQueue(20d);
            queue.EnQueue(30d);
            queue.EnQueue(40d);
            queue.EnQueue(50d);
            
            for (Double double1 : queue) {
                System.out.println(double1);
            }

            System.out.println("El proximo en sacar es: " + queue.Front());
            System.out.println("Sacando el valor " + queue.DeQueue());
            System.out.println("El ultimo valor es: "+queue.Last());
            queue.EnQueue(60d);
            for (Double double1 : queue) {
                System.out.println(double1);
            }
            System.out.println("El proximo en sacar es: " + queue.Front());
            System.out.println("Sacando el valor " + queue.DeQueue());
            System.out.println("El ultimo valor es: "+queue.Last());
            for (Double double1 : queue) {
                System.out.println(double1);
            }
            
        } catch (IsFullException | IsEmptyException e) {
            System.err.println(e.getMessage());
        }
    }
}
