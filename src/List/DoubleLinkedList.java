package List;

import Excepciones.IsEmptyException;
import Node.Node;
import java.util.Iterator;

public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T>, Metodos<T> {

    private Node<T> head, tail;
    private long lenght;

    public DoubleLinkedList() {
        this.head = new Node<>();
        this.tail = new Node<>();
        lenght = -1;
    }

    @Override
    public boolean Add(T value) {
        Node<T> _new = new Node<>(value);
        if (_new == null) {
            return false;
        }
        try {
            IsEmpty();
            Node<T> tmp = tail.getBack();
            tmp.setNext(_new);
            _new.setBack(tmp);
            tail.setBack(_new);
        } catch (IsEmptyException e) {
            head.setNext(_new);
            tail.setBack(_new);
        }
        lenght++;
        return true;
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
            Node<T> next = head.getNext();
            head.setNext(_new);
            _new.setNext(next);
            next.setBack(_new);
        } catch (IsEmptyException e) {
            head.setNext(_new);
            tail.setBack(_new);
        }
        lenght++;
        return true;
    }

    @Override
    public boolean AddStart(Node<T> node) {
        return AddStart(node.getValue());
    }

    @Override
    public boolean AddAt(T value, int position) {
        try {
            IsEmpty();
            Node<T> _new = new Node<>(value);
            if (position >= lenght) {
                return false;
            } else if (position == 0) {
                Node<T> next = head.getNext();
                head.setNext(_new);
                _new.setNext(next);
                next.setBack(_new);
            } else if (position == lenght) {
                Node<T> prev = tail.getBack();
                tail.setBack(_new);
                _new.setBack(prev);
                prev.setNext(_new);
            } else {
                Node<T> next = GetElementAt(position);
                Node<T> prev = next.getBack();
                prev.setNext(_new);
                _new.setBack(prev);
                _new.setNext(next);
                next.setBack(_new);
            }
            lenght++;
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean AddAt(Node<T> node, int position) {
        return AddAt(node.getValue(), position);
    }

    @Override
    public boolean AddAfter(T after, T value) {
        try {
            IsEmpty();
            Node<T> _new = new Node<>(value);
            Node<T> prev = IsThereNode(head, after);
            if (prev != null) {
                if (prev.getNext() == null) {
                    prev.setNext(_new);
                    tail.setBack(_new);
                    _new.setNext(null);
                } else {
                    Node<T> next = prev.getNext();
                    prev.setNext(_new);
                    _new.setBack(prev);
                    _new.setNext(next);
                    next.setBack(_new);
                }
                lenght++;
                return true;
            } else {
                return false;
            }
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean RemoveAll(T value) {
         try {
            IsEmpty();
            while (IsThere(head, value)) {
                Remove(value);
            }
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean Remove(T value) {
        try {
            IsEmpty();
            Node<T> tmp = GetPrevElement(head, value);
            if (tmp != null) {
                if (tmp.getNext().getNext() == null && tmp.getNext().getBack() == null) {
                    head.setNext(null);
                    tail.setBack(null);
                } else if (tmp.getNext().getBack() == null) {
                    tmp.getNext().getNext().setBack(null);
                    head.setNext(tmp.getNext().getNext());
                } else if (tmp.getNext().getNext() == null) {
                    tmp.setNext(null);
                    tail.setBack(tmp);
                } else {
                    tmp.setNext(tmp.getNext().getNext());
                    tmp.getNext().setBack(tmp);
                }
                lenght--;
                System.gc();
            }
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    public boolean RemoveFirst() {
        try {
            IsEmpty();
            Node<T> tmp = head;
            if (tmp != null) {
                if (tmp.getNext() == null && tail.getBack() == null) {
                    head.setNext(null);
                    tail.setBack(null);
                } else {
                    tmp.getNext().setBack(null);
                    head.setNext(tmp.getNext());
                }
                lenght--;
                System.gc();
            }
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }
    
    @Override
    public boolean Remove(Node<T> node) {
        return Remove(node.getValue());
    }

    @Override
    public boolean AddBefore(T before, T value) {
        try {
            IsEmpty();
            Node<T> _new = new Node<>(value);
            Node<T> next = IsThereNode(head, before);
            if (next.getBack() == null) {
                head.setNext(_new);
                _new.setNext(next);
                next.setBack(_new);
            } else {
                Node<T> prev = next.getBack();
                prev.setNext(_new);
                _new.setBack(prev);
                _new.setNext(next);
                next.setBack(_new);
            }
            lenght++;
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean RemoveAfter(T value) {
        try {
            IsEmpty();
            Node<T> tmp = IsThereNode(head, value);
            if (tmp != null) {
                return RemoveAfter(tmp);
            }
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    private boolean RemoveAfter(Node<T> node) {
        Node<T> tmp = node;
        if (tmp.getNext() == null) {
            return false;
        } else {
            if (tmp.getNext().getNext() == null) {
                tmp.setNext(null);
                tail.setBack(tmp);
            } else {
                tmp.setNext(tmp.getNext().getNext());
                tmp.setBack(tmp);
                tmp.getNext().setBack(tmp);
            }
            lenght--;
            return true;
        }
    }

    @Override
    public boolean RemoveBefore(T value) {
        try {
            IsEmpty();
            Node<T> tmp = IsThereNode(head, value);
            if (tmp != null) {
                return RemoveBefore(tmp);
            }
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }
    
    private boolean RemoveBefore(Node<T> node) {
        try {
            IsEmpty();
            if (node.getBack() == null) {
                head.setNext(node);
                node.setBack(null);
            } else {
                Node<T> prev = node.getBack().getBack();
                prev.setNext(node);
                node.setBack(prev);
            }
            System.gc();
            lenght--;
            return true;
        } catch (IsEmptyException e) {
            return false;
        }
    }

    @Override
    public Node<T> GetElementAt(int value) {
        return GetElementAt(head, 0, value);
    }
    
    

    private Node<T> GetElementAt(Node<T> node, int i, int value) {
        if (node.getNext() == null) {
            return null;
        } else if (value == i) {
            return node.getNext();
        } else if (i >= value) {
            return null;
        } else {
            return GetElementAt(node.getNext(), ++i, value);
        }
    }

    @Override
    public boolean IsEmpty() throws IsEmptyException {
        if (head.getNext() == null) {
            throw new IsEmptyException("Lista vacia.");
        } else {
            return false;
        }
    }

    @Override
    public boolean IsThere(Node<T> node, T value) {
        if (node.getNext() == null) {
            return false;
        } else if (node.getNext().getValue().equals(value)) {
            return true;
        } else {
            return IsThere(node.getNext(), value);
        }
    }

    @Override
    public long Length() {
        return lenght;
    }

    @Override
    public Node<T> GetPrevElement(Node<T> node, T value) {
        if (node.getNext().getValue().equals(value)) {
            return node;
        } else {
            if (node.getNext() == null) {
                return null;
            } else {
                return GetPrevElement(node.getNext(), value);
            }
        }
    }

    @Override
    public T GetLastElement() {
        return GetLastElement(tail).getValue();
    }

    private Node<T> GetLastElement(Node<T> tmp){
        if (tmp.getBack() == null) {
            return null;
        } else {
            return tmp.getBack();
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> Y = head.getNext(), X;

            @Override
            public boolean hasNext() {
                if (Y == null) {
                    return false;
                } else {
                    X = Y;
                    Y = Y.getNext();
                    return true;
                }
            }

            @Override
            public T next() {
                return X.getValue();
            }
        };
    }

    private Node<T> IsThereNode(Node<T> node, T value) {
        try {
            IsEmpty();
            if (node.getNext() == null) {
                if (node.getValue().equals(value)) {
                    return node;
                }
                return null;
            } else if (node.getNext().getValue().equals(value)) {
                return node.getNext();
            } else {
                return IsThereNode(node.getNext(), value);
            }
        } catch (IsEmptyException e) {
            return null;
        }
    }
}
