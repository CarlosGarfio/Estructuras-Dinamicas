package Ejercicios;

import List.LinkedList;
import Node.Node;

public class Ejercicio_4<T extends Comparable<T>> {

    static private long suma = 0;

    public long Suma(LinkedList<Integer> list) {
        suma = 0;
        if (list.GetElementAt(0) != null)
            Suma(list.GetElementAt(0));
        return suma;
    }

    private boolean Suma(Node<Integer> node) {
        if (node.getNext() == null) {
            suma = suma + node.getValue();
            return true;
        } else {
            suma = suma + node.getValue();
            return Suma(node.getNext());
        }
    }
}
