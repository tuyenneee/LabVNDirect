import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.FileVisitResult.*;

public class FileFinderExample {
    static class Finder extends SimpleFileVisitor<Path>{
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{txt}");

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            if(matcher.matches(path.getFileName())){
                System.out.println("Found: "+path);
            }
            return CONTINUE;
        }

        public static void main(String[] args) {
            Path path = Paths.get("C:\\Temp");
            try {
                Files.walkFileTree(path, new Finder());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
