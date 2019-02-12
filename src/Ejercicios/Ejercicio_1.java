package Ejercicios;

import List.LinkedList;
import Node.Node;

public class Ejercicio_1<T extends Comparable<T>> {

    public boolean SonIguales(LinkedList<T> x, LinkedList<T> y) {
        if (x.Length() != y.Length()) {
            return false;
        } else {
            if (x.GetElementAt(0) != null) {
                if (Compare(x.GetElementAt(0), y.GetElementAt(0))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    private boolean Compare(Node<T> x, Node<T> y) {
        if (x.getNext() == null && y.getNext() == null) {
            return true;
        } else {
            if (x.getValue().equals(y.getValue())) {
                return Compare(x.getNext(), y.getNext());
            } else {
                return false;
            }
        }
    }

}
