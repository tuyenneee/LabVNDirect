import java.io.FilterOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLExample {
    public static void main(String[] args) {
        String link = "https:www.google.com.vn/search?q=hello";
        try {
            URL url = new URL(link);
            System.out.println("Protocol "+url.getProtocol());
            System.out.println("Port "+url.getPort());
            System.out.println("Query "+url.getQuery());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
