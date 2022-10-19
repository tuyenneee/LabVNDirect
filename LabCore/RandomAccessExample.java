import java.awt.*;
import java.io.File;
import java.io.RandomAccessFile;

public class RandomAccessExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Temp\\hanoijava.txt");
        try (RandomAccessFile randomAccess = new RandomAccessFile(file, "rw")) {
            randomAccess.seek(10);
            byte[] bytes = new byte[4 * 102];
            int read = randomAccess.read(bytes);
            System.out.println(new String(bytes, 0, read));
            randomAccess.seek(file.length());
            randomAccess.write("\r".getBytes());
            randomAccess.writeChars("Hello co ba");
            Desktop.getDesktop().open(file);
        }
    }
}
