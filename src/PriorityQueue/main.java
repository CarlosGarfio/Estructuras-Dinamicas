package PriorityQueue;

public class main {

    static PriorityQueue<Double> queue = new PriorityQueue<>();

    public static void main(String[] args) {
      queue.Insert(0, 1d);
      queue.Insert(0, 1d);
      queue.Insert(0, 1d);
      queue.Insert(0, 2d);
      queue.Insert(0, 3d);
      
      queue.Insert(1, 1d);
      queue.Insert(3, 1d);
      
//queue.Random();
queue.Remove(1);
queue.Remove(0);
      queue.Print();

    }

}
