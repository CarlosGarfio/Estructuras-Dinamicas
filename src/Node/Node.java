/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Node;

/**
 *
 * @author LS12DOCENTE
 * @param <T>
 */
public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> next;
    private Node<T> back;

    public Node<T> getBack() {
        return back;
    }

    public void setBack(Node<T> back) {
        this.back = back;
    }
public Node(T value, Node<T> next,Node<T> back) {
        this.value = value;
        this.next  = next;
        this.back  = back;
    }
    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
    public Node(){
        this.value =  null;
        this.next  =  null;
        this.back  = null;
    }
    public Node(T value){
        this.value = value;
        
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
    
}
