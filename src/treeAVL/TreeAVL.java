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
        this.root.setHeight(0L);

    }

    /**
     *
     * @param value Recive el valor a agregar.
     * @return Retorna true si logra agregarlo. Retorna false si no logra
     * agregarlo.
     */
    @Override
    public boolean add(T value) {
        if (value == null) {
            return false;
        } else {
            if (root.getValue() == null) {
                root.setValue(value);
                lvlUpdate(root, 0);
                return true;
            } else {
                if (add(root, value) != null) {
                    lvlUpdate(root, 0);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private Node<T> add(Node<T> node, T value) {
        if (node == null) {
            Node<T> nod = new Node(value);
            nod.setCont(0l);
            update(nod);
            return balance(nod);
        }
        int cmp = value.compareTo(node.getValue());
        if (cmp < 0) {
            node.setBack(add(node.getBack(), value));
        } else if (cmp > 0) {
            node.setNext(add(node.getNext(), value));
        } else {
            node.setCont(node.getCont() + 1);
        }
        update(node);
        return balance(node);
    }

    private Node<T> balance(Node<T> node) {
        if (node.getBf() == -2) {
            if (node.getBack().getBf() <= 0) {
                return leftLeftCase(node);
            } else {
                return leftRightCase(node);
            }
        } else if (node.getBf() == +2) {
            if (node.getNext().getBf() >= 0) {
                return rightRightCase(node);
            } else {
                return rightLeftCase(node);
            }
        }
        return node;
    }

    private Node<T> leftLeftCase(Node<T> node) {
        return rightRotation(node);
    }

    private Node<T> leftRightCase(Node<T> node) {
        node.setBack(leftRotation(node.getBack()));
        return leftLeftCase(node);
    }

    private Node<T> rightRightCase(Node<T> node) {
        return leftRotation(node);
    }

    private Node<T> rightLeftCase(Node<T> node) {
        node.setNext(rightRotation(node.getNext()));
        return rightRightCase(node);
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> newParent = node.getNext();
        node.setNext(newParent.getBack());
        newParent.setBack(node);
        update(node);
        update(newParent);
        return newParent;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> newParent = node.getBack();
        node.setBack(newParent.getNext());
        newParent.setNext(node);
        update(node);
        update(newParent);
        return newParent;
    }

    private void update(Node<T> node) {
        long leftNodeHeight = (node.getBack() == null) ? -1 : node.getBack().getHeight();
        long rightNodeHeight = (node.getNext() == null) ? -1 : node.getNext().getHeight();
        node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
        node.setBf(rightNodeHeight - leftNodeHeight);
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
            root = remove(root, value);
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
        if (node == null) {
            return null;
        }
        if (value.equals(node.getValue())) {
            if (node.getCont() > 0) {
                node.setCont(node.getCont() - 1);
                return node;
            }
        }
        int cmp = value.compareTo(node.getValue());
        if (cmp < 0) {
            node.setBack(remove(node.getBack(), value));
        } else if (cmp > 0) {
            node.setNext(remove(node.getNext(), value));
        } else {
            if (node.getBack() == null) {
                return node.getNext();
            } else if (node.getNext() == null) {
                return node.getBack();
            } else {
                if (node.getBack().getHeight() > node.getNext().getHeight()) {
                    T successorValue = findMax(node.getBack());
                    node.setValue(successorValue);
                    node.setBack(remove(node.getBack(), successorValue));
                } else {
                    T successorValue = findMin(node.getNext());
                    node.setValue(successorValue);
                    node.setNext(remove(node.getNext(), successorValue));
                }
            }
        }
        update(node);
        return balance(node);
    }

    private T findMin(Node<T> node) {
        while (node.getBack() != null) {
            node = node.getBack();
        }
        return node.getValue();
    }

    private T findMax(Node<T> node) {
        while (node.getNext() != null) {
            node = node.getNext();
        }
        return node.getValue();
    }

    /**
     *
     * @param value Nodo del padre a buscar.
     * @param root Recive el arbol.
     * @param father Padre del nodo a buscar.
     * @return Retorna el nodo padre.
     */
    private Node<T> hasFather(Node<T> value, Node<T> root, Node<T> father) {
        if (value.getValue().equals(root.getValue())) {
            return father;
        }
        if (value.getValue().compareTo(root.getValue()) <= -1) {
            return hasFather(value, root.getBack(), root);
        } else {
            return hasFather(value, root.getNext(), root);
        }
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
        if (root != null) {
            root.setLevel(nivel);
            heigth1(root.getBack(), nivel + 1);
            root.setLevel(nivel);
            heigth1(root.getNext(), nivel + 1);

        }
    }
    
    private long altura=0l;
    
    public long height1() throws IsEmptyException {
        heigth1(root, 0);
        return altura;
    }

    /**
     * @param reco Arbol.
     * @param nivel Empieza en 1.
     */
    private void heigth1(Node reco, int nivel) {
        if (reco != null) {
            heigth1(reco.getBack(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            heigth1(reco.getNext(), nivel + 1);
        }
        altura=0l;
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
