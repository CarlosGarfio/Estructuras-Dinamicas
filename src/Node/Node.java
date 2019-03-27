package Node;

public class Node<T extends Comparable<T>> {

    private T value;
    private Node<T> next;
    private Node<T> back;
    private long cont;
    private long level;
    private long height;
    private long bf;

    /**
     *
     * @param value Valor a setear.
     * @param next Valor siguiente.
     * @param back Valor anterior.
     */
    public Node(T value, Node<T> next, Node<T> back) {
        this.value = value;
        this.next = next;
        this.back = back;
        this.level = 0l;
        this.cont = 0l;
    }

    public Node() {
        this.value = null;
        this.next = null;
        this.back = null;
        this.level = -1l;
        this.cont=-1l;
    }

    public Node(T value) {
        this(value, null, null);

    }

    public Node<T> getBack() {
        return back;
    }

    public void setBack(Node<T> back) {
        this.back = back;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public long getCont() {
        return cont;
    }

    public void setCont(long cont) {
        this.cont = cont;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getBf() {
        return bf;
    }

    public void setBf(long bf) {
        this.bf = bf;
    }

    public String getText() {
        return this.value.toString() + " {L=" + this.level + ",C=" + this.cont + ",H=" + this.height + "}";
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }
}
