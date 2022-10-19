import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionExample {
    public static void main(String[] args) throws Exception {
        try {
            URL url = new URL("https://github.com/adapham/SWP391_SE1615_NET_GROUP_2-NEVERGIVEUP");
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            int read;
            byte[] bytes = new byte[1024];
            while ((read = stream.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, read));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
