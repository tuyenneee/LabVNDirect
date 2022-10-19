import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFileNIOExample {
    public static void main(String[] args) {
        Charset charset = Charset.forName("utf-8");
        Pattern pattern = Pattern.compile("\\S", Pattern.CASE_INSENSITIVE);
        File file = new File("C:\\Temp\\hanoijava.txt");
        FileInputStream stream;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileChannel channel = stream.getChannel();
        ByteBuffer bytes;
        try {
            bytes = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CharBuffer chars = charset.decode(bytes);

        Matcher matcher = pattern.matcher(chars);
        if(matcher.find()){
            System.out.println("Found at "+Integer.toString(matcher.regionStart()));
            System.out.println("Found at "+Integer.toString(matcher.regionEnd()));
            System.out.println("Value "+chars.subSequence(matcher.regionStart(), matcher.regionEnd())+"");
        }
        else {
            System.out.println("Not found");
        }
    }
}
