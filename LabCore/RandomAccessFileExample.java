import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class RandomAccessFileExample {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp\\hanoijava.txt");
        ByteBuffer buffer = ByteBuffer.allocate(30);
        try(FileChannel fc = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            fc.read(buffer);
            System.out.println(new String(buffer.array()));
            fc.position(20);
            String str = "Nguyễn";
            byte[] bytes = str.getBytes();
            fc.write(ByteBuffer.wrap(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
