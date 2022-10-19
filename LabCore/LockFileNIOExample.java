import java.awt.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LockFileNIOExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Temp\\hanoijava.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        FileChannel channel = raf.getChannel();
        FileLock lock = channel.tryLock(0, Long.MAX_VALUE, false);

        Desktop.getDesktop().edit(file);
        Thread.sleep(15*1000l);
        lock.release();
    }
}
