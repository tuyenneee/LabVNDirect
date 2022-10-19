import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LinkFileExample {
    public static void main(String[] args) {
        Path source = Paths.get("C:\\Temp\\hanoijava.txt");
        Path target = Paths.get("C:\\Temp2\\io_sample.txt");

        Charset charset = Charset.forName("utf8");
        try(BufferedWriter writer = Files.newBufferedWriter(source, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write("Java NIO 2 example \r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedReader reader = Files.newBufferedReader(target, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
