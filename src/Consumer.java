import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable{
    private ReentrantLock lock;
    private CharData data;

    public Consumer(CharData data, ReentrantLock lock) {
        this.data = data;
        this.lock = lock;
    }

    public void run(){
        while (true){
            lock.lock();
            try {
               data.consumeItem();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            lock.unlock();
        }
    }
}
