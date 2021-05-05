import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class CharData {
    private Queue<Character> list;
    private int capacity;
    private ReentrantLock lock;
    private Condition condition;

    public CharData(Queue<Character> list, int capacity, ReentrantLock lock) {
        this.setList(list);
        this.setCapacity(capacity);
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Queue<Character> getList() {
        return list;
    }

    public void setList(Queue<Character> list) {
        this.list = list;
    }

    public void produceItem(char item) throws InterruptedException{
        lock.lock();
        while(list.size() == capacity) condition.await();
        list.add(item);
        System.out.println("Produced item " + item);
        Thread.sleep(500);
        lock.unlock();
        condition.signalAll();
    }

    public char consumeItem() throws InterruptedException{
        lock.lock();
        while(list.size() == 0) condition.await();
        char item = list.remove();
        System.out.println("Consumed item " + item);
        Thread.sleep(500);
        lock.unlock();
        condition.signalAll();
        return item;
    }

}
