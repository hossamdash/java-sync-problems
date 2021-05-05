import java.util.concurrent.locks.ReadWriteLock;

public class ReaderWriterData {
    private String file = "";
    private ReadWriteLock readWriteLock;

    public ReaderWriterData(ReadWriteLock readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    void writeData(int id, String item) {
        readWriteLock.writeLock().lock();
        file += item;
        System.out.println("[#W" + id + "] appended '" + item + "' to file");
        readWriteLock.writeLock().unlock();
    }

    String readData(int id) {
        try {
            readWriteLock.readLock().lock();
            System.out.println("[#R" + id + "] Read: " + file);
            return file;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
