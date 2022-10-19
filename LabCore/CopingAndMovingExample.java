import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CopingAndMovingExample {
    public static void main(String[] args) {
        Path source = Paths.get("C:\\Temp\\hanoijava.txt");
        Path target = Paths.get("C:\\Temp4\\io_sample.txt");
        try {
            Files.copy(source, target, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.delete(target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path target1 = Paths.get("C:\\Temp2\\io_sample.txt");
        try {
            Files.createLink(target1, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
