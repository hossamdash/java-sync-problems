import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWMain {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        ReaderWriterData data = new ReaderWriterData(lock);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            System.out.println("> [#"+i+"] Started Reader & Writer");
            executorService.execute(new Writer(i, data));
            executorService.execute(new Reader(i, data));
            // executorService.execute(() -> {
            //     data.readData(position)
            // });
        }
        executorService.shutdown();
        while(!executorService.isTerminated());
    }
}
