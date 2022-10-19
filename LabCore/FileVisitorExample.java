import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.*;

public class FileVisitorExample {
    public static class PrintFiles extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            System.out.println(path+" is file!");
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println(dir+" is directory!");
            return CONTINUE;
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp");
        try {
            Files.walkFileTree(path, new PrintFiles());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
