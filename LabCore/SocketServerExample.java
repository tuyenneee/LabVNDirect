import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerExample {
    public SocketServerExample(int port) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("SERVER Listening...");
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
            try (DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            ) {
                System.out.println("Client say: " + input.readUTF());
                output.writeUTF("I'm a socket server!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }).start();
        }
    }

    public static void main(String[] args) throws Exception {
        SocketServerExample serverSocket = new SocketServerExample(9245);
    }
}
