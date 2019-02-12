package Ejercicios;

import List.LinkedList;
import Node.Node;

public class Ejercicio_5<T extends Comparable<T>> {

    private T maximo = null;

    public T Maximo(LinkedList<T> list) {
        maximo = list.GetElementAt(0).getValue();
        Maximo(list.GetElementAt(0));
        return maximo;
    }

    private T Maximo(Node<T> node) {
        if (node.getNext() == null) {
            if (node.getValue().compareTo(maximo) > 0) {
                maximo = node.getValue();
            }
            return maximo;
        } else {
            if (node.getValue().compareTo(maximo) > 0) {
                maximo = node.getValue();
                return Maximo(node.getNext());
            } else {
                return Maximo(node.getNext());
            }
        }
    }
}
