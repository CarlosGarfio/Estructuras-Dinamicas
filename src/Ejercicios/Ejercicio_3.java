package Ejercicios;

import List.LinkedList;
import Node.Node;

public class Ejercicio_3<T extends Comparable<T>> {

    static private int count = 0;

    public int Ocurrencia(T value, LinkedList<T> list) {
        if (list.GetElementAt(0) != null) {
            Ocurrencia(value, list.GetElementAt(0));
        }
        return count;
    }

    private boolean Ocurrencia(T value, Node<T> node) {
        if (node.getNext() == null) {
            if (node.getValue().equals(value)) {
                count++;
            }
            return true;
        } else {
            if (value.equals(node.getValue())) {
                count++;
                return Ocurrencia(value, node.getNext());
            } else {
                return Ocurrencia(value, node.getNext());
            }
        }
    }

}
