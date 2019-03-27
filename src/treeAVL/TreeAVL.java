//https://grokonez.com/java/balanced-tree-avl-tree-java
package treeAVL;

import Excepciones.IsEmptyException;
import Node.Node;
import bTree.TreePrinter;
import tree.Tree;

public class TreeAVL<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public TreeAVL(T value) {
        this.root = new Node<>(value);
        this.root.setCont(0L);
        this.root.setLevel(0L);
    }

    public TreeAVL(Node<T> node) {
        this.root = node;
        this.root.setCont(0L);
        this.root.setLevel(0L);
    }

    /**
     *
     * @param value Recive el valor a agregar.
     * @return Retorna true si logra agregarlo. Retorna false si no logra
     * agregarlo.
     */
    @Override
    public boolean add(T value) {
        return (add(root, value) != null) ? true : false;
    }

    private Node<T> add(Node<T> node, T value) {
        if (node == null) {
            return new Node(value);
        }
        int i = value.compareTo(node.getValue());
        switch (i) {
            case -1:
                node.setBack(add(node.getBack(), value));
                break;
            case 0:
                node.setCont(node.getCont() + 1);
                break;
            case 1:
                node.setNext(add(node.getNext(), value));
                break;
        }
        node.setHeight(Math.max(height(node.getBack()), height(node.getNext())));
        return checkBalanceAndRotate(node, value); // check balance and make rotations if neccessary
    }
    
    private Node<T> checkBalanceAndRotate(Node<T> node) {
    long balance = getBalance(node);
 
    // left heavy -> left-right heavy or left-left heavy
    if (balance > 1) {
      // if left-right: left rotation before right rotation
      if (getBalance(node.getBack()) < 0) {
        node.setBack(leftRotation(node.getBack()));
      }
 
      // left-left
      return rightRotation(node);
    }
 
    // right heavy -> left-right heavy or right-right heavy
    if (balance < -1) {
      // if right-left: right rotation before left rotation
      if (getBalance(node.getNext()) > 0) {
        node.setNext(rightRotation(node.getNext()));
      }
 
      // right-right
      return leftRotation(node);
    }
 
    return node;
  }
    
    private Node<T> checkBalanceAndRotate(Node<T> node, T value) {
        long balance = getBalance(node); // balance = leftNode.height - rightNode.height

        // left-left
//        if (balance > 1 && data < node.getLeftNode().getData()) {
        if (balance > 1l && value.compareTo(node.getBack().getValue()) < 0l) {
            return rightRotation(node);
        }

        // right-right
//        if (balance < -1 && data > node.getRightNode().getData()) {
        if (balance < -1l && value.compareTo(node.getNext().getValue()) > 0l) {
            return leftRotation(node);
        }

        // left-right
//        if (balance > 1 && data > node.getLeftNode().getData()) {
        if (balance > 1l && value.compareTo(node.getBack().getValue()) > 0l) {
            node.setBack(leftRotation(node.getBack()));
            return rightRotation(node);
        }

        // right-left
//        if (balance < -1 && data < node.getRightNode().getData()) {
        if (balance < -1l && value.compareTo(node.getNext().getValue()) < 0l) {
            node.setNext(rightRotation(node.getNext()));
            return leftRotation(node);
        }
        return node;
    }

    private Node<T> rightRotation(Node<T> node) { // input C
        Node<T> newParentNode = node.getBack(); // newParentNode = B
        Node mid = newParentNode.getNext(); // store B's right node 'mid' (B < mid < C)

        newParentNode.setNext(node); // C now becomes right node of B
        node.setBack(mid); // 'mid' now becomes left node of C

        node.setHeight(Math.max(height(node.getBack()), height(node.getNext())) + 1);
        newParentNode.setHeight(Math.max(height(newParentNode.getBack()), height(newParentNode.getNext())) + 1);

        return newParentNode; // return B as the parent of A and C
    }

    private Node<T> leftRotation(Node<T> node) { // input A
        Node<T> newParentNode = node.getNext(); // newParentNode = B
        Node<T> mid = newParentNode.getBack(); // store B's left node 'mid' (A < mid < B)

        newParentNode.setBack(node); // A now becomes left node of B
        node.setNext(mid); // 'mid' now becomes right node of A

        node.setHeight(Math.max(height(node.getBack()), height(node.getNext())) + 1);
        newParentNode.setHeight(Math.max(height(newParentNode.getBack()), height(newParentNode.getNext())) + 1);

        return newParentNode; // return B as the parent of A and C
    }

    long getBalance(Node<T> node) {
        if (node == null) {
            return 0l;
        }
        return height(node.getBack()) - height(node.getNext());
    }

    private long count;

    /**
     * @param start Valor a iniciar.
     * @param end Valor a terminar.
     * @throws IsEmptyException
     * @return Retorna la cantidad de valores que hay entre LOW y HIGH.
     */
    @Override
    public long beetwen(T start, T end) throws IsEmptyException {
        count = -1l;
        beetwen(root, start, end);
        return count+1;
    }

    /**
     *
     * @param node Recive el arbol.
     * @param x Recive el valor minimo a buscar.
     * @param y Recive el valor maximo a buscar.
     */
    private void beetwen(Node<T> node, T x, T y) {
        if (node == null) {
            return;
        }
        if (node.getValue().compareTo(x) == 1) {
            beetwen(node.getBack(), x, y);
        }
        if ((node.getValue().compareTo(x) == 0 || node.getValue().compareTo(x) == 1) && (node.getValue().compareTo(y) == 0 || node.getValue().compareTo(y) == -1)) {
            if (node.getCont() > 0) {
                for (int i = 0; i < node.getCont() + 1; i++) {
                    count += 1l;
                }
            } else {
                count += 1l;
            }
        }
        if (node.getValue().compareTo(y) == -1) {
            beetwen(node.getNext(), x, y);
        }
    }

    /**
     *
     * @return Retorna el valor mas grande.
     * @throws IsEmptyException
     */
    @Override
    public T bigger() throws IsEmptyException {
        return bigger(root);
    }

    /**
     *
     * @param node Recive el arbol.
     * @return
     */
    private T bigger(Node<T> node) {
        return node.getNext() == null ? node.getValue() : bigger(node.getNext());
    }

    /**
     *
     * @return Retorna la altura del arbol.
     * @throws IsEmptyException
     */
    @Override
    public long height() throws IsEmptyException {
        return height(root);
    }

    /**
     * @param reco Arbol.
     * @param nivel Empieza en 1.
     */
    private long height(Node<T> reco) {
        return reco == null ? -1l : reco.getHeight();
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void inOrder() throws IsEmptyException {
        System.out.println("\nIn-Order:");
        inOrder(root);
    }

    private void inOrder(Node<T> root) {
        if (root != null) {
            inOrder(root.getBack());
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
            inOrder(root.getNext());
        }
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void isEmpty() throws IsEmptyException {
        if (root == null) {
            throw new IsEmptyException("Esmpty tree.");
        }
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void posOrder() throws IsEmptyException {
        System.out.println("\nPos-Order:");
        posOrder(root);
    }

    private void posOrder(Node<T> root) {
        if (root != null) {
            posOrder(root.getBack());
            posOrder(root.getNext());
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
        }
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void preOrder() throws IsEmptyException {
        System.out.println("\nPre-Order:");
        preOrder(root);
    }

    /**
     *
     * @param root Recive el arbol.
     */
    private void preOrder(Node<T> root) {
        if (root != null) {
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
            preOrder(root.getBack());
            preOrder(root.getNext());
        }
    }

    /**
     *
     * @param value Valor a remover.
     * @return true or false.
     * @throws IsEmptyException
     */
    @Override
    public boolean remove(T value) throws IsEmptyException {
        if (value == null) {
            return false;
        }
        if (search(value) != null) {
            remove(root, value);
            return true;
        }
        return false;
    }

    /**
     *
     * @param node Recive el nodo a borrar.
     * @return Retorna true si logra borrar. Retorna false si no logra borralo.
     * @throws IsEmptyException
     */
    private Node<T> remove(Node<T> node, T value) throws IsEmptyException {
        return null;
    }

    /**
     * @param value Recive el valor a buscar.
     * @return Retorna el valor si se encuentra. Retorna null si no se
     * encuentra.
     * @throws IsEmptyException
     */
    @Override
    public Node<T> search(T value) throws IsEmptyException {
        return search(value, root);
    }

    /**
     *
     * @param value Recive el valor a buscar.
     * @param root Recive el arbol.
     * @return Retorna el valor si se encuentra. Retorna null si no se
     * encuentra.
     */
    private Node<T> search(T value, Node<T> root) {
        if (root == null) {
            return null;
        } else {
            if (root.getValue().equals(value)) {
                return root;
            } else {
                return value.compareTo(root.getValue()) < 0 ? search(value, root.getBack()) : search(value, root.getNext());
            }
        }
    }

    /**
     *
     * @param root Arbol.
     * @param nivel Empieza en 0.
     */
    @Override
    public void lvlUpdate(Node<T> root, int nivel) {
    
    }

    /**
     *
     * @return Retornta el valor menor de todo el arbol.
     * @throws IsEmptyException
     */
    @Override
    public T minor() throws IsEmptyException {
        return minor(root).getValue();
    }

    /**
     *
     * @param node Recive el nodo en el que se encuentra.
     * @return Retorna el valor menor de todo el arbol.
     * @throws IsEmptyException
     */
    @Override
    public Node<T> minor(Node<T> node) throws IsEmptyException {
        if (node.getBack() == null) {
            return node;
        } else {
            return minor(node.getBack());
        }
    }

    /**
     *
     * @throws IsEmptyException
     */
    public void printLevel() throws IsEmptyException {
        long h = height();
        for (int i = 1; i <= h; i++) {
            System.out.print("Lvl " + (i - 1) + " : ");
            printLevel(root, i);
            System.out.println();
        }
    }

    /**
     *
     * @param node Recive el nodo en el que se encuentra.
     * @param level Recive su nivel.
     */
    private void printLevel(Node<T> node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.getValue() + " ");
        } else if (level > 1) {
            printLevel(node.getBack(), level - 1);
            printLevel(node.getNext(), level - 1);
        }
    }

    /**
     * @param tree Arbol.
     * @param x Cantidad de datos a agregar.
     * @param n Rango minimo.
     * @param m Rango maximo.
     */
    public void fill(TreeAVL tree, int x, int n, int m) {
        for (int i = 0; i < x; i++) {
            tree.add((int) Math.abs(Math.floor(Math.random() * (n - m + 1) + m)));
        }
    }

    @Override
    public String toString() {
        return TreePrinter.print(root);
    }
}
