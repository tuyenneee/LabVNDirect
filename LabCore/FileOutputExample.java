import java.io.*;

public class FileOutputExample {
    public static void main(String[] args) throws IOException {
        File file = new File("C:" + File.separator + "Temp"
                + File.separator + "hanoijava.txt");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            String s = "Welcome to VNDirect! Hello world!";
            byte bytes[] = s.getBytes();
            outputStream.write(bytes);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte bytes[] = new byte[124];
            int read = -1;
            StringBuilder builder = new StringBuilder();
            while((read=inputStream.read(bytes))!=-1){
                builder.append(new String(bytes, 0, read));
            }
            inputStream.read(bytes);
            System.out.println(builder);
        } finally {
            inputStream.close();
        }
    }

}
