/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import Excepciones.IsEmptyException;
import Node.Node;
import java.util.Iterator;

/**
 *
 * @author LS12DOCENTE
 */
public class doublelinkedList<T extends Comparable<T>> implements Iterable<T>, Comparable<T> {

    private Node<T> head, tail;
    private long length;

    public doublelinkedList() {
        head = new Node<>();
        tail = new Node<>();
        length = 0;
    }

    public doublelinkedList(T value) {
        this();
        Node<T> _new = new Node<>(value);
        head.setNext(_new);
        tail.setBack(_new);
        length++;
    }

    public doublelinkedList(Node<T> node) {
        this(node.getValue());
    }

    public boolean add(T value) {
        Node<T> _new = new Node<>(value);
        if (_new == null) {
            return false;
        }
        try {
            isEmpty();
            Node<T> tmp = tail.getBack();
            tmp.setNext(_new);
            _new.setBack(tmp);
            tail.setBack(_new);

        } catch (IsEmptyException ee) {
            head.setNext(_new);
            tail.setBack(_new);

        }
        length++;
        return true;
    }

    public boolean add(Node<T> node) {
        return add(node.getValue());
    }

    private boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null) {
            return false;
        } else if (node.getNext().getValue().equals(value)) {
            return true;
        }

        return isThere(node.getNext(), value);
    }

    private Node<T> isThereNode(Node<T> node, T value) {
        if (node.getNext() == null) {
            return null;
        } else if (node.getNext().getValue().equals(value)) {
            return node.getNext();
        }
        return isThereNode(node.getNext(), value);
    }

    public boolean removeAll(T value) {
        boolean flag = false;
        try {
            isEmpty();
            while (isThere(head, value)) {
                remove(value);
                flag = true;
            }
            return flag;
        } catch (IsEmptyException e) {
            return flag;
        }
    }

    public boolean remove(T value) {
        try {
            isEmpty();
            Node<T> tmp = getPrevElement(head, value);
            if (tmp != null) {
                if (tmp.getNext().getNext() == null && tmp.getNext().getBack() == null) {//es el unico
                    head.setNext(null);
                    tail.setBack(null);
                } else if (tmp.getNext().getBack() == null) { //primero de la lista
                    tmp.getNext().getNext().setBack(null);
                    head.setNext(tmp.getNext().getNext());
                } else if (tmp.getNext().getNext() == null) {      //ultimo de la lista
                    tmp.setNext(null);
                    tail.setBack(tmp);
                } else {
                    tmp.setNext(tmp.getNext().getNext());
                    tmp.getNext().setBack(tmp);
                }
                length--;
                System.gc(); //llamamos al
                return true;
            } else {
                return false;
            }
        } catch (IsEmptyException e) {
            return false;
        }
    }

    private Node<T> getPrevElement(Node<T> node, T value) {
        if (node.getNext() == null) {
            return null;
        }
        if (node.getNext().getValue().equals(value)) {
            return node;
        } else {
            return getPrevElement(node.getNext(), value);
        }
    }

    public boolean remove(Node<T> node) {
        return remove(node.getValue());
    }

    public boolean addAt(T value, int position) {
        return false;
    }

    public boolean addAt(Node<T> node, int position) {
        return false;
    }

    public boolean AddAfter(T after, T value) {
        return false;
    }

    public boolean addBefore(T before, T value) {
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, before);
            if (tmp == null) {
                return false;
            } else {
                Node<T> _node = new Node<T>(value);
                if (tmp.getBack() == null) { //es el inicio de la lista

                    tmp.setBack(_node);
                    head.setNext(_node);
                    _node.setNext(tmp);
                    //_node.setBack(null);
                } else {
                    tmp.getBack().setNext(_node);
                    _node.setBack(tmp.getBack());
                    tmp.setBack(_node);
                    _node.setNext(tmp);
                }
                length++;
                return true;
            }
        } catch (IsEmptyException w) {
            return false;
        }
       }

    public boolean removeAfter(T value) {
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, value);
            if (tmp != null && tmp.getNext() != null) {
                return removefrom(tmp);
            } else {
                return false;
            }

        } catch (IsEmptyException e) {
            return false;
        }
    }

    private boolean removefrom(Node<T> node) {
        Node<T> tmp = node;
        if (tmp.getNext() == null) {
            return false;
        } else {
            if (tmp.getNext().getNext() == null) {
                tmp.setNext(null);
                tail.setBack(tmp);

            } else {
                tmp.setNext(tmp.getNext().getNext());
                tmp.getNext().setBack(tmp);
            }
            return true;
        }
    }

    public boolean removeBefore(T value) {
        return false;
    }

    public boolean addStart(T value) {
        return false;
    }

    public boolean addStart(Node<T> node) {
        return addStart(node.getValue());
    }

    public Node<T> getElementAt(T value) {
        return null;
    }

    public boolean isEmpty() throws IsEmptyException {
        if (head.getNext() == null) {
            throw new IsEmptyException("La lista esta vacia");
        } else {
            return true;
        }
    }

    public long length() {
        return this.length;
    }

    public void rprint() {
        rprint(tail);
    }

    private void rprint(Node<T> node) {
        if (node.getBack() == null) {
            return;
        } else {
            System.out.println(node.getBack().getValue());
        }
        rprint(node.getBack());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cpy = head.getNext(), sub_head;

            @Override
            public boolean hasNext() {
                if (cpy == null) {
                    return false;
                } else {
                    sub_head = cpy;
                    cpy = cpy.getNext();
                    return true;
                }
            }

            @Override
            public T next() {
                return sub_head.getValue();
            }
        };
    }

    @Override
    public int compareTo(T o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
