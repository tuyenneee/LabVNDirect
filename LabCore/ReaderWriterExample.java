import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ReaderWriterExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Temp\\hanoijava.txt");
        FileReader reader = null;
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("\r\n");
            writer.write("Nguyen Van Tuyen");


            reader = new FileReader(file);
            char[] buffer = new char[4 * 1024];
            int read = -1;
            StringBuilder builder = new StringBuilder();
            while ((read = reader.read(buffer)) != -1) {
                builder.append(buffer, 0, read);
            }
            System.out.println("["+builder+"]");
            Desktop.getDesktop().open(file);
        } finally {
            reader.close();
        }


    }
}
