package List;

import Excepciones.IsEmptyException;
import Node.Node;
import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements Iterable<T>, Metodos<T> {

    private Node<T> head;
    private long length = -1;

    public LinkedList(Node<T> _new) {
        this();
        this.head.setNext(_new);
    }

    public LinkedList() {
        this.head = new Node<>();
    }

    @Override
    public boolean Add(T value) {
        Node<T> _new = new Node<>(value);
        try {
            Node<T> tmp = null;
            IsEmpty();
            tmp = GetLastElement(this.head); //necesito el nodo que en su next es null para agregarle el nuevo
            tmp.setNext(_new);
            length++;
        } catch (IsEmptyException e) {
            head.setNext(_new);
            length++;
        }
        return false;
    }

    @Override
    public boolean Add(Node<T> node) {
        return Add(node.getValue());
    }

    @Override
    public boolean AddStart(T value) {
        Node<T> _new = new Node<>(value);
        try {
            IsEmpty();
            Node<T> tmp = head.getNext();
            head.setNext(_new);
            _new.setNext(tmp);
            length++;
            return true;
        } catch (IsEmptyException e) {
            head.setNext(_new);
            length++;
            return true;
        }
    }

    @Override
    public boolean AddStart(Node<T> node) {
        return AddStart(node.getValue());
    }

    @Override
    public boolean AddAfter(T after, T value) {
        try {
            IsEmpty();
            Node<T> tmp, _new;
            if ((tmp = GetPrevElement(head, after)) != null) {
                _new = new Node<T>(value);
                _new.setNext(tmp.getNext().getNext());
                tmp.getNext().setNext(_new);
                length++;
                System.gc();
                return true;
            } else {
                return false;
            }
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean AddAt(T value, int position) {
        try {
            IsEmpty();
            if (position >= Length()) {
                return false;
            } else {
                Node<T> _new = new Node<>(value);
                Node<T> next = GetElementAt(position);
                Node<T> prev = GetElementAt((position - 1));
                prev.setNext(_new);
                _new.setNext(next);
                length++;
                return true;
            }
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean AddAt(Node<T> node, int position) {
        return AddAt(node.getValue(), position);
    }

    @Override
    public boolean RemoveAll(T value) {
        boolean flag = false;
        try {
            IsEmpty();
            while (IsThere(head, value)) {
                Remove(value);
                flag = true;
            }
            return flag;
        } catch (IsEmptyException e) {
            return flag;
        }
    }

    @Override
    public boolean Remove(T value) {
        try {
            IsEmpty();
            Node<T> tmp = GetPrevElement(head, value);
            if (tmp != null) {
                tmp.setNext(tmp.getNext().getNext());
                length--;
                System.gc();
                return true;
            } else {
                return false;
            }
        } catch (IsEmptyException e) {
            return false;
        }
    }

    
    @Override
    public boolean Remove(Node<T> node) {
        return Remove(node.getValue());
    }
    
    public boolean RemoveFirst(){
        try {
            IsEmpty();
            if(head.getNext() != null){
                Remove(head.getNext());
                return true;
            }
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean AddBefore(T before, T value) {
        try {
            if (IsThere(head, value)) {
                IsEmpty();
                Node<T> _new = new Node<>(value);
                Node<T> prev = GetPrevElement(head, before);
                Node<T> next = prev.getNext();
                prev.setNext(_new);
                _new.setNext(next);
                length++;
                return true;
            } else {
                return false;
            }
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean RemoveAfter(T value) {
        try {
            IsEmpty();
            if (IsThere(head, value)) {
                return Remove(GetPrevElement(head, value));
            }
            return false;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean RemoveBefore(T value) {
        try {
            IsEmpty();
            if (IsThere(head, value)) {
                return Remove(GetPrevElement(head, value));
            }
            return false;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public Node<T> GetElementAt(int value) {
        return GetElementAt(head, 0, value);
    }

    private Node<T> GetElementAt(Node<T> node, int index, int value) {
        if (node.getNext() == null) {
            return null;
        } else {
            if (value == index) {
                return node.getNext();
            } else {
                if (index >= value) {
                    return null;
                } else {
                    return GetElementAt(node.getNext(), ++index, value);
                }
            }
        }
    }

    @Override
    public boolean IsThere(Node<T> node, T value) {
        if (node.getNext() == null) {
            return false;
        } else if (node.getNext().getValue().equals(value)) {
            return true;
        }
        return IsThere(node.getNext(), value);
    }

    @Override
    public long Length() {
        return this.length;
    }

    @Override
    public Node<T> GetPrevElement(Node<T> node, T value) {
        if (node.getNext() == null) {
            return null;
        }
        if (node.getNext().getValue().equals(value)) {
            return node;
        } else {
            return GetPrevElement(node.getNext(), value);
        }
    }

    @Override
    public T GetLastElement() {
        return GetLastElement(head).getValue();
    }

    private Node<T> GetLastElement(Node<T> tmp){
        if (tmp.getNext() == null) {
            return tmp;
        } else {
            return GetLastElement(tmp.getNext());
        }
    }

    @Override
    public boolean IsEmpty() throws IsEmptyException {
        if (head.getNext() == null) {
            throw new IsEmptyException("La lista esta vacia");
        } else {
            return true;
        }
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
}
