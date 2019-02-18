package QueueArray;

import Excepciones.IsEmptyException;
import Excepciones.IsFullException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args) {
        QueueMain<Integer> qm = new QueueMain<>(Integer.class, 4);
        try {
            qm.EnQueue(1);
            qm.EnQueue(2);
            qm.EnQueue(3);
            qm.EnQueue(4);
            //qm.EnQueue(5);
            qm.DeQueue();
        } catch (IsFullException ex) {
            System.err.println(ex.getMessage());
        } catch (IsEmptyException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
