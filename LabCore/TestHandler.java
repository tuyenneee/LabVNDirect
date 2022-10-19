import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestHandler implements HttpHandler {
    OutputStream out;

    @Override
    public void handle(HttpExchange httpExchance) throws IOException {
        String message = "<html lang=\"en\">\n" +
                "\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n" +
                "\n" +
                "        <title>404 HTML Template by Colorlib</title>\n" +
                "\n" +
                "        <!-- Google font -->\n" +
                "        <link href=\"https://fonts.googleapis.com/css?family=Montserrat:300,700\" rel=\"stylesheet\">\n" +
                "\n" +
                "        <!-- Custom stlylesheet -->\n" +
                "        <link type=\"text/css\" rel=\"stylesheet\" href=\"css/style1.css\" />\n" +
                "\n" +
                "    </head>\n" +
                "\n" +
                "    <body>\n" +
                "\n" +
                "        <div id=\"notfound\">\n" +
                "            <div class=\"notfound\">\n" +
                "                <div class=\"notfound-404\">\n" +
                "                    <h1>5<span></span>0</h1>\n" +
                "                </div>\n" +
                "                <h2>Oops! Page Not Be Found</h2>\n" +
                "                <p>We're sorry our website is under maintenance, sorry for the inconvenience!</p>\n" +
                "                <c:if test=\"${sessionScope.Account ==null}\">\n" +
                "                   <a href=\"home?do=home&fresh=1\">Back to homepage </a>\n" +
                "                </c:if>\n" +
                "\n" +
                "                <c:if test=\"${sessionScope.Account !=null}\">\n" +
                "                   <a href=\"home\">Back to homepage </a>\n" +
                "                </c:if>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->\n" +
                "</html>";
        httpExchance.sendResponseHeaders(200, message.length());
        out = httpExchance.getResponseBody();
        try {
            out = httpExchance.getResponseBody();
            out.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class HttpServerExample {
    public static void main(String[] args) {

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/test", new TestHandler());
            server.setExecutor(Executors.newFixedThreadPool(5));
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
