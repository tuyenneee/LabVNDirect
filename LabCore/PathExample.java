import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp");
        Path path2 = path.resolve("hanoijava.txt");

        Charset charset = Charset.forName("utf8");
        System.out.println("This is "+(Files.isDirectory(path) ? "file" : "folder")+"!");
        System.out.println(Files.exists(path));
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isDirectory(entry);
            }
        };

        try(BufferedReader reader = Files.newBufferedReader(path2, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, "*.{java,txt}")){
//            for(Path p : directoryStream){
//                System.out.println(p.getFileName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
