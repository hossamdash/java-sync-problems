
public class Reader implements Runnable {
    private final int id;
    private final ReaderWriterData data;

    public Reader(int id, ReaderWriterData data) {
        this.id = id;
        this.data = data;
    }

    public void run() {
        data.readData(id);
    }

}