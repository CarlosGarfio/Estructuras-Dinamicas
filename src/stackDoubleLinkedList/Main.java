package stackDoubleLinkedList;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;

public class Main {

    public static void main(String[] args) {
        StackDoubleLinkedList<Double> pila = new StackDoubleLinkedList<>(255L);
        try {
            pila.push(10d);
            pila.push(20d);
            pila.push(30d);
            pila.push(40d);
            pila.push(50d);

            for (Double double1 : pila) {
                System.out.println(double1);
            }

            System.out.println("El proximo en sacar es: " + pila.peek());
            System.out.println("Sacando el valor " + pila.pop());

            for (Double double1 : pila) {
                System.out.println(double1);
            }
            
        } catch (IsFullException | IsEmptyException e) {
            System.err.println(e.getMessage());
        }
    }

}
