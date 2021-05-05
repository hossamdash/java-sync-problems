import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable{
    private static char item;
    private ReentrantLock lock;
    private CharData data;

    public Producer(CharData data, ReentrantLock lock) {
        item = 'a';
        this.data = data;
        this.lock = lock;
    }

    public void run(){
        while (true){
            lock.lock();
            try {
                data.produceItem(item);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            item = item == 'z' ? 'a' : ++item;
            lock.unlock();
        }
    }
}
