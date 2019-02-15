package Ejercicios;

import List.Metodos;
import Node.Node;

public class Ejercicio_2<T extends Comparable<T>> {

    public boolean ExisteElemento(T value, Metodos<T> list) {
        if (ExisteElemento(value, list.GetElementAt(0))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean ExisteElemento(T value, Node<T> node) {
        if (node.getNext() == null) {
            return false;
        } else {
            if (value.equals(node.getValue())) {
                return true;
            } else {
                return ExisteElemento(value, node.getNext());
            }
        }
    }
}
