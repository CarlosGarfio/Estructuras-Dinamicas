package List;

import Excepciones.IsEmptyException;
import Node.Node;
import java.util.Iterator;

public interface Metodos<T extends Comparable<T>> {

    public boolean Add(T value);

    public boolean Add(Node<T> node);

    public boolean AddStart(T value);

    public boolean AddStart(Node<T> node);

    public boolean AddAt(T value, int position);

    public boolean AddAt(Node<T> node, int position);

    public boolean AddAfter(T after, T value);

    public boolean RemoveAll(T value);

    public boolean Remove(T value);

    public boolean Remove(Node<T> node);

    public boolean AddBefore(T before,T value);

    public boolean RemoveAfter(T value);

    public boolean RemoveBefore(T value);

    public Node<T> GetElementAt(int value);

    public boolean IsEmpty() throws IsEmptyException;

    boolean IsThere(Node<T> node, T value);

    public long Length();

    Node<T> GetPrevElement(Node<T> node, T value);

    Node<T> GetLastElement(Node<T> tmp);

    public Iterator<T> iterator();
}
