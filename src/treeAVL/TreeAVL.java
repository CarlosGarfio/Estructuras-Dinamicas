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
        boolean tmp;
        if (add(root, value, 0l) != null) {
            tmp = true;
        } else {
            tmp = false;
        }
        lvlUpdate(root, 1);
        return tmp;
    }

    private Node<T> add(Node<T> node, T value, long lvl) {
        if (node == null) {
            return new Node<>(value);
        }

        if (value.compareTo(node.getValue()) == -1) {
            node.setBack(add(node.getBack(), value, ++lvl));
        } else if (value.compareTo(node.getValue()) == 0) {
            node.setCont(node.getCont()+1l);
        } else {
            node.setNext(add(node.getNext(), value, ++lvl));
        }

        node.setLevel(Math.max(height(node.getBack()), height(node.getNext())) + 1);

        return balanceAndRotate(node); // check balance and make rotations if neccessary
    }

//    private Node<T> add(Node<T> node, T value,long lvl) {
//        if (node == null) {
//            node = new Node<>(value,null,null);
//            node.setCont(0l);
//            node.setLevel(lvl);
//            //return node;
//        } else if (value.compareTo(node.getValue()) < 0) {
//            node.setBack(add(node.getBack(), value,++lvl));
//            if (height(node.getBack()) - height(node.getNext()) == 2) {
//                if (value.compareTo(node.getBack().getValue()) < 0) {
//                    node = rotateWithLeftChild(node);
//                    /* Caso 1 */
//                } else {
//                    node = doubleWithLeftChild(node);
//                    /* Caso 2 */
//                }
//            }
//        } else if (value.compareTo(node.getValue()) > 0) {
//            node.setNext(add(node.getNext(), value,++lvl));
//            if (height(node.getNext()) - height(node.getBack()) == 2) {
//                if (value.compareTo(node.getNext().getValue()) > 0) {
//                    node = rotateWithRightChild(node);
//                    /* Caso 4 */
//                } else {
//                    node = doubleWithRightChild(node);
//                    /* Caso 3 */
//                }
//            }
//        } 
//        //Duplicado
//        node.setCont(Long.max(height(node.getBack()), height(node.getNext())) + 1);
//
//        return node;
//    }
    
    private Node balanceAndRotate(Node node) {
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

    private Node<T> balanceAndRotate(Node<T> node, T value) {
        long balance = getBalance(node); // balance = leftNode.height - rightNode.height

        // left-left
        if (balance > 1 && value.compareTo(node.getBack().getValue()) == -1) {
            return rightRotation(node);
        }
        // right-right
        if (balance < -1 && value.compareTo(node.getNext().getValue()) == 1) {
            return leftRotation(node);
        }
        // left-right
        if (balance > 1 && value.compareTo(node.getBack().getValue()) == 1) {
            node.setBack(leftRotation(node.getBack()));
            return rightRotation(node);
        }
        // right-left
        if (balance < -1 && value.compareTo(node.getNext().getValue()) == -1) {
            node.setNext(rightRotation(node.getNext()));
            return leftRotation(node);
        }
        return node;
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
        return count + 1;
    }

    private void beetwen(Node node, T x, T y) {
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
     * @param node Arbol.
     * @param nivel Empieza en 1.
     */
    private long height(Node<T> node) {
        return node == null ? -1l : node.getLevel();
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

    private Node<T> leftRotation(Node<T> node) {

        Node<T> newParentNode = node.getNext();
        Node<T> mid = newParentNode.getBack();

        newParentNode.setBack(node);
        node.setNext(mid);

        node.setLevel(Math.max(height(node.getBack()), height(node.getNext())) + 1);
        newParentNode
                .setLevel(Math.max(height(newParentNode.getBack()), height(newParentNode.getNext())) + 1);

        return newParentNode;
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
        Node<T> tmp;
        try {
            if ((tmp = search(value)) != null) {
                if (remove(root, tmp) != null) {
                    return true;
                }
            }
        } catch (IsEmptyException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    private Node<T> remove(Node<T> node, Node<T> value) throws IsEmptyException {
        if (node == null) {
            return null;
        }

        if (value.getValue().compareTo(node.getValue()) == -1) { // go to the left recursively
            node.setBack(remove(node.getBack(), value));
        } else if (value.getValue().compareTo(node.getValue()) == 1) { // go to the right recursively
            node.setNext(remove(node.getNext(), value));
        } else { // find node
            if (node.getBack() == null && node.getNext() == null) {
                return null;
            }
            if (node.getBack() == null) {
                Node<T> tempNode = node.getNext();
                node = null;
                return tempNode;
            } else if (node.getNext() == null) {
                Node tempNode = node.getBack();
                node = null;
                return tempNode;
            }
            Node<T> tempNode = getPredecessor(node.getBack());

            node.setValue(tempNode.getValue());
            node.setBack(remove(node.getBack(), tempNode));
        }
        node.setLevel(Math.max(height(node.getBack()), height(node.getNext())) + 1);
        return balanceAndRotate(node);
    }

    private Node<T> getPredecessor(Node<T> node) {
        Node<T> predecessor = node;
        while (predecessor.getNext() != null) {
            predecessor = predecessor.getNext();
        }
        return predecessor;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> newParentNode = node.getBack();
        Node<T> mid = newParentNode.getNext();
        newParentNode.setNext(node);
        node.setBack(mid);
        node.setLevel(Math.max(height(node.getBack()), height(node.getNext())) + 1);
        newParentNode.setLevel(Math.max(height(newParentNode.getBack()), height(newParentNode.getNext())) + 1);
        return newParentNode;
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
     * @param root Arbol.
     * @param nivel Empieza en 0.
     */
    @Override
    public void lvlUpdate(Node<T> root, int nivel) {
        if (root != null) {
            root.setLevel(nivel);
            heigth(root.getBack(), nivel + 1);
            root.setLevel(nivel);
            heigth(root.getNext(), nivel + 1);

        }
    }

    private int altura = 0;

    /**
     * @param reco Arbol.
     * @param nivel Empieza en 1.
     */
    private void heigth(Node reco, int nivel) {
        if (reco != null) {
            heigth(reco.getBack(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            heigth(reco.getNext(), nivel + 1);
        }
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
        return null;
    }

    /**
     *
     * @throws IsEmptyException
     */
    public void printLevel() throws IsEmptyException {

    }

    /**
     *
     * @param node Recive el nodo en el que se encuentra.
     * @param level Recive su nivel.
     */
    private void printLevel(Node<T> node, int level) {

    }

    /**
     * @param b Arbol.
     * @param x Cantidad de datos a agregar.
     * @param n Rango minimo.
     * @param m Rango maximo.
     */
    public void fill(TreeAVL b, int x, int n, int m) {
        for (int i = 0; i < x; i++) {
            b.add((int) Math.abs(Math.floor(Math.random() * (n - m + 1) + m)));
        }
    }

    private long getBalance(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getBack()) - height(node.getNext());
    }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }
}
