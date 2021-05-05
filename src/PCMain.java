import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class PCMain {
    public static void main(String[] args) throws InterruptedException{
        LinkedList<Character> list = new LinkedList<Character>();
        ReentrantLock lock = new ReentrantLock();
        CharData data = new CharData(list, 4, lock );
        Producer producer = new Producer(data, lock);
        Consumer consumer = new Consumer(data, lock);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
        // producerThread.join();
        // consumerThread.join();
    }
}