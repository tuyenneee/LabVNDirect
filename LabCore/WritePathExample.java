import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WritePathExample {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp\\hanoijava.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (int i = 0; i < 10; i++) {
                writer.write("Nguyễn Văn "+i+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
