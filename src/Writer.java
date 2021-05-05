import java.util.Random;

public class Writer implements Runnable {
    private final int id;
    private final ReaderWriterData data;

    public Writer(int id, ReaderWriterData data) {
        this.id = id;
        this.data = data;
    }

    public void run() {
        String dataToWrite = "" + (char) (65 + new Random().nextInt(26));
        data.writeData(id, dataToWrite);
    }

}